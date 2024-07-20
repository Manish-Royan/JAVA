import java.util.*;
import java.io.*;

class Solution {
    public static void main(String[] argh) {
        Scanner sc = new Scanner(System.in);

        // Read the number of integers to check
        int t = sc.nextInt();

        // Loop through each integer
        for (int i = 0; i < t; i++) {
            try {
                // Read the next long integer
                long x = sc.nextLong();
                System.out.println(x + " can be fitted in:");

                // Check if the integer can fit in a byte
                if (x >= -128 && x <= 127) {
                    System.out.println("* byte");
                }
                // Check if the integer can fit in a short
                if (x >= -32768 && x <= 32767) {
                    System.out.println("* short");
                }
                // Check if the integer can fit in an int
                if (x >= -2147483648 && x <= 2147483647) {
                    System.out.println("* int");
                }
                // Check if the integer can fit in a long
                if (x >= -9223372036854775808L && x <= 9223372036854775807L) {
                    System.out.println("* long");
                }

            } catch (Exception e) {
                // Handle cases where the input is not a valid long integer
                System.out.println(sc.next() + " can't be fitted anywhere.");
            }
        }

        // Close the scanner to free up resources
        sc.close();
    }
}
