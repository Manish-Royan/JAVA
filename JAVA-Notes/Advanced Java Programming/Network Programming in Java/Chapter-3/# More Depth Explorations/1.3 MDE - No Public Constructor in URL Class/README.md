# No Public Constructor in URL class from JDK 20
↳ That's a very specific and modern piece of Java guidance, reflecting a significant shift that happened in JDK 20.

↳ This statement is the culmination of all the previous discussions about the problems with the `URL` class. The Java team finally decided to formalize the best practices by **deprecating** the old, unsafe constructors.

---

## 1. "No public constructors in the classical sense—all are deprecated since JDK 20!"

➡️ This is the core announcement.

*   **What it means:** The constructors you used to use to create a `URL` object directly are now marked as obsolete and dangerous.
    *   `new URL(String spec)`
    *   `new URL(URL context, String spec)`
    *   `new URL(String protocol, String host, int port, String file)`
    *   And all other variants.

*   **What "Deprecated" means here:** In JDK 20, these constructors are marked with `@Deprecated(since="20", forRemoval=true)`.
    *   `since="20"`: Tells you when it was marked as deprecated.
    *   `forRemoval=true`: This is a very strong warning. It means the Java team intends to **completely remove** these constructors in a future version of Java. Your code will break if you continue to use them.

#### 👉 Your IDE (like IntelliJ or VS Code) will now cross out any use of `new URL(...)` and give you a warning, urging you to use the new, safer methods.


## 2. "Instead, prefer creating via URI parsing then `toURL()`, or the static `URL.of(URI, URLStreamHandler)`."
➡️ This is the "**how-to**" part, explaining the two new approved ways to create a `URL`.

### \# Method A: The Standard, Recommended Path for 99% of Cases
> **"...creating via URI parsing then `toURL()`"**

↳ This is the workflow we've discussed. It is now the official, primary way to create a URL.

#### **The Process:**
1.  **Create a `URI`:** You start by creating a `java.net.URI` object. This step is purely for parsing and validating the string according to strict rules (RFC 2396/3986) **without any network activity**.
2.  **Convert to `URL`:** Only when you are ready to connect, you call the `.toURL()` method on the `URI` object to get a `URL` instance.

### 📌 Code Example (The "New" Way):
```java
import java.net.URI;
import java.net.URL;

try {
    // Step 1: Safely parse the string into a URI.
    // This step validates syntax and handles encoding without network calls.
    URI myUri = new URI("https://www.example.com/path/to/resource");

    // You can now safely use 'myUri' in HashMaps, etc.

    // Step 2: Convert to a URL only when you are ready to open a connection.
    URL myUrl = myUri.toURL();
    
    // Now you can use myUrl.openStream(), etc.
    System.out.println("URL created successfully: " + myUrl);

} catch (Exception e) {
    // Catches URISyntaxException or MalformedURLException
    e.printStackTrace();
}
```

### \# Method B: The Advanced, Specialized Path
> **"...or the static `URL.of(URI, URLStreamHandler)`"**

➡️ This is a new static factory method for advanced use cases.

*   **What is a `URLStreamHandler`?** Think of it as a "driver" or a "plugin" for a protocol. Java has built-in handlers for `http`, `https`, `ftp`, etc. This method allows you to provide your *own custom handler* for a protocol that Java doesn't know about.
*   **When would you use this?** If you were inventing your own protocol, like `myprotocol://...`, and you needed to teach Java how to open a connection for it.
*   **For most developers:** You will likely **never** need to use this method. The `URI.toURL()` approach is the one to remember. This is mainly for people writing new frameworks or complex networking libraries.
Of course, Manish. Here is a detailed explanation and a complete, runnable code sample for that advanced use case.

### \# Simple Explanation of this Concept

💭 Imagine Java is a mail carrier. It knows how to deliver mail to standard addresses like "HTTP" streets and "FTP" avenues because it has built-in maps (`URLStreamHandler`) for them.

💭 Now, imagine you invent a brand new type of address on a "Memory" street (`memory://...`). The mail carrier (Java) has no idea what this is or how to deliver mail there.

#### 👉 The `URL.of(URI, URLStreamHandler)` method is your way of handing the mail carrier a **custom, hand-drawn map** (`URLStreamHandler`) and saying:

> "Hey, when you see an address for 'Memory' street, use *this special map* I made. It will show you exactly how to get there and deliver the package (`openConnection`)."

↳ This is a very specialized feature. Most developers will only ever use the standard maps that Java already provides. You only need to do this if you are creating a new protocol or fundamentally changing how an existing one works within your application.

### 📌 Code Example
👇 This example will teach Java how to handle a new, imaginary protocol called `memory://`. A URL like `memory://data/hello` will not connect to a network but will instead retrieve the string "hello" from a predefined `Map` in the program's memory.

🔸 We will need to create three parts:
1.  **`MemoryURLConnection`**: The custom "delivery truck" that knows how to fetch data for our `memory://` protocol.
2.  **`MemoryURLStreamHandler`**: The "custom map" that tells Java to use our `MemoryURLConnection`.
3.  **`AdvancedURLDemo`**: The main program that puts it all together.


### 📌 Part 1: The Custom `URLConnection`
➡️ This class defines what happens when you call `openConnection()`. It's responsible for getting the actual data.

```java name=MemoryURLConnection.java
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * This is our custom "delivery truck." It knows how to handle a connection
 * to a "memory:" URL. Instead of connecting to a network, it looks up data
 * in a predefined Map.
 */
public class MemoryURLConnection extends URLConnection {

    // A simple in-memory "database" to store our data.
    private static final Map<String, String> memoryDatabase = Map.of(
        "welcome", "Hello from the memory protocol!",
        "time", "The current time is (not really) 12:00 PM.",
        "author", "Manish-Bishwakarma"
    );

    private final String dataKey;

    protected MemoryURLConnection(URL url) {
        super(url);
        // The "path" of the URL (e.g., "/welcome") is used as the key.
        // We remove the leading "/" to match the map key.
        this.dataKey = url.getPath().substring(1);
    }

    @Override
    public void connect() throws IOException {
        // In a real network connection, this would establish the socket link.
        // For us, it's a no-op because the data is already in memory.
        // We can check if the key exists here.
        if (!memoryDatabase.containsKey(dataKey)) {
            throw new IOException("Data not found in memory for key: " + dataKey);
        }
        System.out.println("-> [MemoryURLConnection] 'Connected' to memory for key: " + dataKey);
    }

    @Override
    public InputStream getInputStream() throws IOException {
        // Ensure "connect" has been called implicitly or explicitly.
        connect();

        // Get the data from our map.
        String data = memoryDatabase.get(dataKey);

        // Return the data as a stream, just like a real URLConnection would.
        return new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8));
    }
}
```

### 📌Part 2: The Custom `URLStreamHandler`
➡️ This is the "custom map." Its only job is to tell Java: "When you need a connection for a `memory://` URL, create an instance of `MemoryURLConnection`."

```java name=MemoryURLStreamHandler.java
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

/**
 * This is our "custom map" for the "memory:" protocol.
 * Its one and only job is to create our custom URLConnection when requested.
 */
public class MemoryURLStreamHandler extends URLStreamHandler {

    @Override
    protected URLConnection openConnection(URL url) throws IOException {
        System.out.println("-> [Handler] Intercepting call for protocol: " + url.getProtocol());
        System.out.println("-> [Handler] Creating a new MemoryURLConnection.");
        return new MemoryURLConnection(url);
    }
}
```

---

### 📌Part 3: The Main Application
➡️ This class demonstrates how to use `URL.of()` to register our custom handler and then use the resulting `URL` object.

```java name=AdvancedURLDemo.java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.util.stream.Collectors;

/**
 * Main demo showing how to use the advanced URL.of() method with a custom
 * URLStreamHandler to teach Java a new protocol.
 */
public class AdvancedURLDemo {

    public static void main(String[] args) {
        System.out.println("### Advanced URL Demo: Teaching Java the 'memory://' protocol ###");

        // 1. Instantiate our custom "map" (the handler).
        MemoryURLStreamHandler memoryHandler = new MemoryURLStreamHandler();

        // 2. Define the URI for our custom protocol.
        // We want to access the data associated with the key "author".
        String uriString = "memory://database/author";
        
        try {
            System.out.println("\nStep 1: Creating URI -> " + uriString);
            URI memoryUri = new URI(uriString);

            // 3. Use the ADVANCED URL.of() method.
            // This is where we associate our URI with our custom handler.
            System.out.println("Step 2: Creating URL with URL.of(uri, customHandler)");
            URL memoryUrl = URL.of(memoryUri, memoryHandler);

            System.out.println("Step 3: Opening connection and reading data...");
            // When we call getInputStream(), Java will use our handler, which will
            // create our custom URLConnection, which will fetch data from our map.
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(memoryUrl.getInputStream()))) {
                String content = reader.lines().collect(Collectors.joining());
                System.out.println("\nSUCCESS! Content read from " + memoryUrl + ":");
                System.out.println("=========================================");
                System.out.println(content);
                System.out.println("=========================================");
            }

        } catch (Exception e) {
            System.err.println("\nAn error occurred:");
            e.printStackTrace();
        }
    }
}
```

### 🏃‍♂️‍➡️ How to Run and Expected Output 

1.  Save all three files (`MemoryURLConnection.java`, `MemoryURLStreamHandler.java`, and `AdvancedURLDemo.java`) in the same directory.
2.  Compile them: `javac *.java`
3.  Run the main demo: `java AdvancedURLDemo`

### 💡 You will see the following output, showing the flow of control from the handler to the connection and finally the retrieved data.

```
### Advanced URL Demo: Teaching Java the 'memory://' protocol ###

Step 1: Creating URI -> memory://database/author
Step 2: Creating URL with URL.of(uri, customHandler)
Step 3: Opening connection and reading data...
-> [Handler] Intercepting call for protocol: memory
-> [Handler] Creating a new MemoryURLConnection.
-> [MemoryURLConnection] 'Connected' to memory for key: author

SUCCESS! Content read from memory://database/author:
=========================================
Manish-Bishwakarma
=========================================
```

---

## 3. "This shift promotes safer parsing."
➡️ This explains the **"why."** Let's summarize it in the context of this deprecation. "**Safer parsing**" means:
1.  **No Hidden Network Calls:** By forcing you to start with `URI`, the new pattern prevents you from accidentally triggering a slow and blocking DNS lookup just by creating an object or putting it in a `HashMap`.
2.  **Strict and Predictable Validation:** `URI` is much stricter about what constitutes a valid identifier. It will immediately fail on illegal characters (like spaces), forcing you to write correct, properly-encoded code. The old `URL` constructors were too lenient and could lead to unpredictable behavior.
3.  **Clear Separation of Concerns:** The new pattern makes your code's intent clearer.
    *   `new URI(...)`: "I am defining and validating an identifier."
    *   `.toURL()`: "I am now preparing to perform a network operation with this identifier."

## 4. "Constructors/methods throw `MalformedURLException` for invalid syntax, unknown protocols, or handler issues."
➡️ This is a reminder of the kind of errors you need to handle.
*   `MalformedURLException` is a **checked exception**. Your code will not compile unless you handle it (with a `try-catch` block or by declaring that your method `throws` it).
*   **When does it get thrown?**
    *   **Invalid Syntax:** Although `URI` catches most syntax errors with `URISyntaxException`, `.toURL()` can still fail if the URI, while syntactically valid, cannot be represented as a URL (this is rare).
    *   **Unknown Protocols:** This is the most common reason. If you call `uri.toURL()` on a `URI` like `new URI("urn:isbn:12345")`, it will throw a `MalformedURLException` because Java has no built-in stream handler for the `urn` protocol. It doesn't know "how" to open a connection to a URN.
    *   **Handler Issues:** Problems finding or initializing the required protocol handler.

#### ➡️ In summary, the deprecation of `URL` constructors is the Java platform's way of enforcing a safer, more robust programming model that developers have been recommending for years. It forces you to separate the act of **parsing an identifier** from the act of **connecting to a resource**.
***