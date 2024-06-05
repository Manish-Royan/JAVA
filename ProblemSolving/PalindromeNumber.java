import java.util.Scanner;

public class PalindromeNumber {
	public static void main(String[] args) {
		
		//declaring number 
		int n, c, r, sum = 0;
		System.out.print("Enter number: ");
		
		//scanning user input
		Scanner input = new Scanner(System.in);
		n = input.nextInt();
		c = n;
		
		//implementing logic
		while (n>0 ) {
			r = n%10;
			sum = (sum*10) + r;
			n = n/10;			
		}
		
		//checking if given number is palindrome or nor
		if (c == sum)
		{
			System.out.print("The given number "+ c +" is Palindorme");
		}
		else
		{
			System.out.print("The given number "+ c +" is not Palindorme");
		}
		
	}
}