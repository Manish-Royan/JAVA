/* Print Power of Integer */

import java.util.Scanner;
public class Power {
	public static void main (String[] args) {
		int n,p,result=1; //result = 1; in multiply we use '1' as anything can be multiply by '1'
		
		System.out.print("Enter Number: ");		
		Scanner input = new Scanner(System.in);
		n = input.nextInt();
		
		System.out.print("Enter power: ");
		p = input.nextInt();
		
		for(int i = 1; i<=p; i++) {
			result = n*result;
		}
		
		System.out.print(result);
	}
}