/* Check if the given number is Odd or Even */

import java.util.Scanner;
public class CheckOddEven {
	public static void main(String[] args) {
		int n;
		System.out.println("Enter any number");
		
		Scanner input = new Scanner(System.in);
		n = input.nextInt();
		
		if(n%2==0) {
			System.out.print(n + " is Even Number");
		} else {
			System.out.print(n +" is Odd number");
		}
	}
}