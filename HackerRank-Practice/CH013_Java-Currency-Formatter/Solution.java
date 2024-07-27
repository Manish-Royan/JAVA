import java.util.*;
import java.text.*;

public class Solution {

    public static void main(String[] args) {
        // Creating a Scanner object to read user input
        Scanner scanner = new Scanner(System.in);
        // Reading the payment amount as a double
        double payment = scanner.nextDouble();
        // Closing the scanner as it's no longer needed
        scanner.close();
        
        // Creating NumberFormat instances for different locales
        NumberFormat usFormat = NumberFormat.getCurrencyInstance(Locale.US);
        NumberFormat cnFormat = NumberFormat.getCurrencyInstance(Locale.CHINA);
        NumberFormat frFormat = NumberFormat.getCurrencyInstance(Locale.FRANCE);
        // For India, we need to specify the locale manually as it's not predefined
        NumberFormat inFormat = NumberFormat.getCurrencyInstance(new Locale("en", "IN"));
        
        // Formatting the payment amount in different currency formats
        String us = usFormat.format(payment);
        String india = inFormat.format(payment);
        String france = frFormat.format(payment);
        String china = cnFormat.format(payment);
        
        // Printing the formatted amounts
        System.out.println("US: " + us);
        System.out.println("India: " + india);
        System.out.println("China: " + china);
        System.out.println("France: " + france);
    }
}
