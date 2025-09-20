package p2p.network;

import javax.swing.*;
import java.io.*;
import java.net.*;
import java.util.function.Consumer;

public class PeerClient {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private Consumer<String> messageHandler;
    private boolean running = false;

    public PeerClient(Consumer<String> messageHandler) {
        this.messageHandler = messageHandler;
    }

    public void connect(String host, int port) {
        new Thread(() -> {
            try {
                socket = new Socket(host, port);
                running = true;

                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                messageHandler.accept("Connected to peer at " + host + ":" + port);

                String inputLine;
                while (running && (inputLine = in.readLine()) != null) {
                    final String message = inputLine;
                    SwingUtilities.invokeLater(() -> messageHandler.accept("Peer: " + message));
                }
            } catch (IOException e) {
                if (running) {
                    messageHandler.accept("Error in client: " + e.getMessage());
                }
            } finally {
                disconnect();
            }
        }).start();
    }

    public void sendMessage(String message) {
        if (out != null) {
            out.println(message);
            SwingUtilities.invokeLater(() -> messageHandler.accept("You: " + message));
        } else {
            messageHandler.accept("Error: Not connected to any peer");
        }
    }

    public void disconnect() {
        running = false;
        try {
            if (out != null) out.close();
            if (in != null) in.close();
            if (socket != null) socket.close();
        } catch (IOException e) {
            messageHandler.accept("Error closing connection: " + e.getMessage());
        }
    }
}