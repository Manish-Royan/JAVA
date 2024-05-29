/*Print Even number in range*/
import java.util.Scanner;
public class PrintEvenInRange {
    public static void main (String[] args) {
        int n;
        System.out.print("Enter number of term: ");
        
        Scanner input = new Scanner(System.in);
        n = input.nextInt();
        
        for (int i = 0; i<=n; i = i+2) //int i = 0, becuase even number starts with '0'
        {
            System.out.print(i + " ");
        }
    }
}
                