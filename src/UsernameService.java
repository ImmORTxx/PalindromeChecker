import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class UsernameService {

    // username -> userId mapping
    private final ConcurrentHashMap<String, String> usernameMap;

    // username -> attempt count
    private final ConcurrentHashMap<String, AtomicInteger> attemptFrequency;

    public UsernameService() {
        usernameMap = new ConcurrentHashMap<>();
        attemptFrequency = new ConcurrentHashMap<>();
    }

    // 1️⃣ Check Availability (O(1))
    public boolean checkAvailability(String username) {
        // Increase attempt count
        attemptFrequency
                .computeIfAbsent(username, k -> new AtomicInteger(0))
                .incrementAndGet();

        return !usernameMap.containsKey(username);
    }

    // 2️⃣ Register Username
    public boolean register(String username, String userId) {
        if (checkAvailability(username)) {
            usernameMap.put(username, userId);
            return true;
        }
        return false;
    }

    // 3️⃣ Suggest Alternatives
    public List<String> suggestAlternatives(String username) {
        List<String> suggestions = new ArrayList<>();

        // Try appending numbers
        for (int i = 1; i <= 5; i++) {
            String newUsername = username + i;
            if (!usernameMap.containsKey(newUsername)) {
                suggestions.add(newUsername);
            }
        }

        // Replace underscore with dot
        if (username.contains("_")) {
            String modified = username.replace("_", ".");
            if (!usernameMap.containsKey(modified)) {
                suggestions.add(modified);
            }
        }

        // Add prefix "real"
        String prefixed = "real_" + username;
        if (!usernameMap.containsKey(prefixed)) {
            suggestions.add(prefixed);
        }

        return suggestions;
    }

    // 4️⃣ Get Most Attempted Username
    public String getMostAttempted() {
        String mostAttempted = null;
        int maxAttempts = 0;

        for (Map.Entry<String, AtomicInteger> entry : attemptFrequency.entrySet()) {
            if (entry.getValue().get() > maxAttempts) {
                maxAttempts = entry.getValue().get();
                mostAttempted = entry.getKey();
            }
        }

        return mostAttempted;
    }

    // Utility method for testing
    public void printAllUsers() {
        System.out.println("Registered Users:");
        usernameMap.forEach((k, v) ->
                System.out.println("Username: " + k + " | UserId: " + v));
    }

    // MAIN METHOD (Demo)
    public static void main(String[] args) {

        UsernameService service = new UsernameService();

        // Register some users
        service.register("john_doe", "101");
        service.register("admin", "102");

        // Check availability
        System.out.println("Is john_doe available? " +
                service.checkAvailability("john_doe"));

        System.out.println("Is jane_smith available? " +
                service.checkAvailability("jane_smith"));

        // Suggestions
        System.out.println("Suggestions for john_doe: " +
                service.suggestAlternatives("john_doe"));

        // Simulate attempts
        service.checkAvailability("admin");
        service.checkAvailability("admin");
        service.checkAvailability("admin");

        System.out.println("Most Attempted Username: " +
                service.getMostAttempted());
    }
}
