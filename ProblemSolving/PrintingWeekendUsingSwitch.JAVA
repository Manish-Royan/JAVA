import java.util.Scanner;
public class PrintingWeekendUsingSwitch {
	public static void main(String[] args) {
		
		//declaring variable
		int n;
		System.out.print("Enter number of day");
		
		//scanning user input
		Scanner input = new Scanner(System.in);
		n = input.nextInt();
		
		//implementation of logic
		switch(n) {
		case 1 : System.out.print("Sunday");
		break;
		case 2 : System.out.print("Monday");
		break;
		case 3 : System.out.print("Tuseday");
		break;
		case 4 : System.out.print("Wensday");
		break;
		case 5 : System.out.print("Thursday");
		break;
		case 6 : System.out.print("Friday");
		break;
		case 7 : System.out.print("Saturday");
		break;
		default : System.out.print("Invalid input");
		break;
		}
	}
}