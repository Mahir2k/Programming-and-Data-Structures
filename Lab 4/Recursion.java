import java.util.Scanner;

/**
 * This class demonstrates recursive methods for character counting and string permutations.
 */
public class Recursion {
    
    /**
     * Counts the occurrences of a character in a string recursively.
     * @param str The string to search in
     * @param c The character to count
     * @return The number of occurrences of c in str
     */
    public static int count(String str, char c) {
        // base case
        if (str.length() == 0) {
            return 0;
        }
        if (str.charAt(0) == c) {
            return 1 + count(str.substring(1), c);
        }
        return count(str.substring(1), c);
    }

    /**
     * Generates all permutations of a given string.
     * @param str The string to permute
     */
    public static void permutations(String str) {
        permutations("", str); // helper method
    }

    /**
     * Helper recursive method for generating permutations.
     * @param s1 The current permutation being built
     * @param s2 The remaining characters to permute
     */
    private static void permutations(String s1, String s2) {
        // base case
        if (s2.length() == 0) {
            System.out.println(s1);
        }
        for (int i = 0; i < s2.length(); i++) {
            String nextS1 = s1 + s2.charAt(i); // moving one char from s2 to s1
            String nextS2 = s2.substring(0, i) + s2.substring(i + 1);
            permutations(nextS1, nextS2); // recursion
        }
    }

    /**
     * Main method to demonstrate the recursive methods.
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter a string:");
        String str = keyboard.nextLine();
        System.out.println("Enter a character: ");
        char c = keyboard.nextLine().charAt(0);
        System.out.println(c + " appears " + count(str, c) + " times in \"" + str + "\"");

        System.out.println("Enter a string:");
        str = keyboard.nextLine();
        permutations(str);
    }
}
