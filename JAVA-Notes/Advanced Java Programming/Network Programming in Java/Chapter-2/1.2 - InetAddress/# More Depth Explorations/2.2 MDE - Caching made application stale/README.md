### What it means by Caching made application “stale”❓

When your Java application looks up a hostname (like `example.com`), it gets an IP address from DNS. To avoid repeating this lookup every time, Java **remembers** (caches) the result for a while.

But here's the catch:

- If the **DNS record changes** (say, `example.com` now points to a new IP), your app might still use the **old IP** from its cache.
- That makes your app **“stale”** — it’s using outdated info and might fail to connect or reach the wrong server.

And even if Java tries to refresh the DNS:

- Your **operating system** also caches DNS results.
- So do **DNS servers** on the internet (like your ISP or cloud provider).
- These layers of caching mean that **DNS changes take time to spread** — sometimes minutes, sometimes hours.

---

### 🧪 Real-world example

Let’s say your cloud service moves from `192.0.2.1` to `192.0.2.99`.

- Java cached `192.0.2.1` for 30 minutes.
- Your OS cached it for 10 minutes.
- Your ISP’s DNS server cached it for 1 hour.

Even if the DNS record is updated instantly, your app might still use the old IP for a while — unless you manually flush caches or reduce TTLs.

---

### 💡 What you can do

- Set Java’s DNS cache TTL using:
  - `networkaddress.cache.ttl` for successful lookups.
  - `networkaddress.cache.negative.ttl` for failed ones.
- Use short TTLs in dynamic environments (like cloud or microservices).
- Consider using a custom DNS resolver (JDK 18+) if you need full control.

---

## 📌 Sample code demostrating how Java caches DNS lookups and how TTL settings affect behavior. You’ll see:
- ✅ Positive caching: repeated lookups return instantly.
* ❌ Negative caching: failed lookups are remembered briefly.
- ⏳ TTL expiration: after a delay, Java re-queries DNS.
```java
import java.net.InetAddress;
import java.security.Security;
import java.time.LocalTime;

public class DnsCachingDemo {
    public static void main(String[] args) throws Exception {
        String host = "example.com"; // A known host
        String badHost = "no-such-host-xyz-12345.example.invalid"; // A fake host

        // Set TTLs (requires permission)
        Security.setProperty("networkaddress.cache.ttl", "5");      // Cache good results for 5s
        Security.setProperty("networkaddress.cache.negative.ttl", "3"); // Cache failures for 3s

        System.out.println(now() + " First lookup (should hit DNS)");
        resolve(host);

        System.out.println(now() + " Second lookup (should be cached)");
        resolve(host);

        Thread.sleep(6000); // Wait beyond TTL
        System.out.println(now() + " Third lookup (should re-query DNS)");
        resolve(host);

        System.out.println("\n" + now() + " Negative lookup (should fail)");
        resolve(badHost);

        System.out.println(now() + " Immediate retry (should be cached failure)");
        resolve(badHost);

        Thread.sleep(4000); // Wait beyond negative TTL
        System.out.println(now() + " Retry after TTL (should re-query DNS)");
        resolve(badHost);
    }

    private static void resolve(String host) {
        try {
            InetAddress addr = InetAddress.getByName(host);
            System.out.println("  -> " + addr.getHostAddress());
        } catch (Exception e) {
            System.out.println("  -> Failed: " + e.getClass().getSimpleName());
        }
    }

    private static String now() {
        return LocalTime.now().withNano(0).toString();
    }
}
```

### Expected Output:
```
01:56:00 First lookup (should hit DNS)
  -> 93.184.216.34
01:56:00 Second lookup (should be cached)
  -> 93.184.216.34
01:56:06 Third lookup (should re-query DNS)
  -> 93.184.216.34

01:56:06 Negative lookup (should fail)
  -> Failed: UnknownHostException
01:56:06 Immediate retry (should be cached failure)
  -> Failed: UnknownHostException
01:56:10 Retry after TTL (should re-query DNS)
  -> Failed: UnknownHostException
```