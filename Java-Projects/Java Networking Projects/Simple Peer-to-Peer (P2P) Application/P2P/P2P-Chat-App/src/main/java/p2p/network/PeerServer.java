package p2p.network;

import javax.swing.*;
import java.io.*;
import java.net.*;
import java.util.function.Consumer;

public class PeerServer {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;
    private Consumer<String> messageHandler;
    private boolean running = false;

    public PeerServer(Consumer<String> messageHandler) {
        this.messageHandler = messageHandler;
    }

    public void start(int port) {
        new Thread(() -> {
            try {
                serverSocket = new ServerSocket(port);
                running = true;
                messageHandler.accept("Server started on port " + port);

                clientSocket = serverSocket.accept();

                // Set up input and output streams for communication
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new PrintWriter(clientSocket.getOutputStream(), true);

                String inputLine;
                while (running && (inputLine = in.readLine()) != null) {
                    final String message = inputLine;
                    SwingUtilities.invokeLater(() -> messageHandler.accept("Peer: " + message));
                }
            } catch (IOException e) {
                if (running) {
                    messageHandler.accept("Error in server: " + e.getMessage());
                }
            } finally {
                stop();
            }
        }).start();
    }

    public void sendMessage(String message) {
        if (out != null) {
            out.println(message);
            SwingUtilities.invokeLater(() -> messageHandler.accept("Server: " + message));
        } else {
            messageHandler.accept("Error: No connected peer to send message to");
        }
    }

    public void stop() {
        running = false;
        try {
            if (out != null) out.close();
            if (in != null) in.close();
            if (clientSocket != null) clientSocket.close();
            if (serverSocket != null) serverSocket.close();
        } catch (IOException e) {
            messageHandler.accept("Error closing server: " + e.getMessage());
        }
    }
}