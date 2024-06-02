import java.util.Scanner;
public class ASCII {
	public static void main (String[] args) {
		char ch;
		System.out.print("Enter character ");
		
		Scanner input = new Scanner(System.in);
		ch = input.next().charAt(0); //String character starts with index '0'
		
		int a = ch;
		
		System.out.print("ASCII of "+ ch +" is: "+ a);
	}
}
