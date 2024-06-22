import java.util.Scanner;

public class PrintArrayInReverse {
	public static void main (String[] args) {
		
		// Creating a Scanner object to take user input
		Scanner sc = new Scanner (System.in);
		
		int size;
		System.out.print("Enter the size of array: ");
		// Reading the size of the array from user input
		size = sc.nextInt();
		
		// Declaring an array of the given size
		int a[] = new int[size];
		
		System.out.print("Enter elements in array: ");
		// Taking array elements as input from the user
		for (int i = 0; i < a.length; i++) {
			a[i] = sc.nextInt();
		}
		
		System.out.print("Array Elements: ");
		// Printing the array elements in the original order
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		
		System.out.println(""); // Printing a new line for better output formatting
		
		System.out.print("Array in reverse order: ");
		// Printing the array elements in reverse order
		for (int i = a.length - 1; i >= 0; i--) {
			System.out.print(a[i] + " ");
		}
		
		sc.close(); // Closing the Scanner object
	}
}
