import java.util.Scanner;

public class SumOfArrElem {
    public static void main(String[] args) {
        
        // Creating a Scanner object to take user input
        Scanner sc = new Scanner(System.in);
        
        int sum = 0; // Variable to store the sum of array elements
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
        
        System.out.print("Sum of Array Elements: ");
        // Calculating and printing the sum of array elements
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " " + "+ ");
            sum = a[i] + sum; // Adding each element to the sum
        }
        
        System.out.print("= " + sum);
        
        sc.close(); // Closing the Scanner object
    }
}
