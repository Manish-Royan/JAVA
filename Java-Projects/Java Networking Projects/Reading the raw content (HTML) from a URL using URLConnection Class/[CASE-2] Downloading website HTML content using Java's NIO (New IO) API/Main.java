//CASE-2: Demonstrate the use of URLConnection class to connect downloading website HTML content with BufferedReader and Channels.

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;

public class Main {

/*  URLConnection;
    // URLConnection is used to communicate with the URL
    // It is an abstract class that represents a communication link between the application and a URL.
    // It is a simple way to accessing a server using a URL.

    ReadableByteChannel;
    // ReadableByteChannel is a channel that can read bytes from a source.
    // It allows reading bytes from a source using its read() method.

    ByteBuffer;
    // ByteBuffer is a buffer that can hold bytes.
    // It is used to read and write bytes from/to a channel.
    // ByteBuffer holds 64 bytes of data at a time.
*/

    public static void main(String[] args) {
        try{
            URL url = new URL("https://www.facebook.com");

            // URLConnection is used to communicate with the URL
            URLConnection urlConnection = url.openConnection(); // Here we are opening a connection to the URL
            urlConnection.connect(); // .connect() method is used to establish a connection to the URL

            // To read the content of the URL, we can use InputStream
            InputStream inputStream = urlConnection.getInputStream(); // .getInputStream() method is used to get the input stream of the URL

            //
            ReadableByteChannel readableByteChannel = java.nio.channels.Channels.newChannel(inputStream); // .newChannel() method is used to create a new channel that can read bytes from the input stream
            ByteBuffer byteBuffer = ByteBuffer.allocate(64); // Allocate a ByteBuffer with a capacity of 64 bytes

            String line = null; //This String will hold the content per line


            // readableByteChannel.read(byteBuffer) > 0 means read bytes from the channel into the buffer until there are no more bytes to read
            while (readableByteChannel.read(byteBuffer) > 0) // .read() method is used to read bytes from the channel into the buffer
            {
                // byteBuffer.array() returns the underlying byte array of the buffer
                // It will convert the byte array to a String and print it
                System.out.println(new String(byteBuffer.array())); // Print the content of the buffer as a String. It will display the data in chunks of 64 bytes

                byteBuffer.clear(); // Clear the buffer for the next read
            }

            System.out.println("----------------------------------");
            System.out.println("Content downloaded successfully!");
            System.out.println("----------------------------------");

            readableByteChannel.close(); // Close the channel
            inputStream.close(); // Close the InputStream

        } catch (IOException io) // URL and URLConnection classes throw IOException
        {
            io.printStackTrace();
        }
    }
}