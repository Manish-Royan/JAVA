import java.util.Scanner;
public class printMonth {
	public static void main(String[] args) {
		
		//declaring variable
		int n;
		System.out.print("Enter month number");
		
		//scanning user input
		Scanner input = new Scanner(System.in);
		n = input.nextInt();
		
		//implementing logic
		if (n == 1) 
		{
			System.out.print("Jan = 31 Days"); 
		}
			else if (n == 2)
			{
				System.out.print("Feb = 28 Days");
			}
			else if (n == 3)
			{
				System.out.print("March = 31 Days");
			}
			else if (n == 4)
			{
				System.out.print("April = 30 Days");
			}else if (n == 5)
			{
				System.out.print("May = 31 Days");
			}
			else if (n == 6)
			{
				System.out.print("June = 30 Days");
			}
			else if (n == 7)
			{
				System.out.print("July = 31 Days");
			}
			else if (n == 8)
			{
				System.out.print("August = 31 Days");
			}
			else if (n == 9)
			{
				System.out.print("September = 30 Days");
			}
			else if (n == 10)
			{
				System.out.print("October = 31 Days");	
			}
			else if (n == 11)
			{
				System.out.print("November = 30 Days");
			}
			else if (n == 12)
			{
				System.out.print("December = 31 Days");
			}			
			else
			{
				System.out.print("Invalid input");
			}
		
		input.close(); // Closing the Scanner object
		
		}	
}