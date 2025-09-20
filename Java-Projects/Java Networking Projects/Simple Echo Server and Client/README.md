# Echo Server-Client Application in Java 👋

This repository contains a **simple TCP/IP echo server and client** implementation in Java. The server echoes back any message sent by the client, demonstrating basic socket programming concepts.

## 📚 Overview 

### This project demonstrates:
- Basic TCP/IP Socket Programming in Java
- Server-Client Architecture
- Communication using Input/Output Streams
- Connection Handling
- Basic Network Protocol Design

## 🪶 Features
- **Echo Server**: Receives messages from clients and echoes them back
- **Client Application**: Connects to the server and sends user input messages
- **Single Client Connection**: Handles one client connection at a time
- **Persistent Connection**: Maintains connection until explicitly closed
- **Message Counting**: Tracks number of messages exchanged
- **Clean Disconnection**: Proper handling of connection closure

## How It Works❓

### » Communication Flow 💬
1. Server starts and listens on **port 8080**
2. Client connects to the server
3. Server accepts the connection
4. Client sends messages to the server
5. Server echoes back each message with a count
6. Communication continues until client sends "exit"
7. Connection closes gracefully

### » Protocol 🖥️🛜
The application follows a simple alternating send-receive protocol:
- Client sends a message to the server
- Client waits for the server's response
- Server receives the message and processes it (echoes it back)
- Server sends the response back to the client
- This cycle continues until either side terminates the connection

## Code Structure

### Server (SERVER.java)

```java
// The server creates a ServerSocket that listens on port 8080
// It accepts client connections and handles communication
// For each connection, it:
//   1. Sets up input/output streams
//   2. Receives messages from the client
//   3. Echoes messages back with a count
//   4. Continues until client sends "exit"
```

🗝️ Key components:
- `ServerSocket` - Listens for incoming connections on port 8080
- `Socket` - Represents the connection to the client
- `Scanner` - Reads input from the client
- `PrintWriter` - Sends output to the client
- `handleClient()` method - Manages client communication

### Client (CLIENT.java)

```java
// The client creates a Socket to connect to the server
// It:
//   1. Gets user input from the console
//   2. Sends messages to the server
//   3. Displays the server's responses
//   4. Continues until user types "exit"
```

🗝️ Key components:
- `Socket` - Connects to the server at the specified host and port
- `Scanner` - Reads input from both the server and user
- `PrintWriter` - Sends output to the server
- `accessServer()` method - Manages server communication

## Running the Application 🏃‍♂️‍➡️

1. Compile both Java files:
   ```
   javac SERVER.java
   javac CLIENT.java
   ```

2. Start the server first:
   ```
   java SERVER
   ```

3. In a separate terminal, start the client:
   ```
   java CLIENT
   ```

4. Type messages in the client terminal and observe the server's responses
5. Type "**exit**" to terminate the connection

## 🔖Learning Outcomes 

This project demonstrates several important networking concepts:

1. **Socket Programming Fundamentals**
   - Creating server sockets and client sockets
   - Establishing connections
   - Handling I/O streams

2. **Network Communication Patterns**
   - Request-response pattern
   - Alternating send-receive protocol

3. **Error Handling**
   - Managing connection issues
   - Proper resource cleanup

4. **Connection Management**
   - Opening and closing connections
   - Handling client disconnections

## Implementation Details

### ◻ Server Side

The server implementation follows these steps:
1. Creates a `ServerSocket` on **port 8080**
2. Listens for and accepts incoming connections
3. Sets up I/O streams for communication
4. Processes client messages in a loop
5. Echoes messages back with a count
6. Closes the connection when client sends "exit"

### ◻ Client Side

The client implementation follows these steps:
1. Resolves the local host address
2. Creates a `Socket` to connect to the server
3. Sets up I/O streams for communication
4. Gets user input and sends it to the server
5. Displays the server's response
6. Continues until the user types "exit"

## 🔮 Future Enhancements

Possible improvements to this basic implementation:
- Support for multiple concurrent clients
- Multithreaded server implementation
- Timeout handling
- More sophisticated message processing
- GUI client interface