import java.io.*;
import java.util.*;

public class Solution {

    // Declare static variables for breadth and height
    static int B, H;
    static boolean flag = true;

    // Static block to initialize the static variables
    static {
        // Create a Scanner object to read input
        Scanner sc = new Scanner(System.in);
        try {
            // Read breadth and height from input
            B = sc.nextInt();
            H = sc.nextInt();
            // Validate if both breadth and height are positive
            if (B <= 0 || H <= 0) {
                flag = false;
                throw new Exception("Breadth and height must be positive");
            }
        } catch (Exception e) {
            // Print the exception message if any
            System.out.println(e);
            flag = false;
        } finally {
            // Close the scanner to free up resources
            sc.close();
        }
    }

    public static void main(String[] args) {
        // Calculate and print the area if the flag is true
        if (flag) {
            int area = B * H;
            System.out.print(area);
        }
    }
}
