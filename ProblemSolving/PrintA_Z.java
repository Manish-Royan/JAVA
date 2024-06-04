
import java.util.Scanner;
public class PrintA_Z {
	public static void main (String[] args) {	
		char c;
		System.out.print("Enter Character: ");
		
		Scanner check = new Scanner(System.in);
		c = check.next().charAt(0);
		
		if (c == 'a') {
			for (c = 'a'; c<='z'; c++) {
				System.out.print(c + " ");
			}
		}
		
		if (c == 'A') {
			for (c = 'A'; c<='Z'; c++) {
				System.out.print(c + " ");
			}
		}		
	}
}