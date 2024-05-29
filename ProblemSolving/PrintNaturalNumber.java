/* Print first N natural numbers */

import java.util.Scanner;
public class PrintNaturalNumber {
 public static void main(String[] args) {
	 int n;
	 System.out.print("Enter number of terms: ");
	 
	 Scanner input = new Scanner(System.in);
	 n = input.nextInt();
	 
	 for(int i = 1;i<=n; i++) //int i = 1, because natural number starts with '1'
	 {
		 System.out.print(i + " ");
	}	 
 }
}