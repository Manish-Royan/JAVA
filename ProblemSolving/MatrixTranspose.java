import java.util.Scanner;

public class MatrixTranspose {
    public static void main(String[] args) {
        // Creating a Scanner object to take user input
        Scanner sc = new Scanner(System.in);
        
        // Reading the number of rows from user input
        System.out.print("Enter number of rows: ");
        int row = sc.nextInt();
        
        // Reading the number of columns from user input
        System.out.print("Enter number of columns: ");
        int col = sc.nextInt();
        
        // Declaring a 2D array with the given size
        int a[][] = new int[row][col];
        
        // Taking the elements of the matrix as input from the user
        System.out.print("\nEnter Matrix elements: ");
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                a[i][j] = sc.nextInt();
            }
        }
        
        // Printing the matrix by rows
        System.out.println("\nMatrix row elements: ");
        for (int i = 0; i < row; i++) {
            System.out.print("Row " + i + ": ");
            for (int j = 0; j < col; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }

        // Printing the matrix by columns
        System.out.println("\nMatrix column elements: ");
        for (int j = 0; j < col; j++) {
            System.out.print("Column " + j + ": ");
            for (int i = 0; i < row; i++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }

        // Printing the matrix as a whole
        System.out.print("\nMatrix elements: \n");
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
        
        // Transposing the matrix
        int[][] transpose = new int[col][row];  // Note the new size
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                transpose[j][i] = a[i][j];
            }
        }

        // Printing the transposed matrix
        System.out.print("\nTransposed Matrix: \n");
        for (int i = 0; i < col; i++) {  // Note the iteration over columns
            for (int j = 0; j < row; j++) {  // Note the iteration over rows
                System.out.print(transpose[i][j] + " ");
            }
            System.out.println();
        }

        // Closing the Scanner object
        sc.close();
    }
}
