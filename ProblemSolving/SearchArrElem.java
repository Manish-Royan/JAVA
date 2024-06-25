import java.util.Scanner;

public class SearchArrElem {
    public static void main(String[] args) {
        
        // Creating a Scanner object to take user input
        Scanner sc = new Scanner(System.in);
        
        int n, counter = 0; // Variable 'n' to store the search element, 'counter' to count occurrences
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
        // Printing the array elements
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");           
        }
        
        System.out.print("\nEnter Search Element: ");
        // Reading the element to search for in the array
        n = sc.nextInt();
        
        // Searching for the element in the array
        for (int i = 0; i < a.length; i++) {
          if (a[i] == n) {
              counter++; // Increment counter if the element is found
          }        
        }  
        
        // Checking if the element was found
        if (counter > 0) {
            System.out.print("Element " + n + " found.");
        } else {
            System.out.print("Element " + n + " not found.");
        }
                
        sc.close(); // Closing the Scanner object
    }
}
