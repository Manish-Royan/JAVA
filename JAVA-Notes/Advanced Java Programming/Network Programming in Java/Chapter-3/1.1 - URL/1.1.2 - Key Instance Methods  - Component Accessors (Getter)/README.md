# URL Component Accessors (Getter)

➡️ Once you have a URL object, you can easily access its individual parts. These methods are invaluable for parsing and analysis.


### 👉 Let's use this URL: http://www.example.com:8080/path/to/file?query=1#fragment
```java
try {
    URL url = new URL("http://www.example.com:8080/path/to/file?query=1#fragment");

    System.out.println("Protocol: " + url.getProtocol()); // -> http
    System.out.println("Host: " + url.getHost());         // -> www.example.com
    System.out.println("Port: " + url.getPort());         // -> 8080
    System.out.println("Default Port: " + url.getDefaultPort()); // -> 80
    System.out.println("Path: " + url.getPath());         // -> /path/to/file
    System.out.println("File: " + url.getFile());         // -> /path/to/file?query=1 (Path + Query)
    System.out.println("Query: " + url.getQuery());       // -> query=1
    System.out.println("Authority: " + url.getAuthority()); // -> www.example.com:8080
    System.out.println("Ref (Fragment): " + url.getRef());  // -> fragment
    
} catch (MalformedURLException e) {
    e.printStackTrace();
}
```

## 💭 URL Components: The "Address" Analogy
➡️ Think of a URL as a detailed address to a specific resource. Let's use this complete URL as our example:
```
https://username:password@www.example.com:8080/products/electronics?brand=samsung&sort=price#reviews
```

💭 This is like an address with detailed instructions:
- **Protocol**: `https://` - The vehicle you'll use (car, boat, etc.)
- **User Info**: `username:password@` - Your ID card to enter the building
- **Host**: `www.example.com` - The name of the building
- **Port**: `:8080` - A specific entrance door number
- **Path**: `/products/electronics` - The floor and room number
- **Query**: `?brand=samsung&sort=price` - Specific instructions for the person you're meeting
- **Reference/Fragment**: `#reviews` - The exact spot in the room

## 📌 Sample Code with Explanations

Let's see these components in action with code samples:

```java name=URLComponentAccessorDemo.java
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Demonstrates URL component accessors with a real example
 * @author Manish-Bishwakarma
 * @version 1.0
 * @since 2025-10-19
 */
public class URLComponentAccessorDemo {
    public static void main(String[] args) {
        // Current timestamp for demonstration
        String timestamp = LocalDateTime.now()
            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println("URL Component Accessor Demo - " + timestamp);
        System.out.println("===============================================\n");
        
        try {
            // Example URL with all possible components
            URL complexUrl = new URL(
                "https://username:password@api.github.com:443/repos/Manish-Bishwakarma/project/issues?state=open&sort=created#issue-description"
            );
            
            System.out.println("Complete URL: " + complexUrl + "\n");
            
            // 1. getProtocol() - The "transportation method"
            String protocol = complexUrl.getProtocol();
            System.out.println("1. getProtocol(): " + protocol);
            System.out.println("   Like the vehicle you use to get there (car, boat, etc.)");
            
            // 2. getHost() - The "building name" 
            String host = complexUrl.getHost();
            System.out.println("\n2. getHost(): " + host);
            System.out.println("   Like the name of the building you're visiting");
            
            // 3. getPort() and getDefaultPort() - The "entrance door number"
            int port = complexUrl.getPort();
            int defaultPort = complexUrl.getDefaultPort();
            System.out.println("\n3. getPort(): " + port);
            System.out.println("   getDefaultPort(): " + defaultPort);
            System.out.println("   Like the specific entrance door (or default door) to use");
            
            // 4. getPath() - The "room number"
            String path = complexUrl.getPath();
            System.out.println("\n4. getPath(): " + path);
            System.out.println("   Like the floor and room number in the building");
            
            // 5. getFile() - The "room number with instructions"
            String file = complexUrl.getFile();
            System.out.println("\n5. getFile(): " + file);
            System.out.println("   Combines the path and query (room + instructions)");
            
            // 6. getQuery() - The "specific instructions"
            String query = complexUrl.getQuery();
            System.out.println("\n6. getQuery(): " + query);
            System.out.println("   Like specific instructions for the person you're meeting");
            
            // 7. getRef() - The "exact spot"
            String ref = complexUrl.getRef();
            System.out.println("\n7. getRef(): " + ref);
            System.out.println("   Like the exact spot in the room you need to go to");
            
            // 8. getUserInfo() - The "ID card"
            String userInfo = complexUrl.getUserInfo();
            System.out.println("\n8. getUserInfo(): " + userInfo);
            System.out.println("   Like your ID card to enter the building");
            
            // 9. getAuthority() - The "building identity"
            String authority = complexUrl.getAuthority();
            System.out.println("\n9. getAuthority(): " + authority);
            System.out.println("   Combines user info, host, and port (complete building identity)");
            
            // 10. toExternalForm() and toString() - The "complete written address"
            String externalForm = complexUrl.toExternalForm();
            String toString = complexUrl.toString();
            System.out.println("\n10. toExternalForm(): " + externalForm);
            System.out.println("    toString(): " + toString);
            System.out.println("    The complete address in a standard format");
            
            // Let's demonstrate a URL with an IPv6 address
            System.out.println("\n===============================================");
            System.out.println("SPECIAL CASE: URL with IPv6 Address");
            URL ipv6Url = new URL("https://[2001:db8:85a3:8d3:1319:8a2e:370:7348]:443/index.html");
            System.out.println("IPv6 URL: " + ipv6Url);
            System.out.println("Host: " + ipv6Url.getHost() + " (Note: brackets removed)");
            System.out.println("Port: " + ipv6Url.getPort());
            
        } catch (MalformedURLException e) {
            System.err.println("Error in URL format: " + e.getMessage());
        }
    }
}
```

# 🔹Component Accessor Methods

## 1. `getProtocol()`
* **What is `getProtocol()`**: In Java, when you use the `URL` class, the method `getProtocol()` returns the **scheme** part of the URL — the part that tells your program **how** to access the resource.
* **What it returns:** The scheme/protocol part (e.g., "http", "https", "ftp")  
* **Simple explanation:** It tells you how the communication will happen (the "transportation method")

```java name=ProtocolExample.java
import java.net.URL;

public class ProtocolExample {
    public static void main(String[] args) {
        try {
            // Common protocols
            URL httpsUrl = new URL("https://www.google.com");
            URL httpUrl = new URL("http://example.org");
            URL ftpUrl = new URL("ftp://ftp.mozilla.org/pub/");
            
            System.out.println("Google protocol: " + httpsUrl.getProtocol());  // "https"
            System.out.println("Example protocol: " + httpUrl.getProtocol());  // "http"
            System.out.println("Mozilla FTP protocol: " + ftpUrl.getProtocol()); // "ftp"
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
```

### 💭 Think of it like:
- The **verb** in a sentence: it tells you **how** to fetch the resource.
- The **transport method** or **access strategy**: whether it's over the web, a secure channel, a file system, or something else.

### 📌Examples of Protocols

| Protocol | Meaning |
|----------|--------|
| `http`   | HyperText Transfer Protocol (standard web access) |
| `https`  | Secure HTTP (encrypted web access) |
| `ftp`    | File Transfer Protocol (used for downloading/uploading files) |
| `file`   | Accessing a local file on disk |
| `mailto` | Email link (used to open email clients) |
| `jar`    | Accessing resources inside a JAR file |

### ↳ Why It Matters❓
- Helps you decide **which Java classes or libraries** to use for accessing the resource.
- For example:
  - `http/https` → Use `HttpURLConnection` or `HttpClient`
  - `ftp` → Use Apache Commons Net or a custom FTP client
  - `file` → Use `FileInputStream` or `Files.readAllBytes()`


## 2. `getHost()`
### ↳ What Does `getHost()` Do❓
➡️ The method `getHost()` from the `java.net.URL` class returns the **host component** of a URL. This is typically:
- A **domain name** (e.g., `"www.example.com"`)
- A **numeric IP address** (e.g., `"192.168.1.1"`)
- An **IPv6 address** (e.g., `"2001:db8::1"` — without square brackets)
* **What it returns:** The hostname or IP address (without brackets for IPv6)  
* **Simple explanation:** It tells you which server/computer to connect to (the "building name")

### ⚠️It **does not include**:
- The **port number**
- Any **user credentials** (e.g., `user:pass@`)
- Protocol or path

### 📌 Simple Java Example

```java
import java.net.URL;

public class HostDemo {
    public static void main(String[] args) throws Exception {
        URL url1 = new URL("https://www.example.com:443/home");
        URL url2 = new URL("ftp://192.168.1.10/resource.txt");
        URL url3 = new URL("http://[2001:db8::1]:8080/index.html");
        URL url4 = new URL("http://user:pass@host.com:80/page");

        System.out.println("URL 1 Host: " + url1.getHost()); // www.example.com
        System.out.println("URL 2 Host: " + url2.getHost()); // 192.168.1.10
        System.out.println("URL 3 Host: " + url3.getHost()); // 2001:db8::1 (no brackets)
        System.out.println("URL 4 Host: " + url4.getHost()); // host.com
    }
}
```
### 📦 Breakdown by URL Type

| URL Example | `getHost()` Output |
|-------------|---------------------|
| `http://www.example.com:8080/index.html` | `"www.example.com"` |
| `ftp://192.168.1.10/resource.txt` | `"192.168.1.10"` |
| `http://user:pass@host.com:80/page` | `"host.com"` |
| `http://[2001:db8::1]:8080/index.html` | `"2001:db8::1"` (no brackets) |


### 📌IPv4/6 HostExample:
```java name=HostExample.java
import java.net.URL;

public class HostExample {
    public static void main(String[] args) {
        try {
            // Different types of hosts
            URL domainUrl = new URL("https://www.microsoft.com/en-us/");
            URL ipv4Url = new URL("http://142.250.190.78/");  // Google IP
            URL ipv6Url = new URL("https://[2001:4860:4860::8888]/");  // Google DNS IP
            
            System.out.println("Domain host: " + domainUrl.getHost());  // "www.microsoft.com"
            System.out.println("IPv4 host: " + ipv4Url.getHost());      // "142.250.190.78"
            System.out.println("IPv6 host: " + ipv6Url.getHost());      // "2001:4860:4860::8888" (no brackets)
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
```

### ↳ Why It Matters❓
- Helps you extract the **target machine** from a URL.
- Useful for:
  - Logging
  - Network diagnostics
  - Custom routing
  - Security checks

## 3. `getPort()` and `getDefaultPort()`
### ↳ What Does `getPort()` Do❓
- Returns the **explicit port number** written in the URL.
- If **no port is specified**, it returns `-1`.
- It **does not guess or substitute** the default port for the protocol.

### 🔸Example

```java
URL url = new URL("https://www.example.com:8443/index.html");
System.out.println(url.getPort()); // Output: 8443
```

➡️ If the URL was `"https://www.example.com/index.html"` (no port specified), then:

```java
System.out.println(url.getPort()); // Output: -1
```

### ↳ What Does `getDefaultPort()` Do❓
- Returns the **standard port** associated with the protocol.
- Examples:
  - `http` → 80
  - `https` → 443
  - `ftp` → 21
- Useful when `getPort()` returns `-1` and you still want to know what port would be used by default.

### 🔸Example
```java
URL url = new URL("https://www.example.com/index.html");
System.out.println(url.getPort());        // Output: -1
System.out.println(url.getDefaultPort()); // Output: 443
``` 

### 📌 Example

```java
import java.net.URL;

public class PortDemo {
    public static void main(String[] args) throws Exception {
        URL url1 = new URL("http://www.example.com:8080/page");
        URL url2 = new URL("https://www.example.com/page");
        URL url3 = new URL("ftp://ftp.example.com/resource");

        System.out.println("URL 1: " + url1);
        System.out.println("  getPort():        " + url1.getPort());        // 8080
        System.out.println("  getDefaultPort(): " + url1.getDefaultPort()); // 80

        System.out.println("URL 2: " + url2);
        System.out.println("  getPort():        " + url2.getPort());        // -1
        System.out.println("  getDefaultPort(): " + url2.getDefaultPort()); // 443

        System.out.println("URL 3: " + url3);
        System.out.println("  getPort():        " + url3.getPort());        // -1
        System.out.println("  getDefaultPort(): " + url3.getDefaultPort()); // 21
    }
}
```

### 📌**Simple explanation:** They tell you which specific "door" to enter through

```java name=PortExample.java
import java.net.URL;

public class PortExample {
    public static void main(String[] args) {
        try {
            // Different port scenarios
            URL explicitPort = new URL("https://www.amazon.com:443/");
            URL noPort = new URL("https://www.netflix.com/");
            URL customPort = new URL("http://localhost:8080/api/users");
            
            System.out.println("Amazon explicit port: " + explicitPort.getPort());  // 443
            System.out.println("Amazon default port: " + explicitPort.getDefaultPort());  // 443
            
            System.out.println("\nNetflix explicit port: " + noPort.getPort());  // -1 (unspecified)
            System.out.println("Netflix default port: " + noPort.getDefaultPort());  // 443 (HTTPS)
            
            System.out.println("\nLocal server explicit port: " + customPort.getPort());  // 8080
            System.out.println("Local server default port: " + customPort.getDefaultPort());  // 80 (HTTP)
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
```

### 🔸Simple Explanation
➡️ These two methods sound similar but return different parts of the URL:
* `getPath()`: Returns only the path portion of the URL (before any ? character)
* `getFile()`: Returns both the path AND query string together (everything after the host/port)

### 💭Think of it like this:
* `getPath()` is just the folder/file location
* `getFile()` is the location plus any parameters

### 📌File vs Path Example
```java
import java.net.URL;

public class FileVsPathExample {
    public static void main(String[] args) {
        System.out.println("getFile() vs getPath() - Demo for Manish-Bishwakarma");
        System.out.println("===================================================\n");
        
        try {
            // A URL with both path and query components
            URL searchUrl = new URL("https://www.google.com/search?q=java+url+tutorial&lang=en");
            
            System.out.println("Full URL: " + searchUrl);
            System.out.println("getPath(): \"" + searchUrl.getPath() + "\"");    // Returns "/search"
            System.out.println("getFile(): \"" + searchUrl.getFile() + "\"");    // Returns "/search?q=java+url+tutorial&lang=en"
            
            // A URL with path only (no query)
            URL pathOnlyUrl = new URL("https://www.oracle.com/java/technologies/");
            System.out.println("\nURL without query: " + pathOnlyUrl);
            System.out.println("getPath(): \"" + pathOnlyUrl.getPath() + "\"");  // Returns "/java/technologies/"
            System.out.println("getFile(): \"" + pathOnlyUrl.getFile() + "\"");  // Also returns "/java/technologies/"
            
            // Real-world use case: Extract just the resource path vs. full resource identifier
            URL apiUrl = new URL("https://api.github.com/users/Manish-Bishwakarma/repos?sort=updated&per_page=5");
            
            System.out.println("\n--- Real-world Use Case ---");
            System.out.println("API Base Path: " + apiUrl.getPath());             // For routing
            System.out.println("Complete Resource: " + apiUrl.getFile());        // For logging/analytics
            
            // Practical scenario
            String resourcePath = apiUrl.getPath();
            String[] pathSegments = resourcePath.split("/");
            System.out.println("\nAPI path analysis:");
            System.out.println("Resource type: " + pathSegments[1]);             // "users"
            System.out.println("User: " + pathSegments[2]);                      // "Manish-Bishwakarma"
            System.out.println("Endpoint: " + pathSegments[3]);                  // "repos"
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
```

### 🗝️ Key Points
* Use `getPath()` when you only need the hierarchical path component
* Use `getFile()` when you need both path and query parameters
* When there's no query string, both methods return the same value

## 4. `getPath()`
* **What it returns:** The path portion of the URL (e.g., "/index.html", "/users/profile")  
* **Simple explanation:** It specifies the location within the server (like a specific "room")

```java name=PathExample.java
import java.net.URL;

public class PathExample {
    public static void main(String[] args) {
        try {
            // Different path scenarios
            URL rootPath = new URL("https://www.apple.com/");
            URL simplePath = new URL("https://developer.mozilla.org/en-US/docs/Web/JavaScript");
            URL complexPath = new URL("https://github.com/Manish-Bishwakarma/java-projects/tree/main/src/util");
            
            System.out.println("Root path: \"" + rootPath.getPath() + "\"");  // "/"
            System.out.println("MDN docs path: " + simplePath.getPath());  // "/en-US/docs/Web/JavaScript"
            System.out.println("GitHub repo path: " + complexPath.getPath());  // "/Manish-Bishwakarma/java-projects/tree/main/src/util"
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
```

### ↳ What is `getPath()`❓
➡️ In Java’s `URL` class, `getPath()` extracts the **hierarchical path** portion of a URL the part that tells you **where** the resource is located on the server.

### 📌 Think of it like:
- The **file path** or **route** on the server.
- It starts with `/` and may include folders, subfolders, or filenames.
- It **excludes**:
  - Query parameters (`?key=value`)
  - Fragment identifiers (`#section`)

### ⛓️‍💥 Breakdown of a URL
👉 Let’s take this full URL:

```
https://www.example.com/articles/java/networking.html?ref=homepage#intro
```

| Component         | Value                          |
|------------------|--------------------------------|
| Protocol          | `https`                        |
| Host              | `www.example.com`              |
| Port              | (default 443 for HTTPS)        |
| **Path**          | `/articles/java/networking.html` |
| Query             | `?ref=homepage`                |
| Fragment          | `#intro`                       |

### ✅ `getPath()` returns:  
```java
"/articles/java/networking.html"
```

### 📌Example:

```java
import java.net.URL;

public class PathDemo {
    public static void main(String[] args) throws Exception {
        URL url1 = new URL("https://www.example.com/a/b/c.html?x=1#top");
        URL url2 = new URL("http://localhost:8080/");
        URL url3 = new URL("ftp://ftp.example.com/files/report.pdf");

        System.out.println("URL 1 Path: " + url1.getPath()); // /a/b/c.html
        System.out.println("URL 2 Path: " + url2.getPath()); // /
        System.out.println("URL 3 Path: " + url3.getPath()); // /files/report.pdf
    }
}
```

### ↳ Why It’s Useful❓
- Helps you extract the **resource location** on the server.
- Useful for:
  - Routing logic
  - Logging and analytics
  - File access and validation
  - Building RESTful APIs


## 5. `getFile()`
* **What it returns:** The path plus the query string (everything after host:port)  
* **Simple explanation:** It combines where you're going with what you're asking for

```java name=FileExample.java
import java.net.URL;

public class FileExample {
    public static void main(String[] args) {
        try {
            // Different file components
            URL pathOnly = new URL("https://www.youtube.com/watch");
            URL pathAndQuery = new URL("https://www.youtube.com/watch?v=dQw4w9WgXcQ");
            URL complexQuery = new URL("https://www.amazon.com/s?k=java+programming&ref=nb_sb_noss");
            
            System.out.println("Path only: " + pathOnly.getFile());  // "/watch"
            System.out.println("Path and query: " + pathAndQuery.getFile());  // "/watch?v=dQw4w9WgXcQ"
            System.out.println("Complex query: " + complexQuery.getFile());  // "/s?k=java+programming&ref=nb_sb_noss"
            
            // Compare with just getPath()
            System.out.println("\nCompare with getPath():");
            System.out.println("getPath(): " + complexQuery.getPath());  // "/s"
            System.out.println("getFile(): " + complexQuery.getFile());  // "/s?k=java+programming&ref=nb_sb_noss"
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
```

### ↳ What is `getFile()`❓
➡️ In Java, `getFile()` returns:
- The **path** portion of the URL (same as `getPath()`)
- Plus the **query string**, if present  
- It does **not** include the protocol, host, port, or fragment (`#`)

### 📌 Think of it like:
> The exact part of the URL that the server uses to locate and process the resource — including any parameters passed via the query string.

### ⛓️‍💥 Breakdown of a URL
➡️ Let’s take this full URL: ```https://www.example.com/products/view?id=123&sort=asc#top```

| Component         | Value                          |
|------------------|--------------------------------|
| Protocol          | `https`                        |
| Host              | `www.example.com`              |
| Path              | `/products/view`               |
| Query             | `id=123&sort=asc`              |
| Fragment          | `top`                          |
| **getFile()**     | `/products/view?id=123&sort=asc` ✅

### 📌Example:

```java
import java.net.URL;

public class FileDemo {
    public static void main(String[] args) throws Exception {
        URL url1 = new URL("https://www.example.com/a/b/c.html?x=1&y=2");
        URL url2 = new URL("http://localhost:8080/index.html");
        URL url3 = new URL("ftp://ftp.example.com/files/report.pdf?download=true");

        System.out.println("URL 1 getFile(): " + url1.getFile()); // /a/b/c.html?x=1&y=2
        System.out.println("URL 2 getFile(): " + url2.getFile()); // /index.html
        System.out.println("URL 3 getFile(): " + url3.getFile()); // /files/report.pdf?download=true
    }
}
```

---

### ↳ Why It’s Useful❓
- Represents the **exact request target** the server sees.
- Useful for:
  - Logging full request paths
  - Building proxies or URL routers
  - Debugging server-side request handling
  - Reconstructing request URIs


## 5.1. `getFile()` vs `getPath()`: Understanding the Difference

### \# Simple Explanation
➡️ These two methods sound similar but return different parts of the URL:
- `getPath()`: Returns **only the path** portion of the URL (before any `?` character)
- `getFile()`: Returns **both the path AND query string** together (everything after the host/port)

### \# Think of it like this:
- `getPath()` is just the folder/file location
- `getFile()` is the location plus any parameters

```java
URL u = new URL("https://example.com/a/b/index.html?q=1#frag");
u.getPath(); // "/a/b/index.html"
u.getFile(); // "/a/b/index.html?q=1"
u.getQuery(); // "q=1"
u.getRef(); // "frag"
```

### 📌Example

```java name=FileVsPathExample.java
import java.net.URL;

public class FileVsPathExample {
    public static void main(String[] args) {
        System.out.println("Demo: getFile() vs getPath()");
        System.out.println("===================================================\n");
        
        try {
            // A URL with both path and query components
            URL searchUrl = new URL("https://www.google.com/search?q=java+url+tutorial&lang=en");
            
            System.out.println("Full URL: " + searchUrl);
            System.out.println("getPath(): \"" + searchUrl.getPath() + "\"");    // Returns "/search"
            System.out.println("getFile(): \"" + searchUrl.getFile() + "\"");    // Returns "/search?q=java+url+tutorial&lang=en"
            
            // A URL with path only (no query)
            URL pathOnlyUrl = new URL("https://www.oracle.com/java/technologies/");
            System.out.println("\nURL without query: " + pathOnlyUrl);
            System.out.println("getPath(): \"" + pathOnlyUrl.getPath() + "\"");  // Returns "/java/technologies/"
            System.out.println("getFile(): \"" + pathOnlyUrl.getFile() + "\"");  // Also returns "/java/technologies/"
            
            // Real-world use case: Extract just the resource path vs. full resource identifier
            URL apiUrl = new URL("https://api.github.com/users/Manish-Bishwakarma/repos?sort=updated&per_page=5");
            
            System.out.println("\n--- Real-world Use Case ---");
            System.out.println("API Base Path: " + apiUrl.getPath());             // For routing
            System.out.println("Complete Resource: " + apiUrl.getFile());        // For logging/analytics
            
            // Practical scenario
            String resourcePath = apiUrl.getPath();
            String[] pathSegments = resourcePath.split("/");
            System.out.println("\nAPI path analysis:");
            System.out.println("Resource type: " + pathSegments[1]);             // "users"
            System.out.println("User: " + pathSegments[2]);                      // "Manish-Bishwakarma"
            System.out.println("Endpoint: " + pathSegments[3]);                  // "repos"
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
```

### ↳ What’s the Difference❓
| Method        | What It Returns                            | Includes Query? | Use Case |
|---------------|---------------------------------------------|------------------|----------|
| `getPath()`   | Just the **hierarchical path** (e.g. `/docs/page.html`) | ❌ No             | When you only care about the resource location |
| `getFile()`   | Path **plus query string** (e.g. `/docs/page.html?x=1`) | ✅ Yes            | When you need the full request target including parameters |


### 📦 Think of a URL like this:

```
https://www.example.com/docs/page.html?x=1&y=2#section3
```

| Component         | Value                         |
|------------------|-------------------------------|
| Protocol          | `https`                       |
| Host              | `www.example.com`             |
| Path              | `/docs/page.html`             |
| Query             | `x=1&y=2`                     |
| Fragment          | `section3`                    |
| `getPath()`       | `/docs/page.html`             |
| `getFile()`       | `/docs/page.html?x=1&y=2` ✅   |


### 📌Example:

```java
import java.net.URL;

public class PathVsFileDemo {
    public static void main(String[] args) throws Exception {
        URL url = new URL("https://www.example.com/docs/page.html?x=1&y=2#section3");

        System.out.println("Full URL:           " + url);
        System.out.println("getPath():          " + url.getPath()); // /docs/page.html
        System.out.println("getFile():          " + url.getFile()); // /docs/page.html?x=1&y=2
        System.out.println("getQuery():         " + url.getQuery()); // x=1&y=2
        System.out.println("getRef():           " + url.getRef());   // section3
    }
}
```
### 💭 Analogy
➡️ Imagine a URL as a **file path with optional instructions**:
- `getPath()` → Just the file path: `/docs/page.html` → only the path portion
- `getFile()` → File path **plus instructions**: `/docs/page.html?x=1&y=2` → path + query string


### 🗝️ Key Points
- Use `getPath()` when you only need the hierarchical path component
- Use `getFile()` when you need both path and query parameters
- When there's no query string, both methods return the same value


## 6. `getQuery()`
* **What it returns:** The query string without the leading "?" character  
* **Simple explanation:** It contains extra parameters or instructions (like telling a librarian what book you want)

```java name=QueryExample.java
import java.net.URL;

public class QueryExample {
    public static void main(String[] args) {
        try {
            // Different query scenarios
            URL noQuery = new URL("https://www.twitter.com/profile");
            URL simpleQuery = new URL("https://www.google.com/search?q=java+url");
            URL complexQuery = new URL("https://www.linkedin.com/jobs/search?keywords=Java&location=United%20States&geoId=103644278&trk=public_jobs_jobs-search-bar_search-submit");
            
            System.out.println("No query: " + noQuery.getQuery());  // null
            System.out.println("Simple query: " + simpleQuery.getQuery());  // "q=java+url"
            System.out.println("Complex query: " + complexQuery.getQuery());  // "keywords=Java&location=United%20States&geoId=103644278&trk=public_jobs_jobs-search-bar_search-submit"
            
            // Parsing a query parameter manually
            String query = simpleQuery.getQuery();
            if (query != null && query.startsWith("q=")) {
                String searchTerm = query.substring(2);  // Remove "q="
                System.out.println("\nGoogle search term: " + searchTerm);  // "java+url"
            }
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
```

### ↳ What is `getQuery()`❓
➡️ The `getQuery()` method in Java’s `URL` class returns the **raw query string** from a URL — that is, the part that comes **after the `?` symbol**.

### 📌 Key characteristics:
- It **does not include** the leading `?`.
- It **does not parse** the query into key-value pairs — it gives you the entire string as-is.
- If the URL **doesn’t have a query**, it returns `null`.

### 💭 URL Anatomy Refresher
👉 Let’s take this URL:

```
https://www.example.com/search?q=java&sort=recent#top
```

| Component         | Value                  |
|------------------|------------------------|
| Protocol          | `https`                |
| Host              | `www.example.com`      |
| Path              | `/search`              |
| **Query**         | `q=java&sort=recent` ✅ |
| Fragment          | `top`                  |

- `getQuery()` returns: `"q=java&sort=recent"`
- `getPath()` returns: `"/search"`
- `getFile()` returns: `"/search?q=java&sort=recent"`

### 📌Example

```java
import java.net.URL;

public class QueryDemo {
    public static void main(String[] args) throws Exception {
        URL url1 = new URL("https://www.example.com/search?q=java&sort=recent");
        URL url2 = new URL("https://www.example.com/about");

        System.out.println("URL 1 getQuery(): " + url1.getQuery()); // q=java&sort=recent
        System.out.println("URL 2 getQuery(): " + url2.getQuery()); // null
    }
}
```

### ↳ When to Use `getQuery()`❓
- When you want to **inspect or log** the full query string.
- When you plan to **manually parse** the query parameters.
- When building tools like:
  - Routers
  - Proxies
  - Analytics/logging systems
  - Custom query parsers

### 📝 Tip: Parsing the Query
👇 Since `getQuery()` gives you a raw string, you can split and decode it yourself:

```java
String query = url.getQuery(); // e.g., "q=java&sort=recent"
if (query != null) {
    String[] pairs = query.split("&");
    for (String pair : pairs) {
        String[] kv = pair.split("=");
        String key = kv[0];
        String value = kv.length > 1 ? kv[1] : "";
        System.out.println(key + " = " + value);
    }
}
```

## 7. `getRef()`
* **What it returns:** The fragment identifier (part after #)  
* **Simple explanation:** It points to a specific section within a page (like a bookmark in a book)

```java name=RefExample.java
import java.net.URL;

public class RefExample {
    public static void main(String[] args) {
        try {
            // Different reference/fragment scenarios
            URL noRef = new URL("https://en.wikipedia.org/wiki/Java_(programming_language)");
            URL simpleRef = new URL("https://en.wikipedia.org/wiki/Java_(programming_language)#History");
            URL complexRef = new URL("https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/String#instance_methods");
            
            System.out.println("No fragment: " + noRef.getRef());  // null
            System.out.println("Simple fragment: " + simpleRef.getRef());  // "History"
            System.out.println("Complex fragment: " + complexRef.getRef());  // "instance_methods"
            
            // Demonstrating fragment use
            if (simpleRef.getRef() != null) {
                System.out.println("\nJumping to '" + simpleRef.getRef() + "' section of the Java Wikipedia page");
            }
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
```

### ↳ What is `getRef()`❓
👉 `getRef()` returns the **fragment** portion of a URL — the part that comes **after the `#` symbol**.

### 📌 Key traits:
- Also called a **reference**, **anchor**, or **fragment identifier**.
- It’s **client-side only** — **not sent to the server** during HTTP requests.
- It’s used by browsers to **navigate to a specific section** of a page (like an anchor in HTML).
- Java returns it as a **raw string**, without decoding or interpretation.
- If no `#` is present, `getRef()` returns `null`.

### 💭 URL Anatomy Example

```
https://www.example.com/docs/page.html?x=1#section2
```

| Component         | Value              |
|------------------|--------------------|
| Protocol          | `https`            |
| Host              | `www.example.com`  |
| Path              | `/docs/page.html`  |
| Query             | `x=1`              |
| **Fragment (Ref)**| `section2` ✅       |

#### 👉 ✅ `getRef()` returns: `"section2"`


### 📌Example

```java
import java.net.URL;

public class RefDemo {
    public static void main(String[] args) throws Exception {
        URL url1 = new URL("https://www.example.com/docs/page.html#intro");
        URL url2 = new URL("https://www.example.com/docs/page.html");

        System.out.println("URL 1 getRef(): " + url1.getRef()); // intro
        System.out.println("URL 2 getRef(): " + url2.getRef()); // null
    }
}
```

### ↳ Why It’s Useful❓
- Helps you extract **client-side navigation targets**.
- Useful for:
  - Logging full URLs
  - Building custom link parsers
  - Analyzing user behavior (e.g., which section they landed on)
  - Generating anchor-aware documentation or routing


## 8. `getUserInfo()` and `getAuthority()`

### ↳ What is the “Authority” in a URL❓
➡️ In a URL, the **authority** is everything between the scheme (`http://`) and the path (`/docs`), and it can include:

```
[username[:password]@]host[:port]
```

So the full authority might look like:
```
admin:secret@host.com:8080
```

### 🔸`getUserInfo()`
- Returns just the **username:password** part (if present).
- Does **not** include the `@` symbol.
- Returns `null` if no credentials are specified.
- ⚠️ Rarely used today due to security risks — credentials in URLs are discouraged.

### 📌Example:

```java
URL url = new URL("http://admin:secret@host.com:8080/docs");
System.out.println(url.getUserInfo()); // Output: admin:secret
```

### 🔸`getAuthority()`
- Returns the **entire authority string**: `userInfo@host:port`
- Includes:
  - Credentials (if present)
  - Hostname or IP
  - Port (if specified)
- Returns just `host` if no userInfo or port is present.

### 📌Sample:

```java
URL url = new URL("http://admin:secret@host.com:8080/docs");
System.out.println(url.getAuthority()); // Output: admin:secret@host.com:8080
```

### 📌Example

```java
import java.net.URL;

public class AuthorityDemo {
    public static void main(String[] args) throws Exception {
        URL url1 = new URL("http://admin:secret@host.com:8080/docs");
        URL url2 = new URL("https://www.example.com/docs");
        URL url3 = new URL("ftp://user@ftp.example.com");

        System.out.println("URL 1:");
        System.out.println("  getUserInfo():   " + url1.getUserInfo());   // admin:secret
        System.out.println("  getAuthority():  " + url1.getAuthority());  // admin:secret@host.com:8080

        System.out.println("URL 2:");
        System.out.println("  getUserInfo():   " + url2.getUserInfo());   // null
        System.out.println("  getAuthority():  " + url2.getAuthority());  // www.example.com

        System.out.println("URL 3:");
        System.out.println("  getUserInfo():   " + url3.getUserInfo());   // user
        System.out.println("  getAuthority():  " + url3.getAuthority());  // user@ftp.example.com
    }
}
```

* **What they return:** 
    - `getUserInfo()`: The username:password part (rarely used today)
    - `getAuthority()`: The full authority (userInfo@host:port)
* **Simple explanation:** They contain authentication information and the complete server identity

### 📌Example

```java name=AuthorityExample.java
import java.net.URL;

public class AuthorityExample {
    public static void main(String[] args) {
        try {
            // Different authority scenarios
            URL noUserInfo = new URL("https://github.com/Manish-Bishwakarma");
            URL withUserInfo = new URL("https://user:pass@example.com/secure");
            URL withPort = new URL("https://api.github.com:443/users");
            URL complete = new URL("https://admin:secret@server.example.com:8443/admin/panel");
            
            System.out.println("=== USER INFO ===");
            System.out.println("No user info: " + noUserInfo.getUserInfo());  // null
            System.out.println("With user info: " + withUserInfo.getUserInfo());  // "user:pass"
            System.out.println("With port: " + withPort.getUserInfo());  // null
            System.out.println("Complete: " + complete.getUserInfo());  // "admin:secret"
            
            System.out.println("\n=== AUTHORITY ===");
            System.out.println("No user info: " + noUserInfo.getAuthority());  // "github.com"
            System.out.println("With user info: " + withUserInfo.getAuthority());  // "user:pass@example.com"
            System.out.println("With port: " + withPort.getAuthority());  // "api.github.com:443"
            System.out.println("Complete: " + complete.getAuthority());  // "admin:secret@server.example.com:8443"
            
            // Security note
            System.out.println("\nSecurity Note: Including passwords in URLs is not recommended!");
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
```
## 9. `toExternalForm()` and `toString()`

### ↳ What Do `toExternalForm()` and `toString()` Do❓
➡️ Both methods return the **complete textual representation** of the URL — exactly as Java internally models it. This includes:

```
<protocol>://<userInfo@><host>:<port><path>?<query>#<fragment>
```

### ✅ They reconstruct the full URL from its components:
- Protocol (e.g., `http`)
- Authority (host + optional user info + optional port)
- Path
- Query string
- Fragment

---

### ↳ When to Use Them❓

| Use Case            | Why It’s Useful |
|---------------------|-----------------|
| **Logging**         | Shows the full URL for debugging or audit trails |
| **Storage**         | Saves the exact URL string to a file or database |
| **Comparison**      | Compares URLs as strings for equality or uniqueness |
| **Display**         | Shows the full URL to users or in reports |


### 📌Example

```java
import java.net.URL;

public class ExternalFormDemo {
    public static void main(String[] args) throws Exception {
        URL url = new URL("https://admin:pass@www.example.com:8443/docs/page.html?x=1&y=2#section3");

        System.out.println("toExternalForm(): " + url.toExternalForm());
        System.out.println("toString():       " + url.toString());
    }
}
```

### 💡Output:
```
toExternalForm(): https://admin:pass@www.example.com:8443/docs/page.html?x=1&y=2#section3
toString():       https://admin:pass@www.example.com:8443/docs/page.html?x=1&y=2#section3
```
### 👉 ✅ Both methods return the same result — the full URL string.

### ↳ How It Compares to Other Methods❓

| Method           | Returns                          |
|------------------|----------------------------------|
| `getProtocol()`  | `https`                          |
| `getHost()`      | `www.example.com`                |
| `getPort()`      | `8443`                           |
| `getPath()`      | `/docs/page.html`                |
| `getQuery()`     | `x=1&y=2`                        |
| `getRef()`       | `section3`                       |
| `getUserInfo()`  | `admin:pass`                     |
| `getAuthority()` | `admin:pass@www.example.com:8443`|
| `toExternalForm()` / `toString()` | Full URL ✅      |

* **What they return:** The complete textual representation of the URL  
* **Simple explanation:** They give you the entire URL as a standardized string

### 📌String Representation Example:
```java name=StringRepresentationExample.java
import java.net.URL;

public class StringRepresentationExample {
    public static void main(String[] args) {
        try {
            // URL with all components
            URL fullUrl = new URL("https://user:pass@example.com:443/path/to/page.html?param=value#section");
            
            System.out.println("toExternalForm(): " + fullUrl.toExternalForm());
            System.out.println("toString(): " + fullUrl.toString());
            
            // In most cases, they produce identical output
            boolean areEqual = fullUrl.toExternalForm().equals(fullUrl.toString());
            System.out.println("\nAre toExternalForm() and toString() equal? " + areEqual);
            
            // Common use case: logging
            System.out.println("\n=== Usage in logging ===");
            System.out.println("Log: [2025-10-19 19:50:19] Connecting to " + fullUrl.toString());
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
```

### 📖 Summary
| Method               | Returns the full URL string |
|----------------------|-----------------------------|
| `toExternalForm()`   | ✅ Yes                       |
| `toString()`         | ✅ Yes                       |
| Difference?          | None — they behave identically |


## \# Important URL Parsing Notes in Java

### 1. IPv6 Addresses in URLs: The Square Bracket Requirement

### 🔸Simple Explanation
➡️ When using IPv6 addresses in URLs, you **must** enclose the address in square brackets `[...]`. This is especially important when you also need to specify a port number.

➡️ Without brackets, Java wouldn't know where the IPv6 address ends and the port begins, because IPv6 addresses contain colons (`:`) and ports are also separated by a colon.

### 📌Example

```java name=IPv6URLExample.java
import java.net.MalformedURLException;
import java.net.URL;

public class IPv6URLExample {
    public static void main(String[] args) {
        System.out.println("DEMO: IPv6 in URLs");
        System.out.println("============================================\n");
        
        try {
            // CORRECT way: IPv6 address with brackets
            URL correctUrl = new URL("http://[2001:0db8:85a3:0000:0000:8a2e:0370:7334]:8080/path");
            System.out.println("✅ Correct IPv6 URL: " + correctUrl);
            System.out.println("   Host: " + correctUrl.getHost());
            System.out.println("   Port: " + correctUrl.getPort());
            
            try {
                // INCORRECT way: Missing brackets will cause error
                URL incorrectUrl = new URL("http://2001:0db8:85a3:0000:0000:8a2e:0370:7334:8080/path");
                System.out.println("This line won't execute due to error");
            } catch (MalformedURLException e) {
                System.out.println("\n❌ ERROR: Without brackets, Java can't parse IPv6 addresses with ports");
                System.out.println("   " + e.getMessage());
            }
            
            // IPv6 without port (still recommended to use brackets)
            URL ipv6NoPort = new URL("http://[2001:0db8:85a3::8a2e:0370:7334]/page");
            System.out.println("\n✅ IPv6 without port: " + ipv6NoPort);
            
            // Working with localhost in IPv6 ([::1])
            URL localhost = new URL("http://[::1]:8080/test");
            System.out.println("\n✅ IPv6 localhost: " + localhost);
            System.out.println("   Host: " + localhost.getHost());  // Returns "::1" (brackets removed)
            System.out.println("   Port: " + localhost.getPort());  // Returns 8080
            
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
    }
}
```

### 🗝️ Key Points
- **Always use brackets** `[...]` for IPv6 addresses in URLs
- When you get the host with `getHost()`, Java removes the brackets for you
- IPv6 addresses can be compressed (`::`), but still need brackets in URLs 

## 📖 Summary of URL Component Accessors

1. **`getProtocol()`**: The scheme (https, http, ftp) - how to communicate
2. **`getHost()`**: The server name or IP address - where to go
3. **`getPort()`**: The specific port number (-1 if unspecified)
4. **`getDefaultPort()`**: The protocol's standard port
5. **`getPath()`**: The path to the resource - which folder/file
6. **`getFile()`**: The path plus query - full resource identifier
7. **`getQuery()`**: The parameters - additional instructions
8. **`getRef()`**: The fragment - specific section
9. **`getUserInfo()`**: Authentication credentials
10. **`getAuthority()`**: The combined server identity info
11. **`toString()/toExternalForm()`**: The full URL string

### 👉 Remember that these accessor methods don't modify the URL - they just extract parts of it for your use.
***