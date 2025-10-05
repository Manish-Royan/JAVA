# What Can You Do with an InetAddress Object❓
---

➡️ Once you have an `InetAddress` object, you can query it for information.

➡️ These Instance Methods operate on an InetAddress object. Most are boolean checks for address types, introduced in JDK 1.4 unless noted.
***

# Address Retrieval Method
➡️ These address-retrieval functions on InetAddress are instance accessor methods (getters). They expose different representations or derived information about the InetAddress object without modifying it. Some are purely local, CPU-only accessors; others may perform network I/O (name service calls) and therefore have side effects (blocking, delays, errors).

