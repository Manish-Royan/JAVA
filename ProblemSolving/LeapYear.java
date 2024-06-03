//Century (year%100 == 0 && year%400 == 0)
//Year year%100 !=0 && year%4 == 0

import java.util.Scanner;
public class LeapYear {
	public static void main (String[] args) {
		int year;
		System.out.print("Enter Year: ");
		
		Scanner input = new Scanner(System.in);
		year = input.nextInt();
		
		if(year%100 == 0 && year%400 == 0 || year%100 !=0 && year%4 == 0) 
		{
			System.out.print(year +" is leap year");
		} 
		else 
		{
			System.out.print(year +" is not leap year");
		}
	}
}