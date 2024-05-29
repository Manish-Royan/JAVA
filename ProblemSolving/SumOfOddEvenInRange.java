/*Sum of Odd Or Even in given range*/
import java.util.Scanner;
public class SumOfOddEvenInRange {
    public static void main (String[] args) {
        int n, i, sum = 0;
    System.out.print("Enter Range: ");

    Scanner input = new Scanner(System.in);
    n = input.nextInt();

    if(n%2==0) {
        for(i = 0; i<=n; i = i+2) {
            sum = sum + i;
        }			
        System.out.print("Sum of Even number: " + sum);
    } else {
        for(i = 1; i<=n; i = i+2) {
            sum = sum + i;
        }			
        System.out.print("Sum of Odd number: " + sum);
    }
    }   
}