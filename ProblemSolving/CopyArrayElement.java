import java.util.Scanner;

public class CopyArrayElement {
    public static void main (String[] args) {
        
        // Creating a Scanner object to take user input
        Scanner sc = new Scanner(System.in);
        
        int size;
        System.out.print("Enter the size of array: ");
        // Reading the size of the array from user input
        size = sc.nextInt();
        
        // Declaring two arrays: one to store the input elements, and the other to store the copied elements
        int a[] = new int[size];
        int b[] = new int[a.length];
        
        System.out.print("Enter elements in First Array: ");
        // Taking array elements as input from the user
        for (int i = 0; i < a.length; i++) {
            a[i] = sc.nextInt();
        }
        
        System.out.print("First Array Elements: ");
        // Printing the elements of the first array
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        
        // Copying elements from the first array to the second array
        System.out.print("\nSecond Array Elements: ");
        for (int i = 0; i < a.length; i++) {
            b[i] = a[i];
            System.out.print(b[i] + " ");
        }
        
        sc.close(); // Closing the Scanner object
    }
}
