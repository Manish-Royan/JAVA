import java.util.Scanner;

public class UppercaseToLowercase {
    public static void main (String[] args) {
        // Declaring variables
        char ch1, ch2;
        System.out.print("Enter any character: ");
        
        // Scanning user input
        Scanner r = new Scanner(System.in);
        ch1 = r.next().charAt(0); // Read the first character input by the user
        
        // Implementing logic to convert the case of the character
        if (ch1 >= 'A' && ch1 <= 'Z') { // Check if the character is uppercase
            ch2 = Character.toLowerCase(ch1); // Convert to lowercase
            System.out.print("LowerCase: " + ch2);
        } else { // If the character is not uppercase, assume it is lowercase
            ch2 = Character.toUpperCase(ch1); // Convert to uppercase
            System.out.print("UpperCase: " + ch2);
        }
        
        r.close(); // Closing the Scanner object
    }
}