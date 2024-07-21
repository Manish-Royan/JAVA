import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        // Create a Scanner object to read input from STDIN
        Scanner sc = new Scanner(System.in);
        
        int n = 1;  // Initialize a line number counter
        
        // Iterate until there is no more input (EOF)
        while (sc.hasNext()) {
            // Print the current line number and the input line
            System.out.println(n++ + " " + sc.nextLine());
        }
        
        // Close the scanner to free up resources
        sc.close();
    }
}
