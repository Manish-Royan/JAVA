import java.io.*;
import java.util.Scanner;

class LCM {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Prompt the user to enter two numbers
        System.out.print("Enter any two numbers: ");
        int a = sc.nextInt(), b = sc.nextInt();

        // Determine the larger of the two numbers
        int ans = (a > b) ? a : b;

        // Loop to find the smallest number that can be divided by both numbers
        while (true) {
            // Check if 'ans' is divisible by both 'a' and 'b'
            if (ans % a == 0 && ans % b == 0)
                break; // If true, break out of the loop
            ans++; // Increment 'ans' and continue the loop
        }

        // Print the result
        System.out.println("LCM of " + a + " and " + b + " : " + ans);
    }
}
