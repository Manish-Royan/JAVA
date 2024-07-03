import java.util.Scanner;

class Fibonacci {
    public static void main(String[] args) {
        
        int term, a = 0, b = 1, c;

        System.out.print("Enter Term: ");
        Scanner sc = new Scanner(System.in);
        // Reading the number of terms from the user
        term = sc.nextInt();

        // Loop to generate Fibonacci sequence
        for (int i = 1; i <= term; i++) {
            // Printing the current term
            System.out.print(a + " ");

            // Calculating the next term in the sequence
            c = a + b;
            a = b;
            b = c;
        }
        
        // Closing the Scanner object
        sc.close();
    }
}
