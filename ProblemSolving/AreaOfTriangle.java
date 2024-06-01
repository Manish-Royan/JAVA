/*Area of Triangle*/

//import java.lang.Math;
import java.util.Scanner;
public class AreaOfTriangle {
	public static void main (String[] args) {
		
		//Declaring Variable
		int a,b,c;
		double SP; //'SP' means semiperimeter
		double area;
		System.out.print("Enter value for each sides of triangle: ");
		
		//Scanning user input 
		Scanner input = new Scanner(System.in);
		a = input.nextInt();
		b = input.nextInt();
		c = input.nextInt();
		
		//Implementing formula
		SP = (a+b+c)/2;
		area = Math.sqrt(SP*(SP-a)*(SP-b)*(SP-c)); 
		//In programing language we can't create square root own, so In java we use 'sqrt()' method of 'Math' class which is default pakage of 'java.lang.*'
		
		//Prining Area
		System.out.print("Area of Circle: "+ area);
	}
}