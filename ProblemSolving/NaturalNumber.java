/* Sum of first N natural numbers */

import java.util.Scanner;
public class NaturalNumber {
	public static void main(String[] args) {
		int n, sum=0; //we are assigning sum = 0 because we can add whatever to 0
		System.out.print("Enter no of term ");
		
		Scanner input = new Scanner(System.in);
		n = input.nextInt(); //storing input to 'n' variable
		
		for(int i =1; i<=n; i++) //int i = 1 becuase natural number starts with 1
		{
			sum=sum+i;
		}
		System.out.print("Additon "+ sum);
	}
	
}