import java.util.Scanner;

public class SumOfTwoMatrix {
    public static void main (String[] args) {
        // Creating a Scanner object to take user input
        Scanner sc = new Scanner(System.in);
        
        int r_size, c_size; // Row size and column size
        
        // Reading the number of rows from the user
        System.out.print("Enter number of rows: ");
        r_size = sc.nextInt();
        
        // Reading the number of columns from the user
        System.out.print("Enter number of columns: ");
        c_size = sc.nextInt();
        
        // Declaring three 2D arrays of the given size: one for each input matrix and one for the sum
        int a[][] = new int[r_size][c_size];
        int b[][] = new int[r_size][c_size];
        int c[][] = new int[r_size][c_size];
        
        // Reading the first matrix from user input
        System.out.print("\nEnter First matrix data: ");		
        for (int i = 0; i < r_size; i++) {
            for (int j = 0; j < c_size; j++) {
                a[i][j] = sc.nextInt();
            }
        } 
        
        // Printing the first matrix
        System.out.print("\nFirst Matrix: \n");		
        for (int i = 0; i < r_size; i++) {
            for (int j = 0; j < c_size; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.print("\n");
        } 	
        
        // Reading the second matrix from user input
        System.out.print("\nEnter Second matrix data: ");		
        for (int i = 0; i < r_size; i++) {
            for (int j = 0; j < c_size; j++) {
                b[i][j] = sc.nextInt();
            }
        } 
        
        // Printing the second matrix
        System.out.print("\nSecond Matrix: \n");		
        for (int i = 0; i < r_size; i++) {
            for (int j = 0; j < c_size; j++) {
                System.out.print(b[i][j] + " ");
            }
            System.out.print("\n");
        } 	
        
        // Calculating the sum of the two matrices and storing it in the third matrix
        System.out.print("\nSum of two matrices: \n");
        for (int i = 0; i < r_size; i++) {
            for (int j = 0; j < c_size; j++) {
                c[i][j] = a[i][j] + b[i][j];
                System.out.print(c[i][j] + " ");
            }
            System.out.print("\n");
        } 
        
        // Closing the Scanner object
        sc.close();		
    }
}