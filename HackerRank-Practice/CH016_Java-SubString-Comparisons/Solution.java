import java.util.Scanner;

public class Solution {

    // Method to find the smallest and largest substrings of length k
    public static String getSmallestAndLargest(String s, int k) {
        // Initialize the first substring as both smallest and largest
        String str = s.substring(0, k);
        String smallest = str;
        String largest = str;
        
        // Loop through the string to find all possible substrings of length k
        for (int i = 1; i <= s.length() - k; i++) {
            str = s.substring(i, i + k);
            
            // Update smallest if the current substring is lexicographically smaller
            if (str.compareTo(smallest) < 0) {
                smallest = str;
            }
            // Update largest if the current substring is lexicographically larger
            if (str.compareTo(largest) > 0) {
                largest = str;
            }
        }
        
        // Return the smallest and largest substrings
        return smallest + "\n" + largest;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        // Read input string and integer k from the user
        String s = scan.next();
        int k = scan.nextInt();
        scan.close();
      
        // Print the smallest and largest substrings of length k
        System.out.println(getSmallestAndLargest(s, k));
    }
}
