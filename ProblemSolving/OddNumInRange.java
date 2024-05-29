/*Print odd number in given range*/

import java.util.Scanner;
public class OddNumInRange {
	public static void main (String[] args) {
		int n; 
		System.out.print("Enter number of range: ");
		
		Scanner input = new Scanner(System.in);
		n = input.nextInt();
		
		//int i = 1, becuase odd number starts with 1
		for(int i = 1; i<=n; i = i+2) {
			System.out.print(i + " ");
		} 
	}
}
