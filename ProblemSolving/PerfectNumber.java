//A number is perfect if it is equal to the sum of all its proper divisors

import java.util.Scanner;

public class PerfectNumber {
	public static void main (String[] args) {
		
		//declaring variable
		int n, sum = 0;
		System.out.print("Enter number: ");
		
		//scanning user input
		Scanner input = new Scanner (System.in);
		n = input.nextInt();
		
		//implementing logic
		for (int i = 1; i < n; i++) {
			if (n%i == 0) {
				sum = sum + i;
			}
		}
		
		if (n == sum) {
			System.out.print("The number "+ n +" is perfect number");
		} else {
			System.out.print("The number "+ n +" is not perfect number");
		}	
        
        input.close(); // Closing the Scanner object
	}
}