import java.util.Scanner;

public class IsDivisibleByN {
    public static void main(String[] args) {
        // Declaring variables
        int n, div;
        System.out.print("Enter number: ");
        
        Scanner input = new Scanner(System.in);
        n = input.nextInt();
        
        System.out.print("Enter number to check if divisible or not: ");
        div = input.nextInt();
        
        // Check for division by zero
        if (div == 0) {
            System.out.print("Error: Division by zero is not allowed.");
        } else {
            if (n % div == 0) {
                System.out.print(n + " is divisible by " + div);
            } else {
                System.out.print(n + " is not divisible by " + div);
            }
        }

        input.close(); // Closing the Scanner object
    }
}