# What Can You Do with an InetAddress Object❓

➡️ Once you have an `InetAddress` object, you can query it for information.

➡️ These Instance Methods operate on an InetAddress object. Most are boolean checks for address types, introduced in JDK 1.4 unless noted.


# \# [Address Retrieval Method](https://github.com/Manish-Royan/JAVA/tree/main/JAVA-Notes/Advanced%20Java%20Programming/Network%20Programming%20in%20Java/Chapter-2/1.2%20-%20InetAddress/%23%20More%20Depth%20Explorations/1.8%20MDE%20-%20Address%20Retrieval%20Method)
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

```java
import java.net.*;

public class getCanonical {
    public static void main (String[] args) throws UnknownHostException {
        InetAddress ia = InetAddress.getByName("104.21.79.8");
        System.out.println(ia.getCanonicalHostName());
    }
}
```

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


# \# Equality and hashing
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

### 📌 Overriding equals/hashCode for a value class
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

# \# Type checks (Unicast / Multicast / Special)
➡️ These methods on InetAddress are predicate methods (**boolean instance queries**). They examine the stored address bytes and return true/false about category or properties of the address. They are pure, local, deterministic checks that do not perform network I/O or DNS lookups and therefore are safe, cheap, and thread‑safe to call on hot paths.

➡️ These methods are simple, instant boolean checks that tell you the category of an IP address by looking at its numerical value. They're fast and reliable because they don't use the network, which allows your code to safely decide how to handle an address.

### 🤔 Predicate Methods: Asking a `True`/`False` Question 

➡️ A **predicate method** is just a function that asks a yes-or-no question about an object and returns `true` or `false`.

➡️ For `InetAddress`, these methods don't perform complex actions; they just check a property. Examples include:
* `isMulticastAddress()`: Is this a one-to-a-group address?
* `isLoopbackAddress()`: Is this the special "self" address (`127.0.0.1`)?
* `isLinkLocalAddress()`: Is this an automatic private address (`169.254.x.x`)?

#### 👉 They answer the question: "Does this IP address belong to a specific, well-defined category?"

### 🤷‍♀️ How They Work: A Purely Local Check 🔢
↳ The key to their speed and safety is that they work by examining the **raw bytes** of the IP address itself. IP addresses are not random; their numerical ranges are reserved for specific purposes by internet standards.

* **For `isMulticastAddress()`:** An IPv4 address is multicast if it's in the range `224.0.0.0` to `239.255.255.255`. The method simply checks if the first byte of the address is between 224 and 239. This is a simple mathematical comparison.
* **For `isLoopbackAddress()`:** The IPv4 loopback address is always `127.0.0.1`. The method just checks if the address bytes match this exact, pre-defined value.

➡️ This process is:
* **Local & No Network I/O:** It only uses the CPU and memory. It never sends or receives data over the network.
* **Deterministic:** The same IP address will *always* give the same `true`/`false` result.
* **Cheap & Safe:** It's incredibly fast and can't fail, making it safe to use in performance-critical code (a "hot path").

***

### 🛎️ Purpose: Making Smart Decisions
* These methods allow your application to handle IP addresses intelligently instead of treating them all the same. This is crucial for writing correct and secure networking code.
    * **Correct Binding:** A server application needs to "bind" to an IP address to listen for connections. A common requirement is to listen on all real network connections but ignore the loopback one. You'd loop through all addresses and use `if (!addr.isLoopbackAddress())` to filter them.

    * **Security (ACLs):** An Access Control List (ACL) is a set of security rules. You could implement a rule like, "This administrative service should only be accessible from within our company's network." The code would enforce this by checking `if (addr.isSiteLocalAddress())`.

    * **Avoiding Errors:** You cannot establish a normal one-to-one connection with a multicast address. By checking `isMulticastAddress()`, your code can avoid trying to perform an invalid operation.

#### 👉 In short, these checks let you make decisions based on the **fundamental properties** of an IP address, which is far more reliable than guessing based on its name.

## What is Unicast and what is Multicast❓

### 🔸**Unicast (One-to-One)** 📞
 
  - A unicast address identifies a single network interface on a single host. Packets sent to a unicast address are delivered to exactly one destination. Examples: 192.0.2.5, 2001:db8::1. Typical TCP/UDP client-server communication uses unicast.
  
  - Unicast is the most common form of communication on the internet. It's a **direct, private conversation between exactly two devices**: one sender and one receiver.

#### 💭 Analogy: A Private Phone Call
➡️ Think of unicast like making a **private phone call** or sending a letter to a specific mailing address. You dial a specific number, and the phone network connects you only to that person. The message is intended for a single recipient, and the network infrastructure is designed to deliver it exclusively to them.

#### **Q. How It Works**❓
➡️ Every packet of data sent via unicast is like a letter with a specific "To:" address—the destination's unique IP address. Network devices like routers and switches act like postal workers. They read this destination address on each packet and forward it along the most efficient path until it reaches the single, intended recipient. Every time you browse a website, the server sends the data in a unicast stream directly and only to your device.

### 🔸**Multicast (One-to-Many)** 📺
  - A multicast address identifies a group of interfaces (often on many hosts) that have joined a particular multicast group. A packet sent to a multicast address is delivered to all group members. Examples: IPv4 224.0.0.1 (all hosts on local network), IPv6 ff02::1 (all nodes link-local). Multicast is used for group communication, discovery protocols, media streaming, and some service discovery mechanisms.
  
  - Multicast is a much more efficient method for sending the same information from **one sender to a specific group of interested receivers** simultaneously. It's a "one-to-many" broadcast, but only for those who have opted in.

#### 💭 Analogy: A TV Broadcast Channel**
➡️ A perfect analogy for multicast is a **TV channel** or a magazine subscription. A TV station sends out one signal for a specific channel. It doesn't send a separate signal to every single television in the country. Instead, only the TVs that are actively "tuned in" to that channel (i.e., have joined the group) will receive the broadcast. People watching other channels (or with their TVs off) are not affected. This is vastly more efficient than the TV station making a separate, private phone call (unicast) to every viewer.

#### **Q. How It Works**❓
1.  **Group Membership:** Devices must explicitly "join" a multicast group to receive data. This is like tuning your TV to a specific channel. A protocol called **IGMP (Internet Group Management Protocol)** is often used for this, where a device tells its local router, "Hey, I'm interested in the data for group `239.0.0.1`."
2.  **Efficient Delivery:** When the sender transmits a single packet addressed to the multicast group, the network's routers intelligently handle the delivery. A router will duplicate the packet and send a copy *only* down the network paths that lead to devices that have joined the group. This prevents the data from flooding parts of the network where no one is listening, saving enormous amounts of bandwidth.

### ☑️ Practical differences:
- Unicast: one-to-one communication, per-peer connections, typical for most apps.  
- Multicast: one-to-many or many-to-many, requires group membership (joining) and router support for forwarding between subnets (scope-dependent).

### 📖 Summary of Practical Differences

| Feature               | Unicast (One-to-One)                                             | Multicast (One-to-Many)                                          |
| :-------------------- | :--------------------------------------------------------------- | :--------------------------------------------------------------- |
| **Analogy** | A private phone call                                             | A TV broadcast channel                                           |
| **Communication Scope** | A single sender to a single receiver.                            | A single sender to a specific group of interested receivers.     |
| **Network Efficiency** | Inefficient for sending the same data to many people.            | Extremely efficient for sending the same data to many people.    |
| **Setup** | Default communication method; no special setup needed.           | Requires devices to "join" a group and routers to support it.    |
| **Primary Use** | Web browsing, file downloads, email.                             | Live video streaming (IPTV), online gaming, stock market data.   |

## 📦 Common type-check methods

### 1. `isAnyLocalAddress`
- **What it means**: `true` for the unspecified/wildcard address — 0.0.0.0 (IPv4) or :: (IPv6). It indicates absence of a specific address and is used as a bind target to accept connections on all local interfaces. It must not be used as a destination address.
- **When to use**: detect wildcard binds or validate that an address is not a usable remote destination.

### 📌 Example:
```java
import java.net.InetAddress;
public class AnyLocalDemo {
    public static void main(String[] args) throws Exception {
        InetAddress a = InetAddress.getByName("0.0.0.0");
        InetAddress b = InetAddress.getByName("::");
        System.out.println(a.getHostAddress() + " isAnyLocalAddress: " + a.isAnyLocalAddress());
        System.out.println(b.getHostAddress() + " isAnyLocalAddress: " + b.isAnyLocalAddress());
    }
}
```

### 2. `isLoopbackAddress`
- **What it means**: `true` for loopback ranges — 127.0.0.0/8 for IPv4 and ::1 for IPv6. Loopback addresses route traffic back to the same host and are local-only.
- **When to use**: detect local-only endpoints, avoid connecting to yourself in discovery code, or restrict services to local testing.

### 📌 Example:
```java
import java.net.InetAddress;
public class LoopbackDemo {
    public static void main(String[] args) throws Exception {
        InetAddress a = InetAddress.getByName("127.0.0.1");
        InetAddress b = InetAddress.getByName("::1");
        System.out.println(a.getHostAddress() + " isLoopbackAddress: " + a.isLoopbackAddress());
        System.out.println(b.getHostAddress() + " isLoopbackAddress: " + b.isLoopbackAddress());
    }
}
```

### 3. `isLinkLocalAddress`
- **What it means**: `true` for link-local addresses that are valid only on the same network segment — IPv4 APIPA 169.254.0.0/16 and IPv6 fe80::/10. Not routable across routers.
- **When to use**: detect addresses that shouldn’t be used for cross-subnet communication, or for local auto-configuration logic.

### 📌 Example:
```java
import java.net.InetAddress;
public class LinkLocalDemo {
    public static void main(String[] args) throws Exception {
        InetAddress ipv4 = InetAddress.getByName("169.254.10.20");
        InetAddress ipv6 = InetAddress.getByName("fe80::1234");
        System.out.println(ipv4.getHostAddress() + " isLinkLocalAddress: " + ipv4.isLinkLocalAddress());
        System.out.println(ipv6.getHostAddress() + " isLinkLocalAddress: " + ipv6.isLinkLocalAddress());
    }
}
```

### 4. `isSiteLocalAddress`
- **What it means**: `true` for private/site-local addresses:
  - IPv4: 10.0.0.0/8, 172.16.0.0/12, 192.168.0.0/16.
  - IPv6 unique local: fc00::/7 (commonly used for private IPv6).
- **When to use**: restrict services to private networks, apply different policies for internal vs external addresses.

### 📌 Example:
```java
import java.net.InetAddress;
public class SiteLocalDemo {
    public static void main(String[] args) throws Exception {
        InetAddress a = InetAddress.getByName("10.1.2.3");
        InetAddress b = InetAddress.getByName("192.168.0.5");
        InetAddress c = InetAddress.getByName("fc00::1");
        System.out.println(a.getHostAddress() + " isSiteLocalAddress: " + a.isSiteLocalAddress());
        System.out.println(b.getHostAddress() + " isSiteLocalAddress: " + b.isSiteLocalAddress());
        System.out.println(c.getHostAddress() + " isSiteLocalAddress: " + c.isSiteLocalAddress());
    }
}
```

---

### 5. `isMulticastAddress` and multicast scopes
- **What it means**: `isMulticastAddress()` is `true` for multicast group addresses:
  - IPv4 multicast: 224.0.0.0 — 239.255.255.255 (first nibble 1110).
  - IPv6 multicast: ff00::/8.
- When to use: verify an address is a multicast group before calling socket joinGroup/joinMulticastGroup.

### Multicast scope helpers (meaning and use):
- `isMCNodeLocal()`: multicast scoped to the node (IPv6 ff01::/16); very restricted.
- `isMCLinkLocal()`: link-local multicast (IPv4 224.0.0.x; IPv6 ff02::/16); delivered only on the local link.
- `isMCSiteLocal()`: site-local multicast (IPv4 administratively scoped ranges like 239.192.0.0/14; IPv6 ff05::/16); delivered within a site.
- `isMCOrgLocal()`: organization-local multicast (IPv4/IPv6 org-scoped).
- `isMCGlobal()`: global multicast (IPv4 ranges outside link/site-scoped; IPv6 ff0e::/16).

### 📌 Example: (shows isMulticastAddress and scope predicates):
```java
import java.net.InetAddress;
public class MulticastDemo {
    public static void main(String[] args) throws Exception {
        InetAddress m1 = InetAddress.getByName("224.0.0.1");   // IPv4 link-local all hosts
        InetAddress m2 = InetAddress.getByName("239.255.0.1"); // IPv4 administratively scoped
        InetAddress m6 = InetAddress.getByName("ff02::1");     // IPv6 all-nodes link-local

        printMulticastInfo(m1);
        printMulticastInfo(m2);
        printMulticastInfo(m6);
    }

    private static void printMulticastInfo(InetAddress m) {
        System.out.println("Address: " + m.getHostAddress());
        System.out.println("  isMulticastAddress(): " + m.isMulticastAddress());
        if (m.isMulticastAddress()) {
            System.out.println("    isMCNodeLocal(): " + m.isMCNodeLocal());
            System.out.println("    isMCLinkLocal(): " + m.isMCLinkLocal());
            System.out.println("    isMCSiteLocal(): " + m.isMCSiteLocal());
            System.out.println("    isMCOrgLocal(): " + m.isMCOrgLocal());
            System.out.println("    isMCGlobal(): " + m.isMCGlobal());
        }
        System.out.println();
    }
}
```

### Q.When to use each predicate in real code❓
* Use `isLoopbackAddress()` to prevent a service from attempting remote connections to itself or to allow only local testing traffic.
* Use `isAnyLocalAddress()` when deciding whether to bind a server to all interfaces or when interpreting a bind result.
* Use `isLinkLocalAddress()` to detect addresses that should not be used across routers; useful for auto-configuration and discovery fallbacks.
* Use `isSiteLocalAddress()` to restrict services to private networks if desired.
* Use `isMulticastAddress()` before attempting to join/subscribe to a multicast group; verify scope with the MC* methods to avoid joining global groups when only link-local is appropriate.

### 📌 Simple example of Testing the characteristics of an IP address
```java
import java.net.InetAddress;
import java.util.Arrays;

public class AddressTypeChecksDemo {
    public static void main(String[] args) throws Exception {
        InetAddress[] tests = new InetAddress[] {
            InetAddress.getByName("127.0.0.1"),       // loopback
            InetAddress.getByName("0.0.0.0"),         // any local / wildcard
            InetAddress.getByName("169.254.10.20"),   // IPv4 link-local (APIPA)
            InetAddress.getByName("192.168.1.100"),   // private/site-local IPv4
            InetAddress.getByName("224.0.0.1"),       // IPv4 multicast (all hosts)
            InetAddress.getByName("::1"),             // IPv6 loopback
            InetAddress.getByName("fe80::1234"),      // IPv6 link-local
            InetAddress.getByName("ff02::1")          // IPv6 multicast (all-nodes, link-local)
        };

        for (InetAddress a : tests) {
            System.out.println("Address: " + a.getHostAddress());
            System.out.println("  isAnyLocalAddress(): " + a.isAnyLocalAddress());
            System.out.println("  isLoopbackAddress(): " + a.isLoopbackAddress());
            System.out.println("  isLinkLocalAddress(): " + a.isLinkLocalAddress());
            System.out.println("  isSiteLocalAddress(): " + a.isSiteLocalAddress());
            System.out.println("  isMulticastAddress(): " + a.isMulticastAddress());
            if (a.isMulticastAddress()) {
                System.out.println("    isMCNodeLocal(): " + a.isMCNodeLocal());
                System.out.println("    isMCLinkLocal(): " + a.isMCLinkLocal());
                System.out.println("    isMCSiteLocal(): " + a.isMCSiteLocal());
                System.out.println("    isMCOrgLocal(): " + a.isMCOrgLocal());
                System.out.println("    isMCGlobal(): " + a.isMCGlobal());
            }
            System.out.println();
        }
    }
}
```

### Example: Testing the characteristics of an IP address
```java
import java.net.*;

public class IPCharacteristics {
    public static void main(String[] args) {
        try {
            InetAddress address = InetAddress.getByName(args[0]);

            if (address.isAnyLocalAddress()) {
                System.out.println(address + " is a wildcard address.");
            }
            if (address.isLoopbackAddress()) {
                System.out.println(address + " is loopback address.");
            }
            if (address.isLinkLocalAddress()) {
                System.out.println(address + " is a link-local address.");
            } else if (address.isSiteLocalAddress()) {
                System.out.println(address + " is a site-local address.");
            } else {
                System.out.println(address + " is a global address.");
            }

            if (address.isMulticastAddress()) {
                if (address.isMCGlobal()) {
                    System.out.println(address + " is a global multicast address.");
                } else if (address.isMCOrgLocal()) {
                    System.out.println(address + " is an organization wide multicast address.");
                } else if (address.isMCSiteLocal()) {
                    System.out.println(address + " is a site wide multicast address.");
                } else if (address.isMCLinkLocal()) {
                    System.out.println(address + " is a subnet wide multicast address.");
                } else if (address.isMCNodeLocal()) {
                    System.out.println(address + " is an interface-local multicast address.");
                } else {
                    System.out.println(address + " is an unknown multicast address type.");
                }
            } else {
                System.out.println(address + " is a unicast address.");
            }
        } catch (UnknownHostException ex) {
            System.err.println("Could not resolve " + args[0]);
        }
    }
}
```
### \* Expected Output:
```
java IPCharacteristics 0.0.0.0
/0.0.0.0 is a wildcard address.
/0.0.0.0 is a global address.
/0.0.0.0 is a unicast address.

java IPCharacteristics 8.8.8.8 → (public IPv4)
/8.8.8.8 is a global address.
/8.8.8.8 is a unicast address.

java IPCharacteristics 127.0.0.1
/127.0.0.1 is loopback address.
/127.0.0.1 is a global address.
/127.0.0.1 is a unicast address.

java IPCharacteristics www.example.com
www.example.com/93.184.216.34 is a global address.
www.example.com/93.184.216.34 is a unicast address.

java IPCharacteristics 192.168.254.32 → (IPv4 site-local / private)
/192.168.254.32 is a site-local address.
/192.168.254.32 is a unicast address.

java IPCharacteristics 0:0:0:0:0:0:0:0 → (IPv6 unspecified)
/0:0:0:0:0:0:0:0 is a wildcard address.
/0:0:0:0:0:0:0:0 is a global address.
/0:0:0:0:0:0:0:0 is a unicast address.

java IPCharacteristics 0:0:0:0:0:0:0:1 → (IPv6 loopback)
/0:0:0:0:0:0:0:1 is loopback address.
/0:0:0:0:0:0:0:1 is a global address.
/0:0:0:0:0:0:0:1 is a unicast address.

java IPCharacteristics 169.254.10.20 → (IPv4 link-local)
/169.254.10.20 is a link-local address.
/169.254.10.20 is a unicast address.

java IPCharacteristics 192.168.1.100 → (IPv4 site-local / private)
/192.168.1.100 is a site-local address.
/192.168.1.100 is a unicast address.

java IPCharacteristics 224.0.0.1 → (IPv4 multicast - link-local)
/224.0.0.1 is a global address.
/224.0.0.1 is a subnet wide multicast address.

java IPCharacteristics 239.255.0.1
/239.255.0.1 is a global address.
/239.255.0.1 is a site wide multicast address.

java IPCharacteristics FF01:0:0:0:0:0:0:1
/FF01:0:0:0:0:0:0:1 is a global address.
/FF01:0:0:0:0:0:0:1 is an interface-local multicast address.

java IPCharacteristics ff02:0:0:0:0:0:0:1
/ff02:0:0:0:0:0:0:1 is a global address.
/ff02:0:0:0:0:0:0:1 is an interface-local multicast address.

java IPCharacteristics FF05:0:0:0:0:0:0:101
/FF05:0:0:0:0:0:0:101 is a global address.
/FF05:0:0:0:0:0:0:101 is a site wide multicast address.

java IPCharacteristics fe80:0:0:0:0:0:0:1
/fe80:0:0:0:0:0:0:1 is a link-local address.
/fe80:0:0:0:0:0:0:1 is a unicast address.

java IPCharacteristics ::
/0:0:0:0:0:0:0:0 is a wildcard address.
/0:0:0:0:0:0:0:0 is a global address.
/0:0:0:0:0:0:0:0 is a unicast address.

java IPCharacteristics ff0e:0:0:0:0:0:0:1
/ff0e:0:0:0:0:0:0:1 is a global address.
/ff0e:0:0:0:0:0:0:1 is a global multicast address.

java IPCharacteristics ::ffff:192.168.1.100 → (IPv4-mapped IPv6)
/0:0:0:0:0:ffff:c0a8:164 is a site-local address.
/0:0:0:0:0:ffff:c0a8:164 is a unicast address.
```

# \# Reachability Tests (Since JDK 1.5)
* `InetAddress.isReachable` is a network reachability predicate method introduced in JDK 1.5. 
* It is a blocking instance method that attempts to determine whether the remote address can be reached from the local host within a given timeout. It is a convenience API for a “can I contact that IP” check, not a guaranteed network truth — useful for diagnostics, probing, or best-effort connectivity checks.
* It returns a **boolean** indicating whether the target address appears reachable within a caller-supplied timeout. 
* Its behavior depends on the operating system, JVM implementation, privileges, and intervening firewalls.
*  Connections can be blocked for many reasons, including firewalls, proxy servers, misbehaving routers, and broken cables, or simply because the remote host is not turned on.
* An `IOException` will be thrown if there's a network error. The second variant also lets you specify the local network interface the connection is made from and the "time-to-live" (the maximum number of network hops the connection will attempt before being discarded).


### Q. What kind of method is it❓
- **Type**: blocking network I/O predicate (boolean-returning instance method).  
- **Signature examples**:  
  - `boolean isReachable(int timeoutMillis)`  
  - `boolean isReachable(NetworkInterface netif, int ttl, int timeoutMillis)`  
- **Behavior**: performs active probes (ICMP or TCP-based) using the platform-dependent resolver and network operations, waits up to the timeout, then returns true/false.

## Q. How it works❓
➡️ `isReachable()` is a method that attempts to determine if a remote host is "alive" by sending a network probe. However, its results are often unreliable because firewalls and system permissions can block these probes, leading to a "false negative" where a live host appears to be down.

### How It Works: Trying to Get a Response ❓
↳ The goal of `isReachable()` is to get **any kind of response** from the target machine to prove it's online. It doesn't care about connecting to a specific application; it just wants a sign of life.

↳ To do this, it tries one of the following methods, often in this order:

* **Primary Method: ICMP Echo (Ping):** The best and most direct way is to send an **ICMP echo request**, which is what the common `ping` command does. It's like shouting "Are you there?" across the network and waiting for an "I am here!" reply.

* **Fallback Method: TCP/UDP Probes:** If the JVM doesn't have the necessary permissions to send a ping (a common issue), it falls back to a different trick. It will try to establish a very brief connection to a common service on the remote machine, such as the "echo" service on **TCP port 7**. The goal isn't to have a full conversation; it's just to see if the host responds at all. Even if the host replies with "Sorry, that port is closed," the JVM knows the host is online and considers it reachable.

### Why It's So Unreliable: Permissions and Firewalls ⚠️
↳ This is the most important part to understand. The result of `isReachable()` can be misleading, and you should not rely on it to definitively say a host is offline.

* **Permissions Problem:** On many operating systems (like Linux), creating the special "raw socket" needed to send an ICMP ping requires administrator (root) privileges. Since your Java application usually runs as a normal user, it often can't send a true ping and must use the less reliable TCP/UDP fallback.

* **The Firewall Problem (The Main Culprit):** For security, it is extremely common for network firewalls to be configured to **block all incoming ICMP traffic**. They simply drop ping requests without replying. This creates a **false negative**:
    * Your program calls `isReachable()` on `www.google.com`.
    * The probe (ICMP or TCP) is sent.
    * A firewall in front of Google's servers sees the probe and blocks it.
    * Your program never gets a reply and the timeout expires.
    * `isReachable()` returns `false`.

#### 👉 Even though `isReachable()` returned `false`, the Google web server is obviously online and reachable through a web browser. The method failed because the *probe* was blocked, not because the *host* was down.

### ⚙️ Advanced Controls: `NetworkInterface` and TTL
↳ The method has an advanced version that lets you specify:

* **`NetworkInterface`:** On a computer with multiple network connections (e.g., Wi-Fi and a wired Ethernet cable), this allows you to choose *which* connection to send the probe from. This is useful for "multi-homed" hosts.
* **`TTL` (Time-To-Live):** This sets how many network "hops" (routers) the probe is allowed to travel through before it's discarded. It's an advanced diagnostic tool for checking network topology.

#### 👉 In summary, treat `isReachable()` as a quick, optimistic check. If it returns `true`, the host is very likely online. If it returns `false`, you cannot be certain if the host is truly offline or if your probe was simply blocked.

## 1. `isReachable(int timeout)`
* Checks if host is reachable (uses ICMP echo or TCP to port 7). Timeout in ms (0 = infinite, but avoid).
```java
import java.net.InetAddress;

public class IsReachableTimeoutDemo {
    public static void main(String[] args) throws Exception {
        InetAddress target = InetAddress.getByName("8.8.8.8"); // Google DNS
        int timeoutMs = 2000; // 2 seconds

        boolean reachable = target.isReachable(timeoutMs);
        System.out.println(target.getHostAddress() + " reachable within " + timeoutMs + "ms: " + reachable);
    }
}
```

## 2. `isReachable(NetworkInterface netif, int ttl, int timeout)` 
* Via specific interface and TTL (hops limit).
* **Caution**: Requires OS privileges; firewalls may block. Not foolproof—use for diagnostics, not production logic.
* Type: blocking instance predicate that sends probes using a specific local NetworkInterface and limits probe hops with TTL (time-to-live) when applicable.
* Purpose: control the outgoing interface (useful on multi-homed hosts) and confine probes in hop count (helpful for testing link-local or limited-scope reachability).
#### 👉Notes: netif must be an actual interface available on the host; TTL controls hop count for IP packets; platform restrictions and firewall rules still apply; behavior is environment-dependent.
```java
import java.net.InetAddress;
import java.net.NetworkInterface;

public class IsReachableInterfaceDemo {
    public static void main(String[] args) throws Exception {
        InetAddress target = InetAddress.getByName("fe80::1%eth0"); // example link-local literal with scope
        NetworkInterface netif = NetworkInterface.getByName("eth0"); // adjust to your system
        int ttl = 1;           // limit hops (1 = local link)
        int timeoutMs = 3000;  // 3 seconds

        boolean ok = target.isReachable(netif, ttl, timeoutMs);
        System.out.println("Reachable via interface " + (netif != null ? netif.getName() : "null")
                           + " with ttl=" + ttl + ": " + ok);
    }
}
```


### When to use and when not to use it❓
- Use it when: you need a quick, best-effort check for reachability in diagnostics, tools, or non-critical background tasks.  
- Do not use it when: you require authoritative connectivity guarantees, low-latency checks on the request path, or security-critical decisions. Avoid it in hot paths because it blocks and may be slow or unreliable.  
- Prefer targeted protocol checks (TCP connect to a known service port) when you need to know whether a particular service is reachable.

### ☑️ Common pitfalls and recommended alternatives

- Pitfalls:
  - False negatives due to blocked ICMP, firewalls, or OS restriction on raw sockets.  
  - False positives are rare but possible if an intermediate device responds unexpectedly.  
  - Blocking nature: calling on main/request threads can hang user-facing operations.  
- Alternatives:
  - TCP connect probe: attempt to connect to a specific service port with a socket and timeout — more reliable for service reachability.  
  - Application-level health checks (HTTP HEAD/GET, TLS handshake) for service-level availability.  
  - Run reachability checks off-thread (ExecutorService) and cache results with TTL.

### 📌 Simple sample code: using isReachable and a TCP-connect alternative

- Example A: isReachable with default interface and timeout.
```java
import java.net.InetAddress;

public class IsReachableDemo {
    public static void main(String[] args) throws Exception {
        InetAddress addr = InetAddress.getByName("8.8.8.8"); // Google DNS
        int timeoutMs = 2000; // 2 seconds

        boolean reachable = addr.isReachable(timeoutMs);
        System.out.println(addr.getHostAddress() + " isReachable(" + timeoutMs + "ms): " + reachable);
    }
}
```

### 📌 Example B: `isReachable` specifying interface and TTL (useful for link-local tests).
```java
import java.net.InetAddress;
import java.net.NetworkInterface;

public class IsReachableWithInterfaceDemo {
    public static void main(String[] args) throws Exception {
        InetAddress target = InetAddress.getByName("fe80::1%eth0"); // example link-local with scope (platform dependent)
        NetworkInterface nif = NetworkInterface.getByName("eth0"); // pick appropriate interface name for your system
        int ttl = 1;
        int timeoutMs = 3000;

        boolean ok = target.isReachable(nif, ttl, timeoutMs);
        System.out.println("Reachable via " + nif.getName() + ": " + ok);
    }
}
```

### 📌 Example C: recommended alternative — TCP connect probe to a known port (more reliable for service reachability).
```java
import java.net.InetSocketAddress;
import java.net.Socket;
import java.io.IOException;

public class TcpConnectProbe {
    public static boolean canConnect(String host, int port, int timeoutMs) {
        try (Socket s = new Socket()) {
            s.connect(new InetSocketAddress(host, port), timeoutMs);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        String host = "example.com";
        int port = 80; // HTTP
        int timeoutMs = 1500;
        System.out.println(host + ":" + port + " reachable: " + canConnect(host, port, timeoutMs));
    }
}
```
---