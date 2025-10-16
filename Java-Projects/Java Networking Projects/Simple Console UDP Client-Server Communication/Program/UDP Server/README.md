## \# Explanation of the Program (UDP Server)
* **Purpose**: This server listens on `port 8080` for incoming UDP messages from clients, processes them, and sends replies. It's a basic echo server that acknowledges each message with a counter (e.g., "Message 1 received"). Unlike TCP (which is connection-oriented), UDP doesn't establish a connection—packets can arrive from any client, and there's no guarantee of delivery or order.

## 🔑 Key Concepts Demonstrated:
* **DatagramSocket**: The core class for UDP communication. It's created on a specific port (`new DatagramSocket(PORT)`) to receive/send datagrams. It's not bound to a single client, allowing the server to handle multiple clients simultaneously.
* **DatagramPacket**: Represents a UDP packet. Used for both receiving (`inPacket`) and sending (`outPacket`). Contains **data**, **address**, and **port**.
* **Receiving Data**: The server waits indefinitely (`datagramSocket.receive(inPacket)`) for packets. It extracts the client's address and port from the packet, processes the message (converts `bytes` to `string`), and logs it.
* **Sending Replies**: Creates an outgoing packet with the reply message, client's address/port, and sends it back.
* **Infinite Loop**: **Keeps the server running to handle continuous requests**. In a real app, you'd add a shutdown mechanism (e.g., via a special message or signal).
* **Error Handling and Cleanup**: Catches `IOException` and ensures the socket is closed in finally (important since UDP has no built-in connection closure).
* **Buffer Management**: Uses a `256-byte` buffer for incoming data; resizes as needed for replies.
* **Output**: Prints status messages like **`"WAITING FOR A MESSAGE..."`**, received messages, and sent replies. It increments a message counter for each interaction.

## ⚠️ Limitations and Notes:
* UDP is "fire-and-forget"—no acknowledgments or retransmissions, so packets might be lost. For reliability, use TCP.
* The server runs forever unless an exception occurs. Add a way to stop it (e.g., check for a "STOP" message).
* **Security**: No authentication; anyone can send packets to the port. In production, add firewalls or validation.
* **Comparison to TCP**: TCP would use `ServerSocket` and `Socket` for persistent connections; UDP is simpler but less reliable.
* To test: Run this server, then send UDP packets to `port 8080` (e.g., using a client program or tools like **netcat**).