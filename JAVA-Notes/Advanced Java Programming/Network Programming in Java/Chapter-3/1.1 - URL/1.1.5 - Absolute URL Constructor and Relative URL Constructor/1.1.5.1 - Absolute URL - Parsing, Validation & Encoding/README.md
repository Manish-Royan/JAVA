# \# What gets parsed/extracted (key getters)

### 🤔 What Does “Parsed/Extracted” Mean❓
➡️ When you create a `URL` object, Java **breaks it into parts**. These getters let you access each part individually — like pulling ingredients from a recipe.

### 👇 Let’s use this sample URL:

```
https://user:pass@example.com:8443/a/b?x=1&y=2#top
```

➡️ These are methods that extract specific parts of a URL after it’s been parsed.
- `getProtocol()`: e.g., "https"
- `getAuthority()`: e.g., "user:pass@example.com:8443"
- `getUserInfo()`: e.g., "user:pass" (rare; discouraged in practice)
- `getHost()`: e.g., "example.com" or IPv4/IPv6 (IPv6 in output string is bracketed)
- `getPort()`: explicit port or -1 if none specified
- `getDefaultPort()`: default per protocol (80 for http, 443 for https, etc.)
- `getPath()`: path only (e.g., "/a/b")
- `getQuery()`: e.g., "x=1&y=2"
- `getFile()`: path plus query (legacy: "/a/b?x=1&y=2")
- `getRef()`: fragment without the leading "#" (e.g., "top")


### 🧩 Breakdown of Key Getters

| Getter | What It Returns | Example | Notes |
|--------|------------------|---------|-------|
| `getProtocol()` | The scheme (protocol) | `"https"` | Always lowercase |
| `getAuthority()` | Full authority: user info + host + port | `"user:pass@example.com:8443"` | Combines multiple parts |
| `getUserInfo()` | Credentials before `@` | `"user:pass"` | Rarely used; discouraged for security |
| `getHost()` | Hostname or IP address | `"example.com"` or `"2001:db8::1"` | IPv6 is bracketed in text, but not in output |
| `getPort()` | Explicit port number | `8443` | Returns `-1` if not specified |
| `getDefaultPort()` | Default for protocol | `443` for HTTPS, `80` for HTTP | Useful for fallback logic |
| `getPath()` | Just the path | `"/a/b"` | No query or fragment |
| `getQuery()` | Query string after `?` | `"x=1&y=2"` | Raw string; not parsed |
| `getFile()` | Path + query | `"/a/b?x=1&y=2"` | Legacy method; includes query |
| `getRef()` | Fragment after `#` | `"top"` | Used for client-side navigation |

### 📌Example

```java
import java.net.URL;

public class URLPartsDemo {
    public static void main(String[] args) throws Exception {
        URL url = new URL("https://user:pass@example.com:8443/a/b?x=1&y=2#top");

        System.out.println("Protocol:      " + url.getProtocol());     // https
        System.out.println("Authority:     " + url.getAuthority());    // user:pass@example.com:8443
        System.out.println("User Info:     " + url.getUserInfo());     // user:pass
        System.out.println("Host:          " + url.getHost());         // example.com
        System.out.println("Port:          " + url.getPort());         // 8443
        System.out.println("Default Port:  " + url.getDefaultPort());  // 443
        System.out.println("Path:          " + url.getPath());         // /a/b
        System.out.println("Query:         " + url.getQuery());        // x=1&y=2
        System.out.println("File:          " + url.getFile());         // /a/b?x=1&y=2
        System.out.println("Fragment:      " + url.getRef());          // top
    }
}
```

### ✅ Summary
↳ These getters help you:
- **Log and inspect** URLs
- **Build routing logic**
- **Validate or normalize** user input
- **Extract parameters** for backend processing

# \# Syntactic validation, Percent encoding, and Internationalized domain names (IDNs)

## 1. **Syntactic Validation**
➡️ URL constructors validate structural rules: scheme must exist; host must be syntactically valid; ports must be in range or -1; IPv6 literals require bracket syntax when a port is present in textual forms. Malformed input causes MalformedURLException.

### ✅ What it means:
↳ When you create a `URL` object, Java checks that the structure is valid:
- **Scheme** (like `http`, `https`) must be present.
- **Host** must follow valid syntax (e.g., no illegal characters).
- **Port** must be between `0–65535` or `-1` (meaning “unspecified”).
- **IPv6 addresses** must be wrapped in brackets (`[ ]`) if a port is included.

#### 👉 If anything is wrong → `MalformedURLException` is thrown.

### 📌Example:

```java
URL valid = new URL("http://[2001:db8::1]:8080/page"); // ✅ IPv6 with port
URL invalid = new URL("http://2001:db8::1:8080/page"); // ❌ Missing brackets → throws exception
```

## 2. **Percent Encoding**
➡️ URL constructors historically accept some unencoded characters; they do not reliably enforce RFC percent‑encoding for path and query components. Use URI builders to enforce correct encoding and avoid ambiguous characters like spaces or unescaped reserved characters.

### ✅ What it means:
↳ URLs must follow **RFC 3986 encoding rules**:
- Reserved characters (like space, `#`, `?`, `&`) must be **percent-encoded**.
- Java’s `URL` constructor is **lenient** — it may accept unencoded characters like spaces, but this can lead to bugs or misinterpretation.

### ⚠️ Safer approach: Use `URI` or `URLEncoder` to enforce correct encoding.
#### 📌 Example:

```java
// Unsafe: space is not encoded
URL unsafe = new URL("https://example.com/docs/my file.html"); // May work, but not RFC-compliant

// Safe: encode the path first
String encodedPath = URLEncoder.encode("my file.html", "UTF-8"); // my+file.html
URI safeUri = new URI("https", "example.com", "/docs/" + encodedPath, null);
URL safeUrl = safeUri.toURL();
```

## 3. **Internationalized Domain Names (IDNs)**
➡️ Unicode hostnames should be converted to ASCII Compatible Encoding (punycode) using IDN APIs before constructing or rely on URI to perform conversion.

### ✅ What it means:
➡️ Some domain names use **Unicode characters** (e.g., `münchen.de`). These must be converted to **ASCII-Compatible Encoding (ACE)** — known as **punycode** — before being used in a URL.

### 🔧 Use Java’s `IDN` class to convert:

```java
String unicodeHost = "münchen.de";
String asciiHost = IDN.toASCII(unicodeHost); // xn--mnchen-3ya.de

URL url = new URL("https", asciiHost, "/welcome.html");
System.out.println("URL: " + url); // https://xn--mnchen-3ya.de/welcome.html
```

### ✅ Summary Table

| Concern | What It Checks | Risk | Best Practice |
|--------|----------------|------|---------------|
| **Syntactic Validation** | Scheme, host, port, IPv6 brackets | `MalformedURLException` | Use correct format and catch exceptions |
| **Percent Encoding** | Reserved characters in path/query | Misinterpreted URLs | Use `URI` or `URLEncoder` |
| **IDNs** | Unicode hostnames | Invalid host or DNS failure | Use `IDN.toASCII()` or build from `URI` |


# \# Important behaviors and pitfalls of `URL` class — (during parsing, comparison, and runtime usage)

### 1. **Default Ports**
- If a URL doesn’t explicitly specify a port, `getPort()` returns `-1`.
- To find the actual port used, call `getDefaultPort()` — it returns:
  - `80` for `http`
  - `443` for `https`
  - `21` for `ftp`, etc.

```java
URL url = new URL("https://example.com");
System.out.println(url.getPort());         // -1
System.out.println(url.getDefaultPort());  // 443
```

### 2. **Encoding Leniency**
- Java’s `URL` constructor may accept **unescaped characters** like spaces or `#`, which are technically invalid.
- This leniency varies by Java version and protocol handler.
- ✅ Safer alternative: use `URI`, which enforces **strict RFC 3986 rules**.

```java
URL unsafe = new URL("https://example.com/docs/my file.html"); // May work, but not RFC-compliant
URI safe = new URI("https", "example.com", "/docs/my%20file.html", null); // Strict and safe
```

### 3. **equals() and hashCode()**
- These methods may trigger **DNS lookups** to resolve hostnames to IPs.
- This can be **slow**, **blocking**, and **environment-dependent**.
- ⚠️ Avoid using `URL` as keys in `HashMap` or `HashSet`.
- ✅ Use `URI` instead — it compares strings, not resolved IPs.

```java
Map<URI, String> safeMap = new HashMap<>();
safeMap.put(new URI("https://example.com"), "value");
```

### 4. **Unknown Protocol**
- If you use a protocol like `"myapp"` and Java doesn’t have a handler for it, you’ll get a `MalformedURLException`.

```java
URL url = new URL("myapp://host/resource"); // ❌ Throws exception unless handler is registered
```
#### 👉 ✅ You can register a custom `URLStreamHandler` if needed.

### 5. **File vs Query**
- `getFile()` returns **path + query** (e.g., `/docs/page.html?x=1`)
- For clarity, use `getPath()` and `getQuery()` separately.

```java
URL url = new URL("https://example.com/docs/page.html?x=1");
System.out.println(url.getFile());   // /docs/page.html?x=1
System.out.println(url.getPath());   // /docs/page.html
System.out.println(url.getQuery());  // x=1
```

## ⚙️ Behavior Characteristics and Runtime Implications

### 1️⃣ **No Network Activity on Construction**
- Creating a `URL` object does **not** trigger DNS, TCP, TLS, or HTTP.
- Network activity only happens when you call:
  - `openConnection()`
  - `openStream()`
  - `URLConnection.connect()`

```java
URL url = new URL("https://example.com"); // ✅ No network call
url.openStream();                         // ❌ Triggers HTTP request
```

### 2️⃣ **Immutability and Thread Safety**
- `URL` objects are **immutable** — once created, they don’t change.
- ✅ Safe to share across threads for **read-only use**.
- ⚠️ But `URLConnection` objects are **not thread-safe** — don’t share them across threads.

### 3️⃣ **String Representation**
- `toString()` and `toExternalForm()` return the full URL string.
- If the host is IPv6 and a port is present, brackets will appear in the output.

```java
URL url = new URL("http://[2001:db8::1]:8080/page");
System.out.println(url.toString()); // http://[2001:db8::1]:8080/page
```
### ✅ Summary Table

| Behavior | Risk | Best Practice |
|----------|------|---------------|
| `getPort()` returns `-1` | Misinterpreting port | Use `getDefaultPort()` |
| Encoding leniency | Invalid URLs accepted | Use `URI` for strict parsing |
| `equals()`/`hashCode()` | DNS lookup, slow | Use `URI` for map keys |
| Unknown protocol | Exception | Use known protocols or custom handler |
| `getFile()` includes query | Confusion | Use `getPath()` + `getQuery()` |
| URL construction | No network | Safe for offline parsing |
| URL immutability | Safe | Share across threads (read-only) |
| URLConnection | Not thread-safe | Avoid sharing across threads |

### 📝 When to use absolute URL constructors
- Use them when you have a complete locator or when you must produce a canonical, self-contained resource reference (for logging, for storing links, or for immediate resolution to a remote resource). Use URI-based construction when you require strict RFC adherence and correct percent‑encoding.

#### 💡 Scenario
↳ You want to:
- Log a full URL
- Store it in a database
- Use it to open a connection
- Ensure it’s canonical and complete

## 📌 Sample Code: Using Absolute URL Constructor

```java
import java.net.URL;
import java.net.URI;

public class AbsoluteURLExample {
    public static void main(String[] args) {
        try {
            // ✅ Absolute URL constructor: full locator string
            URL url = new URL("https://www.example.com:8443/docs/page.html?x=1&y=2#section3");

            // Use case: logging the full URL
            System.out.println("Logging URL: " + url.toString());

            // Use case: storing canonical form
            String canonical = url.toExternalForm();
            System.out.println("Canonical URL for storage: " + canonical);

            // Use case: resolving remote resource
            System.out.println("Host: " + url.getHost());
            System.out.println("Port: " + (url.getPort() != -1 ? url.getPort() : url.getDefaultPort()));
            System.out.println("Path: " + url.getPath());

            // ✅ Safer alternative: URI-based construction for strict encoding
            URI safeUri = new URI("https", "www.example.com", "/docs/page.html", "x=1&y=2", "section3");
            URL safeUrl = safeUri.toURL();
            System.out.println("Strict RFC-compliant URL: " + safeUrl);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

---

## 🗝️ Key Takeaways

| Use Case | Method | Why |
|----------|--------|-----|
| Full locator known | `new URL(String spec)` | Quick and direct |
| Strict encoding needed | `new URI(...).toURL()` | RFC-compliant and safer |
| Logging or storage | `toExternalForm()` | Canonical format |
| Remote access | `openConnection()` or `openStream()` | Uses full URL |
***