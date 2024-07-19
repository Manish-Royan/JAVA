import java.util.*;
import java.io.*;

class Solution{
    public static void main(String []argh){
        Scanner in = new Scanner(System.in);

        // Read the number of queries
        int t = in.nextInt(); // 't' denotes the number of queries

         // Loop through each query
        for(int i=0;i<t;i++){
            int a = in.nextInt(); // Base value
            int b = in.nextInt(); // Incremental value
            int n = in.nextInt(); // Number of terms to generate

            // Generate and print the sequence for the current query
            for (int j = 0; j < n; j++)  //initialized 'j' with '0' because (2^0) start with '0' and increase j < n-1
            {
                // Calculate the next term in the sequence
                a += b * (int) Math.pow(2, j); //b*(int) Math.pow(2, j) represent (2^0)
                System.out.print(a +" ");
            }
            System.out.println();
        }
        // Close the scanner to free up resources
        in.close();
    }
}