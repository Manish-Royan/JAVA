import java.util.Scanner;

public class BiggestInArray {
    public static void main(String[] args) {
        // Creating a Scanner object to take user input
        Scanner sc = new Scanner(System.in);
        
        int size, max;
        
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
        
        // Initializing max with the first element of the array
        max = a[0];
        
        // Iterating through the array to find the maximum element
        for (int i = 1; i < a.length; i++) {
            if (a[i] > max) {
                max = a[i]; // Updating max if the current element is greater
            }
        }
        
        // Printing the maximum value found in the array
        System.out.print("Maximum value in array is: " + max);   
        
        sc.close(); // Closing the Scanner object
    }
}
