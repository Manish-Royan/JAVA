import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Result {

    // Method to find the day of the week for a given date
    public static String findDay(int month, int day, int year) {
        // Array of days of the week
        String[] day_of_week = {"SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY"};
        
        // Creating a Calendar instance
        Calendar cal = Calendar.getInstance();
        
        // Setting the calendar to the given date
        cal.set(year, month - 1, day); // month - 1 because Calendar months are zero-based
        
        // Getting the day of the week (1 for Sunday, 2 for Monday, etc.)
        int i = cal.get(Calendar.DAY_OF_WEEK);
        
        // Returning the corresponding day of the week
        return day_of_week[i - 1];
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        // Reading input using BufferedReader
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        // Writing output using BufferedWriter
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        // Reading and parsing input
        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");
        int month = Integer.parseInt(firstMultipleInput[0]);
        int day = Integer.parseInt(firstMultipleInput[1]);
        int year = Integer.parseInt(firstMultipleInput[2]);

        // Finding the day of the week
        String res = Result.findDay(month, day, year);

        // Writing the result to output
        bufferedWriter.write(res);
        bufferedWriter.newLine();

        // Closing resources
        bufferedReader.close();
        bufferedWriter.close();
    }
}