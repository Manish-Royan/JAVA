import java.util.Scanner;
public class SimpleCalculator {
    public static void main(String[] args) {
        
        // Declaring variables
        int operand1, operand2, result;
        char ch;
        double x;
        System.out.print("Enter any two number: ");
        
        // Scanning user input
        Scanner input = new Scanner(System.in);
        operand1 = input.nextInt();
        operand2 = input.nextInt();
        System.out.print("Select Operation: '+' '-' '*' '/' '%' ");
        ch = input.next().charAt(0); //'ch' stands for choice 
        
        // Implementation of logic
        if (ch == '+') 
        {
            result = operand1 + operand2;
            System.out.print("Addition of: " + operand1 + " + " + operand2 + " = " + result);
        } 
        else if (ch == '-') 
        {
            result = operand1 - operand2;
            System.out.print("Subtraction of: " + operand1 + " - " + operand2 + " = " + result);
        } 
        else if (ch == '*') 
        {
            result = operand1 * operand2;
            System.out.print("Multiplication of: " + operand1 + " * " + operand2 + " = " + result);
        } 
        else if (ch == '/') 
        {
            if (operand2 == 0) { // Check for division by zero
                System.out.print("Error: Division by zero is not allowed.");
            } else {
                x = (double) operand1 / operand2; // Use double for division to avoid integer division - 'double / double'
                //what if varial 'x' was assign in 'int' datatype, then we simply do explicit turncating to by: x = (int)((double) operand1 / operand2);
                System.out.print("Division of: " + operand1 + " / " + operand2 + " = " + x);
            }
        } 
        else if (ch == '%') 
        {    
            if (operand2 == 0) { // Check for modulus by zero
                System.out.print("Error: Modulus by zero is not allowed.");
            } else {
                result = operand1 % operand2;
                System.out.print("Remainder of: " + operand1 + " % " + operand2 + " = " + result);
            }
        } 
        else 
        { // Handle invalid operation choice
            System.out.print("Error: Invalid operation selected.");
        }
        
        input.close(); // Close the Scanner object
    }
}
