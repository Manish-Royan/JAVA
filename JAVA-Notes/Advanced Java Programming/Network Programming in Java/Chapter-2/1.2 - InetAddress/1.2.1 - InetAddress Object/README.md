# What Can You Do with an InetAddress Object❓

➡️ Once you have an `InetAddress` object, you can query it for information.

➡️ These Instance Methods operate on an InetAddress object. Most are boolean checks for address types, introduced in JDK 1.4 unless noted.
***

# [Address Retrieval Method](https://github.com/Manish-Royan/JAVA/tree/main/JAVA-Notes/Advanced%20Java%20Programming/Network%20Programming%20in%20Java/Chapter-2/1.2%20-%20InetAddress/%23%20More%20Depth%20Explorations/1.8%20MDE%20-%20Address%20Retrieval%20Method)
➡️ These address-retrieval functions on InetAddress are instance accessor methods (getters). They expose different representations or derived information about the InetAddress object without modifying it. Some are purely local, CPU-only accessors; others may perform network I/O (name service calls) and therefore have side effects (blocking, delays, errors).

## 1. `getAddress()`
* **Type and content**: A `byte[]` containing the raw IP bytes — 4 bytes for IPv4, 16 bytes for IPv6.
* **Order**: Network byte order (big-endian), so index 0 is the highest-order byte.
* **Purpose**: use it when you need the binary form (packing into network packets, comparing bytes, calculating checksums).
* **Safety**: the returned array is a copy; modifying it does not change the InetAddress instance. Always mask bytes to unsigned values when printing: (b & 0xFF).
```java
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

public class GetAddressDemo {
    public static void main(String[] args) {
        try {
            // IPv4 example
            InetAddress ipv4 = InetAddress.getByName("192.168.1.100");
            byte[] b4 = ipv4.getAddress(); // length == 4
            System.out.println("IPv4 getHostAddress(): " + ipv4.getHostAddress());
            System.out.println("IPv4 raw bytes (signed): " + Arrays.toString(b4));
            System.out.print("IPv4 octets (unsigned): ");
            for (int i = 0; i < b4.length; i++) {
                System.out.print((b4[i] & 0xFF) + (i < b4.length - 1 ? "." : ""));
            }
            System.out.println("\n");

            // IPv6 example (Google DNS)
            InetAddress ipv6 = InetAddress.getByName("2001:4860:4860::8888");
            byte[] b6 = ipv6.getAddress(); // length == 16
            System.out.println("IPv6 getHostAddress(): " + ipv6.getHostAddress());
            System.out.println("IPv6 raw bytes length: " + b6.length);
            System.out.println("IPv6 raw bytes (signed): " + Arrays.toString(b6));
            System.out.print("IPv6 bytes (unsigned hex): ");
            for (int i = 0; i < b6.length; i++) {
                System.out.printf("%02x", b6[i] & 0xFF);
                if (i % 2 == 1 && i < b6.length - 1) System.out.print(":");
            }
            System.out.println();

        } catch (UnknownHostException e) {
            System.err.println("Resolution failed: " + e.getMessage());
        }
    }
}
```

## 2. `getHostAddress()`
- **Type and content**: A String with the textual IP literal, e.g., "192.168.1.1" or "2001:db8::1".  
- **Deterministic and cheap**: It simply formats the stored address bytes; it never performs DNS lookups and therefore does not block on network I/O.  
- **Use cases**: logging, display, sending textual addresses over protocols, building socket connect strings.
```java
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

public class GetHostAddressDemo {
    public static void main(String[] args) {
        try {
            // IPv4 example
            InetAddress ipv4 = InetAddress.getByName("8.8.8.8"); // numeric literal -> no DNS
            System.out.println("IPv4 getHostAddress(): " + ipv4.getHostAddress()); // "8.8.8.8"

            // IPv6 example
            InetAddress ipv6 = InetAddress.getByName("2001:4860:4860::8888"); // Google's IPv6 DNS
            System.out.println("IPv6 getHostAddress(): " + ipv6.getHostAddress()); // maybe "2001:4860:4860::8888"

            // Demonstrate difference from getAddress() raw bytes
            byte[] bytes = ipv4.getAddress(); // length == 4
            System.out.println("IPv4 raw bytes (signed): " + Arrays.toString(bytes));
            // Convert raw bytes to unsigned octets and recreate dotted string (equivalent to getHostAddress())
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(bytes[i] & 0xFF);
                if (i < bytes.length - 1) sb.append(".");
            }
            System.out.println("Reconstructed dotted decimal: " + sb.toString());

        } catch (UnknownHostException e) {
            System.err.println("Address creation failed: " + e.getMessage());
        }
    }
}
/*Expected output (example)
    IPv4 getHostAddress(): 8.8.8.8
    IPv6 getHostAddress(): 2001:4860:4860::8888
    IPv4 raw bytes (signed): [8, 8, 8, 8]
    Reconstructed dotted decimal: 8.8.8.8
*/
```

## 3. `getHostName()` 
- **Behavior depends on how the InetAddress was created**:
  - If the instance was created with a hostname (e.g., via getByName("host.example")), getHostName() normally returns that name immediately (no network call).  
  - If the instance was created from a literal address (getByAddress or parsed literal) and no hostname was attached, calling getHostName() may trigger a reverse DNS lookup to obtain a hostname. That lookup may block and can be slow or fail.
- **Return value**: either the hostname (from creation or reverse lookup) or, if lookup fails or is disabled, often the textual IP address as a fallback.  
- **Performance note**: because it may block, avoid calling getHostName() on hot paths or use it asynchronously with timeouts.
```java
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.*;

public class GetHostNameDemo {
    public static void main(String[] args) {
        try {
            // 1) InetAddress created from hostname -> getHostName() returns quickly
            InetAddress fromName = InetAddress.getByName("www.google.com");
            System.out.println("fromName.getHostAddress(): " + fromName.getHostAddress());
            System.out.println("fromName.getHostName():    " + fromName.getHostName());
            System.out.println();

            // 2) InetAddress created from numeric literal -> getHostName() may trigger reverse DNS
            InetAddress fromLiteral = InetAddress.getByName("8.8.8.8"); // numeric literal
            System.out.println("fromLiteral.getHostAddress(): " + fromLiteral.getHostAddress());
            System.out.println("Calling getHostName() may perform a reverse DNS lookup and block");

            // 3) Safe reverse lookup with timeout to demonstrate blocking behavior
            ExecutorService ex = Executors.newSingleThreadExecutor();
            Callable<String> reverseLookup = () -> fromLiteral.getHostName();
            Future<String> future = ex.submit(reverseLookup);

            try {
                // wait up to 3 seconds for reverse DNS; adjust timeout as needed
                String host = future.get(3, TimeUnit.SECONDS);
                System.out.println("Reverse lookup result: " + host);
            } catch (TimeoutException te) {
                System.err.println("Reverse lookup timed out after 3 seconds; it was blocking");
                future.cancel(true);
            } catch (ExecutionException ee) {
                System.err.println("Reverse lookup failed: " + ee.getCause());
            } finally {
                ex.shutdownNow();
            }

            // 4) If you only need numeric IP, use getHostAddress() which never blocks
            System.out.println("Numeric IP without lookup: " + fromLiteral.getHostAddress());

        } catch (UnknownHostException e) {
            System.err.println("Creation failed: " + e.getMessage());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Interrupted");
        }
    }
} //NOTE: For GUIs or servers, never call getHostName() on the main thread. Prefer getHostAddress() when you only need the numeric IP string.
```

## 4. What `getCanonicalHostName()` does
- **Purpose**: obtain the fully qualified domain name (FQDN) for the address as determined by the name service.  
- **Always uses name resolution**: if no canonical name is known, it will attempt a reverse lookup (PTR) and possibly forward lookup to verify the canonical form; this can block and be slower than getHostName().  
- **Return value**: the FQDN if one is found; otherwise often the textual IP or the original hostname.  
- **When to use**: for logging or audit where you want the authoritative DNS name and are prepared to accept the latency or perform the call off the critical path.

```java
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.*;

public class CanonicalHostDemo {
    public static void main(String[] args) {
        try {
            // Example 1: InetAddress created from hostname (no extra lookup normally)
            InetAddress fromName = InetAddress.getByName("www.google.com");
            System.out.println("getHostAddress():         " + fromName.getHostAddress());
            System.out.println("getHostName():            " + fromName.getHostName());
            System.out.println("getCanonicalHostName():   " + fromName.getCanonicalHostName());
            System.out.println();

            // Example 2: InetAddress created from numeric literal -> canonical lookup may block
            InetAddress numeric = InetAddress.getByName("8.8.8.8"); // numeric literal
            System.out.println("Numeric IP: " + numeric.getHostAddress());
            System.out.println("Calling getCanonicalHostName() may perform a reverse DNS lookup and block");

            // Run the potentially blocking canonical lookup with a timeout
            ExecutorService ex = Executors.newSingleThreadExecutor();
            Future<String> f = ex.submit(() -> numeric.getCanonicalHostName());

            try {
                // wait up to 4 seconds for reverse lookup
                String canonical = f.get(4, TimeUnit.SECONDS);
                System.out.println("Canonical name: " + canonical);
            } catch (TimeoutException te) {
                System.err.println("Reverse lookup timed out after 4 seconds; operation was blocking");
                f.cancel(true);
            } catch (ExecutionException ee) {
                System.err.println("Reverse lookup failed: " + ee.getCause());
            } finally {
                ex.shutdownNow();
            }

        } catch (UnknownHostException e) {
            System.err.println("Failed to create InetAddress: " + e.getMessage());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Interrupted");
        }
    }
}
```

---

### ☑️ Practical differences and consequences

- `getAddress()` is binary, suitable for low-level network code, comparisons, or serialization.  
- `getHostAddress()` is non-blocking and purely local formatting of stored bytes.  
- `getHostName()` may be non-blocking (if a hostname was provided earlier) or blocking (if it triggers reverse DNS). Do not assume it is cheap.  
- `getCanonicalHostName()` is the most expensive of the four because it actively attempts to resolve canonical DNS names; treat it as a network operation.

### 📌 Full example program demonstrating all four methods

```java
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

public class InetAddressRetrievalDemo {
    public static void main(String[] args) {
        try {
            // 1) From hostname (may have been resolved previously)
            InetAddress fromName = InetAddress.getByName("example.com");

            // 2) From textual IP literal (no DNS at creation)
            InetAddress fromLiteral = InetAddress.getByName("8.8.8.8");

            // 3) From raw bytes (IPv4)
            byte[] ipv4 = new byte[] {(byte)192, (byte)168, 1, 101};
            InetAddress fromBytes = InetAddress.getByAddress(ipv4);

            printInfo("fromName", fromName);
            printInfo("fromLiteral", fromLiteral);
            printInfo("fromBytes", fromBytes);

            // Demonstrate potential blocking: reverse lookups (do cautiously in real apps)
            System.out.println("Demonstrating reverse lookups (may block)...");
            System.out.println("  fromLiteral.getHostName(): " + fromLiteral.getHostName());
            System.out.println("  fromLiteral.getCanonicalHostName(): " + fromLiteral.getCanonicalHostName());

        } catch (UnknownHostException e) {
            System.err.println("Resolution error: " + e.getMessage());
        }
    }

    private static void printInfo(String label, InetAddress addr) {
        System.out.println("---- " + label + " ----");
        // raw bytes
        byte[] raw = addr.getAddress();
        System.out.println("getAddress() bytes: " + Arrays.toString(raw));

        // print bytes as unsigned values
        System.out.print("getAddress() unsigned: ");
        for (int i = 0; i < raw.length; i++) {
            System.out.print((raw[i] & 0xFF) + (i < raw.length - 1 ? "." : ""));
        }
        System.out.println();

        // textual IP
        System.out.println("getHostAddress(): " + addr.getHostAddress());

        // hostname - may trigger reverse lookup if not present
        System.out.println("getHostName(): " + addr.getHostName());

        // canonical hostname - will attempt canonical resolution
        System.out.println("getCanonicalHostName(): " + addr.getCanonicalHostName());

        System.out.println();
    }
}
```
---