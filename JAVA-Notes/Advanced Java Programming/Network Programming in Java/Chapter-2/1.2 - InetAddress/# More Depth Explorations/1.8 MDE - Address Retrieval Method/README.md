# Address Retrieval Method
----
➡️ These address-retrieval functions on InetAddress are instance accessor methods (getters). They expose different representations or derived information about the InetAddress object without modifying it. Some are purely local, CPU-only accessors; others may perform network I/O (name service calls) and therefore have side effects (blocking, delays, errors).

➡️ That statement explains that while all "**getter**" methods on an InetAddress object retrieve information, they don't all behave the same way. Some are instant and safe, while others can be slow and risky because they might need to use the network.

## \# Classification of the methods (precise)

- **Pure accessors (non-blocking, local, deterministic)**  
  - `getAddress()` — returns a byte[] copy of the raw address bytes.  
  - `getHostAddress()` — returns a textual IP literal (no DNS).  
  - `toString()`, `equals()`, `hashCode()` — object-formatting and identity helpers.

- **Potentially blocking resolvers (may perform network I/O / name service lookups)**  
  - `getHostName()` — may return a stored hostname immediately or perform a reverse DNS lookup if no hostname was attached when the object was created. This can block and may throw or fallback.  
  - `getCanonicalHostName()` — actively attempts to obtain the authoritative FQDN via name service (reverse lookup, sometimes followed by forward verification). This is the most network‑dependent and can block significantly.

- **Query/inspection helpers (fast, local checks)**  
  - `isLoopbackAddress()`, `isSiteLocalAddress()`, `isLinkLocalAddress()`, `isMulticastAddress()`, `isAnyLocalAddress()` — examine the stored bytes and return boolean properties (non-blocking).

---

### \# Purpose of each type (why they exist)

- **Raw retrieval (`getAddress`)**  
  - Purpose: provide binary form for low-level networking, packet construction, serialization, checksum or byte‑level comparison.  
  - Semantics: returns a copy of bytes in network (big-endian) order; length 4 (IPv4) or 16 (IPv6).

- **Textual representation (`getHostAddress`)**  
  - Purpose: present a canonical textual form to humans or text-based protocols (logging, CLI, config files).  
  - Semantics: deterministic formatting from stored bytes; never contacts network.

- **Name retrieval / resolution (`getHostName`, `getCanonicalHostName`)**  
  - Purpose: map address ↔ hostname for display, audit, or when protocol semantics require names; canonical for authoritative naming.  
  - Semantics: may use cached values or perform DNS PTR (reverse) lookup; can be slow, fail, or return the literal IP as fallback.

- **Property checks (`isLoopbackAddress` etc.)**  
  - Purpose: quickly decide routing, binding, access control, or behavior based on address class (loopback, multicast, private).  
  - Semantics: pure byte-pattern checks; fast and safe on hot paths.

---

### \# Return types, exceptions, and runtime behavior

- **Return types**
  - `getAddress`(): `byte[] (copy)`  
  - `getHostAddress`(): `String`  
  - `getHostName`(): `String`  
  - `getCanonicalHostName`(): `String`  
  - property checks: `boolean`

- **Exceptions / failure modes**
  - Pure accessors do not throw checked network exceptions.  
  - `getHostName()` and `getCanonicalHostName()` do not declare checked exceptions but can block and return the IP literal if lookup fails; underlying DNS resolution can fail due to network errors, timeouts, or misconfiguration. JVM DNS caching and security manager settings influence behavior.

- **Thread-safety**
  - InetAddress instances are immutable; calling these methods from multiple threads is safe. Only network latency and shared resolver resources affect performance, not correctness.

---

### \# Short examples showing contrasting usage

- Cheap, safe (non-blocking):
```java
InetAddress a = InetAddress.getByName("192.168.1.10");
byte[] raw = a.getAddress();               // cheap binary form
String ip = a.getHostAddress();            // cheap textual form
boolean loop = a.isLoopbackAddress();      // quick property check
```

- Potentially blocking (do off main thread):
```java
// Run in executor with timeout in real code
String maybeName = a.getHostName();                // may trigger reverse DNS
String canonical = a.getCanonicalHostName();       // more expensive, authoritative attempt
```

---