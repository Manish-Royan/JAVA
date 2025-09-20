# Creating a Simple P2P Swing Application in Java 🖥️↔️🖥️
↳ A simple peer-to-peer chat application built with Java Swing that allows direct communication between two users without a central server.

[IMG]

## ▹ Features
- **True Peer-to-Peer**: Direct communication between two users
- **Simple Interface**: Clean Java Swing UI for messaging
- **Bidirectional Communication**: Both server and client can send and receive messages
- **Customizable Port**: Choose any available port to communicate through

## 🧐 Understanding the Application Interface
↳ When you run the application, you'll see a window with these components:
```bash
┌─────────────────────────────────────────────────────────────┐
│ P2P Chat Application                                    [X] │
├─────────────────────────────────────────────────────────────┤
│ Peer Address: [localhost] Port: [8000] [Connect] [Start]    │
├─────────────────────────────────────────────────────────────┤
│                                                             │
│              Chat Area                                      │
│         (Messages appear here)                              │
│                                                             │
├─────────────────────────────────────────────────────────────┤
│ [Type your message here...              ] [Send]            │
└─────────────────────────────────────────────────────────────┘
```

## STEP-1: PROJECT STRUCTURE 🏗️

```bash
src/
├── main/
│   └── java/
│       ├── p2p/
│       │   ├── ui/
│       │   │   └── ChatUI.java        # UI components and logic
│       │   ├── network/
│       │   │   ├── PeerServer.java    # Server-side network handling
│       │   │   └── PeerClient.java    # Client-side network handling
│       │   └── P2PApplication.java    # Application entry point
└── pom.xml                            # Maven configuration (if using Maven)
```

### ▹ Understanding and Creating the P2P Project Structure 🤓☝️
➡️ Let me explain the project structure and show you exactly how to create it step by step:

### Q. What This Structure Represents❓
➡️ This is a Maven-style Java project structure, which is the industry standard for Java applications. Here's what each part means:
#### ▹ Directory Breakdown:
* `src/` - Root source directory (contains all your source code)
* `main/` - Main application code (as opposed to test code which would be in src/test/)
* `java/` - Java source files directory
* `p2p/` - Your main package (package name: p2p)
* `ui/` - Sub-package for user interface classes (package name: p2p.ui)
* `network/` - Sub-package for networking classes (package name: p2p.network)
* `pom.xml` - Maven configuration file (if using Maven for dependency management)

## 🤷 How to Create This Structure❓
### ◻ Method 1: Using Command Line/Terminal (For Window Terminal)
```bash
# Navigate to where you want to create your project
cd C:\your\desired\location

# Create the project directory
mkdir P2P-Chat-App
cd P2P-Chat-App

# Create the folder structure
mkdir src\main\java\p2p\ui
mkdir src\main\java\p2p\network

# Create the Java files (empty files)
type nul > src\main\java\p2p\P2PApplication.java
type nul > src\main\java\p2p\ui\ChatUI.java
type nul > src\main\java\p2p\network\PeerServer.java
type nul > src\main\java\p2p\network\PeerClient.java

# Create Maven pom.xml (optional)
type nul > pom.xml
```

### ◻ Method 2: Using Your IDE

* **IntelliJ IDEA**:
   - File → New → Project
   - Choose "Maven" or "Java" project
   - Set Project Name: `P2P-Chat-App`
   - Right-click on `src/main/java` → New → Package → Enter `p2p`
   - Right-click on `p2p` package → New → Package → Enter `ui`
   - Right-click on `p2p` package → New → Package → Enter `network`
   - Create Java classes in respective packages
* **Eclipse**:
   - File → New → Java Project (or Maven Project)
   - Project Name: `P2P-Chat-App`
   - Right-click on `src` → New → Package → Enter `p2p`
   - Right-click on `src` → New → Package → Enter `p2p.ui`
   - Right-click on `src` → New → Package → Enter `p2p.network`
   - Create Java classes in respective packages


### ✓ Package Declarations
----
Remember to add the correct package declarations at the top of each Java file:
* **P2PApplication.java**: `package p2p;`
* **ChatUI.java**: `package p2p.ui;`
* **PeerServer.java**: `package p2p.network;`
* **PeerClient.java**: `package p2p.network;`

## Optional: Setting Up Maven
↳ If you want to use Maven for dependency management, create a `pom.xml` file in the **root directory**:
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    
    <groupId>com.example</groupId>
    <artifactId>p2p-chat-app</artifactId>
    <version>1.0.0</version>
    <packaging>jar</packaging>
    
    <properties>
        <maven.compiler.source>11</maven.compiler.source>
        <maven.compiler.target>11</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>
    
    <dependencies>
        <!-- Add dependencies here if needed -->
    </dependencies>
    
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.8.1</version>
                <configuration>
                    <source>11</source>
                    <target>11</target>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```




## STEP-2: IMPLEMENT THE BELOW GIVEN CODE ON THE CREATED JAVA FILES 📃

### 1. **src/main/java/p2p/P2PApplication.java**
   ```java
   package p2p;
   import p2p.ui.ChatUI;

   public class P2PApplication {
       public static void main(String[] args) {
           // Start the application UI
           new ChatUI().setVisible(true);
       }
   }
   ```

### 2. **src/main/java/p2p/ui/ChatUI.java**  
   ```java
   package p2p.ui;

   import p2p.network.PeerServer;
   import p2p.network.PeerClient;

   import javax.swing.*;
   import java.awt.*;
   import java.awt.event.ActionEvent;
   import java.io.IOException;

   public class ChatUI extends JFrame {
       private JTextField messageField;
       private JTextArea chatArea;
       private JTextField peerAddressField;
       private JTextField portField;
       private JButton connectButton;
       private JButton startServerButton;
       private PeerServer server;
       private PeerClient client;

       public ChatUI() {
           setTitle("P2P Chat Application");
           setSize(600, 400);
           setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           initComponents();
       }

       private void initComponents() {
           // Create panels
           JPanel mainPanel = new JPanel(new BorderLayout());
           JPanel connectionPanel = new JPanel(new GridLayout(1, 6, 5, 5));
           JPanel messagePanel = new JPanel(new BorderLayout());

           // Connection components
           peerAddressField = new JTextField("localhost");
           portField = new JTextField("8000");
           connectButton = new JButton("Connect to Peer");
           startServerButton = new JButton("Start Server");

           connectionPanel.add(new JLabel("Peer Address:"));
           connectionPanel.add(peerAddressField);
           connectionPanel.add(new JLabel("Port:"));
           connectionPanel.add(portField);
           connectionPanel.add(connectButton);
           connectionPanel.add(startServerButton);

           // Chat components
           chatArea = new JTextArea();
           chatArea.setEditable(false);
           JScrollPane scrollPane = new JScrollPane(chatArea);

           messageField = new JTextField();
           JButton sendButton = new JButton("Send");

           messagePanel.add(messageField, BorderLayout.CENTER);
           messagePanel.add(sendButton, BorderLayout.EAST);

           // Add components to main panel
           mainPanel.add(connectionPanel, BorderLayout.NORTH);
           mainPanel.add(scrollPane, BorderLayout.CENTER);
           mainPanel.add(messagePanel, BorderLayout.SOUTH);

           // Add main panel to frame
           add(mainPanel);

           // Add action listeners
           startServerButton.addActionListener(this::startServer);
           connectButton.addActionListener(this::connectToPeer);
           sendButton.addActionListener(this::sendMessage);
           messageField.addActionListener(this::sendMessage);
       }

       private void startServer(ActionEvent e) {
           try {
               int port = Integer.parseInt(portField.getText());
               server = new PeerServer(port, message -> {
                   SwingUtilities.invokeLater(() -> chatArea.append("Peer: " + message + "\n"));
               });
               new Thread(server).start();
               chatArea.append("Server started on port " + port + "\n");
               startServerButton.setEnabled(false);
           } catch (IOException ex) {
               JOptionPane.showMessageDialog(this, "Error starting server: " + ex.getMessage());
           }
       }

       private void connectToPeer(ActionEvent e) {
           try {
               String address = peerAddressField.getText();
               int port = Integer.parseInt(portField.getText());
               client = new PeerClient(address, port);
               client.connect();
               chatArea.append("Connected to peer at " + address + ":" + port + "\n");
               connectButton.setEnabled(false);
           } catch (IOException ex) {
               JOptionPane.showMessageDialog(this, "Error connecting to peer: " + ex.getMessage());
           }
       }

       private void sendMessage(ActionEvent e) {
           String message = messageField.getText();
           if (message.isEmpty() || client == null) return;

           try {
               client.sendMessage(message);
               chatArea.append("You: " + message + "\n");
               messageField.setText("");
           } catch (IOException ex) {
               JOptionPane.showMessageDialog(this, "Error sending message: " + ex.getMessage());
           }
       }
   }
   ```

### 3. **src/main/java/p2p/network/PeerServer.java**
   ```java
   package p2p.network;

   import java.io.BufferedReader;
   import java.io.IOException;
   import java.io.InputStreamReader;
   import java.net.ServerSocket;
   import java.net.Socket;
   import java.util.function.Consumer;

   public class PeerServer implements Runnable {
       private int port;
       private ServerSocket serverSocket;
       private boolean running = false;
       private Consumer<String> messageHandler;

       public PeerServer(int port, Consumer<String> messageHandler) throws IOException {
           this.port = port;
           this.messageHandler = messageHandler;
           this.serverSocket = new ServerSocket(port);
       }

       @Override
       public void run() {
           running = true;
           try {
               while (running) {
                   Socket clientSocket = serverSocket.accept();
                   new Thread(() -> handleClient(clientSocket)).start();
               }
           } catch (IOException e) {
               if (running) {
                   e.printStackTrace();
               }
           }
       }

       private void handleClient(Socket clientSocket) {
           try (BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.  getInputStream()))) {
               String message;
               while ((message = reader.readLine()) != null) {
                   messageHandler.accept(message);
               }
           } catch (IOException e) {
               e.printStackTrace();
           }
       }

       public void stop() {
           running = false;
           try {
               if (serverSocket != null) {
                   serverSocket.close();
               }
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
   }
   ```

### 4. **src/main/java/p2p/network/PeerClient.java**
   ```java
   package p2p.network;

   import java.io.IOException;
   import java.io.PrintWriter;
   import java.net.Socket;

   public class PeerClient {
       private String peerAddress;
       private int peerPort;
       private Socket socket;
       private PrintWriter writer;

       public PeerClient(String peerAddress, int peerPort) {
           this.peerAddress = peerAddress;
           this.peerPort = peerPort;
       }

       public void connect() throws IOException {
           socket = new Socket(peerAddress, peerPort);
           writer = new PrintWriter(socket.getOutputStream(), true);
       }

       public void sendMessage(String message) throws IOException {
           if (writer == null) {
               throw new IOException("Not connected to peer");
           }
           writer.println(message);
       }

       public void disconnect() {
           try {
               if (writer != null) {
                   writer.close();
               }
               if (socket != null && !socket.isClosed()) {
                   socket.close();
               }
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
   }
   ```


## STEP-3: HOW TO BUILD AND RUN 🏃‍♂️‍➡️

### 📂 Using the provided batch files:

1. Compile the application:
   ```
   compile.bat
   ```

2. Run the application:
   ```
   run.bat
   ```

### ⚙️ Manual compilation and execution:

1. Compile the application:

   - Navigate to your project root directory and compile:
   ```bash
   # Navigate to project directory
   cd P2P-Chat-App

   # Compile all Java files
   javac -d . src/main/java/p2p/*.java src/main/java/p2p/ui/*.java src/main/java/p2p/network/*.java
   ```
   #### [OR]

   ```bash
   mkdir -p out
   javac -d out src/main/java/p2p/P2PApplication.java src/main/java/p2p/ui/ChatUI.java src/main/java/p2p/network/PeerServer.java src/main/java/p2p/network/PeerClient.java
   ```





2. Run the application:
   ```
   # run the application from terminal
   java -cp out p2p.P2PApplication

   # Check what's using a port (Windows)
   netstat -an | findstr :8000
   ```

## Q. How to Use❓

1. Launch the application
2. To set up a chat session:

   [IMG-CONNECT]

   **As a host**:
   - Enter a port number (default: 8000)
   - Click "**Start Server**"
   - Wait for someone to connect to you

   **As a client**:
   - Enter the host's IP address (or "**localhost**" if on the same machine)
   - Enter the port number the host is using
   - Click "**Connect...**"

3. Once connected, type messages in the text field and click "**Send**" or press Enter
4. Both the host and client can send and receive messages


## ✅ Common Usage Scenarios
1. Learning Network Programming
   Perfect for understanding:
   * Socket programming concepts
   * Client-server architecture
   * P2P communication patterns
   * Threading in network applications
2. Local Team Communication
   * Use within your local network (office/home)
   * No internet required
   * Direct peer-to-peer communication
3. Development Testing
   * Test network applications
   * Simulate distributed systems
   * Debug network protocols

## ⚠️ Common Issues and Troubleshooting
- **Port already in use**: Choose a different port number
   ```bash
   Solution:
   1. Use a different port number
   2. Close other applications using that port
   3. Wait a few minutes and try again
   ```
- **Connection refused**: Ensure the server is running and the port is correct
   ```bash
   Solution:
   1. Ensure server is started first
   2. Check if port is correct
   3. Verify IP address
   4. Check firewall settings
   ```
- **Firewall blocking**: Check your firewall settings or try using a different port
   ```bash
   Solution:
   1. Check if connection was successful
   2. Ensure both instances are running
   3. Try reconnecting
   ```


## 🔮 Future Enhancements
- File sharing capabilities
- Group chat functionality
- End-to-end encryption
- Custom username support
- Message history storage