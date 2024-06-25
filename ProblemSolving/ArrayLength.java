import java.util.Scanner;

public class ArrayLength {
	public static void main (String[] args) {
		
		// Creating a Scanner object to take user input
		Scanner sc = new Scanner (System.in);
		
		int size;
		System.out.print("Enter the size of array: ");
		// Reading the size of the array from user input
		size = sc.nextInt();
		
		// Declaring an array of the given size
		int a[] = new int[size];
		
		System.out.print("Array length is "+ a.length); //print length of Array
		
		sc.close(); 
	}
}
