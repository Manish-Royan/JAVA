import java.util.Scanner;

public class REVERSEStringBuilder {
    public static void main(String[] args) {
        // Creating a Scanner object to take user input
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Character: ");
        // Reading the input string from the user
        StringBuilder ch = new StringBuilder(sc.nextLine());

        // Printing the original string
        System.out.print("Original Character: " + ch);

        // Loop to reverse the string
        for (int i = 0; i < ch.length() / 2; i++) {
            int front = i; // Index of the front character
            int back = ch.length() - 1 - i; // Index of the back character

            // Swapping the characters at the front and back indices
            char frontChar = ch.charAt(front);
            char backChar = ch.charAt(back);

            ch.setCharAt(front, backChar);
            ch.setCharAt(back, frontChar);
        }

        // Printing the reversed string
        System.out.println("\nCharacter in reverse order: " + ch);
    }
}