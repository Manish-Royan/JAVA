package p2p.ui;

import p2p.network.PeerClient;
import p2p.network.PeerServer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ChatUI {
    private JFrame frame;
    private JTextArea chatArea;
    private JTextField messageField;
    private JTextField addressField;
    private JTextField portField;
    private JButton connectButton;
    private JButton serverButton;
    private JButton sendButton;

    private PeerClient client;
    private PeerServer server;

    // Track if we're in server or client mode
    private boolean isServerMode = false;

    public ChatUI() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("P2P Chat Application");
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Add window close handler to clean up resources
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (client != null) {
                    client.disconnect();
                }
                if (server != null) {
                    server.stop();
                }
            }
        });

        // Connection panel (top)
        JPanel connectionPanel = new JPanel();
        connectionPanel.setLayout(new FlowLayout());

        JLabel addressLabel = new JLabel("Peer Address: ");
        addressField = new JTextField("localhost", 10);

        JLabel portLabel = new JLabel("Port: ");
        portField = new JTextField("8000", 5);

        connectButton = new JButton("Connect...");
        serverButton = new JButton("Start Server...");

        connectionPanel.add(addressLabel);
        connectionPanel.add(addressField);
        connectionPanel.add(portLabel);
        connectionPanel.add(portField);
        connectionPanel.add(connectButton);
        connectionPanel.add(serverButton);

        // Chat area (center)
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(chatArea);

        // Message panel (bottom)
        JPanel messagePanel = new JPanel();
        messagePanel.setLayout(new BorderLayout());

        messageField = new JTextField();
        sendButton = new JButton("Send");
        sendButton.setEnabled(false);

        messagePanel.add(messageField, BorderLayout.CENTER);
        messagePanel.add(sendButton, BorderLayout.EAST);

        // Add components to frame
        frame.add(connectionPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(messagePanel, BorderLayout.SOUTH);

        // Add action listeners
        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connectToPeer();
            }
        });

        serverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startServer();
            }
        });

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        messageField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        client = new PeerClient(message -> {
            chatArea.append(message + "\n");
        });

        server = new PeerServer(message -> {
            chatArea.append(message + "\n");
        });
    }

    private void connectToPeer() {
        try {
            String address = addressField.getText();
            int port = Integer.parseInt(portField.getText());

            client = new PeerClient(message -> {
                chatArea.append(message + "\n");
            });
            client.connect(address, port);

            // Update UI state
            connectButton.setEnabled(false);
            serverButton.setEnabled(false);
            sendButton.setEnabled(true);
            isServerMode = false;
        } catch (Exception ex) {
            chatArea.append("Error: " + ex.getMessage() + "\n");
        }
    }

    private void startServer() {
        try {
            int port = Integer.parseInt(portField.getText());

            server = new PeerServer(message -> {
                chatArea.append(message + "\n");
            });
            server.start(port);

            // Update UI state
            connectButton.setEnabled(false);
            serverButton.setEnabled(false);
            sendButton.setEnabled(true);
            isServerMode = true;
        } catch (Exception ex) {
            chatArea.append("Error: " + ex.getMessage() + "\n");
        }
    }

    private void sendMessage() {
        String message = messageField.getText();
        if (message != null && !message.trim().isEmpty()) {
            if (isServerMode && server != null) {
                server.sendMessage(message);
            } else if (!isServerMode && client != null) {
                client.sendMessage(message);
            }
            messageField.setText("");
        }
    }

    public void show() {
        frame.setVisible(true);
    }
}