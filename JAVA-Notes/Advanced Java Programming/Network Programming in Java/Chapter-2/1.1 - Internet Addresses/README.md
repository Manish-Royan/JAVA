# Internet Addresses 

➡️ An Internet Address is a **unique identifier assigned to a device or service connected to a network**. It's how computers find and communicate with each other, much like how a postal address ensures a letter reaches the correct house and person.

➡️ An Internet address is the identifier used by the Internet Protocol (IP) to locate and route packets to a specific network interface on a host; it is either a **32‑bit number (IPv4)** or a **128‑bit number (IPv6)** and combined with a port identifies an endpoint for network communication. Java models these addresses with the `java.net.InetAddress` class and its subclasses `Inet4Address` and `Inet6Address`, which represent both the numeric address and, optionally, an associated hostname.

## 📑 There are several layers of addressing, each serving a different purpose.

### 1. The Physical Address: MAC Address
↳ Before we even get to the "Internet" part, every network-capable device has a physical address.

*   **What it is:** A **MAC (Media Access Control) Address** is a unique, 12-character hexadecimal number burned into the firmware of a Network Interface Card (NIC) by the manufacturer.
*   **Example:** `00:1A:2B:3C:4D:5E`
*   **Purpose:** It's used for communication between devices on the *same local network* (e.g., your laptop talking to your Wi-Fi router). A router uses MAC addresses to forward data packets to the correct device within its own local network.
*   **Analogy:** *Think of it as a person's name within a single household. It's essential for communication inside the house, but useless for sending a letter from another city.*

### 2. The Logical Address: IP Address
↳ This is the most fundamental address for communication across the internet. An IP (Internet Protocol) address identifies a device on a network.

#### 🔸**IPv4 (Internet Protocol version 4)**
→ This has been the standard for decades.

*   **What it is:** A 32-bit numerical address.
*   **Format:** Written as four numbers (octets) separated by periods. Each number ranges from 0 to 255.
*   **Example:** `172.217.16.14` (one of Google's addresses)
*   **Address Space:** It provides approximately 4.3 billion unique addresses. This seemed like a lot, but the rapid growth of the internet has led to address exhaustion.

**# Special IPv4 Addresses:**

*   **Public vs. Private IP:**
    *   **Public IP:** A globally unique address assigned by Internet Service Provider (ISP). This is our (ISP) address on the public internet.
    *   **Private IP:** Addresses used within a local network (your home or office). They are not routable on the public internet. This is why multiple devices can have the same private IP (e.g., `192.168.1.100`) as long as they are on different local networks. Common private ranges are:
        *   `10.0.0.0` to `10.255.255.255`
        *   `172.16.0.0` to `172.31.255.255`
        *   `192.168.0.0` to `192.168.255.255`
    *   **NAT (Network Address Translation):** Our router performs NAT, acting as an intermediary. It uses its single public IP to send and receive data for all the devices with private IPs on our local network.

*   **Loopback Address (`127.0.0.1`):** This special address always refers to the local machine itself (also known as `localhost`). It's used by developers for testing applications without needing a network connection.

#### 🔸**IPv6 (Internet Protocol version 6)**
→ This is the next generation, created to solve the IPv4 address shortage.

*   **What it is:** A 128-bit hexadecimal address.
*   **Format:** Written as eight groups of four hexadecimal digits, separated by colons.
*   **Example:** `2001:0db8:85a3:0000:0000:8a2e:0370:7334`
    *   It can be abbreviated. For instance, consecutive groups of zeros can be replaced with a double colon (`::`), so the above could be written as `2001:0db8:85a3::8a2e:0370:7334`.
*   **Address Space:** It provides 340 undecillion (3.4 x 10³⁸) addresses—an almost unimaginably vast number, ensuring we won't run out anytime soon.

### 3. The Human-Friendly Address: Domain Names
↳ Remembering `172.217.16.14` is difficult. Remembering `google.com` is easy.

*   **What it is:** A human-readable alias for an IP address.
*   **Example:** `github.com`, `Manish-Bishwakarma.github.io`
*   **DNS (Domain Name System):** This is the "phonebook of the internet." When we type a domain name into our browser, our computer queries a DNS server. The DNS server looks up the domain name and returns the corresponding IP address. Our browser then uses that IP address to connect to the web server.

### 4. The Service Address: Port Numbers
↳ An IP address gets a data packet to the correct *computer*. A **port number** gets it to the correct *application* on that computer.

*   **What it is:** A 16-bit number (0 to 65535) that identifies a specific process or service.
*   **Analogy:** If an IP address is the street address of an apartment building, the port number is the apartment number. It ensures your email goes to your email client and your web traffic goes to your web browser.
*   **Well-Known Ports (0-1023):** These are reserved for standard services.
    *   **Port 80:** HTTP (standard web traffic)
    *   **Port 443:** HTTPS (secure web traffic)
    *   **Port 22:** SSH (Secure Shell)
    *   **Port 25:** SMTP (for sending email)
    *   **Port 53:** DNS (Domain Name System service)
*   **Ports range**: 0–65535 because port is a 16-bit number.
    * Compute `2^16 - 1`:
        * `2^10 = 1024`.
        * `2^6 = 64`. 
        * `1024 × 64 = 65536`. (That is `2^16`.)
        * `Subtract 1 → 65536 - 1 = 65535`.
*   **Port categories**:
    *   `0–1023`: well-known (HTTP=80, HTTPS=443, SSH=22, etc.)
    *   `1024–49151`: registered
    *   `49152–65535`: ephemeral (commonly used as client-side ports)

### 📖 Summary: Putting It All Together
↳ When we write network programs in Java, we are primarily concerned with **IP addresses** and **port numbers**.

*   In Java, the `InetAddress` class is used to represent an IP address (both IPv4 and IPv6).
*   A **Socket** in programming is the combination of an IP address and a port number, representing one endpoint of a connection (e.g., `172.217.16.14:443`). 
*   A socket = IP address + transport protocol + port. Example: 192.168.1.5:443 over TCP.

### 🔁 The entire process looks like this:
1.  You type `github.com` into your browser.
2.  Your PC uses **DNS** to resolve `github.com` to an **IP address** (e.g., `140.82.121.4`).
3.  Your browser initiates a **TCP** connection to that IP address on **Port 443** (for HTTPS).
4.  Your router sends the request from your **Private IP** to the internet using its **Public IP**.
5.  On the local network, your router uses the **MAC address** of your device to ensure data gets to and from your specific machine.