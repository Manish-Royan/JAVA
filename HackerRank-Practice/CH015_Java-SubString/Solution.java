import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        // Creating a Scanner object to read user input
        Scanner in = new Scanner(System.in);
        
        // Reading the string from the user
        String S = in.next();
        
        // Reading the starting and ending indices for the substring
        int start = in.nextInt();
        int end = in.nextInt();
        
        // Extracting and printing the substring
        System.out.println(S.substring(start, end));
        
        // Closing the scanner as it's no longer needed
        in.close();
    }
}
