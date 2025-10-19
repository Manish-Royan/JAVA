# URL (Uniform Resource Locator)

## 📚 Overview of URL 
* A URL is a specific type of Uniform Resource Identifier (URI) that provides a way to locate a resource on the internet by describing its primary access mechanism (e.g., its network location).

## \# A URL has two main components:
* **Protocol identifier**: For the URL *http://example.com*, the protocol identifier is **http**.
* **Resource name**: For the URL *http://example.com*, the resource name is example.com.

## \# URL Statements distinction between **identifying** a resource and **locating** a resource

### 🔸 "A URL is a URI that, as well as identifying a resource, provides a specific network location..."
↳ This is the core definition. Think of it as a hierarchy:

*   **URI (Uniform Resource Identifier):** This is the most general category. Its only job is to give a resource a unique name.
*   **URL (Uniform Resource Locator):** This is a *specialized kind* of URI. It does two jobs:
    1.  It gives the resource a unique name (fulfilling its duty as a URI).
    2.  It also provides a complete, step-by-step set of instructions on how to access it over a network.

### 🔸"By contrast, a generic URI may tell you what a resource is, but not actually tell you where or how to get that resource."
↳ This highlights the limitation of a URI that isn't a URL. A non-URL URI is like knowing a product's official model number but having no idea which stores sell it or where those stores are located.

*   **URI:** `urn:isbn:978-0747591054`
    *   **What it tells you:** This uniquely identifies the UK first edition of "Harry Potter and The Deathly Hallows". Any system that understands ISBNs knows *exactly* what book this is.
    *   **What it doesn't tell you:** Where is a copy? How do I read it? Is it in a library? Is it for sale online? It provides zero location information. It is just a name.

*   **URL:** `https://www.amazon.co.uk/dp/0747591059`
    *   **What it tells you:** This also identifies the same book, but it goes much further. It gives you an actionable path:
        *   **How:** Use the `https` protocol.
        *   **Where:** Go to the server at `www.amazon.co.uk`.
        *   **Which Page:** Find the resource at the path `/dp/0747591059`.
    *   A client (like your browser) has enough information to retrieve a representation of that resource (the Amazon product page).

## \# [The Anatomy of a URL](https://github.com/Manish-Royan/JAVA/tree/main/JAVA-Notes/Advanced%20Java%20Programming/Network%20Programming%20in%20Java/Chapter-3/%23%20More%20Depth%20Explorations/1.1%20MDE%20-%20Anatomy%20of%20URL) (The Concept)

👉 Before we touch the Java class, it's crucial to understand that a URL is a structured string with distinct parts. It's not just an opaque blob of text.

### ```protocol://host:port/path?query#fragment```

- **Scheme (protocol)** — the transport or access protocol (for example http, https, ftp, file); determines default port and how the resource is handled.  
- **Authority / Host** — the domain name or IP address of the server hosting the resource; may include userinfo in some schemes though userinfo is rare in modern HTTP usage.  
- **Port** — optional numeric port; when absent the protocol’s default is used (for HTTP default is 80, HTTPS 443).  
- **Path** — the hierarchical resource path on the host that identifies the object (file, endpoint, directory).  
- **Query** — an opaque string of key/value parameters interpreted by the server or application handling the request.  
- **Fragment (ref)** — client-side reference used after retrieval and not sent to the server; used for in-document anchors in HTTP contexts.

### 📌 For example, in 
`https://github.com:443/Manish-Bishwakarma/Network-Programming-in-Java?tab=code#readme`:

1. **Protocol (Scheme):** `https` - The communication protocol
2. **Host:** `github.com` - The domain name or IP address
3. **Port:** `443` - The port number (often omitted when using default ports)
4. **Path:** `/Manish-Bishwakarma/Network-Programming-in-Java` - The resource path
5. **Query:** `?tab=code` - Additional parameters
6. **Fragment:** `#readme` - A specific section within the resource

## Basic URL Creation
```java
import java.net.MalformedURLException;
import java.net.URL;

public class UrlExamples {
    public static void main(String[] args) {
        try {
            // 1) Full URL string
            URL githubURL = new URL("https://github.com/Manish-Royan/JAVA");
            System.out.println("githubURL: " + githubURL);

            // 2) By components: protocol, host, file (path + optional query)
            URL repoURL = new URL("https", "github.com", "/Manish-Royan/JAVA");
            System.out.println("repoURL: " + repoURL);

            // 3) With explicit port (use -1 to indicate unspecified/default)
            URL portSpecificURL = new URL("https", "github.com", 443, "/Manish-Royan/JAVA");
            System.out.println("portSpecificURL: " + portSpecificURL);
            System.out.println("portSpecificURL.getPort(): " + portSpecificURL.getPort()); // 443

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
```

### Modern Approach: Safe creation using URI builder and conversion
```java
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class UrlFromUri {
    public static void main(String[] args) {
        try {

            //using the 7-argument constructor of the URI class: URI(String scheme, String userInfo, String host, int port, String path, String query, String fragment)
            URI uri = new URI("https", null, "github.com", 443, "/Manish-Royan/JAVA", null, null);
            /*This constructor build: https://github.com:443/Manish-Royan/JAVA */
            


            URL url = uri.toURL();
            System.out.println("url from URI: " + url);
        } catch (URISyntaxException | java.net.MalformedURLException e) {
            e.printStackTrace();
        }
    }
}

/*Why Use `null`?
- `null` for `userInfo`: You’re not embedding credentials in the URL (which is discouraged in modern web apps).
- `null` for `query`: You’re not passing any query parameters like `?sort=asc`.
- 
```
### 🔍 Breakdown of the Arguments

| Argument        | Value       | Meaning                                                                 |
|----------------|-------------|-------------------------------------------------------------------------|
| `scheme`        | `"https"`   | The protocol used (e.g., HTTP, HTTPS, FTP)                             |
| `userInfo`      | `null`      | No username/password in the URL (e.g., `user:pass@`)                   |
| `host`          | `"github.com"` | The domain name or IP address of the server                          |
| `port`          | `443`       | Explicit port number (443 is default for HTTPS)                        |
| `path`          | `"/Manish-Royan/JAVA"` | The resource path on the server                          |
| `query`         | `null`      | No query string (e.g., `?key=value`)                                   |
| `fragment`      | `null`      | No fragment identifier (e.g., `#section1`)                             |

---

***