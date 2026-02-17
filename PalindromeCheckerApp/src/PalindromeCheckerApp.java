import java.util.Scanner;

public class PalindromeCheckerApp {

    // Application Version
    private static final String APP_VERSION = "1.0.0";

    public static void main(String[] args) {

        
        System.out.println("==================================");
        System.out.println("     WELCOME TO PALINDROME APP    ");
        System.out.println("==================================");
        System.out.println("Application Name : Palindrome Checker");
        System.out.println("Version          : " + APP_VERSION);
        System.out.println("----------------------------------");

        
        String hardcodedWord = "madam";
        System.out.println("\nUC2 - Hardcoded Check:");
        checkAndPrint(hardcodedWord);

        
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nUC3 - Enter a string to check:");
        String userInput = scanner.nextLine();

        
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

    =
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

   
    public static String cleanString(String input) {
        return input.replaceAll("[^a-zA-Z0-9]", "")
                .toLowerCase();
    }

   
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
