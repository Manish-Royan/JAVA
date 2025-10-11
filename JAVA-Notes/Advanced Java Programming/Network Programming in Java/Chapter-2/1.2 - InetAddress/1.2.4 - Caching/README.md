# Caching and Resolution Mechanics

### ➡️ InetAddress performs DNS lookups when you call methods like `InetAddress.getByName("host")`. Because DNS lookups can be slow, Java caches the results inside the JVM so repeated lookups for the same hostname don’t repeatedly hit the network.  

***

### 💭 Simple Explanation: The "Phonebook" Analogy
➡️ Imagine you need to find the address of a friend. The first time, you have to look it up in a giant, city-wide phonebook (this is the **DNS lookup**). This process can be slow, especially if the phonebook is far away.

➡️ Now, imagine that after you find the address, you write it down on a sticky note and put it on your desk. This is exactly what **caching** is.

*   **The Problem:** Looking up an address in the main phonebook (`DNS`) every single time is slow and inefficient.
*   **Java's Solution (Caching):** The first time Java looks up a hostname (like `google.com`), it "writes it down on a sticky note." The next time your program asks for the same hostname, Java just reads the sticky note instead of going back to the big phonebook. This is much, much faster.

### \# There are two separate caches 👉 Two Types of "Sticky Notes"

1.  **Successful Lookups (Positive Cache):** stores successful name → address mappings (a host that was resolved). By default these entries expire according to the JVM’s configuration.  
    *   **What it is:** You looked up `google.com` and found its IP address.
    *   **Java's Behavior:** Java keeps this "sticky note" for a long time (or even forever, until your program stops). It assumes the address won't change.
    *   **Controlling it:** The `networkaddress.cache.ttl` (Time To Live) setting tells Java how many seconds to keep this sticky note before throwing it away.

2.  **Failed Lookups (Negative Cache):** stores failed lookups (host not found) for a short time because failures are often transient; Java caches negative results for a short default interval to avoid thrashing the resolver with repeated failing queries. 
    *   **What it is:** You tried to look up a hostname that doesn't exist, like `this-host-does-not-exist.com`.
    *   **Java's Behavior:** The failure could be temporary (maybe the network was slow). So, Java makes a "sticky note" that says "NOT FOUND," but it **only keeps it for 10 seconds** by default. After 10 seconds, it throws the note away and will try the main phonebook again if you ask.
    *   **Controlling it:** The `networkaddress.cache.negative.ttl` setting tells Java how many seconds to remember a failed lookup.

- [The lifetimes of these caches are controlled by security properties](https://github.com/Manish-Royan/JAVA/tree/main/JAVA-Notes/Advanced%20Java%20Programming/Network%20Programming%20in%20Java/Chapter-2/1.2%20-%20InetAddress/%23%20More%20Depth%20Explorations/2.1%20MDE%20-%20Lifetimes%20of%20Positive%20TTL%20and%20Negative%20TTL):
  - `networkaddress.cache.ttl` — seconds to keep successful lookups (positive TTL). -1 means cache forever.  
  - `networkaddress.cache.negative.ttl` — seconds to keep failed lookups (negative TTL). -1 means cache failed lookups forever.  
- Where to set them:
  - Typically configured in the JDK security configuration (the java.security file) or via Security.setProperty at runtime if your code has permission. They can also be passed as system properties for some older JVM internals, but the supported, documented mechanism is the security property.  
- Why care:
  - Caching speeds up your application and reduces DNS traffic.  
  - It can also make your application “[stale](https://github.com/Manish-Royan/JAVA/tree/main/JAVA-Notes/Advanced%20Java%20Programming/Network%20Programming%20in%20Java/Chapter-2/1.2%20-%20InetAddress/%23%20More%20Depth%20Explorations/2.2%20MDE%20-%20Caching%20made%20application%20stale)” if DNS records change while the JVM is running. Because other DNS caches (OS resolver, upstream DNS servers) add further caching, changes to DNS may take time to propagate anyway.  
  - Negative caching is intentionally short by default because a transient DNS failure should not be treated as permanent.


## 📌 Simple code demonstrating caching by performing two lookups for the smae hostname.
➡️ This code will demonstrate caching by performing two lookups for the same hostname. The first one will be a "real" lookup, and the second one will be read from the cache, which should be almost instantaneous. We will measure the time for each to see the difference.

```java name=DnsCacheDemo.java
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * This program demonstrates how Java's InetAddress class caches DNS lookups.
 * It measures the time taken for the first lookup and the second lookup to the same host.
 */
public class DnsCacheDemo {

    public static void main(String[] args) throws InterruptedException {
        // We can control the cache policy using system properties.
        // Let's set the positive cache to last for only 5 seconds for this demo.
        // A value of "0" would mean no caching. "-1" means cache forever.
        System.setProperty("networkaddress.cache.ttl", "5");

        String hostname = "google.com";
        System.out.println("--- DNS Caching Demonstration ---");
        System.out.println("Positive cache TTL is set to 5 seconds for this run.\n");

        // --- 1. First Lookup (Should be slower) ---
        System.out.println("1. Performing the FIRST lookup for: " + hostname);
        long startTime1 = System.nanoTime();
        try {
            InetAddress address1 = InetAddress.getByName(hostname);
            long endTime1 = System.nanoTime();
            System.out.println("   -> Success! Found address: " + address1.getHostAddress());
            System.out.printf("   -> Time taken: %,d nanoseconds%n", (endTime1 - startTime1));
        } catch (UnknownHostException e) {
            System.err.println("   -> Could not resolve host.");
        }

        System.out.println("\n--------------------------------------------\n");

        // --- 2. Second Lookup (Should be much faster, from cache) ---
        System.out.println("2. Performing the SECOND lookup for the same host (should be from cache):");
        long startTime2 = System.nanoTime();
        try {
            InetAddress address2 = InetAddress.getByName(hostname);
            long endTime2 = System.nanoTime();
            System.out.println("   -> Success! Found address: " + address2.getHostAddress());
            System.out.printf("   -> Time taken: %,d nanoseconds (MUCH FASTER!)%n", (endTime2 - startTime2));
        } catch (UnknownHostException e) {
            System.err.println("   -> Could not resolve host.");
        }
        
        System.out.println("\n--------------------------------------------\n");

        // --- 3. Third Lookup after Cache Expires ---
        System.out.println("3. Waiting for 6 seconds for the cache to expire...");
        Thread.sleep(6000); // Wait for 6 seconds, which is > 5-second TTL
        
        System.out.println("\n   Performing a THIRD lookup (cache has expired, should be slow again):");
        long startTime3 = System.nanoTime();
        try {
            InetAddress address3 = InetAddress.getByName(hostname);
            long endTime3 = System.nanoTime();
            System.out.println("   -> Success! Found address: " + address3.getHostAddress());
            System.out.printf("   -> Time taken: %,d nanoseconds (slow again)%n", (endTime3 - startTime3));
        } catch (UnknownHostException e) {
            System.err.println("   -> Could not resolve host.");
        }
    }
}
```
### 💡 Expected Example Output: (Your times will vary, but the pattern should be the same)

```
--- DNS Caching Demonstration ---
Positive cache TTL is set to 5 seconds for this run.

1. Performing the FIRST lookup for: google.com
   -> Success! Found address: 142.250.200.78
   -> Time taken: 25,431,800 nanoseconds

--------------------------------------------

2. Performing the SECOND lookup for the same host (should be from cache):
   -> Success! Found address: 142.250.200.78
   -> Time taken: 28,100 nanoseconds (MUCH FASTER!)

--------------------------------------------

3. Waiting for 6 seconds for the cache to expire...

   Performing a THIRD lookup (cache has expired, should be slow again):
   -> Success! Found address: 142.250.200.78
   -> Time taken: 21,987,500 nanoseconds (slow again)
```
### 👉 As you can see, the second lookup is thousands of times faster because it reads from the cache. After waiting for the cache's "Time To Live" (TTL) to expire, the third lookup is slow again because Java has to go back to the network.
---

### Q. How to configure at runtime (programmatic)❓

- You can set the caching properties at runtime using `java.security.Security.setProperty` if your environment allows it (code must have permission to change security properties). Changing the property affects subsequent lookups; it does not necessarily flush any existing cached entries.

Properties names:
-   `networkaddress.cache.ttl` — positive TTL in seconds (String value).
-   `networkaddress.cache.negative.ttl` — negative TTL in seconds (String value).

#### ‼️Important: some JVMs/platforms may also respect the OS resolver behavior and additional caches; behavior can vary across JDK versions.

## 📌 Sample code to read and set the cache properties programmatically:

The sample shows:
- How to read and set the cache properties programmatically.  
- Resolve the same hostname several times, showing timestamps so you can observe that lookups are performed; we attempt to demonstrate the idea of caching by toggling the TTL and doing lookups separated by sleeps. Note: because DNS typically returns the same IP and the JVM/OS may cache, you may not be able to *visibly* detect whether the network was hit — but the code shows how to control the JVM caching settings that govern that behavior.

Compile and run this code with an appropriate security policy or on a JVM that allows Security.setProperty calls from application code.

```java
import java.net.InetAddress;
import java.security.Security;
import java.time.LocalTime;

public class DnsCacheDemo {
    private static final String POS_TTL = "networkaddress.cache.ttl";
    private static final String NEG_TTL = "networkaddress.cache.negative.ttl";

    public static void main(String[] args) throws Exception {
        String host = args.length > 0 ? args[0] : "example.com";

        // Show current security properties (may be null if not set)
        System.out.println(timestamp() + " Initial " + POS_TTL + " = " + Security.getProperty(POS_TTL));
        System.out.println(timestamp() + " Initial " + NEG_TTL + " = " + Security.getProperty(NEG_TTL));
        System.out.println();

        // Set positive cache TTL to 5 seconds and negative TTL to 3 seconds
        Security.setProperty(POS_TTL, "5");      // cache successful lookups for 5 seconds
        Security.setProperty(NEG_TTL, "3");      // cache failed lookups for 3 seconds
        System.out.println(timestamp() + " Set " + POS_TTL + " = " + Security.getProperty(POS_TTL));
        System.out.println(timestamp() + " Set " + NEG_TTL + " = " + Security.getProperty(NEG_TTL));
        System.out.println();

        // First resolution — causes actual DNS lookup if not cached
        lookup(host);

        // Second resolution immediately — should come from JVM cache (within 5 seconds)
        lookup(host);

        // Wait shorter than TTL then lookup again — still cached
        Thread.sleep(3000);
        lookup(host);

        // Wait beyond TTL (total > 5s since first lookup) — JVM should perform a new DNS query
        Thread.sleep(3000);
        lookup(host);

        // Demonstrate negative lookup caching: try a likely-nonexistent name
        String bogus = "no-such-host-xyz-12345.example.invalid";
        System.out.println("\n" + timestamp() + " Negative lookup demo with " + bogus);
        tryResolve(bogus); // first time: fail and cache negative
        tryResolve(bogus); // second time within 3s: should see immediate failure from negative cache
        Thread.sleep(3500); // wait beyond negative TTL (3s)
        tryResolve(bogus); // third attempt after TTL: will try DNS again (and fail again)
    }

    private static void lookup(String host) {
        try {
            System.out.println(timestamp() + " Resolving " + host);
            InetAddress a = InetAddress.getByName(host);
            System.out.println(timestamp() + "  -> " + a.getHostAddress());
        } catch (Exception e) {
            System.out.println(timestamp() + "  Lookup failed: " + e.getClass().getSimpleName() + " " + e.getMessage());
        }
    }

    private static void tryResolve(String host) {
        try {
            System.out.println(timestamp() + " Resolving " + host);
            InetAddress a = InetAddress.getByName(host);
            System.out.println(timestamp() + "  -> " + a.getHostAddress());
        } catch (Exception e) {
            System.out.println(timestamp() + "  Lookup failed: " + e.getClass().getSimpleName());
        }
    }

    private static String timestamp() {
        return LocalTime.now().withNano(0).toString();
    }
}
```

### 💡 Expected output (example run)

Note: The exact times and whether a DNS query actually goes to the network depend on the JVM, OS resolver caches, and permissions. This example assumes Security.setProperty succeeded and the JVM honors the TTL. Times shown are illustrative.

Example:
```
10:00:00 Initial networkaddress.cache.ttl = null
10:00:00 Initial networkaddress.cache.negative.ttl = null

10:00:00 Set networkaddress.cache.ttl = 5
10:00:00 Set networkaddress.cache.negative.ttl = 3

10:00:00 Resolving example.com
10:00:00  -> 93.184.216.34
10:00:00 Resolving example.com
10:00:00  -> 93.184.216.34
10:00:03 Resolving example.com
10:00:03  -> 93.184.216.34
10:00:06 Resolving example.com
10:00:06  -> 93.184.216.34

10:00:06 Negative lookup demo with no-such-host-xyz-12345.example.invalid
10:00:06 Resolving no-such-host-xyz-12345.example.invalid
10:00:06  Lookup failed: UnknownHostException
10:00:06 Resolving no-such-host-xyz-12345.example.invalid
10:00:06  Lookup failed: UnknownHostException
10:00:09 Resolving no-such-host-xyz-12345.example.invalid
10:00:09  Lookup failed: UnknownHostException
```

Interpretation:
- The first three lookups for example.com happened within the 5‑second positive TTL, so the JVM served results from its cache for the second and third calls (no new network lookup required). The lookup after 6 seconds occurs after the TTL expiration and therefore triggers a new DNS resolution.  
- For the bogus host, the first lookup fails and is cached for 3 seconds; the immediate second attempt fails instantly from the negative cache; after the negative TTL expires, the JVM tries DNS again on the third attempt.

---


### 🖼️ The Bigger Picture
➡️ Your computer and the internet itself have many layers of caching (like your local post office having its own list of addresses). Java's cache is just the one inside your program. This is why when a website's IP address changes, it can take hours for the change to be seen everywhere—all the old "sticky notes" across the internet need to expire first.


### ☑️ Practical rules and cautions
- Don’t rely on Java’s DNS cache for short-term load-balancing DNS changes; choose a small **positive TTL** if you must react quickly to DNS updates.  
- Setting positive TTL to `-1` (never expire) is useful when you absolutely expect addresses never to change, but it’s risky for environments where services move.  
- Setting negative TTL too long hides transient DNS recovery; keep negative TTL short (default is small, e.g., 10s).  
----