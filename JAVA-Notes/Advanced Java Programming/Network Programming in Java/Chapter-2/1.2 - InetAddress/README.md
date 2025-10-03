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
***

# 🚦Creation of InetAddress Instances
➡️ Since `InetAddress` doesn't have any **public constructor**, `InetAddress` objects are created via  (constructors are protected) [**static factory methods**](https://github.com/Manish-Royan/JAVA/tree/main/JAVA-Notes/Advanced%20Java%20Programming/Network%20Programming%20in%20Java/Chapter-2/1.2%20-%20InetAddress/1.2.1%20-%20What%20are%20Static%20Factory%20Methods#what-are-the-static-factory-methods-in-java). These methods can throw `UnknownHostException`, a subclass of `IOException`; if resolution fails or `NullPointerException` for null inputs.

## 1. `getByName(String host)`
* This is the most common method. It determines the **IP address of a host**, given the host's name. 
* `InetAddress.getByName(String host)` resolves the given host string into a single InetAddress instance. The host parameter may be:
    ### 1. A hostname (for example "**www.google.com**") — A DNS lookup is attempted and one IP address is returned.
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

    ### 2. A textual IP literal (for example "**192.168.0.10**" or an IPv6 literal) — no DNS lookup is required, the address format is validated and an InetAddress representing that literal is returned.
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

* **Return**: [`InetAddress.getByName()` returns a single InetAddress object](https://github.com/Manish-Royan/JAVA/tree/main/JAVA-Notes/Advanced%20Java%20Programming/Network%20Programming%20in%20Java/Chapter-2/1.2%20-%20InetAddress/1.2.3%20-%20%20Why%20One%20Name%20Can%20Have%20Many%20Addresses). 

### 🗝️ Key behaviors and consequences
* **Single-address result**: `getByName` returns one InetAddress. A host that has multiple A/AAAA records may return any one of them; use `getAllByName` to obtain all addresses.
* **Time of resolution**: the call resolves at the moment you invoke it. Later DNS changes do not affect the existing InetAddress instance. To pick up DNS changes you must call `getByName` (or `getAllByName`) again.
* **Literal IPs accepted**: when you pass an IP string, Java checks format and returns the corresponding InetAddress without performing DNS resolution.
* **Exceptions**: `UnknownHostException` signals the host name could not be resolved or the literal was invalid. Catch and handle it.
* **Caching**: JVM and platform DNS **caching/policies** may affect how often lookups actually go to the network; code should not rely on automatic re-resolution unless you explicitly re-query.
* **IPv4 and IPv6**: `getByName` supports both address families. The concrete returned object may be an Inet4Address or Inet6Address.


## 2. `getAllByName(String host)`
* A single hostname can be associated with multiple IP addresses for load balancing or redundancy. This method retrieves all of them.
* `InetAddress.getAllByName(String host)` returns an array of InetAddress objects containing every IP address that the platform name service associates with the supplied host name or literal IP string. The host parameter may be:
    ### 1. A machine name (for example ***www.google.com***)
    ```java
        import java.net.InetAddress;
        import java.net.UnknownHostException;

        public class InetAddressAllByNameDemo {
            public static void main(String[] args) {
                try {
                    // Case 1: Hostname (DNS lookup is performed)
                    String hostname = "www.google.com";
                    InetAddress[] addressesFromHostname = InetAddress.getAllByName(hostname);
                    System.out.println("Hostname: " + hostname);
                    for (InetAddress addr : addressesFromHostname) {
                        System.out.println("Resolved IP: " + addr.getHostAddress());
                    }
                    System.out.println();
                } catch (UnknownHostException e) {
                    System.err.println("Host resolution failed: " + e.getMessage());
                }
            }
        }
    ```

    ### 2. A textual IP literal (for example ***8.8.8.8*** or an ***IPv6 literal***); if a literal is supplied, the method validates the format and returns the corresponding address(es) without performing DNS lookups.
    ```java
        import java.net.InetAddress;
        import java.net.UnknownHostException;

        public class InetAddressAllByNameDemo {
            public static void main(String[] args) {
                try {
                   // Case 2: IP literal (no DNS lookup, just format validation)
                    String ipLiteral = "8.8.8.8"; // Google's public DNS server
                    InetAddress[] addressesFromIP = InetAddress.getAllByName(ipLiteral);
                    System.out.println("IP Literal: " + ipLiteral);
                    for (InetAddress addr : addressesFromIP) {
                        System.out.println("Resolved IP: " + addr.getHostAddress());
                    }
                } catch (UnknownHostException e) {
                    System.err.println("Host resolution failed: " + e.getMessage());
                }
            }
        }
    ```
### Q. Why to use `getAllByName`❓
- To discover all A and AAAA records for a hostname so your client can try multiple endpoints for load‑balancing or failover.  
- To obtain every IP a host might respond from (useful when a service is published on several machines or interfaces).  
- To compare or iterate addresses when one address may be unreachable or undesired (for example prefer IPv6 when available).

### 🗝️ Key behaviors
- [The returned value is an array `InetAddress[]`;](https://github.com/Manish-Royan/JAVA/tree/main/JAVA-Notes/Advanced%20Java%20Programming/Network%20Programming%20in%20Java/Chapter-2/1.2%20-%20InetAddress/1.2.4%20-%20Returning%20Array%20of%20Multiple%20IP%20Address) each element is an immutable `InetAddress` representing one specific IP address.  
    ```java
    try {
        InetAddress[] addresses = InetAddress.getAllByName("www.google.com");
        for (InetAddress addr : addresses) {
            System.out.println("Found address: " + addr.getHostAddress());
        }
    } catch (UnknownHostException e) {
        System.err.println("Could not find host.");
    }
    ```
- If the host cannot be resolved to at least one address, the method throws `UnknownHostException`.  
- DNS and OS resolver behavior affect which addresses are returned and their order; the first element is typically the address `getByName()` would return, but `getAllByName()` exposes all available records.  
- Use `getAllByName()` when your application must consider multiple IPs; use `getByName()` when a single address suffices and you prefer simpler code.


### 📌 Demonstrating a Simple Program
```java
import java.net.InetAddress;
import java.net.UnknownHostException;

public class GetAllByNameDemo {
    public static void main(String[] args) {
        String host = "www.google.com";

        try {
            InetAddress[] addresses = InetAddress.getAllByName(host);

            System.out.println("Resolved addresses for: " + host);
            for (int i = 0; i < addresses.length; i++) {
                InetAddress addr = addresses[i];
                System.out.printf("  [%d] hostName=%s  hostAddress=%s  isLoopback=%b%n",
                        i,
                        addr.getHostName(),
                        addr.getHostAddress(),
                        addr.isLoopbackAddress());
            }
        } catch (UnknownHostException e) {
            System.err.println("Could not resolve host: " + host + " -> " + e.getMessage());
        }
    }
}
```

### Q. When to re-resolve and when to reuse❓
- Re-resolve (call `getAllByName` again) when you need the latest DNS set (DNS can change over time).  
- Resolve once and reuse the returned array when you need a stable snapshot for a short period and want to avoid repeated lookups.


## 3. Reverse lookup by IP address
➡️ A reverse DNS lookup resolves an IP address back to a hostname. In Java you perform this by creating an InetAddress for the IP and asking it for its host name. The JVM will ask the configured name service (DNS) for a PTR record that maps the IP to a domain name. If a PTR exists you get a hostname, otherwise the lookup often returns the IP itself or a best-effort name the resolver knows.

### 🗝️ Key points and differences
- Use `InetAddress.getByName(ip)` to produce an `InetAddress` from an IP literal.  
- Use `getCanonicalHostName()` to request the fully qualified name returned by the resolver.  
- Use `getHostName()` which may return the name provided when the InetAddress was created or perform a reverse lookup if needed.  
- If no reverse (PTR) record exists, the result may be the textual IP or a provider-specific name.  
- Reverse lookups can be slow, unreliable, or blocked by firewalls and many IPs do not have PTR records.  
- Always handle `UnknownHostException` and consider caching or doing lookups asynchronously for bulk operations.
### 📌 Demonstrating a Simple Program
```java
import java.net.InetAddress;
import java.net.UnknownHostException;

public class ReverseLookupDemo {
    public static void main(String[] args) {
        String ip = "8.8.8.8";
        try {
            InetAddress addr = InetAddress.getByName(ip);

            // getHostName may return the hostname or the IP depending on resolver behavior
            String hostFromGetHostName = addr.getHostName();

            // getCanonicalHostName asks the name service for the canonical (full) name
            String canonical = addr.getCanonicalHostName();

            System.out.println("IP: " + ip);
            System.out.println("getHostName(): " + hostFromGetHostName);
            System.out.println("getCanonicalHostName(): " + canonical);
        } catch (UnknownHostException e) {
            System.err.println("Reverse lookup failed for " + ip + ": " + e.getMessage());
        }
    } // Test with known IPs (public DNS servers, your local machines) to see different behaviors.
}
```


## 4. `getLocalHost()`
➡️ `InetAddress.getLocalHost` returns an InetAddress that represents the local host as known to the JVM and operating system. The method asks the system for the local host name and then resolves that name into one or more IP addresses, returning a single InetAddress instance derived from that resolution.

### 🔁 Process:
* It attempts to determine the hostname of the machine and then resolves that name to an IP. This can sometimes involve a network lookup and may fail, throwing an `UnknownHostException`.
```java
    try {
        InetAddress localAddress = InetAddress.getLocalHost();
        System.out.println("Local Host: " + localAddress.getHostName());
        System.out.println("Local IP: " + localAddress.getHostAddress());
    } catch (UnknownHostException e) {
        System.err.println("Could not determine local address.");
    }
```

### 🗝️ Key behaviors
- The returned `InetAddress` normally contains the machine’s primary hostname and an IP address that the OS/configuration reports for the host.  
- If the OS hostname cannot be resolved by the JVM’s name service, `getLocalHost` can throw `UnknownHostException`.  
- JVM resolver logic and local configuration (hosts file, DNS, network interfaces) determine the exact **hostname/IP** returned, and JVM caching may affect subsequent lookups.

### ☑️ Common uses
- Displaying or logging the local machine’s hostname and IP address.  
- Using the local address for binding sockets or choosing which local identity to present to peers.  
- Health checks and diagnostics that need the machine identity.

### 📌 Demonstrating a Simple example program
```java
import java.net.InetAddress;
import java.net.UnknownHostException;

public class LocalHostDemo {
    public static void main(String[] args) {
        try {
            InetAddress local = InetAddress.getLocalHost();

            System.out.println("Host name: " + local.getHostName());
            System.out.println("Canonical name: " + local.getCanonicalHostName());
            System.out.println("Host address: " + local.getHostAddress());
            System.out.println("ToString: " + local.toString());
        } catch (UnknownHostException e) {
            System.err.println("Failed to determine local host: " + e.getMessage());
        }
    }
}
/*
Example output (typical)
Host name: DESKTOP-EXAMPLE  
Canonical name: DESKTOP-EXAMPLE.example.com  
Host address: 192.168.1.12  
ToString: DESKTOP-EXAMPLE/192.168.1.12
*/
```

### Practical tips
- For multi‑NIC servers choose addresses explicitly using `NetworkInterface.getNetworkInterfaces()` when you need a specific interface address.  
- Use `getCanonicalHostName` when you want the fully qualified domain name resolved by the name service.
