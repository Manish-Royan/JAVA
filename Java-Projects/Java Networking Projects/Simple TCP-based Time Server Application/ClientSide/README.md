## \# Explanation of the Program (CLIENT_SIDE)
- **Purpose**: The client connects to the Time Server on `localhost:8080`, receives the current date and time sent by the server, and prints it to the console. It's a basic example of a TCP client using NIO for efficient byte handling.

## 🔑 Key Concepts Demonstrated:
  - **SocketChannel**: The NIO equivalent of `Socket` for TCP clients. Opened with `SocketChannel.open(socketAddress)` to connect to the server at the specified address/port.
  - **SocketAddress**: Uses `InetSocketAddress` to specify the server location (localhost on port 8080).
  - **ByteBuffer**: Allocates 256 bytes to read incoming data. Reads from the channel in a loop, flipping to read mode, and printing each byte as a character.
  - **Reading Data**: Reads data in chunks using `socketChannel.read(buffer)`. Continues until no more bytes are available (`bytesRead > 0`). Each byte is cast to `char` and printed, reconstructing the string message.
  - **Connection Management**: Uses try-with-resources for automatic closing of the `SocketChannel`. Prints the remote address upon connection.
  - **Blocking I/O**: Like the server, this uses blocking reads—`read()` waits for data. For non-blocking, configure the channel accordingly.

- **Output**: When connected to the running server:
  ```
  Connected to the server: /127.0.0.1:8080
  DATE: Wed Oct 16 20:11:44 UTC 2025
  ```

## ⚠️ Limitations and Notes:
  - Assumes the server is running first; otherwise, it will throw a connection exception.
  - Reads until the connection closes (since the server closes after sending). No persistent connection.
  - Simple text output; in a real app, parse the date/time for further use.
  - Error Handling: Catches `IOException` for network issues.
  - For multiple messages, modify the loop, but here it's one-shot.