import java.util.Scanner;
import java.lang.Math;

public class Squareroot {
	public static void main (String[] args) {
		int n;
		System.out.print("Enter number: ");
		
		Scanner input = new Scanner (System.in);
		n = input.nextInt();
		double r = Math.sqrt(n);
		
		System.out.print("The square root of "+ n +" is: "+ r);
	}
}