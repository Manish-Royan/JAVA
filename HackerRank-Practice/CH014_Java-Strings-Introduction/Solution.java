import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        // Creating a Scanner object to read user input
        Scanner sc = new Scanner(System.in);
        // Reading two strings from the user
        String A = sc.next();
        String B = sc.next();

        // Printing the combined length of the two strings
        System.out.println(A.length() + B.length());

        // Comparing the two strings lexicographically
        int comparisonResult = A.compareTo(B);

        // Printing "Yes" if A is lexicographically greater than B, otherwise "No"
        if (comparisonResult <= 0) {
            System.out.println("No");
        } else {
            System.out.println("Yes");
        }

        // Capitalizing the first letter of each string and printing the result
        String capitalizedA = A.substring(0, 1).toUpperCase() + A.substring(1);
        String capitalizedB = B.substring(0, 1).toUpperCase() + B.substring(1);
        System.out.println(capitalizedA + ' ' + capitalizedB);

        // Closing the scanner as it's no longer needed
        sc.close();
    }
}
