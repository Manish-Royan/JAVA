# \# Why One Name Can Have Many Addresses

First, it's important to understand that a single hostname (like `www.google.com`) often points to **many different IP addresses**. Large companies do this for two main reasons:

1.  **Load Balancing:** A massive website like Google can't run on just one server. It has thousands of servers worldwide. By associating the single name `www.google.com` with a list of many IP addresses, the system can distribute incoming traffic across all of them. When you try to connect, you might get sent to a server in Asia, while someone in Europe gets sent to a different one.
2.  **Redundancy (Failover):** If one server crashes or needs maintenance, its IP address can be temporarily removed from the list, and traffic will automatically be routed to the other healthy servers.

**Analogy:** Think of a large company's main customer service phone number. When you dial that one number, you don't always get the same person. An internal system routes your call to one of hundreds of available agents. The main number is the **hostname**, and each individual agent is a different **IP address**.

-----

### \#\# What `getByName()` Actually Does

The `InetAddress.getByName()` method is designed for convenience. When you call it:

1.  It performs a DNS lookup for the hostname you provided.
2.  The DNS server may respond with a whole **list** of IP addresses.
3.  The `getByName()` method looks at this list, **chooses just one** of the addresses (often the first one it receives), and discards the rest.
4.  It then wraps that single IP address into a single `InetAddress` object and returns it to you.

This is a simplifying abstraction. For most common tasks, a developer just needs *one* valid address to connect to a service, and `getByName()` provides that easily.

-----

### \#\# The Alternative: Getting All Addresses

If your application needs to know about all the possible IP addresses (for example, to try connecting to another one if the first one fails), Java provides a different method: `getAllByName()`.

  * `InetAddress.getByName("google.com")` returns a **single `InetAddress` object**.
  * `InetAddress.getAllByName("google.com")` returns an **array of `InetAddress` objects** (`InetAddress[]`), containing every address associated with that name.

Here's how they compare in code:

```java
import java.net.InetAddress;
import java.net.UnknownHostException;

public class AddressExample {
    public static void main(String[] args) {
        try {
            // --- getByName(): Gets only ONE address ---
            InetAddress singleAddress = InetAddress.getByName("www.google.com");
            System.out.println("getByName() result: " + singleAddress.getHostAddress());
            System.out.println("-------------------------------------");

            // --- getAllByName(): Gets ALL addresses ---
            InetAddress[] allAddresses = InetAddress.getAllByName("www.google.com");
            System.out.println("getAllByName() results (" + allAddresses.length + " addresses found):");
            for (InetAddress addr : allAddresses) {
                System.out.println("- " + addr.getHostAddress());
            }

        } catch (UnknownHostException e) {
            System.err.println("Host could not be found.");
        }
    }
}
```