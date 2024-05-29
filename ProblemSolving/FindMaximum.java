/* Find Maximum Number */


import java.util.Scanner;
public class FindMaximum {
	public static void main (String[] args) {
		int a,b;
		System.out.print("Enter Two number: ");
		
		Scanner check = new Scanner(System.in);
		a = check.nextInt();
		b = check.nextInt();
		
		if(a>b) {
			System.out.print(a +" is Maximum");
		} else {
			System.out.print(b +" is Maximum");
		}		
	}
}