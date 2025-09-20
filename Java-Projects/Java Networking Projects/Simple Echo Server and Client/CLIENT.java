//CREATING CLIENT SIDE APPLICATION

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class CLIENT {

    private static InetAddress host;
    private static final int PORT = 8080;

    //STEP-2: LISTENING FOR INCOMING CONNECTION REQUESTS
    private static void accessServer()
    {
        Socket link = new Socket();
        try {
            link = new Socket(host, PORT); // Create a socket to connect to the server at the specified host and port

            //STEP-3: Setup input and output streamsfor communication
            Scanner input = new Scanner(link.getInputStream()); // Create a Scanner to read input from the server
            PrintWriter output = new PrintWriter(link.getOutputStream(), true); // Create a PrintWriter to send output to the server
            Scanner userEntry = new Scanner(System.in); // Create a Scanner to read input from the user
            String message, response;

            //STEP-4: Communicate with the server by sending and receiving messages
            do {
                System.out.print("Enter message (type 'exit' to quit): ");
                message = userEntry.nextLine(); // Read a message from the user
                output.println(message); // Send the message to the server
                response = input.nextLine(); // Read the server's response
                System.out.println("\nSERVER> " + response); // Print the server's response
            } while (!message.equalsIgnoreCase("exit")); // Continue until the user types "exit"
        } catch (IOException e) {
            System.out.println("Error connecting to server: " + e.getMessage());
        } finally {
            // Ensure the connection is closed in case of an error
            try {
                if (link != null && !link.isClosed()) {
                    link.close(); // Ensure the connection is closed in case of an error
                }
            } catch (IOException e) {
                System.out.println("Error closing connection/disconnect: " + e.getMessage());
                System.exit(1); // 1 represents an error
            }
        }
    }

    public static void main(String[] args) {
        //STEP-1: GETTING THE LOCAL HOST ADDRESS
        try {
            host = InetAddress.getLocalHost(); // host = 127.0.0.1 (local loopback address)
            System.out.println("Host: " + host);
        } catch (UnknownHostException UHE) {
            System.out.println("Host ID not found!" + UHE.getMessage());
            System.exit(1); // Exit the program with a status code of 1 (indicating an error)
        }
        accessServer();
    }
}
