## Explanation to Positive and Negative TTL in simple term

### 💭 Simple Explanation: The "Contact List" Analogy
➡️ Imagine your program is a personal assistant, and its job is to call people (connect to servers). To do this, it needs their phone numbers (IP addresses).

*   **Looking up a number for the first time** is like calling the main directory assistance (a **DNS lookup**). This is slow.
*   **Caching** is your assistant writing the number down in its own private contact list so it doesn't have to call directory assistance again.

#### 👉 Now, let's look at the two settings, which are like rules for managing this contact list.

---

### 1. `networkaddress.cache.ttl` (The "Good Number" Rule)

↳ This stands for **Time To Live** for a **successful** lookup.

*   **What it is:** This rule tells your assistant: "When you find a phone number that works, how long should you keep it in your contact list before you double-check it with directory assistance again?"
*   **Analogy:** You look up "Google's" phone number and it works. You save it in your phone's contacts. The `ttl` is how long you trust that saved number. By default, Java trusts it for a long time because a company's main phone number usually doesn't change.
*   **The `-1` Value:** Setting the `ttl` to `-1` is like telling your assistant: "Once you find a good number, save it and **never** double-check it again for as long as this program is running. Trust it forever."

#### 👉 **In short: This controls how long to remember a *successful* lookup.**

---

### 2. `networkaddress.cache.negative.ttl` (The "Bad Number" Rule)

↳ This stands for **Time To Live** for a **failed** lookup.

*   **What it is:** This rule tells your assistant: "When you try to find a phone number and directory assistance tells you 'no such person exists', how long should you remember that it failed?"
*   **Analogy:** You try to look up the number for `"non-existent-company.com"`. It fails. Your assistant makes a note: "Don't bother trying this number again for a little while." By default, Java only remembers this failure for **10 seconds**.
*   **Why is this time so short?** Because the failure might have been a temporary glitch! Maybe the line was busy, or you had a bad connection to directory assistance. By only remembering the failure for 10 seconds, your assistant is willing to try again soon, just in case it works the next time.
*   **The `-1` Value:** Setting the `negative.ttl` to `-1` is like telling your assistant: "If a number fails once, assume it will **always** fail. Never even try to look it up again."

### 👉 **In short: This controls how long to remember a *failed* lookup.**

### Simple Code Example

This code shows how you would set these properties in your Java program *before* you make any network calls.

```java name=CacheConfigDemo.java
import java.net.InetAddress;
import java.net.UnknownHostException;

public class CacheConfigDemo {

    public static void main(String[] args) {
        
        // --- CONFIGURATION ---
        // You set these properties BEFORE any networking happens.
        
        // Rule 1: Keep successful lookups in the cache for 30 seconds.
        System.setProperty("networkaddress.cache.ttl", "30");

        // Rule 2: Remember a failed lookup for only 5 seconds.
        System.setProperty("networkaddress.cache.negative.ttl", "5");
        
        System.out.println("--- Cache Properties Set ---");
        System.out.println("Positive TTL (successful lookups): " + System.getProperty("networkaddress.cache.ttl") + " seconds");
        System.out.println("Negative TTL (failed lookups): " + System.getProperty("networkaddress.cache.negative.ttl") + " seconds\n");
        

        // --- DEMONSTRATING THE CACHE ---
        
        // 1. Look up a host that exists
        try {
            System.out.println("Looking up 'github.com'...");
            InetAddress.getByName("github.com");
            System.out.println(" -> Success. This result will now be cached for 30 seconds.");
        } catch (UnknownHostException e) {
            System.err.println(" -> Could not find github.com");
        }
        
        System.out.println();

        // 2. Look up a host that does NOT exist
        try {
            System.out.println("Looking up 'this-host-definitely-does-not-exist.com'...");
            InetAddress.getByName("this-host-definitely-does-not-exist.com");
        } catch (UnknownHostException e) {
            System.out.println(" -> Failed as expected. This 'not found' result will be cached for 5 seconds.");
            System.out.println("    (If you try again within 5s, the failure will be instant from the cache).");
        }
    }
} // This code doesn't measure the cache time, but it shows you the correct way to apply the rules to your Java application. You would run this with `java CacheConfigDemo`.
```