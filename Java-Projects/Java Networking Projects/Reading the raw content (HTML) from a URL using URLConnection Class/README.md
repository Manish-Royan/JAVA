# Java Networking - URLConnection Examples
↳ This repository contains practice examples from learning Java Networking concepts. Each case demonstrates a specific networking technique using built-in Java APIs.

## 🔖 Table of Contents
- [CASE-1: URLConnection with BufferedReader](#case-1-urlconnection-with-bufferedreader)
- [CASE-2: URLConnection with NIO Channels and ByteBuffer](#case-2-urlconnection-with-nio-channels-and-bytebuffer)

## \# [CASE-1](https://github.com/Manish-Royan/JAVA/tree/main/Java-Projects/Java%20Networking%20Projects/Reading%20the%20raw%20content%20(HTML)%20from%20a%20URL%20using%20URLConnection%20Class/%5BCASE-1%5D%20Prints%20the%20entire%20HTML%20content%20%20of%20given%20URL%20to%20the%20console) : URLConnection with BufferedReader
* Demonstrates basic URL connection and reading HTML content line-by-line using `BufferedReader`.
* **Key Concepts**: URL, URLConnection, InputStream, BufferedReader.
* **Usage**: Run `Main.java` to fetch and print the HTML from a URL.

## \# [CASE-2](https://github.com/Manish-Royan/JAVA/tree/main/Java-Projects/Java%20Networking%20Projects/Reading%20the%20raw%20content%20(HTML)%20from%20a%20URL%20using%20URLConnection%20Class/%5BCASE-2%5D%20Downloading%20website%20HTML%20content%20using%20Java's%20NIO%20(New%20IO)%20API) : URLConnection with NIO Channels and ByteBuffer
* Demonstrates efficient byte-level reading using NIO channels and buffers for downloading content in chunks.
* **Key Concepts**: URLConnection, InputStream, ReadableByteChannel, ByteBuffer.
* **Usage**: Run `Main.java` to fetch and print the HTML in 64-byte chunks.

## ☑️ Notes
- These examples use public URLs like *https://www.facebook.com* for demonstration. In practice, handle permissions and rate limits.
- For advanced networking, consider libraries like OkHttp or Apache HttpClient.
- 😊 Contributions welcome!