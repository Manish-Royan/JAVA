/*Area of Circle*/

import java.util.Scanner;
public class AreaOfCircle {
	public static void main(String[] args) {
		
		//Declaring Variable
		final double PI = 3.14, area; //'final' keyword is used to declare a varaible constant
		int radius; 
		System.out.print("Enter radius of circle: ");
		
		//Scanning user input
		Scanner input = new Scanner(System.in);
		radius = input.nextInt();
		
		//Implementing Formula 
		area = PI*radius*radius;
		
		//Printing the result
		System.out.print("Area of circle: "+ area);
		
	}
}