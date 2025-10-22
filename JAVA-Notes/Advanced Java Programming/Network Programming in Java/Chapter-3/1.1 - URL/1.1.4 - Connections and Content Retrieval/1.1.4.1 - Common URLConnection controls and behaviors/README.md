## ☑️ Common `URLConnection` controls and behaviors

### 1. `setDoInput` and `setDoOutput`  
- `setDoInput` true means you intend to read a response. setDoOutput true means you intend to send a request body. Set these before any connect or stream calls.
```java name=DoInputOutputExample.java
import java.net.URL;
import java.net.URLConnection;
import java.io.*;

public class DoInputOutputExample {
    public static void main(String[] args) {
        try {
            // Create connection to a POST endpoint
            URL url = new URL("https://httpbin.org/post");
            URLConnection conn = url.openConnection();
            
            // Configure the connection
            conn.setDoInput(true);   // We want to read the response (default is true)
            conn.setDoOutput(true);  // We want to send data (default is false)
            
            // Write data to the server
            try (OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream())) {
                out.write("name=Manish&message=Hello");
            }
            
            // Read the response
            try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String line;
                while ((line = in.readLine()) != null) {
                    System.out.println(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

### 2. `setRequestProperty`  
- Allows custom request headers such as Accept, **Content-Type**, **User-Agent**, or **Authorization**. Set headers before connecting.
```java name=RequestHeadersExample.java
import java.net.URL;
import java.net.URLConnection;
import java.io.*;

public class RequestHeadersExample {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://httpbin.org/headers");
            URLConnection conn = url.openConnection();
            
            // Set custom request headers
            conn.setRequestProperty("User-Agent", "Manish-Java-Client/1.0");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("X-Custom-Header", "Hello from Java");
            
            // Read the response
            try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String line;
                while ((line = in.readLine()) != null) {
                    System.out.println(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

### 3. connect and implicit connect  
- You can call connect explicitly to negotiate TCP and protocol handshakes. Many APIs implicitly call connect when you request a stream. Explicit connect is useful to fail early and check response metadata.

```java name=ExplicitConnectExample.java
import java.net.URL;
import java.net.URLConnection;
import java.io.*;

public class ExplicitConnectExample {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://www.google.com");
            URLConnection conn = url.openConnection();
            
            // Set a short timeout
            conn.setConnectTimeout(5000); // 5 seconds
            
            System.out.println("Connecting explicitly...");
            // Explicit connect - good for catching connection problems early
            conn.connect();
            System.out.println("Connected successfully!");
            
            // Now we can check headers before reading content
            System.out.println("Content type: " + conn.getContentType());
            
            // Read content
            System.out.println("Reading content...");
            try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                // getInputStream() would implicitly call connect() if we hadn't already
                String line = in.readLine();
                System.out.println("First line: " + line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

## 4. `getHeaderFields` and `getHeaderField`  
- Retrieve response headers for status, content-type, caching, and cookies. Headers are available after connection.
```java name=HeaderFieldsExample.java
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

public class HeaderFieldsExample {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://www.github.com");
            URLConnection conn = url.openConnection();
            conn.connect();
            
            // Get a specific header
            String contentType = conn.getHeaderField("Content-Type");
            System.out.println("Content-Type: " + contentType);
            
            // Get all headers
            System.out.println("\nAll Headers:");
            Map<String, List<String>> headers = conn.getHeaderFields();
            for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
                String key = entry.getKey();
                List<String> values = entry.getValue();
                System.out.println(key + ": " + values);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

### 5. `getContent` and `getContentType`  
- `getContent` uses the content handler mechanism to return typed content objects; `getContentType` returns the MIME type string. Rely on raw InputStream and content-type parsing for deterministic handling.
```java name=ContentMethodsExample.java
import java.net.URL;
import java.net.URLConnection;
import java.io.*;

public class ContentMethodsExample {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://example.org");
            URLConnection conn = url.openConnection();
            
            // Get content type
            String contentType = conn.getContentType();
            System.out.println("Content Type: " + contentType);
            
            // Get content
            Object content = conn.getContent();
            System.out.println("Content class: " + content.getClass().getName());
            
            // If content is an input stream, read it
            if (content instanceof InputStream) {
                InputStream stream = (InputStream) content;
                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                System.out.println("\nContent preview:");
                for (int i = 0; i < 3; i++) {  // First 3 lines
                    System.out.println(reader.readLine());
                }
                reader.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

### 6. getInputStream and getOutputStream  
- InputStream gives response body bytes. OutputStream is used to send body bytes for methods that support a request body. Always close streams to free sockets and file descriptors.
```java name=StreamsExample.java
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.*;

public class StreamsExample {
    public static void main(String[] args) {
        try {
            // POST request with input and output
            URL url = new URL("https://httpbin.org/post");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            
            // Write data using output stream
            try (OutputStream out = conn.getOutputStream();
                 Writer writer = new OutputStreamWriter(out)) {
                writer.write("message=Hello from Manish");
            }
            
            // Read response using input stream
            try (InputStream in = conn.getInputStream();
                 BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
                System.out.println("Response:");
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

### 7. Caching and If-Modified-Since  
- URLConnection supports basic caching-related headers such as If-Modified-Since. For production-grade caching use higher-level libraries or dedicated caches.
```java name=CachingExample.java
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class CachingExample {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://www.example.org");
            
            // First request - get the resource and last-modified date
            URLConnection firstConn = url.openConnection();
            firstConn.connect();
            long lastModified = firstConn.getLastModified();
            System.out.println("Last-Modified: " + new Date(lastModified));
            
            // Read the content
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(firstConn.getInputStream()))) {
                System.out.println("First response (first 3 lines):");
                for (int i = 0; i < 3; i++) {
                    System.out.println(reader.readLine());
                }
            }
            
            // Second request with If-Modified-Since
            System.out.println("\nMaking second request with If-Modified-Since...");
            URLConnection secondConn = url.openConnection();
            
            // Format date according to HTTP standard
            SimpleDateFormat dateFormat = new SimpleDateFormat(
                "EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
            String ifModifiedSince = dateFormat.format(new Date(lastModified));
            
            secondConn.setRequestProperty("If-Modified-Since", ifModifiedSince);
            
            // Check if content was modified
            try {
                InputStream in = secondConn.getInputStream();
                System.out.println("Content changed - reading new version");
                in.close();
            } catch (IOException e) {
                System.out.println("Got 304 Not Modified - content is unchanged");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```
***