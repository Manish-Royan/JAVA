import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class UDP_CLIENT {

    private static InetAddress hostAddress;
    private static final int PORT = 8080;
    private static DatagramSocket datagramSocket;
    private static DatagramPacket inPacket, outPacket;
    private static byte[] buffer;

    private static void accessServer()
    {
        //STEP 1: Create a datagram socket for sending and receiving data
        try {
            datagramSocket = new DatagramSocket();
            Scanner userEntry = new Scanner(System.in);
            String message = "", response = "";
            do{
                System.out.println("ENTER A MESSAGE TO SEND TO THE SERVER (TYPE 'exit' TO QUIT): ");
                message = userEntry.nextLine();
                if (!message.equals("exit")) {
                    //STEP 2: Create a datagram packet to send data
                    outPacket = new DatagramPacket(message.getBytes(), message.length(), hostAddress, PORT);

                    //STEP 3: Send the data to the server
                    datagramSocket.send(outPacket);
                    System.out.println("MESSAGE SENT TO SERVER: " + message);

                    //STEP 4: Create a datagram packet to receive the server's response
                    buffer = new byte[256]; //Buffer to receive incoming data
                    inPacket = new DatagramPacket(buffer, buffer.length); //DatagramPacket to receive incoming data

                    //STEP 5: Receive the server's response
                    // Blocking method - the program waits until a message is received from the server
                    datagramSocket.receive(inPacket); //Receive incoming data

                    //STEP 6: Extract the server's response from the incoming data
                    response = new String(inPacket.getData(), 0, inPacket.getLength()); //Extract the message from the incoming data
                    System.out.println("\nRESPONSE RECEIVED FROM SERVER: " + response);
                }
            } while (!message.equals("exit"));
        } catch (IOException IOE)
          {
            System.out.println("ERROR CLOSING SOCKET");
            System.out.println(IOE.getMessage());
            System.exit(1);
        } finally {
            if (datagramSocket != null && !datagramSocket.isClosed()) {
                datagramSocket.close();
            }
        }
    }

    public static void main(String[] args) {
        try
        {
            hostAddress = InetAddress.getLocalHost(); //Get the address of the local host
        } catch (UnknownHostException UHE) {
            System.out.println("ERROR CREATING SOCKET");
            System.out.println(UHE.getMessage());
            System.exit(1);
        }

        accessServer();
    }
}