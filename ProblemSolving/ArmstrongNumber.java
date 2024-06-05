import java.util.Scanner;

public class ArmstrongNumber {
	public static void main(String[] args) {
		
		//declaring variable
		int n, c, armstrong = 0, reminder;
		System.out.print("Enter number: ");
		
		//scanning user input
		Scanner input = new Scanner(System.in);
		n = input.nextInt();
		c = n;
		
		//implementing logic
		while(n>0) {
			reminder = n%10;
			armstrong = (reminder * reminder * reminder) + armstrong;
			n = n/10;				
		}
		
		if (c == armstrong) {
			System.out.print("The given number "+ c +" is Armstrong number.");	
		}
		else
		{
			System.out.print("The given number "+ c +" is not Armstrong number.");
		}
		
	}
}