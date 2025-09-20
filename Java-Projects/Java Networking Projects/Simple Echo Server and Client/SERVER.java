import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

//CREATED A SEVER THAT ACCEPTS MESSAGES FROM CLIENT AND SENDS BACK THE SAME MESSAGE (ECHO SERVER)

public class SERVER {
    private static ServerSocket serverSocket;
    private static final int PORT = 8080;


    //STEP-2: LISTENING FOR INCOMING CONNECTION REQUESTS
    private static void handleClient() // Method to handle client connections and communication
    {
        // This method will handle client connections and communication
        Socket link = null;
        try {
            link = serverSocket.accept(); // Accept incoming connection requests
            System.out.println("Client connected: " + link.getInetAddress()); // Print the client's IP address

            //STEP-3: Setup input and output streams for communication
            Scanner input = new Scanner(link.getInputStream()); // Create a Scanner to read input from the client
            PrintWriter output = new PrintWriter(link.getOutputStream(), true); // Create a PrintWriter to send output to the client

            int numMessages = 0; // Counter for the number of messages received from the client

            //STEP-4: Communicate with the client by sending and receiving messages
            output.println("Awaiting for data..."); // Send a message to the client
            String message = input.nextLine(); // Read a message from the client. This is a blocking call.
            while (!message.equalsIgnoreCase("exit")) { // Continue until the client sends "exit"
                numMessages++; // Increment the message counter
                System.out.println("Message from client: " + message); // Print the received message
                output.println("Message received (" + numMessages + "): " + message); // Echo the message back to the client with a count
                message = input.nextLine(); // Read the next message from the client
            }
            output.println("Connection closed. Total messages received: " + numMessages); // Inform the client that the connection is closing
            link.close(); // Close the connection to the client
            System.out.println("Client disconnected. Total messages received: " + numMessages + "\n");

        } catch (IOException e) {
            System.out.println("Error handling client: " + e.getMessage());
        } finally {
            try {
                if (link != null && !link.isClosed()) {
                    link.close(); // Ensure the connection is closed in case of an error
                }
            } catch (IOException e) {
                System.out.println("Error closing connection: " + e.getMessage());
                System.exit(1); // 1 represents an error
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Server started and listening on port " + PORT + "\n");

        try{
            //STEP-1: CREATING A SERVER SOCKET OBJECT
            serverSocket = new ServerSocket(PORT); // Create a ServerSocket object that listens on port 8080
        } catch (IOException IO) {
            System.out.println("Unable to attach to port " + PORT + IO.getMessage()) ;
            System.exit(1);// Exit the program with a status code of 1 (indicating an error)
        }

        do {
            handleClient();
        } while (true);
    }
}
