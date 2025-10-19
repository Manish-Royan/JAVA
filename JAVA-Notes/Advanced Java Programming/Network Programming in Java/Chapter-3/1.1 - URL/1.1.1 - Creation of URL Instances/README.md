# \# Creation of URL Instances

### 📝 Statement 1:
➡️ The `java.net.URL` class represents a Uniform Resource Locator, which is a reference to a web resource. Creating URL instances correctly is fundamental to network programming in Java.  

### 📝 [Statement 2](https://github.com/Manish-Royan/JAVA/tree/main/JAVA-Notes/Advanced%20Java%20Programming/Network%20Programming%20in%20Java/Chapter-3/%23%20More%20Depth%20Explorations/1.3%20MDE%20-%20No%20Public%20Constructor%20in%20URL%20Class):
➡️ No public constructors in the classical sense—all are deprecated since JDK 20! Instead, prefer creating via URI parsing then `toURL()`, or the static `URL.of(URI, URLStreamHandler)`. This shift promotes safer parsing. Constructors/methods throw `MalformedURLException` for invalid syntax, unknown protocols, or handler issues.

### 📝 [Statement 3](https://github.com/Manish-Royan/JAVA/tree/main/JAVA-Notes/Advanced%20Java%20Programming/Network%20Programming%20in%20Java/Chapter-3/%23%20More%20Depth%20Explorations/1.4%20MDE%20-%20Traditional%20vs%20Modern%20Apporach%20with%20URL):
➡️ Java’s URL API provides means to **parse**, **inspect**, and **open connections** to resources identified by URLs, but recent Java guidance deprecates direct use of URL constructors in favor of creating a URI and converting it to a URL for safer parsing and validation

# Part 1: URL [Constructors](https://github.com/Manish-Royan/JAVA/tree/main/JAVA-Notes/Advanced%20Java%20Programming/Network%20Programming%20in%20Java/Chapter-3/%23%20More%20Depth%20Explorations/1.5%20MDE%20-%20Simplify%20the%20URL%20Constructor) (Deprecated Constructors, Avoid in New Code):
> These parse strings or components, loading a URLStreamHandler if needed (via factory, service loader, system props, or built-ins).

## 📚 Overview
```java
// 1. Complete URL as a String
public URL(String spec) throws MalformedURLException

// 2. Protocol, host, and file
public URL(String protocol, String host, String file) throws MalformedURLException

// 3. Protocol, host, port, and file
public URL(String protocol, String host, int port, String file) throws MalformedURLException

// 4. Context URL (base) and a spec (relative or absolute URL string)
public URL(URL context, String spec) throws MalformedURLException

// 5. Context URL with protocol, host, port, file, and handler
public URL(String protocol, String host, int port, String file, URLStreamHandler handler) throws MalformedURLException
```

➡️ This set of URL constructors offers multiple ways to build URL objects: from a complete textual spec, by resolving a relative spec against a base URL, by assembling components (**protocol**, **host**, **port**, **path**/**query**), and by supplying a custom `URLStreamHandler` to change how a protocol is handled. Key differences are parsing rules, resolution semantics for relative paths, how/when encoding matters, whether a port is explicit or defaulted, and whether a custom protocol handler is used.

## 1️⃣ URL(String spec) - The Classic Full URL Parser
- Purpose: Parse a full URL string such as "https://example.com/path?x=1#frag" into a URL object.  
- What it does: Lexically splits the spec into scheme, authority, host, port (if present), path, query, and fragment; does basic validation and stores values in the URL instance. No network I/O occurs.  
- Error cases: Throws `MalformedURLException` when the syntax is invalid (missing scheme, invalid port number, badly formed IPv6 literal without brackets, etc.).  
- Encoding behavior: Historically permissive; may accept some unescaped characters. For robust handling of reserved/unsafe characters prefer building via URI and calling `toURL()`.  
- Typical usage: Quick parsing of well-formed, normalized URL strings from known sources.  
#### 📌 Example:

```java
URL u = new URL("https://example.com:8443/path/to/file?q=1#section");
```

### 📌Old School Way (Deprecated)
```java name=OldFullURLParser.java
import java.net.MalformedURLException;
import java.net.URL;

public class OldFullURLParser {
    public static void main(String[] args) {
        try {
            // It's 1998, and you're building a Java applet for your website!
            URL javaOracleWebsite = new URL("https://www.oracle.com/java/technologies/downloads/");
            System.out.println("Protocol: " + javaOracleWebsite.getProtocol());
            System.out.println("Host: " + javaOracleWebsite.getHost());
            System.out.println("Path: " + javaOracleWebsite.getPath());
            
            // Special characters? What special characters? 😬
            URL searchUrl = new URL("https://www.google.com/search?q=java programming&hl=en");
            System.out.println("\nSearch URL: " + searchUrl);  // Space handling? Good luck!
            
            // Let's hope this URL is valid, or we'll crash!
        } catch (MalformedURLException e) {
            System.err.println("URL is malformed: " + e.getMessage());
        }
    }
}
```

### 📌Modern Approach
```java name=ModernFullURLParser.java
import java.net.URI;
import java.net.URL;
import java.net.URLStreamHandler;

public class ModernFullURLParser {
    public static void main(String[] args) {
        try {
            // Step 1: Create a URI for safe parsing
            URI javaOracleUri = new URI("https://docs.oracle.com/en/java/javase/21/docs/api/");
            
            // Step 2: Convert to URL (for APIs that require URL)
            URL javaDocsUrl = javaOracleUri.toURL();
            System.out.println("Java Docs URL: " + javaDocsUrl);
            
            // Better handling of special characters
            URI searchUri = new URI("https://www.google.com/search")
                .resolve("?q=" + URI.create("").resolve("java modern approach").getPath() + "&hl=en");
            URL searchUrl = searchUri.toURL();
            System.out.println("\nSearch URL: " + searchUrl);
            
            // For specialized protocols, you can use the new factory method
            URI githubUri = new URI("https://github.com/Manish-Royan/JAVA");
            URLStreamHandler defaultHandler = null;  // Use Java's built-in handler
            URL githubUrl = URL.of(githubUri, defaultHandler);
            System.out.println("\nGitHub URL: " + githubUrl);
            
        } catch (Exception e) {
            System.err.println("Exception occurred: " + e.getMessage());
        }
    }
}
```


## 2️⃣ URL(URL context, String spec) - Relative URL Resolution

- Purpose: Resolve a relative URL spec against a base (context) URL, following RFC resolution rules.  
- Resolution semantics:
  - If spec is absolute (has a scheme), it is parsed as-is and context is ignored.  
  - If spec is network-absolute (begins with //), it inherits scheme from context but replaces authority and path.  
  - If spec has an authority (starts with hostname), it replaces context authority.  
  - If spec is relative, it is resolved against context's path: dot-segments "." and ".." are processed, and an appropriate combined path is produced.  
  - Query and fragment rules: a relative spec may replace or append query/fragment depending on its form.  
- Dot-segment removal: Sequences like "/a/b/../c" are normalized to "/a/c" following standard path normalization.  
- Uses: Building link resolvers, HTML crawler, or resolving resources relative to a base web page. Prefer URI.resolve for stronger normalization and then convert to URL for I/O if necessary.  
- Example:

```java
URL base = new URL("https://example.com/dir/sub/");
URL resolved = new URL(base, "../img/logo.png"); // -> https://example.com/dir/img/logo.png
```

### 📌Old School Way (Deprecated)
```java name=OldRelativeURLResolver.java
import java.net.MalformedURLException;
import java.net.URL;

public class OldRelativeURLResolver {
    public static void main(String[] args) {
        try {
            // You're building a web crawler in 2001!
            URL baseUrl = new URL("https://en.wikipedia.org/wiki/Science/");
            
            // Navigating relative to the base URL
            URL physicsUrl = new URL(baseUrl, "Physics");
            URL chemistryUrl = new URL(baseUrl, "Chemistry/Elements");
            URL upOneLevel = new URL(baseUrl, "../Art");
            
            System.out.println("Base: " + baseUrl);
            System.out.println("Physics: " + physicsUrl);
            System.out.println("Chemistry: " + chemistryUrl);
            System.out.println("Arts: " + upOneLevel);
            
        } catch (MalformedURLException e) {
            System.err.println("URL creation failed: " + e.getMessage());
        }
    }
}
```

### 📌Modern Approach
```java name=ModernRelativeURLResolver.java
import java.net.URI;
import java.net.URL;

public class ModernRelativeURLResolver {
    public static void main(String[] args) {
        try {
            // First create the base URI
            URI baseUri = new URI("https://stackoverflow.com/questions/");
            
            // Resolve relative paths against the base
            URI javaUri = baseUri.resolve("java/");
            URI springUri = baseUri.resolve("spring/dependency-injection");
            URI upOneLevelUri = baseUri.resolve("../users/");
            
            // Convert to URLs only when needed
            URL javaUrl = javaUri.toURL();
            URL springUrl = springUri.toURL();
            URL usersUrl = upOneLevelUri.toURL();
            
            System.out.println("Base URI: " + baseUri);
            System.out.println("Java Questions URL: " + javaUrl);
            System.out.println("Spring Questions URL: " + springUrl);
            System.out.println("Users URL: " + usersUrl);
            
            // Even handles spaces and special characters correctly!
            URI articleUri = baseUri.resolve("java/how to handle spaces in URLs");
            URL articleUrl = articleUri.toURL();
            System.out.println("\nQuestion with spaces: " + articleUrl);
            
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
}
```


## 3️⃣ URL(URL context, String spec, URLStreamHandler handler) - Custom Handler
- Purpose: Same resolution semantics as URL(URL, String) but allows a custom URLStreamHandler to control parsing and connection behavior for the resulting URL.  
- When to use: Implement custom protocols or specialized parsing/connection rules without globally installing a URLStreamHandlerFactory. Useful for testing, embedded handlers, or custom schemes (e.g., "myproto:...").  
- Handler role: The handler participates in parsing, and when openConnection() is called the handler supplies the URLConnection implementation.  
- Caution: Handler supplied here overrides default handling only for this URL object; other URLs remain unaffected. Creating custom handlers requires implementing URLStreamHandler and often URLConnection subclasses.
 
### 📌Old School Way (Deprecated)
```java name=OldCustomHandlerURL.java
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.io.IOException;

public class OldCustomHandlerURL {
    public static void main(String[] args) {
        try {
            // It's 2005, and you're implementing a custom protocol for accessing Microsoft docs
            URL baseUrl = new URL("https://learn.microsoft.com/en-us/");
            
            // Custom handler for a proprietary "msdocs:" protocol
            URLStreamHandler msDocsHandler = new URLStreamHandler() {
                @Override
                protected URLConnection openConnection(URL url) throws IOException {
                    System.out.println("Microsoft Docs handler: Opening connection to: " + url);
                    // Implementation would connect to the Microsoft documentation system
                    return null; // Simplified for example
                }
            };
            
            // Creating a URL with the custom handler
            URL documentUrl = new URL(baseUrl, "dotnet/csharp/", msDocsHandler);
            System.out.println("Document URL: " + documentUrl);
            
            // This would cause a security warning in modern Java!
            
        } catch (MalformedURLException e) {
            System.err.println("Malformed URL: " + e.getMessage());
        }
    }
}
```

### 📌Modern Approach
```java name=ModernCustomHandlerURL.java
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

public class ModernCustomHandlerURL {
    public static void main(String[] args) {
        try {
            // Create base URI first
            URI baseUri = new URI("https://api.github.com/");
            
            // Creating the custom handler for a "github:" protocol
            URLStreamHandler githubHandler = new URLStreamHandler() {
                @Override
                protected URLConnection openConnection(URL url) throws IOException {
                    System.out.println("GitHub API handler: Processing " + url.getProtocol() + " connection");
                    System.out.println("Authenticating with GitHub...");
                    // Implementation details omitted
                    return null; // Simplified for example
                }
            };
            
            // Create a URI for the target resource
            URI resourceUri = new URI("github", "//api.github.com", "/repos/Manish-Bishwakarma/java-projects", null);
            
            // Use the modern factory method with the custom handler
            URL resourceUrl = URL.of(resourceUri, githubHandler);
            
            System.out.println("GitHub Resource URL: " + resourceUrl);
            System.out.println("Protocol: " + resourceUrl.getProtocol());
            
            // This approach is more modular and secure!
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
```

## 4️⃣ URL(String protocol, String host, String file) - Component-Based Creation
- Purpose: Programmatically construct a URL when you have protocol, host, and path+query components separated already. The port is not specified and therefore considered unspecified (-1).  
- Behavior: Combines the arguments to form the authority and path. The supplied file string may contain a path and optional query (e.g., "/path/to?x=1").  
- Port semantics: Because no port is passed, getPort() returns -1 and the effective port is the scheme default (80 for http, 443 for https) for connection logic that uses defaults.  
- Validation: Throws MalformedURLException for invalid inputs. The host may be a name or numeric literal (IPv6 must still use bracket notation when including port elsewhere).  
- Example:

```java
URL u = new URL("https", "example.com", "/index.html?q=1");
System.out.println(u.getPort()); // -1
```

### 📌Old School Way (Deprecated)
```java name=OldComponentURLCreator.java
import java.net.MalformedURLException;
import java.net.URL;

public class OldComponentURLCreator {
    public static void main(String[] args) {
        try {
            // You're building a music sharing app in 2003
            String protocol = "https";
            String host = "open.spotify.com";
            String file = "/track/4cOdK2wGLETKBW3PvgPWqT";
            
            // Creating the URL from components
            URL songUrl = new URL(protocol, host, file);
            
            System.out.println("Song URL: " + songUrl);
            System.out.println("Stream from: " + songUrl.getHost());
            System.out.println("Track path: " + songUrl.getFile());
            
            // What if file has spaces? Let's try...
            String fileWithSpaces = "/search/Queen Greatest Hits";
            URL brokenUrl = new URL(protocol, host, fileWithSpaces);
            System.out.println("Problematic URL: " + brokenUrl);
            // Spaces weren't handled well in the old days!
            
        } catch (MalformedURLException e) {
            System.err.println("Invalid URL: " + e.getMessage());
        }
    }
}
```

### 📌Modern Approach
```java name=ModernComponentURLCreator.java
import java.net.URI;
import java.net.URL;
import java.net.URLStreamHandler;

public class ModernComponentURLCreator {
    public static void main(String[] args) {
        try {
            // Building a music streaming app in 2025
            String scheme = "https";
            String authority = "www.youtube.com";
            String path = "/watch";
            
            // Create URI first to properly escape components
            URI videoUri = new URI(scheme, authority, path, "v=dQw4w9WgXcQ", null);
            
            // Convert to URL
            URL videoUrl = videoUri.toURL();
            
            System.out.println("YouTube Video URL: " + videoUrl);
            
            // Handling special characters properly!
            URI searchUri = new URI(
                scheme,
                authority,
                "/results",
                "search_query=Mozart & Bach Symphony",
                null
            );
            
            // For special protocols, we can use URL.of()
            URL searchUrl = URL.of(searchUri, (URLStreamHandler)null);
            
            System.out.println("\nYouTube search with special characters: " + searchUrl);
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
```


## 5️⃣ URL(String protocol, String host, int port, String file) - With Port Specification

- Purpose: Same as the previous constructor but allows you to explicitly set the port. Use -1 to mean the default port for the protocol.  
- Port handling:
  - If port >= 0 it becomes the explicit port returned by getPort().  
  - If port == -1 it indicates "unspecified" and getPort() returns -1 while the effective port for connections remains the protocol default.  
  - Invalid port values (negative other than -1 or > 65535) cause MalformedURLException.  
- Use cases: When you must target non-default ports (8443, 8080) or explicitly signal that no port was provided.  
- Example:

```java
URL u1 = new URL("http", "example.com", 8080, "/app");
URL u2 = new URL("http", "example.com", -1, "/app"); // no explicit port
```

### 📌 Old School Way (Deprecated)
```java name=OldPortSpecifiedURL.java
import java.net.MalformedURLException;
import java.net.URL;

public class OldPortSpecifiedURL {
    public static void main(String[] args) {
        try {
            // You're building a developer tool in 2007
            String protocol = "http";
            String host = "localhost";
            int port = 8080;
            String file = "/api/v1/users";
            
            // Creating the URL with explicit port
            URL apiUrl = new URL(protocol, host, port, file);
            
            System.out.println("Local API Endpoint: " + apiUrl);
            System.out.println("Port: " + apiUrl.getPort());
            
            // Real website with default port (-1)
            URL amazonUrl = new URL("https", "www.amazon.com", -1, "/dp/B09V3KXJPF");
            System.out.println("\nAmazon product URL: " + amazonUrl);
            System.out.println("Default port value: " + amazonUrl.getPort());
            System.out.println("Default port (from getDefaultPort()): " + amazonUrl.getDefaultPort());
            
        } catch (MalformedURLException e) {
            System.err.println("Invalid URL components: " + e.getMessage());
        }
    }
}
```

### 📌 Modern Approach
```java name=ModernPortSpecifiedURL.java
import java.net.URI;
import java.net.URL;

public class ModernPortSpecifiedURL {
    public static void main(String[] args) {
        try {
            // First, build a proper URI with port specification
            URI localDevUri = new URI(
                "http",        // scheme
                null,          // userInfo
                "localhost",   // host
                8080,          // port
                "/api/v2/users", // path
                "status=active&role=admin", // query
                null           // fragment
            );
            
            // Convert to URL when needed for API compatibility
            URL apiUrl = localDevUri.toURL();
            
            System.out.println("Local API URL: " + apiUrl);
            System.out.println("Port: " + apiUrl.getPort());
            System.out.println("Query parameters are properly escaped: " + apiUrl.getQuery());
            
            // Creating production URL with default HTTPS port
            URI microsoftUri = new URI(
                "https",       // scheme
                null,          // userInfo
                "www.microsoft.com", // host
                -1,            // default port
                "/en-us/windows/", // path
                "q=windows 11 features", // query
                null           // fragment
            );
            
            URL windowsUrl = microsoftUri.toURL();
            System.out.println("\nMicrosoft Windows URL: " + windowsUrl);
            System.out.println("Default port value: " + windowsUrl.getPort());
            System.out.println("Default port for HTTPS: " + windowsUrl.getDefaultPort());
            
        } catch (Exception e) {
            System.err.println("Error creating URL: " + e.getMessage());
        }
    }
}
```

## 6️⃣ URL(String protocol, String host, int port, String file, URLStreamHandler handler) - Full Customization

- Purpose: Construct a URL from components while also supplying a custom URLStreamHandler that will handle parsing nuances and openConnection behavior for this URL.  
- Practical scenarios: Testing environments where you want a controlled URLConnection implementation for specific URLs; custom protocols where the handler must be bound at construction time; or when you need to inject behavior without altering global handler factories.  
- Behavior: Same port and composition semantics as the component-based constructor; handler overrides default behavior only for the created URL instance.

### 📌Old School Way (Deprecated)
```java name=OldFullCustomURL.java
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

public class OldFullCustomURL {
    public static void main(String[] args) {
        try {
            // It's 2010 and you're implementing a custom handler for Netflix
            String protocol = "netflix";
            String host = "www.netflix.com";
            int port = 443;
            String file = "/watch/81330768"; // Stranger Things
            
            // Custom handler for Netflix protocol
            URLStreamHandler netflixHandler = new URLStreamHandler() {
                @Override
                protected URLConnection openConnection(URL url) throws IOException {
                    System.out.println("Netflix streaming: " + url.getPath());
                    System.out.println("Using port: " + url.getPort());
                    // Would connect to Netflix streaming service
                    return null; // Simplified for example
                }
            };
            
            // Creating the full custom URL
            URL showUrl = new URL(protocol, host, port, file, netflixHandler);
            
            System.out.println("Netflix Show URL: " + showUrl);
            System.out.println("Protocol: " + showUrl.getProtocol());
            System.out.println("Host: " + showUrl.getHost());
            
        } catch (MalformedURLException e) {
            System.err.println("Malformed URL: " + e.getMessage());
        }
    }
}
```

### 📌Modern Approach
```java name=ModernFullCustomURL.java
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

public class ModernFullCustomURL {
    public static void main(String[] args) {
        try {
            // It's 2025 and you're building a custom handler for AWS resources
            
            // First, create a properly formatted URI
            URI awsUri = new URI(
                "aws",                 // scheme/protocol
                null,                  // userInfo
                "s3.amazonaws.com",    // host
                443,                   // port
                "/manish-bucket/documents/report.pdf", // path
                "versioning=enabled",  // query
                null                   // fragment
            );
            
            // Create a custom handler for the AWS protocol
            URLStreamHandler awsHandler = new URLStreamHandler() {
                @Override
                protected URLConnection openConnection(URL url) throws IOException {
                    System.out.println("\nAWS S3 Connection initializing:");
                    System.out.println("  Protocol: " + url.getProtocol());
                    System.out.println("  Service: " + url.getHost());
                    System.out.println("  Resource path: " + url.getPath());
                    System.out.println("  Options: " + url.getQuery());
                    System.out.println("  Port: " + url.getPort());
                    
                    // Would actually connect to AWS S3
                    return null; // Simplified for example
                }
            };
            
            // Use the modern factory method with URI and custom handler
            URL s3Url = URL.of(awsUri, awsHandler);
            
            System.out.println("AWS S3 Resource URL: " + s3Url);
            
            // Opening a connection would call our handler
            System.out.println("\nAttempting to open connection to S3...");
            URLConnection conn = s3Url.openConnection();
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
```

# Part 2: Modern URL Creation Methods in Java

## 📌 Method 1: `URI.create()` or `new URI()` followed by `toURL()`

👇 This example demonstrates how to safely create URLs by first creating URI objects (which handle encoding better) and then converting them to URLs.

```java name=ModernURLCreation.java
import java.net.URI;
import java.net.URL;

/**
 * Demonstrates modern URL creation using URI objects first
 * @author Manish-Bishwakarma
 * @version 1.0
 * @since 2025-10-19
 */
public class ModernURLCreation {
    public static void main(String[] args) {
        try {
            System.out.println("Modern URL Creation Methods - Using URI First\n");
            
            // Method 1: Using URI.create() - simpler but less control over exceptions
            System.out.println("=== Method 1A: URI.create() ===");
            URI githubUri = URI.create("https://github.com/Manish-Bishwakarma");
            URL githubUrl = githubUri.toURL();
            System.out.println("GitHub URL: " + githubUrl);
            System.out.println("Host: " + githubUrl.getHost());
            System.out.println("Path: " + githubUrl.getPath());
            
            // Handle special characters and spaces properly
            URI searchUri = URI.create("https://www.google.com/search?q=java+modern+URL+creation");
            URL searchUrl = searchUri.toURL();
            System.out.println("\nGoogle Search URL: " + searchUrl);
            System.out.println("Query: " + searchUrl.getQuery());
            
            // Method 2: Using new URI() - more verbose but with explicit exception handling
            System.out.println("\n=== Method 1B: new URI() with components ===");
            
            // Construct a URL for Stack Overflow with special characters in query
            URI stackOverflowUri = new URI(
                "https",                  // scheme
                "stackoverflow.com",      // host
                "/questions/tagged/java", // path
                "sort=newest&pagesize=15", // query
                null                      // fragment
            );
            
            URL stackOverflowUrl = stackOverflowUri.toURL();
            System.out.println("Stack Overflow URL: " + stackOverflowUrl);
            System.out.println("Protocol: " + stackOverflowUrl.getProtocol());
            System.out.println("Path: " + stackOverflowUrl.getPath());
            System.out.println("Query: " + stackOverflowUrl.getQuery());
            
            // Create a URL for YouTube video with complex components
            URI youtubeUri = new URI(
                "https",
                "www.youtube.com",
                "/watch",
                "v=dQw4w9WgXcQ",  // Famous YouTube video ID
                null
            );
            
            URL youtubeUrl = youtubeUri.toURL();
            System.out.println("\nYouTube URL: " + youtubeUrl);
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
```

## 📌 Method 2: `URL.of(URI, URLStreamHandler)` - Available since JDK 20
👇 This example demonstrates the newer static factory method introduced in JDK 20 that creates URLs directly from URIs, with an optional custom handler.

```java name=URLOfFactoryDemo.java
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

/**
 * Demonstrates the URL.of() factory method introduced in JDK 20
 * @author Manish-Bishwakarma
 * @version 1.0
 * @since 2025-10-19
 */
public class URLOfFactoryDemo {
    public static void main(String[] args) {
        try {
            System.out.println("Modern URL Creation - URL.of() Method (JDK 20+)\n");
            
            // Example 1: Basic usage - with default handler (null)
            URI oracleUri = new URI("https://www.oracle.com/java/technologies/javase/jdk20-archive-downloads.html");
            
            // URL.of() with null handler uses the default handler for the protocol
            URL oracleUrl = URL.of(oracleUri, null);
            System.out.println("Oracle JDK URL: " + oracleUrl);
            System.out.println("Protocol: " + oracleUrl.getProtocol());
            System.out.println("Default Port for HTTPS: " + oracleUrl.getDefaultPort());
            
            // Example 2: Creating a URL for an image with default handler
            URI imageUri = new URI("https://www.nasa.gov/sites/default/files/thumbnails/image/main_image_star-forming_region_carina_nircam_final-5mb.jpg");
            URL imageUrl = URL.of(imageUri, null);
            System.out.println("\nNASA Image URL: " + imageUrl);
            
            // Example 3: Creating a URL with custom handler for the HTTP protocol
            System.out.println("\n=== Custom Handler Example ===");
            URI githubUri = new URI("https://api.github.com/users/octocat");
            
            // Create a custom handler that adds special logging or headers
            URLStreamHandler customHandler = new URLStreamHandler() {
                @Override
                protected URLConnection openConnection(URL url) throws IOException {
                    System.out.println("Custom handler intercepting connection to: " + url);
                    
                    // Get the default connection but we could modify it
                    URLConnection connection = new URL(url.toString()).openConnection();
                    
                    // Add custom headers
                    connection.setRequestProperty("User-Agent", "Manish-CustomJavaClient/1.0");
                    System.out.println("Added custom User-Agent header");
                    
                    return connection;
                }
            };
            
            // Use the URL.of() factory method with our custom handler
            URL githubApiUrl = URL.of(githubUri, customHandler);
            System.out.println("GitHub API URL: " + githubApiUrl);
            
            // Demonstrate that our custom handler works when opening a connection
            System.out.println("\nOpening connection with custom handler...");
            URLConnection connection = githubApiUrl.openConnection();
            
            // Just read a small amount of data to demonstrate
            try (InputStream in = connection.getInputStream()) {
                byte[] buffer = new byte[100];
                int bytesRead = in.read(buffer);
                System.out.println("Read " + bytesRead + " bytes from GitHub API");
                System.out.println("(Full response not shown for brevity)");
            } catch (IOException e) {
                System.out.println("Note: Actual connection might be blocked in some environments.");
                System.out.println("The important part is that our custom handler was invoked.");
            }
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
```

## 🗝️ Key Takeaways

1. **The URI-first approach** (`URI.create()` or `new URI()`, then `toURL()`) is:
   - Safer for handling special characters and internationalized domain names
   - Better at validating URL components before creating the URL
   - More predictable across different Java versions
   - The recommended approach for most applications

2. **The `URL.of(URI, URLStreamHandler)` method** (JDK 20+) is:
   - A modern factory method that follows best practices automatically
   - Particularly useful when you need to provide a custom handler
   - More concise than the older constructor-based approach
   - Type-safe and less error-prone

3. **When to use which method:**
   - For most applications: Use the URI-first approach
   - For frameworks or libraries: Consider `URL.of()` when you need custom handlers
   - For simple URL parsing without connection: URI is often sufficient

### 📖 Summary of Modern Best Practices
1. **Step 1:** Always use `URI` first to parse and validate the URL components.
2. **Step 2:** Convert to `URL` only when needed for API compatibility.
3. **Step 3:** For custom protocols, use `URL.of(URI, handler)` instead of direct constructor calls.
4. **Benefits:** Better handling of special characters, clearer separation of concerns, better error messages, and safer parsing.
***