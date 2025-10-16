# \# Java UDP Networking Examples
↳ This repository contains practice examples from learning Java Networking concepts, focusing on UDP (User Datagram Protocol) communication. Each example demonstrates connectionless networking using built-in Java APIs.

## 🔖 Table of Contents

[IMG]

- [UDP_SERVER: UDP Server with DatagramSocket](#udp_server-udp-server-with-datagramsocket)
- [UDP_CLIENT: UDP Client with DatagramSocket](#udp_client-udp-client-with-datagramsocket)


## ⚙️ Setup
- Ensure you have Java 8+ installed.
- Compile and run each class: `javac UDP_*.java && java UDP_*`
- For testing: Run the server first in one terminal, then the client in another.

## \# [UDP_SERVER](https://github.com/Manish-Royan/JAVA/tree/main/Java-Projects/Java%20Networking%20Projects/Simple%20Console%20UDP%20Client-Server%20Communication/Program/UDP%20Server): UDP Server with DatagramSocket
* Demonstrates a simple UDP server that listens for messages from clients and sends acknowledgments.
* **Key Concepts**: DatagramSocket, DatagramPacket, connectionless communication.
* **Usage**: Run `UDP_SERVER.java` to start the server on `port 8080`. **It waits for UDP packets and replies with message counts**.

## \# [UDP_CLIENT](https://github.com/Manish-Royan/JAVA/tree/main/Java-Projects/Java%20Networking%20Projects/Simple%20Console%20UDP%20Client-Server%20Communication/Program/UDP%20Client): UDP Client with DatagramSocket
* Demonstrates a UDP client that sends messages to the server and receives responses.
* **Key Concepts**: DatagramSocket, DatagramPacket, client-side UDP interaction.
* **Usage**: Run `UDP_CLIENT.java` after starting the server. Enter messages to send; type `'exit'` to quit. 

## ☑️ Notes
- UDP is connectionless and unreliable—packets may be lost. For guaranteed delivery, use TCP.
- These examples use local ports for demonstration. In practice, handle security and network configurations.
- For advanced networking, consider libraries like **Netty**.
- 😊 Contributions welcome!
