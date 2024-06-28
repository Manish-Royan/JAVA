import java.util.Scanner;

public class ArrayElemArrange {
    public static void main(String[] args) {
        // Creating a Scanner object to take user input
        Scanner sc = new Scanner(System.in);
        
        int temp, size;
        
        System.out.print("Enter the size for the array: ");
        // Reading the size of the array from user input
        size = sc.nextInt();
        
        // Declaring an array of the given size
        int a[] = new int[size];
        
        System.out.print("Enter elements in the array: ");
        // Taking array elements as input from the user
        for (int i = 0; i < a.length; i++) {
            a[i] = sc.nextInt();
        }
        
        // Printing array elements before sorting
        System.out.print("Array elements before sorting: ");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        
        // Sorting the array using Bubble Sort
        for (int i = 0; i < a.length; i++) // Outer loop to iterate through each element
        { 
            for (int j = i + 1; j < a.length; j++) // Inner loop to compare the current element with the next elements
            { 
                if (a[i] > a[j]) // If the current element is greater than the next element, swap them
                { 
                    // Swapping the elements
                    temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
        }
        
        // Printing array elements after sorting
        System.out.print("\nArray elements after sorting: ");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        sc.close(); // Closing the Scanner object
    }
}
