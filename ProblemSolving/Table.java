/*Print Multiplication of given number*/

import java.util.Scanner;

public class Table {
    public static void main(String[] args) {
        // Declaring Variables
        int multiplicand, multiplier;
        System.out.print("Enter number to be multiplied: ");
        
        // User input
        Scanner input = new Scanner(System.in);
        multiplicand = input.nextInt();
        System.out.print("Enter multiplier: ");
        multiplier = input.nextInt();
        
        // Implementation logic
        for (int i = 1; i <= multiplier; i++) {
            System.out.println(multiplicand + " * " + i + " = " + (multiplicand * i));
        }

        input.close(); // Close the Scanner object
    }
}