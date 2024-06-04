import java.util.Scanner;
public class charPrint {
	public static void main (String[] args) {
		char ch;
		System.out.print("Enter Character");
		
		Scanner input = new Scanner(System.in);
		//ch = input.next().charAt(0); //if enter Manish it will print 'M' as 'M' is in '0' index
		// charAt is an method of String class
		ch = input.next().charAt(2); //if enter Manish it will print 'n' as 'n' is in '2' index

		System.out.print(ch);
	}
}