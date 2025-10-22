# \# “Absolute URL constructor” and “Relative URL constructor” patterns in Java

## 📚 Overview 
➡️ Absolute and relative URL constructors are two complementary ways Java creates `URL` objects: absolute constructors parse a complete, self‑contained locator string or components into a URL, while relative constructors resolve a partial spec against an existing base URL following standard URL/URI resolution rules


### 📝Note: Since JDK 20, URL constructors are deprecated. The modern, safer approach is:
- Parse/resolve with URI (strict, no network side effects)
- Convert to URL only when you need to connect: uri.toURL()
- For custom protocol handlers: URL.of(URI, URLStreamHandler)

### 📌Modern Approach 1: Parse with `URI`, then convert with `uri.toURL()`

➡️ **Simple Explanation:** This is the standard, recommended way. You first create a `URI` object, which is great at safely parsing the address string without any network activity. Then, only when you're ready to connect, you convert it into a `URL`.

```java name=ModernUriToUrl.java
import java.net.URI;
import java.net.URL;
import java.io.InputStream;

public class ModernUriToUrl {
    public static void main(String[] args) {
        System.out.println("--- Modern URL Creation: URI -> toURL() ---");
        
        try {
            // Step 1: Create a URI object. This safely parses the string.
            // It's like writing down an address carefully before you go there.
            String addressString = "https://www.oracle.com/java/";
            URI websiteUri = new URI(addressString);
            System.out.println("1. URI created successfully: " + websiteUri);

            // Step 2: Convert the URI to a URL only when you need to connect.
            // This is like giving the address to your GPS to start the journey.
            URL websiteUrl = websiteUri.toURL();
            System.out.println("2. Converted to URL: " + websiteUrl);

            // Now you can use the URL to open a connection
            System.out.println("3. Opening stream to read content...");
            try (InputStream stream = websiteUrl.openStream()) {
                // We'll just confirm the stream opened, not read the whole page.
                System.out.println("   Successfully opened input stream!");
            }
            
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
```

### 📌Modern Approach 2: `URL.of(URI, URLStreamHandler)` (Since JDK 20)

* **Simple Explanation:** This is a newer, more direct factory method. It's especially useful when you need to use a custom "protocol handler" (like a special guide for a new type of internet address).

    #### 📌Case A: Using the Default Handler
    ↳ This is the most common way you'll use `URL.of()`. By passing `null` as the handler, you tell Java to use its built-in, default handler for the protocol (like `https`).
    ```java name=UrlOfDefaultHandler.java
    import java.net.URI;
    import java.net.URL;
    import java.net.URLStreamHandler;

    public class UrlOfDefaultHandler {
        public static void main(String[] args) {
            System.out.println("--- Modern URL Creation: URL.of() with Default Handler ---");

            try {
                // Step 1: Create the URI
                URI githubUri = new URI("https://github.com/Manish-Bishwakarma");
                System.out.println("1. URI created: " + githubUri);

                // Step 2: Create the URL using URL.of() with a null handler.
                // 'null' tells Java: "Just use your standard map for https."
                URLStreamHandler defaultHandler = null;
                URL githubUrl = URL.of(githubUri, defaultHandler);
                System.out.println("2. URL created: " + githubUrl);

                // The URL is ready to be used
                System.out.println("   Protocol: " + githubUrl.getProtocol());
                System.out.println("   Host: " + githubUrl.getHost());

            } catch (Exception e) {
                System.err.println("An error occurred: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
    ```
    #### 📌Case B: Using a Custom Handler
    ↳ This is for advanced cases where you invent your own protocol. Here, we'll create a fake protocol called `manish-db://` and provide a custom handler that just prints a message when used.

    ```java name=UrlOfCustomHandler.java
    import java.net.URI;
    import java.net.URL;
    import java.net.URLConnection;
    import java.net.URLStreamHandler;
    import java.io.IOException;

    public class UrlOfCustomHandler {
        public static void main(String[] args) {
            System.out.println("--- Modern URL Creation: URL.of() with Custom Handler ---");

            try {
                // Step 1: Create a URI for our custom protocol.
                URI customDbUri = new URI("manish-db://user-data/12345");
                System.out.println("1. Custom URI created: " + customDbUri);

                // Step 2: Create our custom "guide" (URLStreamHandler).
                // This handler's only job is to print a message when used.
                URLStreamHandler customHandler = new URLStreamHandler() {
                    @Override
                    protected URLConnection openConnection(URL u) throws IOException {
                        System.out.println("   >>> Custom handler is now opening a connection   for: " + u);
                        // In a real app, this would return a custom URLConnection to a    database.
                        return null; // Simplified for this example.
                    }
                };
                System.out.println("2. Custom handler created.");

                // Step 3: Create the URL using URL.of(), providing our custom handler.
                URL customUrl = URL.of(customDbUri, customHandler);
                System.out.println("3. URL with custom handler created: " + customUrl);

                // Step 4: Prove the custom handler is used by trying to open a connection.
                System.out.println("4. Calling openConnection() to trigger the handler...");
                try {
                    customUrl.openConnection();
                } catch (NullPointerException e) {
                    // Expected, because our simple handler returns null.
                    System.out.println("   (Connection attempt finished)");
                }

            } catch (Exception e) {
                System.err.println("An error occurred: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
    ```


# \# Absolute URL (all deprecated since JDK 20)

### 🧾Definition:
➡️ An absolute constructor creates a URL that contains a full scheme (protocol) and authority so it is independently addressable without any external context.

***

### 🔍 Absolute URL — Components and meanings
- #### **Scheme**: The transport or access method (examples: http, https, ftp, file). Determines default semantics and default port.  
- #### **Authority**: The host plus optional user info and port. Form: userinfo@host:port. For textual IPv6 literals the host appears in brackets when combined with a port.  
- #### **Host**: Domain name or numeric IP (IPv4 or IPv6). Stored without bracket characters; textual representation may use brackets for IPv6 with a port.  
- #### **Port**: Optional explicit port number; when absent the URL records “unspecified” and getPort() returns -1 while getDefaultPort() gives the scheme default.  
- #### **Path**: Hierarchical server resource path that begins with “/” when present.  
- #### **Query**: Opaque string after “?” used by the server for parameterization; not parsed automatically by URL.  
- #### **Fragment**: Client-side anchor after “#”, not sent to the server and not used for connections.


### 📌Example: Constructing Absolute URLs

```java
import java.net.URL;

public class AbsoluteURLDemo {
    public static void main(String[] args) {
        try {
            // Constructing an absolute URL
            URL url = new URL("https://www.example.com:443/docs/page.html?x=1&y=2#section3");

            // Displaying components
            System.out.println("Full URL:      " + url.toString());
            System.out.println("Protocol:      " + url.getProtocol());
            System.out.println("Host:          " + url.getHost());
            System.out.println("Port:          " + url.getPort());
            System.out.println("Path:          " + url.getPath());
            System.out.println("Query:         " + url.getQuery());
            System.out.println("Fragment:      " + url.getRef());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

### 💡Expected Output

```
Full URL:      https://www.example.com:443/docs/page.html?x=1&y=2#section3
Protocol:      https
Host:          www.example.com
Port:          443
Path:          /docs/page.html
Query:         x=1&y=2
Fragment:      section3
```

***

➡️ An absolute URL is a self‑contained locator that names a network resource with a scheme and authority so it can be interpreted without any external context. It explicitly specifies the protocol (scheme), the network host (authority), and a resource path; it may also include port, user info, query, and fragment. An absolute URL is ready for parsing and later I/O; it does not require a base or resolution step.

### 🛠️ Work: 
➡️ The constructor parses the given textual **spec** or **component arguments** into **scheme**, **authority** (**host** and optional **userinfo/port**), **path**, **query**, and **fragment**; it validates syntax and stores those parts in the `URL` object without performing network I/O. Important concepts: explicit vs unspecified port (getPort returns -1 when omitted), IPv6 literal handling (brackets appear in textual form but the stored host excludes brackets), and that constructors historically accept slightly lenient encodings—so building from a validated URI is recommended for strict correctness.

#### ⛓️‍💥Breakdown of above statement:
> When you create a `URL` object in Java using a constructor like `new URL(...)`, here’s what happens:

#### 1. **Parsing the URL**
↳ Java **splits** the URL into parts:
- **Scheme**: `http`, `https`, `ftp`, etc.
- **Authority**: includes host, optional port, and optional user info
- **Path**: the location on the server (e.g., `/docs/page.html`)
- **Query**: the part after `?` (e.g., `x=1&y=2`)
- **Fragment**: the part after `#` (e.g., `section3`)

#### 2. **Validation**
↳ Java checks if the syntax is valid (e.g., no illegal characters), but it does **not connect to the internet** — no network I/O happens.

#### 3. **Important Concepts**
| Concept | Meaning |
|--------|---------|
| **Explicit vs Unspecified Port** | If the port is written (e.g., `:8080`), `getPort()` returns it. If not, `getPort()` returns `-1`. |
| **IPv6 Handling** | IPv6 addresses in URLs are wrapped in `[]`, like `[2001:db8::1]`, but `getHost()` returns the address **without** brackets. |
| **Lenient Encoding** | Java’s `URL` constructor may accept slightly malformed URLs (e.g., unescaped spaces), but this can cause issues. For strict correctness, it’s better to build from a `URI` object. |

#### 📌Example

```java
import java.net.URL;

public class URLConstructorDemo {
    public static void main(String[] args) throws Exception {
        // Absolute URL with all components
        URL url = new URL("https://user:pass@[2001:db8::1]:8443/docs/page.html?x=1&y=2#section3");

        System.out.println("Full URL:      " + url.toString());
        System.out.println("Protocol:      " + url.getProtocol());   // https
        System.out.println("User Info:     " + url.getUserInfo());   // user:pass
        System.out.println("Host:          " + url.getHost());       // 2001:db8::1 (no brackets)
        System.out.println("Port:          " + url.getPort());       // 8443
        System.out.println("Path:          " + url.getPath());       // /docs/page.html
        System.out.println("Query:         " + url.getQuery());      // x=1&y=2
        System.out.println("Fragment:      " + url.getRef());        // section3

        // Example of unspecified port
        URL url2 = new URL("https://www.example.com/docs");
        System.out.println("\nUnspecified Port: " + url2.getPort()); // -1
        System.out.println("Default Port:     " + url2.getDefaultPort()); // 443
    }
}
```
#### Why Use `URI` for Strict Parsing
👉 If you want to **enforce strict syntax rules**, use `URI` instead:

```java
import java.net.URI;

URI uri = new URI("https://www.example.com/docs/my file.html"); // ❌ Throws URISyntaxException (space not allowed)
```
#### 👉 Java’s `URL` might accept this, but `URI` will reject it unless properly encoded.

### 📖 Summary
| Feature | Behavior |
|--------|----------|
| `URL` constructor | Parses and stores parts of the URL |
| No network I/O | Safe for offline parsing |
| `getPort()` | Returns `-1` if port is not explicitly set |
| IPv6 | Brackets in text, but not in `getHost()` |
| Encoding | `URL` is lenient; `URI` is strict and safer for validation |

## ⚙️ URL Constructors that create absolute URLs
### 1️⃣ 1. `new URL(String spec)` — **Textual Parsing of Full URL**

### ✅ What it does:
- Takes a **complete URL string** like `"https://www.example.com/docs/page.html?x=1#top"`
- Java **parses** it into components: protocol, host, path, query, fragment, etc.
- Throws `MalformedURLException` if the string is **badly formatted**
- Does **not connect to the internet** — it's just parsing
- Historically **lenient**: may accept unescaped characters (e.g., spaces), so it's safer to validate with `URI`
- Parses a complete URL string into components. It accepts absolute specs directly. It throws MalformedURLException for syntactic errors. Construction does not perform network I/O. Historically tolerant about encoding, so validated inputs are safer. 

### 📌Example:

```java
URL url = new URL("https://www.example.com/docs/page.html?x=1#top");

System.out.println("Protocol: " + url.getProtocol()); // https
System.out.println("Host:     " + url.getHost());     // www.example.com
System.out.println("Path:     " + url.getPath());     // /docs/page.html
System.out.println("Query:    " + url.getQuery());    // x=1
System.out.println("Fragment: " + url.getRef());      // top
```

***

### 2️⃣ `new URL(String protocol, String host, String file)` — **Programmatic Assembly**

### ✅ What it does:
- Builds a URL from **separate parts**:
  - protocol: "http", "https", "ftp", "file", "jar", etc.
  - host: `"www.example.com"`
    - hostname or IP; IPv6 must be in [brackets] when stringified, but you pass the literal without brackets; the handler renders appropriately.
    - port: -1 means default port for the protocol; otherwise an explicit port.
    - file: `"/docs/page.html?x=1"`
  - path plus optional query (e.g., "/docs/index.html?lang=en").
  - Automatically constructs a full URL like: `"https://www.example.com/docs/page.html?x=1"`
  - Same exception behavior. Throws `MalformedURLException` if the string is **badly formatted**

### 📌Example:

```java
URL url = new URL("https", "www.example.com", "/docs/page.html?x=1");

System.out.println("Full URL: " + url.toString()); // https://www.example.com/docs/page.html?x=1
```

***

### 3️⃣ `new URL(String protocol, String host, int port, String file)` — **Explicit Port Control**

### ✅ What it does:
- Same as above with port defaulted to -1 (use **default port**).
- If you pass `-1`, it means “no explicit port” — Java will treat it as **unspecified**

### 📌Example:

```java
URL url = new URL("https", "www.example.com", 8443, "/docs/page.html?x=1");

System.out.println("Full URL: " + url.toString()); // https://www.example.com:8443/docs/page.html?x=1
System.out.println("Port:     " + url.getPort());  // 8443
```

### 📖 Summary:
| Constructor | Input Type | Builds Absolute URL? | Port Control | Notes |
|-------------|------------|-----------------------|--------------|-------|
| `new URL(String spec)` | Full URL string | ✅ Yes | ❌ No | Parses and validates full string |
| `new URL(String protocol, String host, String file)` | Components | ✅ Yes | ❌ No | Assembles from parts |
| `new URL(String protocol, String host, int port, String file)` | Components | ✅ Yes | ✅ Yes | Use `-1` for unspecified port |

***

### 4️⃣ **URI to URL Conversion (Recommended)**

### ✅ What it means:
➡️ Instead of directly creating a `URL`, you first build a **`URI`** — which enforces **strict syntax rules** (RFC 3986). Then, you convert it to a `URL` using `.toURL()`.

### ❓Why this is better:
- `URI` is **stricter** than `URL` — it **rejects malformed input** (like unescaped spaces).
- It’s **safer** for user input or dynamic construction.
- It avoids surprises from Java’s lenient `URL` constructor.

### 📌Example:

```java
import java.net.URI;
import java.net.URL;

public class URItoURLDemo {
    public static void main(String[] args) throws Exception {
        // Build a strict URI
        URI uri = new URI("https", "www.example.com", "/docs/page.html", "x=1&y=2", "section3");

        // Convert to URL
        URL url = uri.toURL();

        System.out.println("URI: " + uri);
        System.out.println("URL: " + url);
    }
}
```

### 💡Output:
```
URI: https://www.example.com/docs/page.html?x=1&y=2#section3
URL: https://www.example.com/docs/page.html?x=1&y=2#section3
```

***

### 5️⃣ **URL Constructor with `URLStreamHandler`**

```java
new URL(String protocol, String host, int port, String file, URLStreamHandler handler)
```

### ✅ What it means:
➡️ This constructor lets you **customize how the URL behaves**, especially how it:
- Parses the URL
- Opens connections (e.g., `openConnection()`)

#### 👉 You provide a **custom `URLStreamHandler`** — a class that defines how to handle the protocol (like `http`, `ftp`, or even your own like `myapp://`).

### ❓Why use it:
- To **override default behavior** (e.g., for testing, mocking, or custom protocols)
- To **intercept or simulate network calls**
- To **extend Java’s URL handling** for non-standard schemes

### 📌Example (Mock Handler):

```java
import java.io.IOException;
import java.net.*;

public class CustomHandlerDemo {
    public static void main(String[] args) throws Exception {
        URLStreamHandler handler = new URLStreamHandler() {
            @Override
            protected URLConnection openConnection(URL u) throws IOException {
                System.out.println("Intercepted connection to: " + u);
                return null; // No real connection
            }
        };

        URL customUrl = new URL("http", "localhost", 8080, "/test", handler);
        System.out.println("Custom URL: " + customUrl);
        customUrl.openConnection(); // Triggers our handler
    }
}
```

### 📖 Summary
| Feature | Purpose | Benefit |
|--------|---------|---------|
| `URI → URL` | Build strict, valid URLs | Safer for user input and dynamic construction |
| `URL(..., URLStreamHandler)` | Customize parsing and connection logic | Useful for testing, mocking, or custom protocols |
