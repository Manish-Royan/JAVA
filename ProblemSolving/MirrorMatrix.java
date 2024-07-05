import java.util.Scanner;

public class MirrorMatrix {
    public static void main(String[] args) {
        // Creating a Scanner object to take user input
        Scanner sc = new Scanner(System.in);
        
        // Reading the number of rows from user input
        int row = sc.nextInt();
        
        // Reading the number of columns from user input
        int col = sc.nextInt();
        
        // Declaring a 2D array with the given size
        int a[][] = new int[row][col];
        
        // Taking the elements of the matrix as input from the user
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                a[i][j] = sc.nextInt();
            }
        }

        // Printing the original matrix elements
        System.out.print("\nMatrix elements: \n");
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
        
        // Printing the mirror matrix elements
        System.out.print("\nMirror Matrix elements: \n");
        for (int i = 0; i < row; i++) {
            for (int j = col - 1; j >= 0; j--) {  // Traverse columns from right to left
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
        
        // Closing the Scanner object
        sc.close();
    }
}