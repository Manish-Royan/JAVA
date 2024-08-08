import java.util.Scanner;

public class SolutionByTrackingCharacterFrequencies {

    static boolean isAnagram(String a, String b)
    {
        //Convert both strings to lowercase to make the comparison case-insensitive.
        a = a.toLowerCase(); 
        b = b.toLowerCase();
        
        //check if there length is same or not - If the two strings don't have the same length, they cannot be anagrams.
        if(a.length() != b.length())
            return false;
        
        // Initialize an Array to Track Character Frequencies
        int arr[] = new int[26]; //Increament through alphabets,  to keep track of (for each letter of the alphabet) the frequency of each character in the strings.
        /*
            Why Size 26? The array corresponds to the 26 letters in the English alphabet.
            Each index in the array represents a letter ('a' = 0, 'b' = 1, ..., 'z' = 25).
         */

        
        // Count Character Frequencies in String 'a'
        for(int i = 0; i<a.length(); i++)
        {
            int index = a.charAt(i) - 'a';
            arr[index]++;
        }
        
         for(int i = 0; i<b.length(); i++)
        {
            int index = b.charAt(i) - 'a';
            arr[index]--;
        }
        
        for(int i =0; i < 26; i++)
        {
            if(arr[i] != 0)
              return false;
        }
        return true;
    }
       

    public static void main(String[] args) {
    
        Scanner scan = new Scanner(System.in);
        String a = scan.next();
        String b = scan.next();
        scan.close();
        boolean ret = isAnagram(a, b);
        System.out.println( (ret) ? "Anagrams" : "Not Anagrams" );
    }
}