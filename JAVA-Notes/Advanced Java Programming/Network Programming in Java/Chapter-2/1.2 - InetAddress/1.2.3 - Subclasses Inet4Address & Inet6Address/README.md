# Subclasses: Inet4Address and Inet6Address

<img width="1165" height="665" alt="Screenshot 2025-10-11 025318" src="https://github.com/user-attachments/assets/dad7566b-82c8-45f1-9ffa-939b6702fb83" />

Inet4Address and Inet6Address are the concrete subclasses of `java.net.InetAddress` that represent addresses from the two IP protocol families. Java exposes the common, family‑neutral API via InetAddress so most code can be protocol‑agnostic; the subclasses exist so code that needs address‑family specific information or behavior can access it safely.

- Purpose of the subclassing
  - Represent the underlying address width and semantics: 32‑bit for IPv4 (Inet4Address) and 128‑bit for IPv6 (Inet6Address).
  - Let the runtime and APIs express IPv4 vs IPv6 semantics and provide address-family specific operations and metadata. Code usually works with InetAddress, and only when you need IPv6-specific data (scope id, scoped interface, IPv4-embedding checks) do you cast to Inet6Address.  

- When you see them in code
  - Most code should use InetAddress and its general methods (getAddress, getHostAddress, isMulticastAddress, equals, hashCode).
  - Cast to Inet6Address only when you need IPv6-specific properties (scope id, scoped NetworkInterface, IPv4-compat detection).
  - Casting to Inet4Address is rarely necessary because IPv4 adds no extra public API beyond InetAddress in the standard library.

- Safety pattern
  - Always check instanceof before casting:
    if (addr instanceof Inet6Address) { Inet6Address i6 = (Inet6Address) addr; /* use i6 methods */ }

---

### Key IPv6-specific APIs (in Inet6Address)

- getScopeId(): returns the numeric scope identifier often used for link‑local addresses (commonly the interface index). Returns 0 when not set.
- getScopedInterface(): returns the NetworkInterface associated with this scoped IPv6 address (may be null).
- isIPv4CompatibleAddress(): true if the IPv6 address is a legacy IPv4‑compatible form (rare / deprecated; e.g., ::192.168.1.1).
- Host literal printing: getHostAddress() for link‑local addresses may include a zone id like "%3" or "%eth0" depending on platform and how the address was constructed.

---

### When to cast and what to expect

- Use Inet6Address when:
  - You must bind/connect using a link‑local IPv6 address and must provide the interface or scope id.
  - You want to detect whether an IPv6 address encodes an IPv4 address.
  - You need to inspect or log the scope id or the NetworkInterface tied to the address.

- Do not cast when:
  - You only need the textual address, raw bytes, or common predicates — InetAddress methods suffice and keep your code portable.

---

### Example 1 — Inspecting an IPv4 address (Inet4Address behavior)

Code: create an IPv4 InetAddress, show its class and common behaviors.
```java
import java.net.InetAddress;
import java.util.Arrays;

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

Expected output (values may vary by DNS / environment):
```
Class: Inet4Address
HostAddress: 8.8.8.8
Raw bytes (unsigned): 8.8.8.8
```

---

### Example 2 — Inspecting an IPv6 address and using Inet6Address APIs

Code: show safe instanceof check, scope id, scoped interface, and IPv4‑compatible detection. Replace the example IPv6 and interface name with values that exist on your host if you want link‑local scoped data.
```java
import java.net.*;
import java.util.Arrays;

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

Example expected output (actual values depend on your host and DNS; linkLocal may be absent or require a real interface):
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

---

### Practical tips and best practices

- Prefer programming to InetAddress unless you need IPv6-only fields; keep code simpler and protocol-agnostic.
- When dealing with link-local IPv6 addresses, use the scope id or pass a NetworkInterface to bind/connect so the OS can route packets correctly.
- Do not rely on isIPv4CompatibleAddress for modern dual‑stack logic; current practice uses IPv4-mapped (::ffff:a.b.c.d) or native dual-stack sockets.
- Always check instanceof before casting to avoid ClassCastException on mixed environments.
- For production-scale apps, ensure correct handling of zone identifiers and platform differences (textual zone formats like %eth0 or %3).

If you want, I can provide a small utility that safely extracts IPv6 scope information and normalizes textual addresses with zone ids for cross-platform use.

***

### Subclasses: Inet4Address and Inet6Address — what they are and why they exist

- Purpose: 
- Practical consequence: equality, hashing, and many boolean checks are implemented on InetAddress, but Inet6Address adds IPv6-only helpers; Inet4Address may override certain checks to follow IPv4 rules.

---

### IPv6-specific additions (what matters)
- getScopeId(): numeric scope identifier for scoped addresses (commonly link-local); often equals the interface index on the host. Returns 0 when not present.  
- getScopedInterface(): the NetworkInterface associated with a scoped (zone) IPv6 address, or null if none.  
- isIPv4CompatibleAddress(): indicates whether the IPv6 address is one of the legacy IPv4-compatible forms (deprecated in modern IPv6 usage) that embed an IPv4 address (e.g., ::192.168.1.1). This form is rare and deprecated, but the method exists for detection.  
- Usage pattern: check instanceof Inet6Address, then cast and call IPv6-only methods when appropriate.

---

### When to cast
- Only cast when you specifically need IPv6 metadata or behavior:  
  if (addr instanceof Inet6Address) { Inet6Address i6 = (Inet6Address) addr; /* call IPv6 methods */ }

---

### Simple runnable sample code
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
Note: exact output depends on your host, DNS, and available interfaces. Example output on a typical machine:

- For the sample addresses above you might see:

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

### Notes and best practices
- getScopeId() and getScopedInterface() are useful for link-local IPv6 addresses where you must specify the outgoing interface when binding or connecting (e.g., fe80::1%eth0). The scope id often equals the network interface index.  
- isIPv4CompatibleAddress() detects legacy embedded IPv4 addresses, but modern practice prefers IPv4-mapped addresses (::ffff:0:0/96) or separate dual-stack usage.  
- Most applications should program to InetAddress and only cast to Inet6Address when they need scope or IPv6-only behavior.  
- For scalable modern Java applications (virtual threads, async IO), prefer nonblocking patterns and let the OS handle IPv6 routing; only inspect family-specific metadata when necessary.
