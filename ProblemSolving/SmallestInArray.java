import java.util.Arrays;
import java.util.Scanner;

public class SmallestInArray {
    public static void main(String[] args) {
        // Creating a Scanner object to take user input
        Scanner sc = new Scanner(System.in);
        
        int size, min;
        
        System.out.print("Enter size for array: ");
        // Reading the size of the array from user input
        size = sc.nextInt();
        
        // Declaring an array of the given size
        int a[] = new int[size];
        
        System.out.print("Enter array elements: ");
        // Taking array elements as input from the user
        for (int i = 0; i < a.length; i++) {
            a[i] = sc.nextInt();
        }
        
        // Initializing the minimum value with the first element of the array
        min = a[0];
        
        // Loop through the array starting from the second element
        for (int i = 1; i < a.length; i++) {
            // If current element is smaller than the current minimum, update the minimum
            if (a[i] < min) {
                min = a[i];
            }
        }
        
        // Print the minimum value found in the array
        System.out.print("Minimum value in array is: " + min);
        
        // Closing the Scanner object
        sc.close();
    }
}
