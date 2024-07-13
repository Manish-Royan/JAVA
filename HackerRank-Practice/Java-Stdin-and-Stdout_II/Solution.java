import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int i = scan.nextInt(); 

        double d = scan.nextDouble(); //reads the next double from the input and assigns it to the variable 'd'.
        
        scan.nextLine(); //reads the remainder of the current line, including any leading whitespace characters, and discards it. This is necessary because 'nextInt()' and 'nextDouble()' do not consume the newline character after the number input, which can interfere with the subsequent 'nextLine()' method call.
        
        // String s = scan.next(); //'next()' will only reads until space occurs

        String s = scan.nextLine();  //scanner 'nextLine()' reads until next line occur
        
        System.out.println("String: " + s);
        System.out.println("Double: " + d);
        System.out.println("Int: " + i);
    }
}