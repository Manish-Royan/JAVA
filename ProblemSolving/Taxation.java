import java.util.Scanner;

public class Taxation {
    public static void main(String[] args) {
        
        // Declaring variables
        int salary;
        double tax;
        System.out.print("Enter salary: ");
        
        // Scanning user input
        Scanner input = new Scanner(System.in);
        salary = input.nextInt();
        
        // Implementation of logic
        if (salary <= 10000) {
            System.out.print("For " + salary + ": No tax");
        } else if (salary > 10000 && salary <= 100000) {
            tax = salary * 0.10;
            System.out.print("For " + salary + ": " + tax);
        } else {
            tax = salary * 0.20; // Assuming a higher tax rate for salaries above 100000
            System.out.print("For " + salary + ": " + tax);
        }
        
        input.close(); // Close the Scanner object
    }
}