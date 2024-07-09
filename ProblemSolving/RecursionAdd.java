import java.util.Scanner;

public class RecursionAdd {
    // Recursive method to calculate the sum of first n natural numbers
    int sum(int b) {
        if (b > 0) {
            return b + sum(b - 1);            
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {
        // Create a Scanner object to read user input
        Scanner sc = new Scanner(System.in);
        
        // Prompt the user to enter a number
        System.out.print("Enter a number: ");
        int n = sc.nextInt();

        // Create an instance of RecursionAdd to call the sum method
        RecursionAdd ref = new RecursionAdd();
        int result = ref.sum(n);

        // Display the result
        System.out.println("Sum of first " + n + " natural numbers is: " + result);
        
        // Close the scanner
        sc.close();
    }
}
