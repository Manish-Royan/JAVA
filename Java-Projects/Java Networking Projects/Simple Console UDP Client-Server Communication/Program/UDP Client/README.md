## \# Explanation of the Program (UDP Client)
- **Purpose**: The client connects to a UDP server (running on localhost at `port 8080`), sends user-input messages, and receives replies. It runs in a loop, allowing multiple interactions until the user types "exit". This showcases client-side UDP communication, emphasizing that UDP is stateless—no persistent connection is maintained.

## 🔑 Key Concepts Demonstrated**:
  - **DatagramSocket**: Created without specifying a port (client-side), allowing it to send/receive packets dynamically.
  - **InetAddress**: Uses `InetAddress.getLocalHost()` to get the loopback address (127.0.0.1), targeting the local server. In a real network, you'd use `InetAddress.getByName("server-ip")`.
  - **Sending Data**: Converts user input to bytes, creates an `outPacket` with the message, server's address, and port, then sends it via `datagramSocket.send(outPacket)`.
  - **Receiving Data**: Prepares an `inPacket` with a 256-byte buffer, blocks until a response arrives (`datagramSocket.receive(inPacket)`), and extracts the string reply.
  - **User Interaction**: Uses `Scanner` for input, with a loop that continues until "exit" is entered. Prints sent messages and received responses for clarity.
  - **Error Handling and Cleanup**: Catches `IOException` and ensures the socket is closed in `finally`.
  - **Notes from Code**: As mentioned, UDP doesn't guarantee delivery, so the client won't error if the server is down—it just waits or times out implicitly. For testing, run the server first.

- **Output**: Prompts the user to enter messages, logs sends/receives, and echoes server responses. Example session:
  ```
  ENTER A MESSAGE TO SEND TO THE SERVER (TYPE 'exit' TO QUIT): Hello
  MESSAGE SENT TO SERVER: Hello
  RESPONSE RECEIVED FROM SERVER: Message 1 received
  ```

## ⚠️ Limitations and Notes**
  - Assumes the server is on localhost; change `hostAddress` for remote servers.
  - No timeouts set, so `receive()` can block indefinitely if no response. Add `setSoTimeout()` for robustness.
  - Unlike TCP clients (which use `Socket`), UDP clients don't "connect"—they just send packets.
  - For production, handle exceptions better and add security (e.g., encryption).
  - To run: Start `UDP_SERVER.java` in one terminal, then `UDP_CLIENT.java` in another.