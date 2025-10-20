# Java URL Methods: Equality, Hashing, and String Representations
➡️ These methods govern how URL objects are compared, hashed, and represented as strings and how they convert to URI. They interact with the URL’s components and in some cases consult name resolution, which can make them blocking or environment dependent.


## 1. `equals(Object obj)` - Comparing URLs for Equality

### 🔸Simple Explanation
➡️ The `equals()` method checks if two URL objects point to the exact same resource. It compares:
- Protocol (http/https) - case-insensitive
- Host (website name) - case-insensitive
- Port number
- Path and query
- Fragment/reference

### ↳ What Does `equals(Object obj)` Do for URLs❓
➡️ In Java, calling `url1.equals(url2)` checks whether **two URL objects refer to the same network resource** — not just whether their strings look the same.

### ✅ It compares:
- **Protocol** (e.g., `http`, `https`)
- **Host** (e.g., `www.example.com`, `EXAMPLE.COM`)
- **Port** (explicit or default)
- **Path** (e.g., `/docs/page.html`)
- **Query** (e.g., `?x=1&y=2`)
- **Fragment** (e.g., `#section3`)

## ⚠️Important Behaviors:

### 1. **Host Comparison May Use DNS Lookup**
- Java may resolve hostnames to their **canonical form** using DNS.
- This means `equals()` might **block** (pause) while performing a network lookup.
- Two URLs with different hostnames may be considered equal **if they resolve to the same IP**.

```java
URL u1 = new URL("http://example.com");
URL u2 = new URL("http://www.example.com");
System.out.println(u1.equals(u2)); // Might be true if DNS resolves them to same IP
```

### 2. **Case Sensitivity**
- **Protocol and host** are compared **case-insensitively**.
- **Path, query, and fragment** are compared **case-sensitively**.

```java
URL u1 = new URL("HTTP://EXAMPLE.COM/page");
URL u2 = new URL("http://example.com/page");
System.out.println(u1.equals(u2)); // true ✅

URL u3 = new URL("http://example.com/Page");
System.out.println(u1.equals(u3)); // false ❌ (path is case-sensitive)
```

### 3. **Default Ports Are Normalized**
- If one URL uses the default port and the other explicitly specifies it, they’re considered equal.

```java
URL u1 = new URL("http://example.com");
URL u2 = new URL("http://example.com:80");
System.out.println(u1.equals(u2)); // true ✅
```

---

## 📌Example

```java
import java.net.URL;

public class URLCompareDemo {
    public static void main(String[] args) throws Exception {
        URL url1 = new URL("http://example.com:80/docs/page.html?x=1#top");
        URL url2 = new URL("http://EXAMPLE.com/docs/page.html?x=1#top");

        System.out.println("URL 1: " + url1.toExternalForm());
        System.out.println("URL 2: " + url2.toExternalForm());
        System.out.println("Are they equal? " + url1.equals(url2)); // true ✅
    }
}
```

### 👇**Important**: This method may trigger a DNS lookup to check if two different hostnames actually resolve to the same IP address, which can be slow and cause your program to freeze momentarily.

### 📌Example

```java name=URLEqualsExample.java
import java.net.URL;

public class URLEqualsExample {
    public static void main(String[] args) {
        System.out.println("Demo for URL equals() Method");
        System.out.println("=================================================\n");
        
        try {
            // Case 1: Identical URLs
            URL url1 = new URL("https://www.example.com/index.html");
            URL url2 = new URL("https://www.example.com/index.html");
            
            System.out.println("Case 1: Identical URLs");
            System.out.println("URL 1: " + url1);
            System.out.println("URL 2: " + url2);
            System.out.println("url1.equals(url2): " + url1.equals(url2));  // true
            
            // Case 2: Case differences in protocol and host (should still be equal)
            URL url3 = new URL("https://www.example.com/index.html");
            URL url4 = new URL("HTTPS://WWW.EXAMPLE.COM/index.html");
            
            System.out.println("\nCase 2: Case differences in protocol/host");
            System.out.println("URL 3: " + url3);
            System.out.println("URL 4: " + url4);
            System.out.println("url3.equals(url4): " + url3.equals(url4));  // true
            
            // Case 3: Different fragments
            URL url5 = new URL("https://www.example.com/page.html#section1");
            URL url6 = new URL("https://www.example.com/page.html#section2");
            
            System.out.println("\nCase 3: Different fragments");
            System.out.println("URL 5: " + url5);
            System.out.println("URL 6: " + url6);
            System.out.println("url5.equals(url6): " + url5.equals(url6));  // false
            
            // Case 4: Default vs explicit port (default port for HTTPS is 443)
            URL url7 = new URL("https://www.example.com/");
            URL url8 = new URL("https://www.example.com:443/");
            
            System.out.println("\nCase 4: Default vs explicit port");
            System.out.println("URL 7: " + url7);
            System.out.println("URL 8: " + url8);
            System.out.println("url7.equals(url8): " + url7.equals(url8));  // true
            
            // Case 5: Same IP but different hostname (WARNING: May cause DNS lookup!)
            System.out.println("\nCase 5: Different hostnames (may trigger DNS lookup)");
            System.out.println("Note: This comparison may be slow due to DNS resolution");
            
            // Using example.com and example.net which are different domains
            URL url9 = new URL("http://example.com/");
            URL url10 = new URL("http://example.net/");
            System.out.println("URL 9: " + url9);
            System.out.println("URL 10: " + url10);
            // This might trigger DNS resolution:
            System.out.println("url9.equals(url10): " + url9.equals(url10));  // false (usually)
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
```

### ✅ Summary

| Compared Component | Case Sensitive? | Notes |
|--------------------|------------------|-------|
| Protocol           | ❌ No             | `http` = `HTTP` |
| Host               | ❌ No             | May resolve via DNS |
| Port               | ✅ Yes (but normalized) | Default ports treated equally |
| Path               | ✅ Yes            | `/docs` ≠ `/Docs` |
| Query              | ✅ Yes            | `x=1` ≠ `X=1` |
| Fragment           | ✅ Yes            | `#top` ≠ `#Top` |

---

#### 👉 `equals()` for URLs is **not just string comparison** — it’s a **semantic comparison** of network identity. That’s why it can block, depend on DNS, and treat some differences as irrelevant (like case in hostnames).


## 2. `sameFile(URL other)` - Comparing URLs Without Fragments

### 🔸Simple Explanation
➡️ The `sameFile()` method is like `equals()` but **ignores the fragment/reference part** (the part after `#`). This is useful when you want to check if two URLs point to the same underlying resource, even if they target different sections of that resource.

### ↳ What Does `sameFile()` Do❓
➡️ The method `sameFile(URL other)` checks whether **two URLs point to the same server-side resource**, ignoring any **fragment identifiers** (`#section`).

### ✅ It compares:
- **Protocol** (e.g., `http`, `https`)
- **Host** (e.g., `www.example.com`)
- **Port** (explicit or default)
- **Path** (e.g., `/docs/page.html`)
- **Query** (e.g., `?x=1&y=2`)

### ❌ It ignores:
- **Fragment** (e.g., `#top`, `#section2`)

### ❌ Why Ignore the Fragment❓
➡️ Because fragments are **client-side only** — they’re used by browsers to scroll to a section of a page, but they’re **not sent to the server**. So if you’re checking whether two URLs refer to the **same server resource**, the fragment is irrelevant.

### 📌 Example

```java
import java.net.URL;

public class SameFileDemo {
    public static void main(String[] args) throws Exception {
        URL url1 = new URL("https://www.example.com/docs/page.html?x=1#section2");
        URL url2 = new URL("https://www.example.com/docs/page.html?x=1#top");

        System.out.println("url1.equals(url2):   " + url1.equals(url2));   // false ❌ (fragment differs)
        System.out.println("url1.sameFile(url2): " + url1.sameFile(url2)); // true ✅ (same server resource)
    }
}
```

### When to Use `sameFile()`❓
- When you want to know if **two URLs hit the same server endpoint**, regardless of where the browser scrolls.
- Useful for:
  - Caching
  - Deduplication
  - Logging
  - Routing
  - Resource validation

### 💭 Analogy
💭 Imagine two URLs:
- `https://docs.com/page.html#intro`
- `https://docs.com/page.html#conclusion`

#### They point to the **same file**, just different sections.  
✅ `sameFile()` says: “Yes, same file.”  
❌ `equals()` says: “No, different fragments.”

### 📌Example

```java name=URLSameFileExample.java
import java.net.URL;

public class URLSameFileExample {
    public static void main(String[] args) {
        System.out.println("Demo for URL sameFile() Method");
        System.out.println("====================================================\n");
        
        try {
            // Create URLs with different fragments but same resource
            URL documentUrl1 = new URL("https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/net/URL.html#equals(java.lang.Object)");
            URL documentUrl2 = new URL("https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/net/URL.html#sameFile(java.net.URL)");
            URL documentUrl3 = new URL("https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/net/URL.html");
            
            // Display the URLs
            System.out.println("URL 1: " + documentUrl1);
            System.out.println("URL 2: " + documentUrl2);
            System.out.println("URL 3: " + documentUrl3);
            
            // Compare with equals() - different fragments means not equal
            System.out.println("\n=== equals() Comparison ===");
            System.out.println("url1.equals(url2): " + documentUrl1.equals(documentUrl2));  // false
            System.out.println("url2.equals(url3): " + documentUrl2.equals(documentUrl3));  // false
            System.out.println("url1.equals(url3): " + documentUrl1.equals(documentUrl3));  // false
            
            // Compare with sameFile() - ignores fragments, so all are the same file
            System.out.println("\n=== sameFile() Comparison ===");
            System.out.println("url1.sameFile(url2): " + documentUrl1.sameFile(documentUrl2));  // true
            System.out.println("url2.sameFile(url3): " + documentUrl2.sameFile(documentUrl3));  // true
            System.out.println("url1.sameFile(url3): " + documentUrl1.sameFile(documentUrl3));  // true
            
            // Real-world use case: Checking if two URLs point to the same PDF but different pages
            URL pdfPage1 = new URL("https://example.com/documents/report.pdf#page=1");
            URL pdfPage5 = new URL("https://example.com/documents/report.pdf#page=5");
            
            System.out.println("\n=== Real-world Use Case: PDF Pages ===");
            System.out.println("PDF URL 1: " + pdfPage1);
            System.out.println("PDF URL 2: " + pdfPage5);
            System.out.println("equals(): " + pdfPage1.equals(pdfPage5));           // false - different pages
            System.out.println("sameFile(): " + pdfPage1.sameFile(pdfPage5));       // true - same document
            
            // Demonstration with query parameters (still considered part of the "file")
            URL queryUrl1 = new URL("https://www.youtube.com/watch?v=dQw4w9WgXcQ");
            URL queryUrl2 = new URL("https://www.youtube.com/watch?v=dQw4w9WgXcQ#t=30");
            URL queryUrl3 = new URL("https://www.youtube.com/watch?v=different");
            
            System.out.println("\n=== Query Parameters and Fragments ===");
            System.out.println("url1.sameFile(url2): " + queryUrl1.sameFile(queryUrl2));  // true - same file, different fragment
            System.out.println("url1.sameFile(url3): " + queryUrl1.sameFile(queryUrl3));  // false - different query = different file
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
```

### ✅ Summary

| Method        | Compares Fragment? | Use Case |
|---------------|---------------------|----------|
| `equals()`    | ✅ Yes              | Full URL identity (client + server) |
| `sameFile()`  | ❌ No               | Server-side resource comparison |


## 3. `hashCode()` - Hash Value for URLs

### 🔸Simple Explanation
➡️ The `hashCode()` method generates a numeric value for a URL, used when storing URLs in hash-based collections like `HashMap` or `HashSet`. It's designed to be consistent with `equals()`, so two URLs that are equal will produce the same hash code.

### ↳ What is `hashCode()` in Java❓
➡️ In Java, every object has a `hashCode()` method that returns an integer. This number is used in:
- **Hash-based collections** like `HashMap`, `HashSet`, `Hashtable`
- **Fast lookups**, comparisons, and deduplication

#### 👉 For a `URL` object, `hashCode()` is designed to be **consistent with `equals()`** — meaning:
> If two URLs are considered equal by `equals()`, they must return the same `hashCode()`.

### ⚠️ Why Is `URL.hashCode()` Special (and Risky)❓
➡️ Unlike most objects, `URL.equals()` may perform **DNS resolution** to compare hostnames. That means:
- `hashCode()` might **block** (pause) while resolving hostnames.
- It might return **different values** depending on the **network environment** or **DNS state**.
- It’s **not guaranteed to be stable** across machines or sessions.

### 📌Example:

```java
URL u1 = new URL("http://example.com");
URL u2 = new URL("http://93.184.216.34"); // example.com's IP

System.out.println(u1.equals(u2));   // Might be true if DNS resolves them as same
System.out.println(u1.hashCode());   // Might differ across environments
System.out.println(u2.hashCode());   // Might differ too
```

#### 👉 Even if `u1.equals(u2)` returns `true`, their `hashCode()` values might differ depending on how the hostnames resolve.

### ↳ Why You Should Be Careful❓

| Risk | Explanation |
|------|-------------|
| **Blocking** | `hashCode()` may trigger DNS lookup, slowing down your app |
| **Unstable** | Same URL string may yield different hash codes on different machines |
| **Not portable** | You can't reliably use `URL.hashCode()` as a persistent key in files or databases |


## ✅ Best Practices

- **Avoid using `URL.hashCode()` for persistent keys** (e.g., cache keys, database IDs).
- If you need a **stable hash**, use `toExternalForm()` and hash the string manually:

```java
String stableKey = url.toExternalForm();
int safeHash = stableKey.hashCode(); // Stable across environments
```

### ⚠️**Warning**: Like `equals()`, this method may trigger DNS lookups to resolve hostnames, which can make it slow and potentially block your program.

### 📌Example

```java name=URLHashCodeExample.java
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;

public class URLHashCodeExample {
    public static void main(String[] args) {
        System.out.println("Demo for URL hashCode() Method");
        System.out.println("===================================================\n");
        
        try {
            // Create some URLs
            URL url1 = new URL("https://www.oracle.com/java/");
            URL url2 = new URL("https://www.ORACLE.com/java/");  // Different case
            URL url3 = new URL("https://www.oracle.com/java/#download");  // With fragment
            
            // Display hash codes
            System.out.println("URL 1: " + url1);
            System.out.println("Hash code: " + url1.hashCode());
            
            System.out.println("\nURL 2 (different case): " + url2);
            System.out.println("Hash code: " + url2.hashCode());
            System.out.println("Same hash as URL 1? " + (url1.hashCode() == url2.hashCode()));
            
            System.out.println("\nURL 3 (with fragment): " + url3);
            System.out.println("Hash code: " + url3.hashCode());
            System.out.println("Same hash as URL 1? " + (url1.hashCode() == url3.hashCode()) + 
                            " (fragments are included in hashCode)");
            
            // Using URLs in hash-based collections
            System.out.println("\n=== URLs in Hash Collections ===");
            
            // HashSet example
            HashSet<URL> visitedUrls = new HashSet<>();
            visitedUrls.add(url1);
            
            System.out.println("Added " + url1 + " to HashSet");
            System.out.println("Contains url1? " + visitedUrls.contains(url1));  // true
            System.out.println("Contains url2 (case-insensitive host)? " + visitedUrls.contains(url2));  // true
            System.out.println("Contains url3 (with fragment)? " + visitedUrls.contains(url3));  // false
            
            // HashMap example
            HashMap<URL, String> urlTitles = new HashMap<>();
            urlTitles.put(new URL("https://github.com/"), "GitHub: Where the world builds software");
            urlTitles.put(new URL("https://stackoverflow.com/"), "Stack Overflow - Where Developers Learn & Share");
            
            System.out.println("\nURL -> Page Title Map:");
            for (URL url : urlTitles.keySet()) {
                System.out.println(url + " => \"" + urlTitles.get(url) + "\"");
            }
            
            // Warning about DNS lookups
            System.out.println("\n⚠️ WARNING: URL.hashCode() may cause DNS lookups!");
            System.out.println("This can make hash operations surprisingly slow.");
            System.out.println("Consider using URIs instead for hash-based collections.");
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
```

### ✅ Summary

| Method         | Behavior |
|----------------|----------|
| `hashCode()`   | May block, may vary across environments, tied to `equals()` |
| `equals()`     | May resolve hostnames via DNS |
| Safe alternative | Use `toExternalForm().hashCode()` for stable, network-independent keys |


## 4. `toString()` and `toExternalForm()` - Converting URL to String

### 🔸Simple Explanation
➡️ Both methods convert a URL object back to its string representation:
- `toString()`: Returns the string representation of the URL
- `toExternalForm()`: Also returns the string representation, but through the URL handler

#### 👉 In practice, these methods usually return identical results. They're useful for displaying URLs, logging, or when you need to pass a URL as a string to another function.

### ↳ What Do `toString()` and `toExternalForm()` Do❓
➡️ Both methods return the **complete, canonical string form** of a URL — meaning they rebuild the full URL from its internal components:
```
<protocol>://<userInfo@><host>:<port><path>?<query>#<fragment>
```

### 🗝️ Key traits:
- They include **everything**: protocol, host, port, path, query, fragment, and user info (if present).
- They reflect how the URL is **parsed and modeled** internally.
- They are **safe for logging, display, and string-based comparisons**.
- They **do not trigger any network activity** — no DNS lookups, no connections.

### 📌Example

```java
import java.net.URL;

public class URLStringDemo {
    public static void main(String[] args) throws Exception {
        URL url = new URL("https://admin:pass@www.example.com:8443/docs/page.html?x=1&y=2#section3");

        System.out.println("toString():         " + url.toString());
        System.out.println("toExternalForm():   " + url.toExternalForm());
    }
}
```

### 💡 Output:
```
toString():         https://admin:pass@www.example.com:8443/docs/page.html?x=1&y=2#section3
toExternalForm():   https://admin:pass@www.example.com:8443/docs/page.html?x=1&y=2#section3
```

#### 👉 ✅ Both methods return the same result — the full URL as a string.

### ↳ Why Are They Useful❓

| Use Case            | Benefit |
|---------------------|---------|
| **Logging**         | Shows the full URL for debugging or audit trails |
| **Display**         | Presents the exact URL to users or in reports |
| **String-based keys** | Useful for caching, deduplication, or comparison |
| **Safe inspection** | No network calls, so it’s fast and predictable |

### ↳ Difference Between Them

| Method             | Description |
|--------------------|-------------|
| `toString()`       | Standard method inherited from `Object`, overridden to return full URL |
| `toExternalForm()` | More explicit method name, but returns the same result |

#### 👉 They are functionally **identical** for `URL` objects — use whichever fits your code style.

### 📌Example

```java name=URLToStringExample.java
import java.net.URL;

public class URLToStringExample {
    public static void main(String[] args) {
        System.out.println("Demo for URL toString() and toExternalForm()");
        System.out.println("=============================================================\n");
        
        try {
            // Create some URLs
            URL simpleUrl = new URL("https://www.google.com/search?q=java");
            URL complexUrl = new URL("https://username:password@api.example.com:8443/v2/users?active=true#results");
            URL ipv6Url = new URL("https://[2001:db8::1]:8080/path");
            
            // Compare toString() and toExternalForm()
            System.out.println("=== Simple URL ===");
            System.out.println("Original: https://www.google.com/search?q=java");
            System.out.println("toString(): " + simpleUrl.toString());
            System.out.println("toExternalForm(): " + simpleUrl.toExternalForm());
            System.out.println("Are they equal? " + simpleUrl.toString().equals(simpleUrl.toExternalForm()));
            
            System.out.println("\n=== Complex URL ===");
            System.out.println("Original: https://username:password@api.example.com:8443/v2/users?active=true#results");
            System.out.println("toString(): " + complexUrl.toString());
            System.out.println("toExternalForm(): " + complexUrl.toExternalForm());
            System.out.println("Are they equal? " + complexUrl.toString().equals(complexUrl.toExternalForm()));
            
            System.out.println("\n=== IPv6 URL ===");
            System.out.println("Original: https://[2001:db8::1]:8080/path");
            System.out.println("toString(): " + ipv6Url.toString());
            System.out.println("toExternalForm(): " + ipv6Url.toExternalForm());
            System.out.println("Are they equal? " + ipv6Url.toString().equals(ipv6Url.toExternalForm()));
            
            // Real-world usage examples
            System.out.println("\n=== Real-world Usage ===");
            
            // Example 1: Logging
            URL apiUrl = new URL("https://api.github.com/users/Manish-Bishwakarma");
            System.out.println("Log entry: [INFO] Sending request to " + apiUrl.toString());
            
            // Example 2: Building HTML links
            URL documentUrl = new URL("https://docs.oracle.com/en/java/javase/21/docs/api/");
            String htmlLink = "<a href=\"" + documentUrl.toExternalForm() + "\">Java 21 Documentation</a>";
            System.out.println("HTML Link: " + htmlLink);
            
            // Example 3: HTTP redirect
            URL originalUrl = new URL("http://example.com/old-page");
            URL newUrl = new URL("http://example.com/new-page");
            System.out.println("HTTP/1.1 302 Found");
            System.out.println("Location: " + newUrl.toExternalForm());
            System.out.println("Content-Type: text/html");
            System.out.println("");
            System.out.println("<html><body>Redirecting from " + originalUrl + " to " + newUrl + "...</body></html>");
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
```

### ✅ Summary

| Method               | Returns full URL string | Includes all components | Triggers network I/O? |
|----------------------|-------------------------|--------------------------|------------------------|
| `toString()`         | ✅ Yes                  | ✅ Yes                   | ❌ No                  |
| `toExternalForm()`   | ✅ Yes                  | ✅ Yes                   | ❌ No                  |


## 5. `toURI()` - Converting URL to URI

### 🔸Simple Explanation
➡️ The `toURI()` method converts a URL object to a URI object. This is useful because:

- URI objects handle special characters better
- URIs don't perform blocking DNS lookups
- URIs are better for parsing and manipulating URLs
- Many modern Java APIs prefer URI over URL

#### 👉 However, if the URL contains invalid characters for a URI, this method will throw a `URISyntaxException`.

### ↳ What Does `toURI()` Do❓
➡️ The method `toURI()` converts a `URL` object into a `URI` object. While both represent resource locators, **URI** is more flexible and standards-compliant for parsing, manipulating, and resolving components.

### ✅ Key traits:
- Converts a `URL` into a **RFC 3986-compliant URI**.
- Enables **safe parsing**, **normalization**, and **resolution** of components.
- Throws a **`URISyntaxException`** if the URL contains illegal characters (like spaces, unescaped symbols, etc.).
- Recommended when you need to **manipulate parts of the URL** (e.g., scheme, host, path, query) or resolve relative paths.

### ↳ Why Use `toURI()`❓

| Feature | `URL` | `URI` |
|--------|-------|-------|
| Network access | ✅ Yes | ❌ No |
| Component parsing | Limited | ✅ Rich |
| Safe manipulation | ❌ No | ✅ Yes |
| Resolution of relative paths | ❌ No | ✅ Yes |
| Standards compliance | Partial | ✅ Full (RFC 3986) |

### 📌Example

```java
import java.net.URL;
import java.net.URI;

public class ToURIDemo {
    public static void main(String[] args) throws Exception {
        URL url = new URL("https://www.example.com/docs/page.html?x=1&y=2#section3");

        URI uri = url.toURI(); // Converts safely
        System.out.println("URI: " + uri);

        System.out.println("Scheme: " + uri.getScheme());       // https
        System.out.println("Host: " + uri.getHost());           // www.example.com
        System.out.println("Path: " + uri.getPath());           // /docs/page.html
        System.out.println("Query: " + uri.getQuery());         // x=1&y=2
        System.out.println("Fragment: " + uri.getFragment());   // section3
    }
}
```

### ⚠️ When Does `toURI()` Throw an Exception❓
➡️ If the URL contains **illegal characters**, like:
- Unescaped spaces
- Control characters
- Improper percent-encoding

```java
URL badUrl = new URL("https://www.example.com/docs/my file.html"); // space in path
URI uri = badUrl.toURI(); // ❌ Throws URISyntaxException
```

### ✅ Solution: Use `URI` directly with proper encoding, or sanitize the URL before converting.

### 📌Example

```java name=URLToURIExample.java
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class URLToURIExample {
    public static void main(String[] args) {
        System.out.println("Demo for URL toURI() Method");
        System.out.println("================================================\n");
        
        try {
            // Simple URL conversion
            URL url1 = new URL("https://www.example.com/path/to/resource");
            URI uri1 = url1.toURI();
            
            System.out.println("Original URL: " + url1);
            System.out.println("Converted URI: " + uri1);
            
            // URL with query parameters
            URL url2 = new URL("https://www.google.com/search?q=java+tutorial");
            URI uri2 = url2.toURI();
            
            System.out.println("\nURL with query: " + url2);
            System.out.println("Converted URI: " + uri2);
            
            // URL with special characters (might need proper encoding)
            String term = "Java URL & URI";
            String encodedTerm = URLEncoder.encode(term, StandardCharsets.UTF_8.toString());
            URL url3 = new URL("https://www.bing.com/search?q=" + encodedTerm);
            URI uri3 = url3.toURI();
            
            System.out.println("\nURL with encoded chars: " + url3);
            System.out.println("Converted URI: " + uri3);
            
            // Benefits of URI over URL - parsing components
            System.out.println("\n=== Benefits of URI over URL ===");
            
            URI githubUri = new URI("https://github.com/Manish-Bishwakarma/project");
            System.out.println("URI: " + githubUri);
            System.out.println("Scheme: " + githubUri.getScheme());
            System.out.println("Host: " + githubUri.getHost());
            System.out.println("Path: " + githubUri.getPath());
            System.out.println("Is Absolute? " + githubUri.isAbsolute());
            
            // Resolving relative URIs (can't do this easily with URL)
            URI baseUri = new URI("https://docs.oracle.com/en/java/");
            URI relativeUri = new URI("javase/21/docs/api/index.html");
            URI resolvedUri = baseUri.resolve(relativeUri);
            
            System.out.println("\n=== URI Resolution (not possible with URL) ===");
            System.out.println("Base URI: " + baseUri);
            System.out.println("Relative URI: " + relativeUri);
            System.out.println("Resolved URI: " + resolvedUri);
            
            // Convert back to URL for connection
            URL resolvedUrl = resolvedUri.toURL();
            System.out.println("Back to URL: " + resolvedUrl);
            
            // Converting from URL to URI may fail for invalid characters
            try {
                // A URL with unencoded spaces (invalid for URI)
                URL badUrl = new URL("http://example.com/path with spaces/file.html");
                URI badUri = badUrl.toURI(); // This will fail
            } catch (Exception e) {
                System.out.println("\n⚠️ Exception when converting invalid URL to URI:");
                System.out.println(e.getMessage());
                System.out.println("Solution: Always properly encode URL components!");
            }
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
```

### ✅ Summary

| Method       | Purpose |
|--------------|---------|
| `toURI()`    | Converts a `URL` to a standards-compliant `URI` |
| Benefits     | Safe parsing, resolution, normalization |
| Risks        | Throws `URISyntaxException` if malformed |
| Use case     | When you need to manipulate or resolve URL components without triggering network I/O |


# Java URL Fun Fact
> Fun Fact: Early Java URLs didn't handle IPv6—added in 2002 amid internet growth. Hashing/equality inspired URL canonicalization in search engines like Google.

## Part 1: Java and IPv6 Support

**"Early Java URLs didn't handle IPv6—added in 2002 amid internet growth."**

### 🧾 The Story Behind This:

➡️ When Java was first released in 1995, the internet primarily used IPv4 addresses (like `192.168.1.1`). The original Java networking APIs were designed with only IPv4 in mind.

➡️ As the internet grew explosively in the late 1990s, it became clear that we would eventually run out of IPv4 addresses. IPv6 was developed as the solution, using much longer addresses (like `2001:0db8:85a3:0000:0000:8a2e:0370:7334`).

➡️ In 2002, with Java 1.4, support for IPv6 was finally added to Java's networking APIs, including the URL class. This was a significant update that allowed Java applications to work with the next generation of internet addressing.

### ↳ Why This Matters:
➡️ This timing is interesting because Java added IPv6 support well before widespread IPv6 adoption. It shows how Java evolved to stay current with internet standards, even though practical IPv6 deployment took many more years (and is still ongoing).

## Part 2: URL Canonicalization and Search Engines

**"Hashing/equality inspired URL canonicalization in search engines like Google."**

### 🧾 The Story Behind This:
➡️ Java's URL class needed to determine when two URLs point to the same resource. For example, should these be considered equal?
- `http://example.com`
- `http://example.com/`
- `http://Example.com`
- `http://example.com:80`

➡️ Java implemented rules for URL normalization and comparison in its `equals()` and `hashCode()` methods for the URL class. These rules helped determine when URLs should be treated as equivalent.

➡️ Search engines like Google faced similar challenges at a massive scale. They needed to avoid indexing the same content multiple times under slightly different URLs. The approaches pioneered in Java and other early systems influenced how search engines perform URL canonicalization.

### ↳ Why This Matters:

URL canonicalization is crucial for:
- Preventing duplicate content in search results
- Accurately counting links to pages
- Efficiently storing web data
- Proper caching of web resources

## Code Example: Demonstrating Both Concepts

```java name=URLHistoricalFeatures.java
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class URLHistoricalFeatures {
    public static void main(String[] args) {
        System.out.println("Java URL Historical Features Demo");
        System.out.println("=================================\n");
        
        // Part 1: IPv6 Support (added in 2002)
        System.out.println("PART 1: IPv6 SUPPORT");
        System.out.println("-------------------");
        try {
            // Create a URL with IPv4 address (original support)
            URL ipv4Url = new URL("http://127.0.0.1/index.html");
            System.out.println("IPv4 URL: " + ipv4Url);
            System.out.println("Host: " + ipv4Url.getHost());
            
            // Create a URL with IPv6 address (added in 2002)
            URL ipv6Url = new URL("http://[::1]/index.html");
            System.out.println("\nIPv6 URL: " + ipv6Url);
            System.out.println("Host: " + ipv6Url.getHost());
            
            // Demonstrate IPv6 with port (requires brackets)
            URL ipv6PortUrl = new URL("https://[2001:db8::1]:8443/secure");
            System.out.println("\nIPv6 URL with port: " + ipv6PortUrl);
            System.out.println("Host: " + ipv6PortUrl.getHost());
            System.out.println("Port: " + ipv6PortUrl.getPort());
            
        } catch (MalformedURLException e) {
            System.err.println("Error with IPv6 URL: " + e.getMessage());
        }
        
        // Part 2: URL Canonicalization (equality and hashing)
        System.out.println("\nPART 2: URL CANONICALIZATION");
        System.out.println("---------------------------");
        try {
            // Create several URLs that point to the same resource
            URL url1 = new URL("http://example.com");
            URL url2 = new URL("http://example.com/");         // Trailing slash
            URL url3 = new URL("http://example.com:80");       // Default port
            URL url4 = new URL("http://Example.com");          // Different case
            
            // Print equality comparisons
            System.out.println("url1.equals(url2): " + url1.equals(url2));
            System.out.println("url1.equals(url3): " + url1.equals(url3));
            System.out.println("url1.equals(url4): " + url1.equals(url4));
            
            // Demonstrate how search engines might use URL canonicalization
            System.out.println("\nSimulating a search engine's URL canonicalization:");
            Map<String, String> pageIndex = new HashMap<>();
            
            // Store page content by canonical URL form
            String canonicalForm1 = canonicalizeUrl(url1.toString());
            pageIndex.put(canonicalForm1, "This is the example.com homepage");
            
            // Check if different URLs map to the same content
            String canonicalForm2 = canonicalizeUrl(url2.toString());
            String canonicalForm3 = canonicalizeUrl(url3.toString());
            String canonicalForm4 = canonicalizeUrl(url4.toString());
            
            System.out.println("Original URLs:");
            System.out.println("- " + url1);
            System.out.println("- " + url2);
            System.out.println("- " + url3);
            System.out.println("- " + url4);
            
            System.out.println("\nCanonical forms:");
            System.out.println("- " + canonicalForm1);
            System.out.println("- " + canonicalForm2);
            System.out.println("- " + canonicalForm3);
            System.out.println("- " + canonicalForm4);
            
            System.out.println("\nThis prevents duplicate content in search indexes!");
            
        } catch (MalformedURLException e) {
            System.err.println("Error with URL canonicalization: " + e.getMessage());
        }
    }
    
    /**
     * Simple URL canonicalization function (similar to what search engines might use)
     */
    private static String canonicalizeUrl(String url) {
        String result = url.toLowerCase();
        
        // Remove default ports
        result = result.replace(":80/", "/");
        
        // Ensure trailing slash on domain root
        if (result.matches("http://[^/]+$")) {
            result += "/";
        }
        
        return result;
    }
}
```

## ↳ Why This Historical Context Matters Today

1. **IPv6 Understanding**: As a developer today, you'll encounter both IPv4 and IPv6 addresses, and understanding how Java handles them is essential.

2. **URL Processing**: Modern web applications still deal with URL normalization challenges. The approaches pioneered decades ago inform best practices today.

3. **Learning from History**: Seeing how Java evolved to address internet changes helps us understand why certain design decisions were made and how they impact our code today.

#### 👉 Java's URL handling might seem like a simple thing, but it reflects the fascinating evolution of the internet itself!

## 📖 Summary

1. **`equals(Object obj)`**: Compares URLs for complete equality (all components including fragment), may trigger DNS lookups.

2. **`sameFile(URL other)`**: Compares URLs ignoring the fragment part, useful when checking if two URLs refer to the same resource.

3. **`hashCode()`**: Generates a hash code for use in hash-based collections, may trigger DNS lookups.

4. **`toString() / toExternalForm()`**: Convert URL objects back to string form, usually identical results.

5. **`toURI()`**: Converts a URL to a URI object, which has better handling of special characters and avoids DNS lookups.

#### 👉 **Recommendation:** When working with URLs in collections or equality checks, consider using URI objects instead to avoid the DNS lookup issues.
***