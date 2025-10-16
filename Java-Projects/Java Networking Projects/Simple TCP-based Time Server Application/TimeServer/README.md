## \# Explanation of the Program (TIME_SERVER)
- **Purpose**: The server listens on **port 8080** for incoming TCP connections from clients. When a client connects, it sends the current date and time as a string message, then immediately closes the connection. This is a basic example of a server that provides time synchronization or timestamp services.

## 🔑 Key Concepts Demonstrated:
  - **ServerSocketChannel**: The NIO equivalent of `ServerSocket` for TCP servers. It's opened, bound to port 8080 using `InetSocketAddress`, and used to accept client connections.
  - **SocketChannel**: Represents the connection to each client. Obtained via `serverSocketChannel.accept()`, which blocks until a client connects.
  - **ByteBuffer**: Used for efficient byte handling. Allocates 64 bytes, puts the date/time string into it, flips to read mode, and writes to the channel.
  - **Connection Handling**: Each client connection is processed sequentially in an infinite `while` loop. After sending data, the `SocketChannel` is closed, allowing the server to handle the next client.
  - **Date/Time Generation**: Uses `new Date(System.currentTimeMillis())` to get the current timestamp, formatted as a string.
  - **Logging**: Prints connection events, sent messages, and disconnections for debugging.

- **Output**: Example logs when a client connects:
  ```
  Time Server Application is running...
  Waiting for client connection...
  Client connected from: /127.0.0.1:xxxxx
  Sent to client: DATE: Wed Oct 16 20:11:44 UTC 2025
  Connection closed with client: /127.0.0.1:xxxxx
  ```

## ⚠️ Limitations and Notes:
  - This is a blocking server—`accept()` waits for connections, and `write()` blocks until data is sent. For true non-blocking NIO, configure channels with `configureBlocking(false)` and use selectors.
  - Each client gets one response and the connection closes; no persistent sessions.
  - Assumes clients connect via TCP (e.g., using `telnet localhost 8080` or a custom client). If no client connects, it waits indefinitely.
  - Error Handling: Catches `IOException` but doesn't handle specific cases like port already in use.
  - **Security**: No authentication or rate limiting; anyone can connect.
  - For production, add threading for concurrent clients or use `Selector` for multiplexing.