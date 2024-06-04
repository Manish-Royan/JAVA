import java.util.Scanner;
public class ReverseNumber {
	public static void main(String[] args) {
		
		//declaring variable
		int n, rev;
		System.out.print("Enter any number  ");
		
		//Scannign user input
		Scanner input = new Scanner(System.in);
		n = input.nextInt();
		
		//implementing logic
		while (n>0)
		{
			rev = n%10;
			System.out.print(rev);
			n = n/10;
		}
		
	}
}