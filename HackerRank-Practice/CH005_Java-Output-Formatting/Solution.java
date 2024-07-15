import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        // Create a Scanner object to read user input
        Scanner sc = new Scanner(System.in);
        
        // Print the header line
        System.out.println("================================");
        
        // Loop to read 3 sets of inputs
        for (int i = 0; i < 3; i++) {
            // Read the string and integer from user input
            String s1 = sc.next();
            int x = sc.nextInt();

            // Print the formatted string and integer:
            // %-15s formats the string s1 to be left-justified within a 15-character wide field
            // %03d formats the integer x to be 3 digits wide, padded with leading zeros if necessary
            // %n is a platform-independent newline character
            System.out.printf("%-15s%03d %n", s1, x);
        }
        
        // Print the footer line
        System.out.println("================================");
        
        // Close the scanner
        sc.close();
    }
}
