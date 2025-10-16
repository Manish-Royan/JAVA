package UDP;

// UDP SERVER

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDP_SERVER {

    private static final int PORT = 8080;
    private static DatagramSocket datagramSocket;
    private static DatagramPacket inPacket, outPacket;
    private static byte[] buffer;


    public static void handleClient()
    {
       String messageIn, messageOut;
       int numMessages = 0;

        InetAddress clientAddress = null;
        int clientPort = 0;

        //STEP 2: Wait for a message from a client
        try {
            do {
                buffer = new byte[256]; //Buffer to receive incoming data

                //STEP 3: Create a DatagramPacket to receive incoming data
                inPacket = new DatagramPacket(buffer, buffer.length); //DatagramPacket to receive incoming data
                System.out.println("WAITING FOR A MESSAGE...");

                //STEP 4: Receive incoming data
                // Blocking method - the program waits until a message is received from a client
                datagramSocket.receive(inPacket); //Receive incoming data

                //STEP 5: Extract the message and client information from the incoming data
                clientAddress = inPacket.getAddress(); //Get the address of the client
                clientPort = inPacket.getPort(); //Get the port of the client

                //STEP 6: Process the message
                messageIn = new String(inPacket.getData(), 0, inPacket.getLength()); //Extract the message from the incoming data
                System.out.println("\nMESSAGE RECEIVED FROM " + clientAddress + ":" + clientPort + " - " + messageIn + "\n");
                numMessages++;
                messageOut = "Message " + numMessages + " received";

                //STEP 7: Send a reply to the client
                outPacket = new DatagramPacket(messageOut.getBytes(), messageOut.length(), clientAddress, clientPort);

                //STEP 8: Send the reply
                datagramSocket.send(outPacket);
                System.out.println("REPLY SENT TO " + clientAddress + ":" + clientPort + " - " + messageOut);
            } while (true); //Infinite loop to keep the server running
        } catch (IOException IOE) {
            System.out.println("ERROR CLOSING SOCKET");
            IOE.printStackTrace();
            System.exit(1);
        } finally {
            //STEP 9: Close the socket
            if (datagramSocket != null && !datagramSocket.isClosed())
            {
                datagramSocket.close();
                System.out.println("SOCKET CLOSED");
            }
        }

    }

    public static void main(String[] args) {

        System.out.println("OPENING THE PORT " + PORT + "...");

        //STEP 1: Create a DatagramSocket for communication
        try {
            datagramSocket = new DatagramSocket(PORT); //DatagramSocket(Port) opens the port for communication
            System.out.println("PORT " + PORT + " OPENED!");
        } catch (SocketException SOE)
        {
            System.out.println("ERROR OPENING PORT " + PORT);
            SOE.printStackTrace();
            System.exit(1);
        }

        handleClient();
    }
}
