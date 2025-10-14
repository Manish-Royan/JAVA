import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

// CASE: Demonstrate the use of URLConnection class to connect to a URL and read its content.

public class Main {
    public static void main(String[] args) {
        try {
            // Create a URL object to represent the URL
            URL url = new URL("https://www.facebook.com");

            // URLConnection is used to communicate with the URL
            URLConnection urlConnection = url.openConnection(); // Here we are opening a connection to the URL
            urlConnection.connect();

            // To read the content of the URL, we can use InputStreamReader and BufferedReader
            BufferedReader bufferedReader = new BufferedReader(new java.io.InputStreamReader(urlConnection.getInputStream()));
            String line; // To read each line of the content

            // This while loop will read each line of the content and print it
            while ((line = bufferedReader.readLine()) != null) // (line = bufferedReader.readLine()) != null means read each line until there is no more line to read
            {
                System.out.println(line); // Print each line of the content which is HTML code of the URL
            }

            bufferedReader.close(); // Close the BufferedReader
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}