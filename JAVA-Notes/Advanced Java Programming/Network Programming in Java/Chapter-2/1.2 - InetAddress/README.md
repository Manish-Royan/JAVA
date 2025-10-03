# InetAddress Class

## 📚 Overview 

➡️ In Java, the `InetAddress` class is the primary abstraction for representing an IP (Internet Protocol) address. The `InetAddress` abstraction represents a network destination, encapsulating both `namesandnumericaladdress` information. 

#### 💭 Ananlogy: Think of InetAddress as a digital contact card for any device on a network (like a server, your phone, or another computer). This contact card holds two key pieces of information:
*   **Hostname**: The human-readable name (e.g., `www.google.com`).
*   **IP Address**: The numerical address used by computers (e.g., `142.250.190.78`).

➡️ This class is an "**abstraction**" because it simplifies things for the programmer. We don't have to worry about the details of whether the address is the older **IPv4** (like **192.168.1.5**) or the newer **IPv6**. Java handles that behind the scenes with its subclasses, `Inet4Address` and `Inet6Address`.

## 📑 Introduction to `java.net.InetAddress` Class

➡️ The InetAddress class in Java is a central building block for all network communication in the Java ecosystem. It serves as the primary abstraction for dealing with **IP addresses** and **hostnames**, (— not a **socket** or **port**) providing a wide array of capabilities for `IP address` and `DNS resolution`, `reverse lookups`, `network interface management`, and integration with other networking APIs such as `Socket`, `ServerSocket`, and newer `non-blocking IO` networking frameworks. 

➡️ Understanding `InetAddress` in both practical and conceptual detail is essential for effective Java networking at any scale—from **simple client-server programs to large-scale, production-grade distributed systems**.

## 🗝️ Key Characteristics of `InetAddress`
➡️ The key characteristics of Java's `InetAddress` class are that it provides an **immutable abstraction** for an IP address, **has no public constructors**, and is created using **static factory methods** that often perform a network lookup.

### 1. **Immutability**: 
➡️ Once an InetAddress object is created, its value (the IP address it represents) cannot be changed. This makes it inherently thread-safe and safe to use as keys in hashmaps.

### 🔒 What Does “Immutable” Mean in Java❓
↳ In Java, immutable means unchangeable after creation. Once an object is created, its internal state (its data) cannot be modified. This is a powerful concept used in many core classes like `String`, `Integer`, and yes—`InetAddress`.

### Q. Why is `InetAddress` Immutable❓
↳ Once you create an  object, it locks in the IP address it represents. You cannot change it to point to a different address later. For example:
```java
InetAddress address = InetAddress.getByName("google.com");
// This object will always refer to Google's IP address.
// You can't change it to "yahoo.com" later.
```
### ⚙️ Core Idea of `InetAddress` Immutability
↳ An `InetAddress` object is immutable means: after we obtain an `InetAddress` instance, the IP address (and the hostname value held by that instance) will not change for the lifetime of that object. You cannot mutate fields inside that instance to make it represent a different IP or hostname. If we need a different address we obtain (create) a new InetAddress.
```java
InetAddress google = InetAddress.getByName("google.com");
InetAddress yahoo = InetAddress.getByName("yahoo.com");
// Each object is separate and immutable.
```

### Q. Why the designers made it immutable❓
*   **Thread safety**: Immutable objects can be freely shared between threads without synchronization because their state cannot change. This makes it inherently thread-safe and safe to use as keys in hashmaps.
* 	**Predictability**: Code that stores or logs an InetAddress can rely on the value staying the same across calls.
* 	**Security and integrity**: An address object cannot be tampered with by some other component in the program to redirect network traffic or corrupt lookups.
* 	**Easier reasoning**: APIs and data structures using InetAddress (maps, sets, caches) behave correctly because equals() / hashCode() are stable.

### Q. What immutability does — practical consequences
*	**DNS resolution vs. object identity**: Resolving a hostname gives you an InetAddress tied to the IP resolved at that moment. If the hostname’s DNS record changes later, your existing InetAddress still represents the original IP; you must re-resolve the hostname to get the new IP.
*	**Reuse**: you can store an InetAddress in collections or reuse one across socket calls without worrying it will change mid-operation.
*	**Logging and auditing**: logs that include an InetAddress remain accurate for that call/context because the object won’t morph into a different address

***

### 2. **No Public Constructors**: 
➡️ We cannot create an instance using `new InetAddress()`. This is a deliberate design choice because creating an InetAddress object from a hostname may require a network operation (a DNS lookup) which can be slow, block, or fail. Instead, you use **static factory methods**.

***

### 3. **Abstraction for IP Addresses** 🌐
➡️ `InetAddress` is a high-level representation of an IP address. It hides the underlying details of whether the address is an older **IPv4** or a newer **IPv6** address. Each object encapsulates both the numerical IP address (e.g., `142.250.66.78`) and potentially its hostname (e.g., `www.google.com`), providing a unified way to work with network destinations.

***

### 4. Network-Dependent Creation
➡️ Creating an InetAddress object is often dependent on your network connection. When you call `getByName()`, Java performs a DNS (Domain Name System) lookup to find the corresponding IP address. If the hostname cannot be found or your device is offline, this method will fail by throwing an `UnknownHostException`. This characteristic highlights that **InetAddress is not just a data container but an active participant in network communication**.


## 🚦Creation of InetAddress Instances
➡️ Since `InetAddress` doesn't have any **public constructor**, `InetAddress` objects are created via  (constructors are protected) [**static factory methods**](https://github.com/Manish-Royan/JAVA/tree/main/JAVA-Notes/Advanced%20Java%20Programming/Network%20Programming%20in%20Java/Chapter-2/1.2%20-%20InetAddress/1.2.1%20-%20What%20are%20Static%20Factory%20Methods#what-are-the-static-factory-methods-in-java). These methods can throw `UnknownHostException` if resolution fails or `NullPointerException` for null inputs.

### 1. `getByName(String host)`
* This is the most common method. It determines the **IP address of a host**, given the host's name. 
* `InetAddress.getByName(String host)` resolves the given host string into a single InetAddress instance. The host parameter may be:
    * A hostname (for example "**www.google.com**") — A DNS lookup is attempted and one IP address is returned.
    #### 📌Example:
    ```java
    import java.net.InetAddress; // Import the InetAddress class from java.net package
    import java.net.UnknownHostException; //for handling unknown host exceptions
    public class DEMO {
        public static void main(String[] args) {
            try {
                // Case 1: Hostname (DNS lookup is performed)
                String hostname = "www.google.com";
                InetAddress addressFromHostname = InetAddress.getByName(hostname);
                System.out.println("Hostname: " + hostname);
                System.out.println("Resolved IP: " + addressFromHostname.getHostAddress());
            } catch (UnknownHostException e) {
                System.err.println("Host resolution failed: " + e.getMessage());
            }
        }
    }
    ```

    * A textual IP literal (for example "**192.168.0.10**" or an IPv6 literal) — no DNS lookup is required, the address format is validated and an InetAddress representing that literal is returned.
    #### 📌Example:
    ```java
    import java.net.InetAddress;
    import java.net.UnknownHostException;
    public class DEMO {
        public static void main(String[] args) {
            try {
                // Case 2: IP literal (no DNS lookup, just format validation)
                String ipLiteral = "192.168.0.10";
                InetAddress addressFromIP = InetAddress.getByName(ipLiteral);
                System.out.println("IP Literal: " + ipLiteral);
                System.out.println("Resolved IP: " + addressFromIP.getHostAddress());
            } catch (UnknownHostException e) {
                System.err.println("Host resolution failed: " + e.getMessage());
            }
        }
    }
    ```
* **Process**: If you provide a hostname, it performs a DNS lookup. This is a [**blocking network operation**](https://github.com/Manish-Royan/JAVA/tree/main/JAVA-Notes/Advanced%20Java%20Programming/Network%20Programming%20in%20Java/Chapter-2/1.2%20-%20InetAddress/1.2.2%20-%20What%20is%20Blocking%20Network%20Operation). If the host cannot be found, it throws an `UnknownHostException`.

* **Return**: `InetAddress.getByName()` returns a single InetAddress object.
