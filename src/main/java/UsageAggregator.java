import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class UsageAggregator {
    public Map<String, Long> aggregateUsage(String jsonResponse) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(jsonResponse);
        JsonNode events = rootNode.get("events");

        // Map zur Speicherung der Startzeiten für jede workloadId
        Map<String, Long> startTimes = new HashMap<>();
        // Map zur Speicherung der Gesamtlaufzeiten für jede customerId
        Map<String, Long> customerUsage = new HashMap<>();

        for (JsonNode event : events) {
            String customerId = event.get("customerId").asText();
            String workloadId = event.get("workloadId").asText();
            String eventType = event.get("eventType").asText();
            long timestamp = event.get("timestamp").asLong();

            if ("start".equals(eventType)) {
                // Speichere die Startzeit für die workloadId
                startTimes.put(workloadId, timestamp);
            } else if ("stop".equals(eventType)) {
                // Hol die Startzeit für die workloadId
                Long startTime = startTimes.remove(workloadId);
                if (startTime != null) {
                    // Berechne die Laufzeit und füge sie zur Gesamtlaufzeit hinzu
                    long duration = timestamp - startTime;
                    customerUsage.merge(customerId, duration, Long::sum);
                }
            }
        }

        return customerUsage;
    }
}
