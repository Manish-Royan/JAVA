/* Swapping Two Numbers */

import java.util.Scanner;
public class SwappingTwoNumbers {
	public static void main(String[] args) {
		//Declaring Variable
		int a,b,temp;
		System.out.print("Enter two numbers: ");
		
		//Scanning User Input
		Scanner input = new Scanner(System.in);
		a = input.nextInt();
		b = input.nextInt();
		
		//Displaying before Swapping
		System.out.println("Before Swapping "+ a +" "+ b);
		
		//Implementation of logic
		temp = a;
		a = b;
		b = temp;
		
		//Displaying after Swapping
		System.out.println("After Swapping "+ a +" "+ b);
	}
}
