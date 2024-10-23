import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String jsonResponse = "{\"events\":[{\"customerId\":\"cust1\",\"workloadId\":\"work1\",\"eventType\":\"start\",\"timestamp\":1699872883000},{\"customerId\":\"cust1\",\"workloadId\":\"work1\",\"eventType\":\"stop\",\"timestamp\":1700401984000}]}";

        UsageAggregator aggregator = new UsageAggregator();
        try {
            Map<String, Long> usage = aggregator.aggregateUsage(jsonResponse);
            usage.forEach((customerId, totalDuration) ->
                    System.out.println("Customer ID: " + customerId + ", Total Usage: " + totalDuration + " ms")
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
