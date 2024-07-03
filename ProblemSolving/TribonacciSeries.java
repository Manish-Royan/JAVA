import java.util.Scanner;

class TribonacciSeries {
    public static void main(String[] args) {
        
        int term, a = 0, b = 1, c = 2, d;

        System.out.print("Enter Term: ");
        Scanner sc = new Scanner(System.in);
        // Reading the number of terms from the user
        term = sc.nextInt();

        // Loop to generate Tribonacci sequence
        for (int i = 1; i <= term; i++) {
            // Printing the current term
            System.out.print(a + " ");

            // Calculating the next term in the sequence
            d = a + b + c;
            a = b;
            b = c;
            c = d;
        }
        
        // Closing the Scanner object
        sc.close();
    }
}
