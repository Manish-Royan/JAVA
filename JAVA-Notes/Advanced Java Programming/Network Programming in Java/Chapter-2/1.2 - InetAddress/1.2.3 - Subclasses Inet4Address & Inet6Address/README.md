# [Subclasses: Inet4Address and Inet6Address](https://github.com/Manish-Royan/JAVA/tree/main/JAVA-Notes/Advanced%20Java%20Programming/Network%20Programming%20in%20Java/Chapter-2/1.2%20-%20InetAddress/%23%20More%20Depth%20Explorations/2.0%20MDE%20-%20Simplify%20SubClasses)

<img width="1165" height="665" alt="Screenshot 2025-10-11 025318" src="https://github.com/user-attachments/assets/dad7566b-82c8-45f1-9ffa-939b6702fb83" />

`Inet4Address` and `Inet6Address` are the concrete subclasses of `java.net.InetAddress` that represent addresses from the two IP protocol families. Java exposes the common, family‑neutral API via InetAddress so most code can be protocol‑agnostic; the subclasses exist so code that needs address‑family specific information or behavior can access it safely.

- Purpose of the subclassing
  - Represent the underlying address width and semantics: 32‑bit for IPv4 (Inet4Address) and 128‑bit for IPv6 (Inet6Address).
  - Let the runtime and APIs express IPv4 vs IPv6 semantics and provide address-family specific operations and metadata. Code usually works with InetAddress, and only when you need IPv6-specific data (scope id, scoped interface, IPv4-embedding checks) do you cast to Inet6Address.  

- When you see them in code
  - Most code should use InetAddress and its general methods (getAddress, getHostAddress, isMulticastAddress, equals, hashCode).
    ```java
    /*This example shows how to write code that works with any kind of IP address (IPv4 or IPv6) using only the general InetAddress class. This is the most common and recommended approach.*/

    import java.net.InetAddress;
    import java.net.UnknownHostException;

    public class GeneralInetAddressUsage {

        public static void main(String[] args) {
            // We'll look up "google.com", which typically has both IPv4 and IPv6 addresses.
            String hostname = "google.com";
            System.out.println("--- Getting addresses for " + hostname + " using general    InetAddress methods ---");

            try {
                InetAddress[] addresses = InetAddress.getAllByName(hostname);

                for (InetAddress addr : addresses) {
                    // getHostAddress() - Works for both IPv4 and IPv6
                    System.out.println("\nFound IP Address: " + addr.getHostAddress());

                    // getAddress() - Returns the raw byte array for any IP type
                    byte[] ipBytes = addr.getAddress();
                    System.out.println("  -> Byte array length: " + ipBytes.length + " bytes"); //  (4 for IPv4, 16 for IPv6)

                    // isMulticastAddress() - A general method that works on any IP
                    System.out.println("  -> Is it a multicast address? " + addr.isMulticastAddress ());
                }

            } catch (UnknownHostException e) {
                System.err.println("Could not resolve host: " + hostname);
            }
        }
    }   
    ```

  - Cast to Inet6Address only when you need IPv6-specific properties (scope id, scoped NetworkInterface, IPv4-compat detection).
    ```java
    /* This example shows the scenario where you need information that is only available for IPv6 addresses. Here, we check if an address is an Inet6Address and then "cast" it to use IPv6-specific methods.
    */

    import java.net.InetAddress;
    import java.net.Inet6Address;
    import java.net.UnknownHostException;

    public class SpecificInet6AddressUsage {

        public static void main(String[] args) {
            // "localhost" often resolves to both ::1 (IPv6) and 127.0.0.1 (IPv4)
            String hostname = "localhost";
            System.out.println("--- Checking for IPv6-specific properties for " + hostname + "  ---");

            try {
                InetAddress[] addresses = InetAddress.getAllByName(hostname);

                for (InetAddress addr : addresses) {
                    System.out.println("\nProcessing address: " + addr.getHostAddress());

                    // Check if the address is an instance of Inet6Address
                    if (addr instanceof Inet6Address) {
                        System.out.println("  -> This is an IPv6 address. Casting to get more   details.");

                        // Now, cast it to access IPv6-specific methods.
                        Inet6Address ipv6Addr = (Inet6Address) addr;

                        // Use an IPv6-specific method, e.g., isLinkLocalAddress()
                        System.out.println("  -> Is it a link-local address? " + ipv6Addr.  isLinkLocalAddress());

                        // Another IPv6-specific property: scope ID
                        // (Often 0 unless it's a scoped address like link-local)
                        System.out.println("  -> Scope ID: " + ipv6Addr.getScopeId());

                    } else {
                        System.out.println("  -> This is not an IPv6 address. No special properties     to check.");
                    }
                }

            } catch (UnknownHostException e) {
                System.err.println("Could not resolve host: " + hostname);
            }
        }
    }
    ```
  - Casting to Inet4Address is rarely necessary because IPv4 adds no extra public API beyond InetAddress in the standard library.
    ```java
    /*This example shows that there is no practical reason to cast an InetAddress object to     Inet4Address, because Inet4Address does not add any new, useful public methods that aren't  already available in InetAddress.*/

    import java.net.InetAddress;
    import java.net.Inet4Address;
    import java.net.UnknownHostException;

    public class RedundantInet4AddressCast {

        public static void main(String[] args) {
            // Use an explicit IPv4 address to ensure we get an Inet4Address object.
            String ipv4String = "8.8.8.8"; 
            System.out.println("--- Demonstrating with IPv4 address: " + ipv4String + " ---");

            try {
                InetAddress addr = InetAddress.getByName(ipv4String);

                if (addr instanceof Inet4Address) {
                    System.out.println("\nThis is an Inet4Address object.");

                    // 1. Using the method directly from the InetAddress reference (standard way)
                    System.out.println("  -> getHostAddress() from InetAddress ref: " + addr.   getHostAddress());

                    // 2. Casting to Inet4Address and then calling the same method (redundant way)
                    Inet4Address ipv4Addr = (Inet4Address) addr;
                    System.out.println("  -> getHostAddress() from Inet4Address cast: " + ipv4Addr. getHostAddress());

                    System.out.println("\nConclusion: The cast to Inet4Address provided no extra    functionality.");
                    System.out.println("All useful methods were already available through the   InetAddress reference.");

                }

            } catch (UnknownHostException e) {
                System.err.println("Could not create address from string: " + ipv4String);
            }
        }
    }
    ```


### ⚠️ Safety pattern
#### 👉 Always check `instanceof` before casting:
```java
if (addr instanceof Inet6Address) { Inet6Address i6 = (Inet6Address) addr; /* use i6 methods */ }
```


## 📌 Example 1 — Inspecting an IPv4 address (Inet4Address behavior)

```java
import java.net.InetAddress;
import java.util.Arrays;

/*create an IPv4 InetAddress, show its class and common behaviors.*/

public class Inet4Example {
    public static void main(String[] args) throws Exception {
        InetAddress a = InetAddress.getByName("8.8.8.8"); // IPv4 literal
        System.out.println("Class: " + a.getClass().getSimpleName());
        System.out.println("HostAddress: " + a.getHostAddress());
        System.out.println("Raw bytes (unsigned): " + unsignedBytes(a.getAddress()));
    }

    private static String unsignedBytes(byte[] b) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < b.length; i++) {
            sb.append(b[i] & 0xFF);
            if (i < b.length - 1) sb.append('.');
        }
        return sb.toString();
    }
}
```

### 💡 Expected output (values may vary by DNS / environment):
```
Class: Inet4Address
HostAddress: 8.8.8.8
Raw bytes (unsigned): 8.8.8.8
```

## 📌 Example 2 — Inspecting an IPv6 address and using Inet6Address APIs

```java
import java.net.*;
import java.util.Arrays;

/*
This program show safe `instanceof` check, scope id, scoped interface, and IPv4‑compatible detection. Replace the example IPv6 and interface name with values that exist on your host if you want link‑local scoped data.
*/

public class Inet6Example {
    public static void main(String[] args) throws Exception {
        // IPv6 examples:
        InetAddress v6 = InetAddress.getByName("2001:4860:4860::8888");   // public IPv6 (Google DNS)
        InetAddress linkLocal = InetAddress.getByName("fe80::1%lo");      // platform dependent; may fail if no scope
        InetAddress v4Embedded = InetAddress.getByName("::192.0.2.33");   // legacy IPv4-embedded style

        inspect(v6);
        System.out.println();
        inspectSafe(linkLocal);
        System.out.println();
        inspect(v4Embedded);
    }

    private static void inspect(InetAddress addr) {
        System.out.println("Address: " + addr.getHostAddress());
        System.out.println("Class: " + addr.getClass().getSimpleName());
        System.out.println("isMulticastAddress(): " + addr.isMulticastAddress());
        if (addr instanceof Inet6Address) {
            Inet6Address i6 = (Inet6Address) addr;
            System.out.println("isIPv4CompatibleAddress(): " + i6.isIPv4CompatibleAddress());
            System.out.println("getScopeId(): " + i6.getScopeId());
            NetworkInterface nif = i6.getScopedInterface();
            System.out.println("getScopedInterface(): " + (nif != null ? nif.getName() : "null"));
        }
    }

    private static void inspectSafe(InetAddress addr) {
        if (addr == null) {
            System.out.println("Address construction failed or not available on platform.");
            return;
        }
        inspect(addr);
    }
}
```
### 💡 Example expected output (actual values depend on your host and DNS; linkLocal may be absent or require a real interface):
```
Address: 2001:4860:4860::8888
Class: Inet6Address
isMulticastAddress(): false
isIPv4CompatibleAddress(): false
getScopeId(): 0
getScopedInterface(): null

Address: fe80::1%lo
Class: Inet6Address
isMulticastAddress(): false
isIPv4CompatibleAddress(): false
getScopeId(): 1
getScopedInterface(): lo

Address: ::192.0.2.33
Class: Inet6Address
isMulticastAddress(): false
isIPv4CompatibleAddress(): true
getScopeId(): 0
getScopedInterface(): null
```


## Key IPv6-specific APIs (in Inet6Address)

- `getScopeId()`: returns the numeric scope identifier often used for link‑local addresses (commonly the interface index). Returns 0 when not set.
- `getScopedInterface()`: returns the NetworkInterface associated with this scoped IPv6 address (may be null).
- `isIPv4CompatibleAddress()`: true if the IPv6 address is a legacy IPv4‑compatible form (rare / deprecated; e.g., ::192.168.1.1).
- Host literal printing: `getHostAddress()` for link‑local addresses may include a zone id like "%3" or "%eth0" depending on platform and how the address was constructed.

### Practical tips and best practices
- Prefer programming to InetAddress unless you need IPv6-only fields; keep code simpler and protocol-agnostic.
- Always check `instanceof` before casting to avoid ClassCastException on mixed environments.
- For production-scale apps, ensure correct handling of zone identifiers and platform differences (textual zone formats like %eth0 or %3).


## 📌 Demonstrate a Inet4v6 Program
What this example does:
- Creates addresses: an IPv4 literal, an IPv6 literal, and an IPv6 that embeds an IPv4 (IPv4-compatible style) for demonstration.  
- Prints type, raw bytes, and family-specific properties: for Inet6Address it prints scope id, scoped interface (if present), and whether it’s IPv4-compatible.  
- Uses safe instanceof checks to avoid ClassCastException.

```java
import java.net.*;
import java.util.Arrays;
import java.util.Enumeration;

public class Inet4v6Demo {
    public static void main(String[] args) throws Exception {
        // Example addresses
        InetAddress ipv4 = InetAddress.getByName("93.184.216.34");        // example.com (IPv4)
        InetAddress ipv6 = InetAddress.getByName("2001:4860:4860::8888"); // Google Public DNS IPv6 (may resolve)
        // IPv4-embedded IPv6 (legacy style) ::192.0.2.33  -> bytes show last 4 bytes as IPv4
        InetAddress v4compatible = InetAddress.getByName("::192.0.2.33");

        printInfo("IPv4 literal", ipv4);
        printInfo("IPv6 literal", ipv6);
        printInfo("IPv4-embedded IPv6", v4compatible);

        // Example of enumerating network interfaces and showing any link-local IPv6 scope ids
        System.out.println("Local network interfaces with IPv6 link-local addresses and indices:");
        Enumeration<NetworkInterface> ifs = NetworkInterface.getNetworkInterfaces();
        while (ifs.hasMoreElements()) {
            NetworkInterface nif = ifs.nextElement();
            Enumeration<InetAddress> addrs = nif.getInetAddresses();
            while (addrs.hasMoreElements()) {
                InetAddress a = addrs.nextElement();
                if (a instanceof Inet6Address && a.isLinkLocalAddress()) {
                    Inet6Address i6 = (Inet6Address) a;
                    System.out.printf("  interface=%s index=%d address=%s%n",
                        nif.getName(), i6.getScopeId(), i6.getHostAddress());
                }
            }
        }
    }

    private static void printInfo(String label, InetAddress addr) {
        System.out.println("---- " + label + " ----");
        System.out.println("Class: " + addr.getClass().getSimpleName());
        byte[] bytes = addr.getAddress();
        System.out.println("Raw bytes (signed): " + Arrays.toString(bytes));
        System.out.print("Raw bytes (unsigned): ");
        for (int i = 0; i < bytes.length; i++) {
            System.out.print((bytes[i] & 0xFF) + (i < bytes.length - 1 ? "." : ""));
        }
        System.out.println();
        System.out.println("getHostAddress(): " + addr.getHostAddress());
        System.out.println("isMulticastAddress(): " + addr.isMulticastAddress());

        if (addr instanceof Inet6Address) {
            Inet6Address i6 = (Inet6Address) addr;
            System.out.println("isIPv4CompatibleAddress(): " + i6.isIPv4CompatibleAddress());
            System.out.println("getScopeId(): " + i6.getScopeId());
            NetworkInterface nif = i6.getScopedInterface();
            System.out.println("getScopedInterface(): " + (nif != null ? nif.getName() : "null"));
        } else if (addr instanceof Inet4Address) {
            System.out.println("Inet4Address specific: (no extra methods beyond InetAddress in public API)");
        }
        System.out.println();
    }
}
```

---

### Expected output (example)
#### ⚠️ Note: exact output depends on your host, DNS, and available interfaces. Example output on a typical machine:

- For the sample addresses above you might see:
```
----
IPv4 literal
Class: Inet4Address
Raw bytes (signed): [93, -72, -40, 34]
Raw bytes (unsigned): 93.184.216.34
getHostAddress(): 93.184.216.34
isMulticastAddress(): false

----
IPv6 literal
Class: Inet6Address
Raw bytes (signed): [32, 1, 72, 96, 72, 96, 0, 0, 0, 0, 0, 0, 0, 0, -120, -120]
Raw bytes (unsigned): 32.1.72.96.72.96.0.0.0.0.0.0.0.0.136.136
getHostAddress(): 2001:4860:4860::8888
isMulticastAddress(): false
isIPv4CompatibleAddress(): false
getScopeId(): 0
getScopedInterface(): null

----
IPv4-embedded IPv6
Class: Inet6Address
Raw bytes (signed): [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 33]
Raw bytes (unsigned): 0.0.0.0.0.0.0.0.0.0.0.0.0.0.2.33
getHostAddress(): ::192.0.2.33
isMulticastAddress(): false
isIPv4CompatibleAddress(): true
getScopeId(): 0
getScopedInterface(): null

Local network interfaces with IPv6 link-local addresses and indices:
  interface=eth0 index=2 address=fe80::xxxx:xxxx:xxxx:xxxx%2
  interface=wlan0 index=3 address=fe80::yyyy:yyyy:yyyy:yyyy%3

---
```