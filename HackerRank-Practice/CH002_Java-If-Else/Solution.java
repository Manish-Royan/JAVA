import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        int N = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
       
        //N%2 gives reminder when divided by 2
        /*using else-if ladder*/
        if(N%2 != 0) //1st condition //N is odd
        {
            System.out.print("Weird");
        }//'N'should automatically even, as 'if' condition is false

        else if (N>=2 && N<=5) 
        //2nd condition //'N' is even and in inclusive range of 2 to 5
        {
            System.out.print("Not Weird");
        }

        else if (N>=6 && N<=20) 
        //3rd condition //'N' is even and in inclusive range of 6 to 20
        {
            System.out.print("Weird");
        }

        else 
        {
            System.out.print("Not Weird");
        }
        
        scanner.close();
    }
}