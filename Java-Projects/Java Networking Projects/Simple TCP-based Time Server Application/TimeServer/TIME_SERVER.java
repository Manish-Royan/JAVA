// Import necessary classes for I/O operations, networking, and date handling
import java.io.IOException; // For handling I/O exceptions
import java.net.InetSocketAddress; // For specifying socket address (host and port)
import java.nio.ByteBuffer; // For allocating and managing byte buffers for data transfer
import java.nio.channels.ServerSocketChannel; // For creating a server socket channel
import java.nio.channels.SocketChannel; // For handling individual client connections
import java.util.Date; // For getting the current date and time

// Main class for the Time Server Application
public class TIME_SERVER {
    
    public static void main(String[] args) {

        /* STEP 1: Indicate that the server is starting and running */
        System.out.println("Time Server Application is running..."); // Print a message to the console to show the server is initializing

        /* STEP 2: Create and configure the server socket channel */
        try {
            // Create a new ServerSocketChannel instance for listening to incoming connections
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            
            // Bind the server socket to port 8080 on the local machine (any available IP)
            serverSocketChannel.socket().bind(new InetSocketAddress(8080)); // This makes the server listen for connections on this port
           
            // Print a message indicating the server is waiting for client connections
            System.out.println("Waiting for client connection..."); // Log that the server is ready to accept connections

            /* STEP 3: Enter an infinite loop to continuously accept and handle client connections */
            while (true) {
                // Accept an incoming client connection (this is a blocking call, waits until a client connects)
                SocketChannel socketChannel = serverSocketChannel.accept();
                // Check if the connection was successfully established (though it usually is after accept)
                if (socketChannel != null) {
                    /* STEP 4: Handle the newly accepted client connection */
                    
                    String clientAddress = socketChannel.getRemoteAddress().toString(); // Get the remote address of the connected client for logging purposes

                    // Print the client's address to the console
                    System.out.println("Client connected from: " + clientAddress);

                    /* STEP 5: Prepare and send the current date and time message to the client */
                    // Create a string message containing the current date and time using System.currentTimeMillis()
                    String dateTimeMessage = "DATE: " + new Date(System.currentTimeMillis());

                    // Allocate a ByteBuffer with a capacity of 64 bytes to hold the message data
                    ByteBuffer buffer = ByteBuffer.allocate(64);

                    buffer.clear(); // Clear the buffer to prepare it for writing (resets position to 0 and limit to capacity)

                    // Put the bytes of the date/time message into the buffer
                    buffer.put(dateTimeMessage.getBytes());
                    
                    buffer.flip(); // Flip the buffer to switch from writing mode to reading mode (prepares for sending)

                    // Write the buffer data to the socket channel until all data is sent
                    while (buffer.hasRemaining()) {
                        socketChannel.write(buffer);
                    }
                    // Print the sent message to the console for logging
                    System.out.println("Sent to client: " + dateTimeMessage);

                    /* STEP 6: Close the connection after sending the data */
                    socketChannel.close(); //// Close the socket channel to end the connection with the client
                    
                    System.out.println("Connection closed with client: " + clientAddress); // Print a message indicating the connection has been closed
                }
            }
        /* STEP 7: Handle any I/O exceptions that occur during server operation */
        } catch (IOException IOE) {
            IOE.printStackTrace();
        }
    }
}