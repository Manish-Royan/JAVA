import java.util.Scanner;

public class Factorial {
    public static void main(String[] args) {
        // Declaring variable
        int n;
        System.out.print("Enter a number: ");
        
        // Scanning user input
        Scanner input = new Scanner(System.in);
        n = input.nextInt();
        
        // Implementing logic to calculate factorial
        int factorial = 1;
        for (int i = 1; i <= n; i++) {
            factorial *= i;
        }
        
        System.out.println("Factorial of " + n + " is: " + factorial);
        
        input.close(); // Closing the Scanner object
    }
}
