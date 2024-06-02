import java.util.Scanner;
public class CheckPostiveNegative {
	public static void main(String[] args) {
		int n;
		System.out.print("Enter number: ");
		
		Scanner input = new Scanner(System.in);
		n = input.nextInt();
		
		if(n>0) {
			System.out.print("Positive");
		} else if (n<0) {
			System.out.print("Negative");
		} else {
			System.out.print("neither Positive nor Negative");
		}
	}
}