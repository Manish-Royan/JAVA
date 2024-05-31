/* Print Sum and Average of Marks */

import java.util.Scanner;
public class SubjectResult {
	public static void main(String[] args) {
		int English,Math,Science,Account,Computer;
		
//		Asking User input
		System.out.println("Marks of each Subjects");
		System.out.println(" ");
		Scanner input = new Scanner(System.in);
		
		System.out.print("Enter Marks for English: ");
		English = input.nextInt();		

		System.out.print("Enter Marks for Math: ");
		Math = input.nextInt();

		System.out.print("Enter Marks for Science: ");
		Science = input.nextInt();

		System.out.print("Enter Marks for Account: ");
		Account = input.nextInt();

		System.out.print("Enter Marks for Computer: ");
		Computer = input.nextInt();
		
//		Sum		
		int sum = English + Math + Science + Account + Computer;
		System.out.println("Total Marks: " + sum);
		
//		Average
		double avg = sum/5.0; // we have total '5' subjects and '.0' to print decimal value
		System.out.println("Average Makrs: " + avg);
		
	}
}