import java.util.Scanner;
public class SumOfDigits {
	public static void main(String[] args) {
		
		//declaring variable
		int n, r, sum = 0; //'r' for reminder
		System.out.print("Enrter any number: ");
		
		//Scanning user input
		Scanner input = new Scanner(System.in);		
		n = input.nextInt();
		int h = n; 
		
		//implementing logic
		while(n>0) {
			r = n%10;
			sum = sum + r;
			n = n/10;
		}
		System.out.print("Sum of digit of "+ h + " is: "+ sum);
	}
}