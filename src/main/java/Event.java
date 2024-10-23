public class Event {
    private String customerId;
    private String workloadId;
    private long timestamp; // Zeitstempel in Millisekunden
    private String eventType; // "start" oder "stop"

    // Konstruktor und Getter
    public Event(String customerId, String workloadId, long timestamp, String eventType) {
        this.customerId = customerId;
        this.workloadId = workloadId;
        this.timestamp = timestamp;
        this.eventType = eventType;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getWorkloadId() {
        return workloadId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getEventType() {
        return eventType;
    }
}
