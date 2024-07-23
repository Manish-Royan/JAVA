import java.util.*;
import java.security.*;

public class Solution {
    public static void main(String[] args) {

        // Prevent the program from terminating using exit(0)
        DoNotTerminate.forbidExit();

        try {
            // Create a Scanner object to read input
            Scanner in = new Scanner(System.in);
            int n = in.nextInt();  // Read an integer input
            in.close();

            // Convert the integer to a string
            String s = Integer.toString(n);

            // Validate the conversion by comparing the original integer with the parsed integer from the string
            if (n == Integer.parseInt(s)) {
                System.out.println("Good job");
            } else {
                System.out.println("Wrong answer.");
            }
        } catch (DoNotTerminate.ExitTrappedException e) {
            // Handle the custom exit trapped exception
            System.out.println("Unsuccessful Termination!!");
        }
    }
}

// The following class will prevent you from terminating the code using exit(0)!
class DoNotTerminate {

    // Custom exception class to handle exit attempts
    public static class ExitTrappedException extends SecurityException {
        private static final long serialVersionUID = 1;
    }

    // Method to set a custom security manager to forbid exit
    public static void forbidExit() {
        final SecurityManager securityManager = new SecurityManager() {
            @Override
            public void checkPermission(Permission permission) {
                // If the permission contains "exitVM", throw the custom exception
                if (permission.getName().contains("exitVM")) {
                    throw new ExitTrappedException();
                }
            }
        };
        System.setSecurityManager(securityManager);
    }
}
