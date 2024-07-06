import java.util.Scanner;

public class DecimalToBinary {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompting the user to enter a decimal number
        System.out.print("Enter a decimal number: ");
        int decimal = scanner.nextInt();

        // Calling the decimalToBinary function to convert decimal to binary
        String binary = decimalToBinary(decimal);

        // Displaying the binary equivalent to the user
        System.out.println("Binary equivalent: " + binary);

        // Closing the scanner object
        scanner.close();
    }

    // Method to convert decimal to binary
    public static String decimalToBinary(int decimal) {
        if (decimal == 0) {
            return "0"; // Special case: decimal number is 0
        }

        StringBuilder binary = new StringBuilder();

        // Converting decimal to binary using division method
        while (decimal > 0) {
            int remainder = decimal % 2; // Finding the remainder
            binary.insert(0, remainder); // Inserting remainder at the beginning of the string builder
            decimal /= 2; // Updating decimal by integer division
        }

        return binary.toString(); // Returning the binary string representation
    }
}