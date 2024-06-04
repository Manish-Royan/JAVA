/* Print if character is Vowel or Constant */

import java.util.Scanner;
public class CheckVowel {
	public static void main (String[] args) {
		char ch;
		System.out.print("Enter character");
		
		Scanner input = new Scanner(System.in);
		ch = input.next().charAt(0);
		
		if (ch == 'A' || ch == 'a' || ch == 'E' || ch == 'e' || ch == 'I' || ch == 'i' || ch == 'O' || ch == 'o' || ch == 'U' || ch == 'u') {
			System.out.print("The letter "+ ch + " is vowel");
		} else {
			System.out.print("The letter "+ ch + " is constant");
		}		
	}
}