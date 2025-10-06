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

### 📌 Demonstrating getAddress demo

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

### 📌 Determining whether an IP address is v4 or v6

```java
// File: AddressTestsDemo.java
import java.net.InetAddress;
import java.net.Inet6Address;
import java.net.UnknownHostException;
import java.util.Arrays;

public class AddressTestsDemo {

    /**
     * Basic version detection by raw byte length:
     *  - 4  -> IPv4
     *  - 16 -> IPv6
     *  - -1 -> unknown/invalid
     */
    public static int getVersion(InetAddress ia) {
        if (ia == null) return -1;
        byte[] address = ia.getAddress();
        if (address == null) return -1;
        if (address.length == 4) return 4;
        else if (address.length == 16) return 6;
        else return -1;
    }

    /**
     * Enhanced detection: treat IPv4-mapped IPv6 addresses ( ::ffff:a.b.c.d ) as IPv4.
     * Returns 4 or 6, or -1 if unknown.
     */
    public static int getLogicalVersion(InetAddress ia) {
        int ver = getVersion(ia);
        if (ver == 4) return 4;
        if (ver == 6) {
            // check for IPv4-mapped IPv6: first 10 bytes = 0, next 2 bytes = 0xFF 0xFF
            byte[] b = ia.getAddress();
            boolean mapped = true;
            for (int i = 0; i < 10; i++) {
                if (b[i] != 0) { mapped = false; break; }
            }
            if (mapped && (b[10] == (byte)0xFF) && (b[11] == (byte)0xFF)) return 4;
            return 6;
        }
        return -1;
    }

    private static void show(String label, String input) {
        try {
            InetAddress ia = InetAddress.getByName(input);
            System.out.printf("%-30s -> %s%n", label, ia.toString());
            System.out.printf("  getAddress() bytes: %s%n", Arrays.toString(ia.getAddress()));
            System.out.printf("  getVersion(): %d%n", getVersion(ia));
            System.out.printf("  getLogicalVersion(): %d%n", getLogicalVersion(ia));
            System.out.println();
        } catch (UnknownHostException e) {
            System.err.printf("%-30s -> resolution failed: %s%n%n", label, e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Examples to demonstrate behavior
        show("IPv4 literal", "192.168.1.100");
        show("Public IPv4 (Google DNS)", "8.8.8.8");
        show("IPv6 literal", "2001:4860:4860::8888");
        show("IPv4-mapped IPv6 literal", "::ffff:192.168.1.100");
        show("Loopback literal IPv4", "127.0.0.1");
        show("Loopback literal IPv6", "::1");
        // Hostname example (may resolve to IPv4 or IPv6 depending on environment)
        show("Hostname (www.google.com)", "www.google.com");
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

## 4. What [`getCanonicalHostName()`](https://github.com/Manish-Royan/JAVA/tree/main/JAVA-Notes/Advanced%20Java%20Programming/Network%20Programming%20in%20Java/Chapter-2/1.2%20-%20InetAddress/%23%20More%20Depth%20Explorations/1.9%20MDE%20-%20getCanonicalHostName) does
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

# Equality and hashing
➡️ Equality (equals) and hashing (hashCode) are instance methods used to define object identity and enable efficient lookup in hash-based collections. Their purposes:
* `equals(Object)`: decide whether two objects are logically equivalent according to the class’s equality semantics.
* `hashCode()`: produce an `int` fingerprint consistent with equals so objects can be partitioned into buckets in hash tables (`HashMap`, `HashSet`). They are not accessors or mutators; they are behavioral contracts that affect correctness and performance of collections, caching, and any code that compares objects by value rather than by reference.

### ☑️ The `equals` / `hashCode` contract (precise rules)
*   **Reflexive**: `a.equals(a)` must be `true`.
*   **Symmetric**: `a.equals(b) == b.equals(a)`.
*   **Transitive**: if `a.equals(b)` and `b.equals(c)` then `a.equals(c)`.
*   **Consistent**: repeated calls return the same result while objects are unchanged.
*   **Non-nullity**: `a.equals(null)` must return `false`.
*   **Hash consistency**: if `a.equals(b)` is true then `a.hashCode() == b.hashCode()`.
*   **Hash non-uniqueness allowed**: if `a.equals(b)` is `false`, hash codes may still collide but should be distributed well for performance.
### ⚠️ Violating these breaks collections (lost entries, incorrect contains/lookup behavior) and makes caching unreliable.

### \# Equality and hashing for InetAddress (special notes)
➡️ InetAddress overrides equals/hashCode to compare the actual IP address bytes, not instance identity; two InetAddress objects representing the same IP will be equal and share the same hashCode. Because InetAddress is immutable, it is safe to use as a `HashMap`/`HashSet` key.
### ⚠️ Beware: InetAddress equality is byte-based and not necessarily name-based; InetAddress.`getHostName()` differences do not affect equals/hashCode.

### 📌 Comparing InetAddress objects (shows equals/hashCode semantics)
```java
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

public class InetAddressEqualityDemo {
    public static void main(String[] args) throws Exception {
        InetAddress a = InetAddress.getByName("8.8.8.8");
        InetAddress b = InetAddress.getByAddress(new byte[] {(byte)8, (byte)8, (byte)8, (byte)8});

        System.out.println("a: " + a + "  hash=" + a.hashCode());
        System.out.println("b: " + b + "  hash=" + b.hashCode());
        System.out.println("a.equals(b): " + a.equals(b));

        Map<InetAddress, String> map = new HashMap<>();
        map.put(a, "Google DNS");

        System.out.println("map.get(b): " + map.get(b)); // works because a.equals(b) and hashCodes match
    }
}
```

### Overriding equals/hashCode for a value class
```java
import java.util.Objects;

public final class Endpoint {
    private final String host;
    private final int port;

    public Endpoint(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Endpoint)) return false;
        Endpoint other = (Endpoint) o;
        return port == other.port && Objects.equals(host, other.host);
    }

    @Override
    public int hashCode() {
        return Objects.hash(host, port);
    }

    @Override
    public String toString() {
        return host + ":" + port;
    }
}
```

## 1. equals(Object obj)
➡️ Equals is an instance equality method that defines logical equivalence for objects. For InetAddress, equals compares the raw IP bytes (4 bytes for IPv4, 16 for IPv6) and ignores any hostname string. Use equals when you need to test whether two address objects represent the same network endpoint.

### 📌 Example: Compare two InetAddress objects that represent the same IP but were created differently.
```java
import java.net.InetAddress;

public class EqualsDemo {
    public static void main(String[] args) throws Exception {
        InetAddress a = InetAddress.getByName("8.8.8.8"); // resolved from literal
        InetAddress b = InetAddress.getByAddress(new byte[] { (byte)8, (byte)8, (byte)8, (byte)8 }); // raw bytes

        System.out.println("a.getHostAddress(): " + a.getHostAddress());
        System.out.println("b.getHostAddress(): " + b.getHostAddress());

        // equals checks raw bytes; hostnames are ignored
        System.out.println("a.equals(b): " + a.equals(b)); // expected true
    }
}
```

## 2. hashCode()
➡️ hashCode is an instance method that returns an int used by hash-based collections. For InetAddress the hashCode is derived from the address bytes so that two InetAddress objects that are equal (same IP bytes) produce the same hash. Use hashCode when you need stable keys for HashMap, HashSet, or caching keyed by addresses.

### 📌 Example: Put an InetAddress into a `HashMap` and retrieve it with an equivalent InetAddress created from bytes.
```java
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

public class HashCodeDemo {
    public static void main(String[] args) throws Exception {
        InetAddress keyA = InetAddress.getByName("8.8.8.8");
        InetAddress keyB = InetAddress.getByAddress(new byte[] { (byte)8, (byte)8, (byte)8, (byte)8 });

        Map<InetAddress, String> map = new HashMap<>();
        map.put(keyA, "Google DNS");

        System.out.println("keyA.hashCode(): " + keyA.hashCode());
        System.out.println("keyB.hashCode(): " + keyB.hashCode());
        System.out.println("map.get(keyB): " + map.get(keyB)); // retrieves "Google DNS"
    }
}
```

## 3. toString()
➡️ toString is an instance formatting method that returns a human-friendly representation. For InetAddress the typical format is "hostname / literal-address" or "address" when no hostname is attached. Use toString for logging and debugging where you want both name and address information in one string.

### 📌 Example: Show toString output for addresses created from a hostname and from raw bytes.
```java
import java.net.InetAddress;

public class ToStringDemo {
    public static void main(String[] args) throws Exception {
        InetAddress fromName = InetAddress.getByName("www.google.com");      // has hostname
        InetAddress fromLiteral = InetAddress.getByName("8.8.8.8");         // literal, hostname may be IP

        System.out.println("fromName.toString():    " + fromName.toString());    // usually "hostname/1.2.3.4"
        System.out.println("fromLiteral.toString(): " + fromLiteral.toString()); // often "8.8.8.8"
    }
}
```

