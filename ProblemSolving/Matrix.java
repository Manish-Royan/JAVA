import java.util.Scanner;

public class Matrix {
    public static void main(String[] args) {
        // Creating a Scanner object to take user input
        Scanner sc = new Scanner(System.in);
        
        int r_size, c_size; // Row size and column size
        
        // Reading the number of rows from the user
        System.out.print("Enter number of rows: ");
        r_size = sc.nextInt();
        
        // Reading the number of columns from the user
        System.out.print("Enter number of columns: ");
        c_size = sc.nextInt();
        
        // Declaring a 2D array of the given size
        int a[][] = new int[r_size][c_size];
        
        // Taking matrix elements as input from the user
        System.out.print("Enter matrix elements: ");
        for (int i = 0; i < r_size; i++) {
            for (int j = 0; j < c_size; j++) {
                a[i][j] = sc.nextInt();
            }
        }
        
        // Printing the matrix
        System.out.print("Matrix: \n");        
        for (int i = 0; i < r_size; i++) { // Loop for rows
            for (int j = 0; j < c_size; j++) { // Loop for columns
                System.out.print(a[i][j] + " ");
            }
            System.out.print("\n");
        }
        
        // Closing the Scanner object
        sc.close();        
    }
}
