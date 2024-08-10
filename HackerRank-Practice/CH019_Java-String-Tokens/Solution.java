import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String s = scan.nextLine();
        
        //Triming the String
        s = s.trim(); //The `trim()` method removes leading and trailing spaces from the string.
        
        //Creating an array
        String token[] = s.split("[^a-zA-Z]+"); //The regular expression [^a-zA-Z]+ is used to split the string wherever there are one or more characters that are not letters (a-z or A-Z). This effectively removes punctuation and other non-alphabetic characters.
        
        if(s.length() == 0)
        {
            System.out.println(0); //If the length of the trimmed string s is '0', it means the string was empty or contained only spaces. In this case, the program prints '0' (indicating no words).
        }
        else {
            System.out.println(token.length);
        }
        
        // Printing Token - using 'forEach'
        for(String word : token)
        {
            System.out.println(word);
        }
        
        scan.close();
    }
}

