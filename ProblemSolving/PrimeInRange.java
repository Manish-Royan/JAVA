import java.util.Scanner;

public class PrimeInRange {
    public static void main (String[] args) {
        
        // Declaring variables
        int n1, n2, i, j;
        System.out.print("Enter Two Numbers: ");
        
        // Scanning user input
        Scanner input = new Scanner(System.in);
        n1 = input.nextInt(); // Read the first number of the range
        n2 = input.nextInt(); // Read the second number of the range
        
        // Implementing logic to find prime numbers within the range
        for (i = n1; i <= n2; i++) { // Initialize 'i' from 'n1' then goes to 'n2'
            for (j = 2; j <= i; j++) { // Initialize 'j = 2' because '2' is the smallest prime number
                if (i % j == 0)
                    break; // If 'i' is divisible by 'j', it's not a prime number, break the inner loop
            }
            
            if (i == j) { // If 'i' is equal to 'j', it means 'i' is only divisible by itself, hence it is a prime number
                System.out.print(j + " ");
            }
        }
        
        input.close(); // Closing the Scanner object
    }
}