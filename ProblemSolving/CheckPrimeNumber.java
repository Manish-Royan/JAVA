import java.util.Scanner;

public class CheckPrimeNumber {
	public static void main (String[] args) {
		
		//declaring variable
		int n, count = 0;
		System.out.print("Enter number: ");
		
		//scanning user input
		Scanner input = new Scanner (System.in);
		n = input.nextInt();
		
		//implementing logic
		for (int i = 1; i<=n; i++) {
			if (n%i == 0) {
				count++;
			}
		}
		
		if (count == 2) {
			System.out.print("The number "+ n + " is prime number.");
		} else {
			System.out.print("The number "+ n + " is not prime number.");
		}
	}
}