/*Count Digits of given Number*/
import java.util.Scanner;
public class CountDigit {
    public static void main (String[] args) {
        
        //Variable decleartion
        int n, hold, count = 0; //we assign count = 0, so when n/value get incremented with '1'
        System.out.print("Enter any nubmer ");
        
        //User input
        Scanner input = new Scanner(System.in);
        n = input.nextInt();
        hold = n;
        
        //logic implementation
        while(n>0) //the loop only works if our given is grester than '0;
        {
            n = n/10;
            count++; //counter variable
        }
        
        //Display result
        System.out.print("No. of Digits for "+ hold +": " + count);
    }
}                                