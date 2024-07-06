import java.util.Scanner;

public class BinaryToDecimal {
    public static void main(String[] args) {
        // Create a Scanner object to read user input
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter a binary number
        System.out.print("Enter a binary number: ");
        String binary = scanner.nextLine();

        // Convert the binary string to decimal
        int decimal = binaryToDecimal(binary);

        // Display the result
        System.out.println("Decimal equivalent: " + decimal);

        // Close the scanner to free up resources
        scanner.close();
    }

    // Method to convert binary string to decimal integer
    public static int binaryToDecimal(String binary) {
        int decimal = 0;
        int power = 0;

        // Iterate through the binary string from right to left
        for (int i = binary.length() - 1; i >= 0; i--) {
            // If the current digit is '1', add 2^power to the result
            if (binary.charAt(i) == '1') {
                decimal += Math.pow(2, power);
            }
            // Increment the power for the next iteration
            power++;
        }

        return decimal;
    }
}