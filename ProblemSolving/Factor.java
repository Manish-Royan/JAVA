import java.util.Scanner;

public class Factor {
    public static void main(String[] args) {
        // Declaring variable
        int n;
        System.out.print("Enter a number: ");
        
        // Scanning user input
        Scanner input = new Scanner(System.in);
        n = input.nextInt();
        
        // Implementing logic to find factors
        System.out.print("Factors of " + n + " are: ");
        for (int i = 1; i <= n; i++) //The loop for (int i = 1; i <= n; i++) iterates from 1 to n.
        {
            if (n % i == 0) //If true, i is printed as a factor.
            {
                System.out.print(i + " ");
            }
        }
        
        input.close(); // Closing the Scanner object
    }
}
