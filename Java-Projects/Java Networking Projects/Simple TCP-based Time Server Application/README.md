# Java Time Server Application

This repository contains a simple TCP-based Time Server and Client using Java NIO APIs. The server sends the current date and time to connecting clients.

<img width="1819" height="316" alt="Screenshot 2025-10-17 022035" src="https://github.com/user-attachments/assets/0c95653c-94a5-4bb1-99e2-486493f2b736" />

## ⚙️ Setup
- Ensure you have Java 8+ installed.
- Run: Start the server first (`java TIME_SERVER`), then the client (`java CLIENT_SIDE`) in separate terminals.

## \# TIME_SERVER: TCP Time Server with NIO
* Demonstrates a TCP server that sends the current date and time to clients.
* **Key Concepts**: ServerSocketChannel, SocketChannel, ByteBuffer.
* **Usage**: Run to listen on `port 8080` and handle client connections.

## \# CLIENT_SIDE: TCP Time Client with NIO
* Demonstrates a TCP client that connects to the server and receives the time.
* **Key Concepts**: SocketChannel, ByteBuffer, reading from channel.
* **Usage**: Run after starting the server to fetch and display the current time.

## ☑️ Notes
- Uses blocking NIO for simplicity. For advanced use, add non-blocking with selectors.
- Assumes **localhost**; modify for remote servers.
- No authentication or error recovery included.
- 😊 Contributions welcome!
