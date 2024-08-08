import java.util.Scanner;
import java.util.Arrays;

public class SolutionUsingArraySort {

    static boolean isAnagram(String a, String b) {
        /*  CONDITION:
            i. Remove Whitespaces
            ii. Remove case sensitivity
            iii. If they have the same number of elements, then it is an anagram, else not
        */
        
        // Removing any whitespace and changing case to lowercase
        a = a.replaceAll(" ", "").toLowerCase();
        b = b.replaceAll("\\s", "").toLowerCase(); //'\\s' - This is a regular expression pattern that matches any whitespace character.
        
        char[] c = a.toCharArray(); // Changing into the character set
        char[] d = b.toCharArray();
        
        // Sorting the character arrays
        Arrays.sort(c); //'Arrays.sort(c)' This method sorts the array 'c' in ascending order. [Example:  if 'c' is ['h', 'e', 'l', 'l', 'o'], after sorting, it would become ['e', 'h', 'l', 'l', 'o'].]
        Arrays.sort(d);
        
        // Checking if arrays 'c' and 'd' are equal
        return Arrays.equals(c, d); //Two arrays are considered equal if they have the same length and the same elements in the same order.
    }

    public static void main(String[] args) {
    
        Scanner scan = new Scanner(System.in);
        String a = scan.nextLine();
        String b = scan.nextLine();
        scan.close();
        boolean ret = isAnagram(a, b);
        System.out.println((ret) ? "Anagrams" : "Not Anagrams");
    }
}
