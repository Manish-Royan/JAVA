// Import necessary classes for I/O operations and networking using NIO channels
import java.io.IOException; // For handling I/O exceptions during socket operations
import java.net.InetSocketAddress; // For specifying the server's socket address (host and port)
import java.net.SocketAddress; // Interface for socket addresses (used for connection target)
import java.nio.ByteBuffer; // For allocating and managing byte buffers for reading data
import java.nio.channels.SocketChannel; // For creating a client-side socket channel to connect to the server

// Main class for the Client Side Application
public class CLIENT_SIDE {

    public static void main(String[] args) {

        /*  STEP 1: Define the server's socket address to connect to Create an InetSocketAddress object specifying the server's host ("localhost") and port (8080) */

        // This address points to the local machine; you can use "127.0.0.1" instead of "localhost"
        SocketAddress socketAddress = new InetSocketAddress("localhost", 8080);

        /*  STEP 2: Open a socket channel and connect to the server (using try-with-resources for auto-closing) */
        try (SocketChannel socketChannel = SocketChannel.open(socketAddress)) {

            // Print a message confirming the connection to the server, including the remote address
            System.out.println("Connected to the server: " + socketChannel.getRemoteAddress());

            /* STEP 3: Allocate a buffer and start reading data from the server */
            // Allocate a ByteBuffer with a capacity of 256 bytes to hold incoming data
            ByteBuffer buffer = ByteBuffer.allocate(256);
            
            // Perform the initial read operation from the socket channel into the buffer
            int bytesRead = socketChannel.read(buffer);// This returns the number of bytes read; if 0 or negative, no more data

            /* STEP 4: Continuously read and process data from the server until no more data is available */
            // Loop while there are bytes being read (bytesRead > 0 indicates data was received)
            while (bytesRead > 0) {
                // Flip the buffer to switch from writing mode to reading mode (prepares for data extraction)
                buffer.flip();
               
                while (buffer.hasRemaining()) // Print each byte as a character while the buffer has remaining data
                {
                    // Read one byte at a time and cast it to a char for printing
                    System.out.print((char) buffer.get());
                }
                // Print a newline after printing the received data for better formatting
                System.out.println("\n");
                
                buffer.clear(); // Clear the buffer to prepare for the next read operation (resets position and limit)

                // Attempt to read more data into the buffer from the socket channel
                bytesRead = socketChannel.read(buffer);
            }

        // STEP 5: Handle any I/O exceptions that occur during connection or data transfer
        } catch (IOException IOE) {
            IOE.printStackTrace();
        }
    }
}