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
                if (x >= Byte.MIN_VALUE && x <= Byte.MAX_VALUE) {
                    System.out.println("* byte");
                }
                // Check if the integer can fit in a short
                if (x >= Short.MIN_VALUE && x <= Short.MAX_VALUE) {
                    System.out.println("* short");
                }
                // Check if the integer can fit in an int
                if (x >= Integer.MIN_VALUE && x <= Integer.MAX_VALUE) {
                    System.out.println("* int");
                }
                // Check if the integer can fit in a long
                if (x >= Long.MIN_VALUE && x <= Long.MAX_VALUE) {
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
