import java.util.Scanner;

public class PalindromeCheckerApp {

    // Application Version
    private static final String APP_VERSION = "1.0.0";

    public static void main(String[] args) {

        // =============================
        // UC1 - Welcome Message
        // =============================
        System.out.println("==================================");
        System.out.println("     WELCOME TO PALINDROME APP    ");
        System.out.println("==================================");
        System.out.println("Application Name : Palindrome Checker");
        System.out.println("Version          : " + APP_VERSION);
        System.out.println("----------------------------------");

        // =============================
        // UC2 - Hardcoded Palindrome
        // =============================
        String hardcodedWord = "madam";
        System.out.println("\nUC2 - Hardcoded Check:");
        checkAndPrint(hardcodedWord);

        // =============================
        // UC3 - User Input
        // =============================
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nUC3 - Enter a string to check:");
        String userInput = scanner.nextLine();

        // =============================
        // UC4 & UC5 - Case Insensitive + Ignore Special Characters
        // =============================
        String cleanedInput = cleanString(userInput);

        System.out.println("\nProcessed String: " + cleanedInput);

        // Using Two Pointer Approach
        boolean isPalindrome = isPalindromeTwoPointer(cleanedInput);

        if (isPalindrome) {
            System.out.println("Result: It is a Palindrome.");
        } else {
            System.out.println("Result: It is NOT a Palindrome.");
        }

        scanner.close();

        System.out.println("\nThank you for using Palindrome Checker App!");
    }

    // =============================
    // Basic Reverse Method (UC2)
    // =============================
    public static void checkAndPrint(String word) {

        String reversed = "";

        for (int i = word.length() - 1; i >= 0; i--) {
            reversed = reversed + word.charAt(i);
        }

        if (word.equals(reversed)) {
            System.out.println(word + " is a Palindrome.");
        } else {
            System.out.println(word + " is NOT a Palindrome.");
        }
    }

    // =============================
    // Clean String (UC4 & UC5)
    // Remove spaces & special characters
    // Convert to lowercase
    // =============================
    public static String cleanString(String input) {
        return input.replaceAll("[^a-zA-Z0-9]", "")
                .toLowerCase();
    }

    // =============================
    // Two Pointer Approach (Optimized)
    // =============================
    public static boolean isPalindromeTwoPointer(String str) {

        int left = 0;
        int right = str.length() - 1;

        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }
}
