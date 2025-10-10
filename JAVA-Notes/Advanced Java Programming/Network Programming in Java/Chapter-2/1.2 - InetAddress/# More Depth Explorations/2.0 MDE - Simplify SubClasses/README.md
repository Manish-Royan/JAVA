## Simple Explanation for Subclasses

### 💭 Analogy
➡️ Imagine `InetAddress` is a general concept for any kind of internet address, like the general idea of a "phone number."

Now, just as phone numbers can come in different formats (like a local number vs. an international one with a country code), internet addresses also come in two main formats:
1.  **IPv4:** The older, more common format (e.g., `192.168.1.1`).
2.  **IPv6:** The newer, longer format designed to handle the massive number of devices on the internet today (e.g., `2001:0db8:85a3:0000:0000:8a2e:0370:7334`).

➡️ In Java, to handle this distinction, there are two specialized classes:

*   `Inet4Address`: Represents an **IPv4** address.
*   `Inet6Address`: Represents an **IPv6** address.

#### 👉 Both of these are more specific versions of the general `InetAddress` class.

## Why We Usually Don't Need to Worry About the Difference

#### 👉 In most application-level programming, **we don't need to care whether you're using IPv4 or IPv6**.

💭 Think of it like sending a letter. You write the recipient's address on the envelope. You don't need to know the exact route the postal service will take or what kind of truck they will use. You just give them the address, and they figure out how to deliver it.

Similarly, in Java, you usually just give an `InetAddress` object to a network component (like a `Socket`), and the underlying operating system handles the complex details of sending data over the correct network protocol (***IPv4*** or ***IPv6***).

## What is `isIPv4CompatibleAddress()`?
💭 This is a special method for a specific scenario during the transition from IPv4 to IPv6. An "IPv4-compatible address" is essentially an old IPv4 address that has been wrapped up to look like a new IPv6 address. It's like putting a small, old photo into a big, new picture frame.

➡️ The format is `0:0:0:0:0:0:0:xxxx`, where `xxxx` represents the old IPv4 address.

➡️ The `isIPv4CompatibleAddress()` method simply checks: "**Is this IPv6 address just an old IPv4 address in disguise?**" We will rarely need to use this in modern applications.

### 📌 Simple Code Example
👉 This program takes a domain name (like `google.com`), finds all the IP addresses associated with it, and then checks whether each one is an IPv4 or IPv6 address.

```java name=IPAddressChecker.java
import java.net.InetAddress;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.UnknownHostException;

public class IPAddressChecker {

    public static void main(String[] args) {
        // We will check the IP addresses for "google.com" and "localhost"
        String[] hosts = {"google.com", "localhost"};

        for (String host : hosts) {
            System.out.println("------------------------------------");
            System.out.println("Looking up addresses for: " + host);
            System.out.println("------------------------------------");

            try {
                // Get all IP addresses for the given host
                InetAddress[] addresses = InetAddress.getAllByName(host);

                // Loop through each address
                for (InetAddress addr : addresses) {
                    // Check the type of the address
                    if (addr instanceof Inet4Address) {
                        System.out.println("Found IPv4 Address:");
                        System.out.println("  -> " + addr.getHostAddress());
                    } else if (addr instanceof Inet6Address) {
                        System.out.println("Found IPv6 Address:");
                        System.out.println("  -> " + addr.getHostAddress());
                        
                        // You can also check if it's a special type of IPv6 address
                        Inet6Address ipv6Addr = (Inet6Address) addr;
                        if (ipv6Addr.isIPv4CompatibleAddress()) {
                            System.out.println("    (This is an IPv4-compatible IPv6 address)");
                        }
                    }
                }
            } catch (UnknownHostException e) {
                System.err.println("Could not find host: " + host);
                e.printStackTrace();
            }
            System.out.println();
        }
    }
}
```
### 💡 Example Output
The above code output will look something like this (the specific IP addresses may vary):

```
------------------------------------
Looking up addresses for: google.com
------------------------------------
Found IPv4 Address:
  -> 142.250.199.14
Found IPv6 Address:
  -> 2404:6800:4007:829::200e

------------------------------------
Looking up addresses for: localhost
------------------------------------
Found IPv4 Address:
  -> 127.0.0.1
Found IPv6 Address:
  -> 0:0:0:0:0:0:0:1

```