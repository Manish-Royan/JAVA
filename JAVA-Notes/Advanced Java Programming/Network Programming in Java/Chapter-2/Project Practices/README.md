# 💡 Project Ideas Based on Java Networking InetAddress Knowledge

➡️ On Chapter-2, we learnt Java Networking InetAddress covering foundational networking concepts including:

1. InetAddress fundamentals and creation methods
2. NetworkInterface class for working with network interfaces
3. IP address classification and properties
4. DNS lookups and resolution
5. Basic reachability testing

### 🔖 Here are several project ideas that would leverage our current knowledge without requiring concepts we haven't learned yet:

## 1. 🔍 Network Discovery Tool

* **Description:** Create an application that scans your local network to discover active devices.
* **Features:**
    - Ping all possible IP addresses in your subnet to find active hosts
    - Display hostnames (if available) and IP addresses of discovered devices
    - Show additional information like if addresses are local, multicast, etc.
    - Allow filtering by address types (site-local, link-local, etc.)
* **Skills Used:** InetAddress creation, `isReachable()`, hostname resolution, address type checking.
