
import java.util.Scanner;
public class SwappingTwoVariableValue {
	public static void main(String[] args) {
		//Declaring Variable
		int a,b;
		System.out.print("Enter two numbers: ");
		
		//Scanning User Input
		Scanner input = new Scanner(System.in);
		a = input.nextInt();
		b = input.nextInt();
		
		//Displaying before Swapping
		System.out.println("Before Swapping "+ a +" "+ b);
		
		//Implementation of logic
		a = a + b;
		b = a - b;
		a = a - b;
		
		//Displaying after Swapping
		System.out.println("After Swapping "+ a +" "+ b);
	}
}