import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        // Create a Scanner object to read input
        Scanner sc = new Scanner(System.in);
        
        // Read the input string
        String A = sc.next();
        
        // Reverse the string
        String R = new StringBuilder(A).reverse().toString();
        
        // Check if the original string is equal to its reverse
        if (A.equals(R)) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
        
        // Close the scanner
        sc.close();
    }
}
