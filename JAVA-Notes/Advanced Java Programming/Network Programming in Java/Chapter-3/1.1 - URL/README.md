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

### 🔍 Breakdown of the Arguments

| Argument        | Value       | Meaning                                                                 |
|----------------|-------------|-------------------------------------------------------------------------|
| `scheme`        | `"https"`   | The protocol used (e.g., HTTP, HTTPS, FTP)                             |
| `userInfo`      | `null`      | No username/password in the URL (e.g., `user:pass@`)                   |
| `host`          | `"github.com"` | The domain name or IP address of the server                          |
| `port`          | `443`       | Explicit port number (443 is default for HTTPS)                        |
| `path`          | `"/Manish-Royan/JAVA"` | The resource path on the server                          |
| `query`         | `null`      | No query string (e.g., `?key=value`)                                   |
| `fragment`      | `null`      | No fragment identifier (e.g., `#section1`)  


## 📌 Basic URL Creation
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

### 🔹Modern Approach: Safe creation using URI builder and conversion
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

## 🔹The `java.net.URL` Class - Your Gateway to the Web
➡️ The `java.net.URL` class is a high-level representation of a URL. Its primary purpose is to make it incredibly simple to locate and retrieve data from a network resource.

**Why is it so important?** It's an **abstraction**. It hides the complex, low-level details of network programming (sockets, protocol handshakes, HTTP requests) behind a clean and simple API.

#### 🔸Creating a `URL` Object (Constructors)
➡️ The first step is to create a `URL` instance. This process can fail if the string isn't a valid, well-formed URL, so you must handle the checked `MalformedURLException`.

**1. Absolute URL Constructor:** The most common way.

```java
import java.net.URL;
import java.net.MalformedURLException;

try {
    URL myWebsite = new URL("https://github.com/Manish-Royan");
    System.out.println("URL is valid: " + myWebsite.toString());
} catch (MalformedURLException e) {
    System.err.println("Invalid URL: " + e.getMessage());
}
```

**2. Relative URL Constructor:** Creates a URL relative to a base (or context) URL.

```java
try {
    URL base = new URL("https://www.example.com/docs/");
    URL relative = new URL(base, "../images/logo.png"); // Go up one level, then to images
    
    // The resulting URL will be: https://www.example.com/images/logo.png
    System.out.println("Resolved URL: " + relative.toString()); 
} catch (MalformedURLException e) {
    System.err.println("Error creating URL: " + e.getMessage());
}
```

**3. Multi-part Constructor:** Less common, but allows you to build a URL from its components.

```java
try {
    // URL(protocol, host, port, file)
    URL apiEndpoint = new URL("https", "api.myservice.com", 443, "/v1/users");
    System.out.println("API URL: " + apiEndpoint);
} catch (MalformedURLException e) {
    System.err.println("Error: " + e.getMessage());
}
```

## \# Explaining x-www-form-urlencoded Strings
➡️ `application/x-www-form-urlencoded` is a standard format for encoding data when sending web forms or query parameters over HTTP. Let me explain it in detail with a simple sample code.

### What is x-www-form-urlencoded?
➡️ This is a content type used by web browsers when submitting HTML forms. It turns form data into a string that can be safely sent in URLs or HTTP requests.

### Why is it called "x-www-form-urlencoded"❓
- "x-www-form" refers to HTML web forms (the old name for form encoding).
- "urlencoded" means the data is encoded to be safe for URLs.

### 💭 Analogy 
➡️ Imagine you fill out a paper form at an office, like this:

| Field       | Your Answer     |
|-------------|-----------------|
| **Name:**   | Manish Sharma   |
| **Topic:**  | Java & Web      |

➡️ Now, you need to send this information to another office over a very basic telegraph system that only understands a single line of text and has rules about which characters it can use.

#### 👉 `x-www-form-urlencoded` is the standard way to "translate" your form into that single, safe line of text.

### Here’s how it works:

1.  **Flatten it into a single line:** It takes each field and its answer.
2.  **Use special separators:**
    *   It connects the field and its answer with an **equals sign (`=`)**.
    *   It separates each field-answer pair with an **ampersand (`&`)**.
3.  **Make it "URL Safe":**
    *   It replaces any spaces with a **plus sign (`+`)**.
    *   It converts any other special characters (like `&` in your text) into a safe code (like `%26`).

#### 🔹 So, your form would be translated into this single, safe string: `Name=Manish+Sharma&Topic=Java+%26+Web`

#### 👉 This is the **`x-www-form-urlencoded`** string. The web browser does this automatically when you click "Submit" on a form. The server on the other end knows how to read this format and reconstruct the original form data.

### ⛓️‍💥 Breaking Down the Name

*   **`x-www-form`**: This is just a historical, technical name that means "**web form**". The "x-" was often used for non-standard names that later became standard.
*   **`urlencoded`**: This tells you the data has been **encoded** (translated) to be **safe for a URL**.

#### 👉 So, `x-www-form-urlencoded` simply means: **"Data from a web form that has been encoded to be safe for a URL."**

### How It Works❓

1. **Key-Value Pairs**: Form data consists of field names and values (e.g., name="John", age="25").

2. **Encoding Rules**:
   - Fields are separated by ampersands (&).
   - Names and values are separated by equals signs (=).
   - Special characters are percent-encoded (%XX).
   - Spaces become plus signs (+).

3. **Example**:
   - Raw data: name="John Doe", age="25"
   - Encoded: `name=John+Doe&age=25`

### Detailed Rules ⤵️

#### Character Encoding
- **Safe characters**: Letters, digits, hyphens (-), periods (.), underscores (_) stay unchanged.
- **Spaces**: Become `+`.
- **Unsafe characters**: Get percent-encoded (e.g., `&` becomes `%26`, `=` becomes `%3D`).
- **Unicode**: Characters are first converted to UTF-8 bytes, then percent-encoded.

#### Special Cases
- **Multiple values**: Same field name can appear multiple times: `hobby=reading&hobby=gaming`
- **Empty values**: Allowed: `name=John&nickname=`
- **No values**: Field appears alone: `agree=yes` (implied true)

### When It's Used

1. **HTML Forms**: `<form method="POST" enctype="application/x-www-form-urlencoded">`
2. **URL Query Strings**: `https://example.com/search?q=java+programming&limit=10`
3. **AJAX Requests**: When sending form data via JavaScript
4. **API Calls**: Some REST APIs accept this format

### 📌Example
➡️ Here's a simple example using `URLEncoder` to create x-www-form-urlencoded data:

```java name=FormUrlEncodedExample.java
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.io.UnsupportedEncodingException;

public class FormUrlEncodedExample {
    public static void main(String[] args) {
        try {
            // Sample form data
            String name = "John Doe";
            String email = "john@example.com";
            String message = "Hello, this is a test message with spaces & special chars!";
            int age = 25;
            
            // Encode each field value
            String encodedName = URLEncoder.encode(name, StandardCharsets.UTF_8.name());
            String encodedEmail = URLEncoder.encode(email, StandardCharsets.UTF_8.name());
            String encodedMessage = URLEncoder.encode(message, StandardCharsets.UTF_8.name());
            String encodedAge = URLEncoder.encode(String.valueOf(age), StandardCharsets.UTF_8.name());
            
            // Build the form-urlencoded string
            String formData = "name=" + encodedName + 
                            "&email=" + encodedEmail + 
                            "&message=" + encodedMessage + 
                            "&age=" + encodedAge;
            
            System.out.println("Raw form data:");
            System.out.println("Name: " + name);
            System.out.println("Email: " + email);
            System.out.println("Message: " + message);
            System.out.println("Age: " + age);
            System.out.println();
            
            System.out.println("URL-encoded form data:");
            System.out.println(formData);
            
            // This can be sent in a POST request body with Content-Type: application/x-www-form-urlencoded
            
        } catch (UnsupportedEncodingException e) {
            System.err.println("UTF-8 encoding not supported: " + e.getMessage());
        }
    }
}
```

### Output Example:
```
Raw form data:
Name: John Doe
Email: john@example.com
Message: Hello, this is a test message with spaces & special chars!
Age: 25

URL-encoded form data:
name=John+Doe&email=john%40example.com&message=Hello%2C+this+is+a+test+message+with+spaces+%26+special+chars%21&age=25
```

### 🗝️ Key Points to Remember when Form Encoding Rules

1.  **Use UTF-8 (The Universal Language)**
    *   To make sure text in *any language* (like Hindi, Japanese, etc.) or emojis (😊) works correctly everywhere.

2.  **Encode Only the "Value"**
    *   In a pair like `name=John Doe`, the `John Doe` part might have spaces or special characters. Always encode this **value** to keep it safe. The `name` part is usually simple and doesn't need it.

3.  **Servers Understand It Automatically**
    *   You send the encoded string (`name=John+Doe`), and the web server automatically translates it back into readable data for your application. You don't have to decode it manually.

4.  **Use JSON for Complex Data**
    *   This format is for simple `key=value` text. If you're sending complex, nested data (like a user with a list of orders), use the JSON format instead.

5.  **No Files Allowed**
    *   This format is for **text only**. To upload files (images, PDFs, etc.), you must use a different format called `multipart/form-data`.

#### 👉 This encoding ensures your form data travels safely over the web without breaking URLs or HTTP requests!


## Part 1: `URLEncoder` is for Forms (Space becomes `+`)

### Explanation
➡️ Think of a URL as having two main parts: the **path** (like a file folder address) and the **query** (extra instructions you give, after the `?`). These two parts have slightly different rules for handling spaces.

1.  **Form/Query Encoding (`URLEncoder`)**:
    *   This is for the **query** part.
    *   It turns a space into a **plus sign (`+`)**.
    *   This is a special rule for web forms (`application/x-www-form-urlencoded`).
    *   **Example**: A search for "java tips" becomes `?q=java+tips`.

2.  **Path Encoding (`URI` class)**:
    *   This is for the **path** part of the URL.
    *   It turns a space into **`%20`**.
    *   This is the standard for file paths on the web.
    *   **Example**: A folder named "my files" becomes `/my%20files`.

#### 👉 **The Rule:** Never use `URLEncoder` to build a whole URL. Use it *only* for the values in the query string.

### 📌Example
➡️ This code shows how to correctly build a URL that has spaces in *both* its path and its query string, using the right tool for each part.

```java name=UrlEncodingStyles.java
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class UrlEncodingStyles {
    public static void main(String[] args) throws Exception {
        // --- Data with spaces ---
        String folderName = "My Documents"; // This will go in the path
        String searchTerm = "java tips";    // This will go in the query

        // --- 1. Encode the QUERY part using URLEncoder (space -> +) ---
        String encodedQuery = URLEncoder.encode(searchTerm, StandardCharsets.UTF_8);
        // Result will be: "java+tips"
        System.out.println("Query part encoded with URLEncoder: " + encodedQuery);

        // --- 2. Build the full URL using the URI class ---
        // The URI class correctly handles the PATH part (space -> %20).
        URI uri = new URI(
            "https",                  // Scheme
            "example.com",            // Host
            "/files/" + folderName,   // Path -> "/files/My Documents"
            "q=" + encodedQuery,      // Query -> "q=java+tips"
            null                      // Fragment
        );

        // The URI constructor automatically encodes the path correctly.
        // It turns "/files/My Documents" into "/files/My%20Documents".
        System.out.println("\nPath part encoded by URI class: " + uri.getRawPath());

        // --- 3. The final, correctly built URL ---
        System.out.println("\nFinal Correct URL: " + uri.toString());
        // Output shows "%20" in the path and "+" in the query.
    }
}
```
#### 👉 **Key Takeaway:** Use `URLEncoder` for the query part (`?name=value`) and let the `URI` class handle the path part.

## Part 2: Reserved vs. Unreserved Characters

### Explanation
➡️ Think of a URL as a sentence. It has **words** (your data) and **punctuation** (special characters that structure the sentence).

1.  **Unreserved Characters (The "Words")**:
    *   These are safe characters that never need to be encoded: `A-Z`, `a-z`, `0-9`, and `- . _ ~`.
    *   They are like the plain words in a sentence—they don't have a special structural meaning.

2.  **Reserved Characters (The "Punctuation")**:
    *   These characters have special jobs in a URL: `: / ? # [ ] @ ! $ & ' ( ) * + , ; =`.
    *   They act like commas, question marks, and periods, giving the URL its structure.
    *   `:` separates protocol and host.
    *   `/` separates path segments.
    *   `?` starts the query string.
    *   `&` separates query parameters.
    *   `=` separates a parameter name from its value.

#### 👉 **The Rule:** You must **encode** any user-provided data that might contain a reserved character. If you don't, the browser might think it's "punctuation" and break the URL. However, you must **not** encode the punctuation that is actually part of the URL's structure.

### 📌Example
⤵️ This code shows why you must encode user data. We have a username that contains an `&`, which is a reserved character.

```java name=ReservedCharacterEncoding.java
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class ReservedCharacterEncoding {
    public static void main(String[] args) throws Exception {
        // User data that contains a reserved character '&'
        String username = "Tom&Jerry";
        String role = "user";

        // --- INCORRECT: Not encoding the username ---
        String badUrl = "https://api.example.com/login?user=" + username + "&role=" + role;
        System.out.println("INCORRECT URL: " + badUrl);
        // A server would see three parameters:
        // 1. user = "Tom"
        // 2. Jerry = "" (empty, because of the &)
        // 3. role = "user"
        // This is wrong and will cause a bug!

        // --- CORRECT: Encoding the username ---
        String encodedUsername = URLEncoder.encode(username, StandardCharsets.UTF_8);
        // The '&' becomes '%26'
        System.out.println("\nEncoded Username: " + encodedUsername);

        String goodUrl = "https://api.example.com/login?user=" + encodedUsername + "&role=" + role;
        System.out.println("CORRECT URL:   " + goodUrl);
        // A server will correctly see two parameters:
        // 1. user = "Tom&Jerry"
        // 2. role = "user"
        // This works perfectly!
    }
}
```
#### 👉 **Key Takeaway:** Always encode data that comes from a user or an external source before putting it into a URL. This prevents their input from breaking your URL's structure.



## \# Important rules for `URLEncoder` 

### 💭 The "Letter and Envelope" Analogy
➡️ Think of building a URL like sending a letter:

1.  **The Envelope (The URL Structure):**
    *   This has the main address: the protocol (`https://`), the host (`example.com`), and the path (`/search-results/`).
    *   You must **not** change or encode this part. It has to follow strict postal rules.

2.  **The Letter Inside (The Data/Query):**
    *   This is the message you're sending, like `search for: java & web`
    *   This message might contain special characters (`&`) or spaces that could confuse the postal service. You must **encode** this part to make it safe.

**`URLEncoder` is for the *letter inside*, NOT the *envelope*.**

*   **Use `URLEncoder` for:** The data you are sending (query values, form data).
*   **Do NOT use `URLEncoder` for:** The structural parts of the URL (the host, path, etc.). It will scramble the address, and your letter will never arrive.

### Why You Must Use `UTF-8` (Modern API Usage)
*   **Simple Reason:** `UTF-8` is the universal language of the web. It understands every character from every language, plus emojis.
*   **Old Way (Bad):** Older code let the computer guess the language, which could fail on different servers.
*   **New Way (Good):** By always specifying `StandardCharsets.UTF_8`, you tell Java, "Use the universal web language." This makes your code reliable and avoids errors.

### 📌Example
⤵️This example builds a URL to `httpbin.org`, a great testing website that shows you exactly what it received. We will try to send a file path with a space and a search query with a space and the `&` symbol.

```java name=UrlEncoderBestPractice.java
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class UrlEncoderBestPractice {
    public static void main(String[] args) throws Exception {

        // --- The pieces of our URL ---
        String filePath = "/files/My Report"; // Part of the PATH, has a space.
        String searchQuery = "Java & C++";    // Part of the QUERY, has a space and '&'.

        // --- 1. CORRECT WAY: Encode ONLY the query value ---
        // Use URLEncoder for the data that goes inside the "letter".
        String encodedQuery = URLEncoder.encode(searchQuery, StandardCharsets.UTF_8);
        // This correctly turns "Java & C++" into "Java+%26+C%2B%2B"

        // --- 2. CORRECT WAY: Use the URI class to build the full URL ---
        // The URI class will handle the path correctly (space -> %20).
        URI correctUri = new URI(
            "https",                    // Scheme
            "httpbin.org",              // Host
            filePath,                   // Path
            "q=" + encodedQuery,        // Query
            null                        // Fragment
        );

        System.out.println("✅ CORRECT USAGE:");
        System.out.println("   - The final URL is built safely.");
        System.out.println("   - Path space becomes '%20'.");
        System.out.println("   - Query space becomes '+', and '&' becomes '%26'.");
        System.out.println("   - Click this working link to see the result:");
        System.out.println("   " + correctUri.toURL());
        System.out.println("\n-------------------------------------------------\n");


        // --- 3. WRONG WAY: Encoding the entire URL string ---
        String fullUrlString = "https://httpbin.org" + filePath + "?q=" + searchQuery;

        // DO NOT DO THIS! It breaks the URL's structure.
        String incorrectlyEncodedUrl = URLEncoder.encode(fullUrlString, StandardCharsets.UTF_8);

        System.out.println("❌ INCORRECT USAGE:");
        System.out.println("   - Encoding the full URL scrambles it.");
        System.out.println("   - The ':', '/', and '?' are all encoded, making it invalid.");
        System.out.println("   - This link will NOT work:");
        System.out.println("   " + incorrectlyEncodedUrl);
    }
}
```

### What Happens When You Run the Code:

1.  **The Correct URL:**
    *   The output will be: `https://httpbin.org/files/My%20Report?q=Java+%26+C%2B%2B`
    *   If you click this link, `httpbin.org` will show you that it correctly received the path and the query `{"q": "Java & C++"}`. It works perfectly.

2.  **The Incorrect URL:**
    *   The output will be a long, scrambled string like: `https%3A%2F%2Fhttpbin.org...`
    *   This is completely broken because the essential characters (`:`, `/`) were wrongly encoded. It's not a valid URL.


# \# URL DECODER

## 📚 Overview of URL 
➡️ URLDecoder reverses application/x-www-form-urlencoded encoding: it converts plus signs back to spaces and percent‑encoded octets (%HH) back into characters using a specified charset. It’s intended primarily for decoding HTML form data and query-string values.

### 💭 Analogy: The "Decoder Ring" for Web Forms
➡️ Imagine your friend sends you a secret message written in a special code. `URLDecoder` is the "decoder ring" you use to translate that message back into plain, readable English.
➡️ It's the exact opposite of `URLEncoder`. It takes a web-safe string and reverses the encoding process.

### **It follows two main rules:**

1.  **Plus signs (`+`) become spaces.**
    *   When it sees `hello+world`, it knows to translate it back to `hello world`.

2.  **Percent codes (`%HH`) become characters.**
    *   When it sees a code like `%26`, it translates it back to the `&` symbol.
    *   For international text, like `%E2%98%95`, it translates it back to the original character (in this case, `☕`).

#### 👉 **The Golden Rule:** You must use the same "decoder key" (the charset, like `UTF-8`) that was used to create the code. If you use the wrong key, you'll get gibberish.

#### 👉**Its Main Job:** To read the data from web forms and URL query strings after they arrive at the server, so the application can work with the original, clean data.

### 📌Example
⤵️This example shows how to decode a string that a web form might send.

```java name=UrlDecoderExample.java
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class UrlDecoderExample {
    public static void main(String[] args) throws Exception {

        // This is a typical string from a web form's query.
        // It represents: "search=Java & C++"
        String encodedData = "search=Java+%26+C%2B%2B";

        System.out.println("Encoded String: " + encodedData);

        // Use URLDecoder to translate it back.
        // We MUST use the same charset (UTF-8) that was used to encode it.
        String decodedData = URLDecoder.decode(encodedData, StandardCharsets.UTF_8);

        // The '+' becomes a space.
        // The '%26' becomes '&'.
        // The '%2B' becomes '+'.
        System.out.println("Decoded String: " + decodedData);
    }
}
```

### 💡 **Output:**

```
Encoded String: search=Java+%26+C%2B%2B
Decoded String: search=Java & C++
```
### What URL decoding means
- Purpose: Convert a URL-safe string back to its original text by reversing percent-encoding (and, for form data, turning + into spaces).
- Basic mechanism:
  - Sequences of %HH (hex) represent a single byte with value 0xHH.
  - Bytes are collected and decoded into characters using a charset (UTF-8 recommended).
  - For application/x-www-form-urlencoded (HTML form/query), + represents a space and must be converted.
- Component awareness: Decoding rules differ by URL component:
  - Query/form values: application/x-www-form-urlencoded rules (+ → space).
  - Path segments: RFC 3986 percent-decoding only; + is a literal plus and must NOT be turned into space.
  - Fragment: Client-side; typically percent-decoded, but + has no special meaning unless you treat it like form data.

Of course, Manish. Let's break down the rules of URL decoding in very simple terms.

**Current Date and Time (UTC):** 2025-11-01 12:02:18  
**Current User's Login:** Manish-Bishwakarma

---

### Translating a "Web-Safe" Message Back to Normal
➡️ URL decoding is like translating a secret code back into plain English. It's the reverse of encoding.

#### 1. What's the Purpose?

*   To take a "web-safe" string (like `hello+world%21`) and turn it back into its original, human-readable form (`hello world!`).

#### 2. How Does It Work? (The Basic Rules)

💭 Imagine you're unpacking a box that was packed for shipping:

*   **Rule A: Unwrapping the Special Codes (`%HH`)**
    *   The decoder sees a code like `%21` and knows it's a "wrapped" character. It unwraps it to get the original character (`!`).

*   **Rule B: Handling Plus Signs (`+`)**
    *   **If it's from a web form**, the decoder knows that every `+` is just a space. So `hello+world` becomes `hello world`.

*   **Rule C: Using the Right "Language" (Charset)**
    *   To translate codes for international characters (like `é` or `😊`), the decoder needs to use the same "language key" (the charset, like `UTF-8`) that was used to pack them.

#### 3. Different Rules for Different Parts of the URL
➡️ This is the most important part. A URL has different sections, and the rules for decoding a `+` are different for each one.

*   **Query/Form Part (after the `?`)**
    *   This is where you send data.
    *   **Rule:** A `+` **always** means a space.
    *   Example: `?search=java+programming` is decoded to `search=java programming`.

*   **Path Part (the main "address")**
    *   This is the folder/file part of the URL.
    *   **Rule:** A `+` is just a literal plus sign. It does **not** mean a space. Only `%20` means a space here.
    *   Example: `/files/report+version.txt` is decoded to `/files/report+version.txt`. The `+` stays.

*   **Fragment Part (after the `#`)**
    *   This is a "bookmark" to a specific spot on the page.
    *   **Rule:** A `+` is just a literal plus sign, not a space.

#### ➡️ **In short: Always know which part of the URL you are decoding, because a `+` means something different in the path versus the query.**

### \# Java’s URLDecoder in a nutshell
- Class: `java.net.URLDecoder`
- What it decodes: Strings encoded with application/x-www-form-urlencoded (HTML forms, typical query strings).
- Key behaviors:
  - Converts `+` to space (`’ ’`).
  - Converts `%HH` to a `byte`; aggregates bytes and decodes with the specified charset.
  - Throws IllegalArgumentException on malformed percent sequences (e.g., stray %, bad hex, incomplete %HH).
- Always specify the charset:
  - Preferred: `URLDecoder.decode(input, StandardCharsets.UTF_8)`
  - Legacy: `URLDecoder.decode(input, "UTF-8") (throws UnsupportedEncodingException)`
  - Avoid: `URLDecoder.decode(input)` without charset `(deprecated; used platform default)`.

### 📌 Parse a query string safely into a multimap:
```java name=QueryParsingExample.java
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class QueryParsingExample {
    public static Map<String, List<String>> parseQuery(String rawQuery) {
        Map<String, List<String>> out = new LinkedHashMap<>();
        if (rawQuery == null || rawQuery.isEmpty()) return out;

        for (String pair : rawQuery.split("&", -1)) { // -1 keeps empty fields
            int idx = pair.indexOf('=');
            String rawKey = idx >= 0 ? pair.substring(0, idx) : pair;
            String rawVal = idx >= 0 ? pair.substring(idx + 1) : "";
            String key = URLDecoder.decode(rawKey, StandardCharsets.UTF_8);
            String val = URLDecoder.decode(rawVal, StandardCharsets.UTF_8);
            out.computeIfAbsent(key, k -> new ArrayList<>()).add(val);
        }
        return out;
    }

    public static void main(String[] args) {
        String q = "q=java+url+decoder&emoji=%F0%9F%98%8A&tags=net%2Fhttp&empty=";
        System.out.println(parseQuery(q));
    }
}
```

### 📌Percent-decode for path segments (no + → space):
```java name=PercentDecodePath.java
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class PercentDecodePath {
    public static String decodePathComponent(String s) {
        return percentDecode(s, StandardCharsets.UTF_8, false);
    }

    public static String percentDecode(String s, Charset cs, boolean plusAsSpace) {
        byte[] buf = new byte[s.length()];
        int bi = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (plusAsSpace && c == '+') {
                buf[bi++] = (byte) ' ';
            } else if (c == '%') {
                if (i + 2 >= s.length())
                    throw new IllegalArgumentException("Incomplete percent sequence at index " + i);
                int hi = hex(s.charAt(++i));
                int lo = hex(s.charAt(++i));
                buf[bi++] = (byte) ((hi << 4) | lo);
            } else {
                // Pass ASCII directly; for non-ASCII this path treats char as bytes in UTF-8 on re-encode.
                if (c <= 0x7F) {
                    buf[bi++] = (byte) c;
                } else {
                    // Encode this char into bytes via charset to preserve content
                    byte[] b = String.valueOf(c).getBytes(cs);
                    for (byte bb : b) buf[bi++] = bb;
                }
            }
        }
        return cs.decode(ByteBuffer.wrap(buf, 0, bi)).toString();
    }

    private static int hex(char c) {
        if (c >= '0' && c <= '9') return c - '0';
        if (c >= 'A' && c <= 'F') return c - 'A' + 10;
        if (c >= 'a' && c <= 'f') return c - 'a' + 10;
        throw new IllegalArgumentException("Bad hex digit: " + c);
    }

    public static void main(String[] args) {
        System.out.println(decodePathComponent("My%20File%20%2B%20Notes")); // "My File + Notes"
        System.out.println(percentDecode("a+b", StandardCharsets.UTF_8, true));  // "a b" (form-style)
        System.out.println(percentDecode("a+b", StandardCharsets.UTF_8, false)); // "a+b" (path-style)
    }
}
```

### 📌End-to-end: extract and decode from a URI
```java name=DecodeFromURI.java
import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class DecodeFromURI {
    public static void main(String[] args) throws Exception {
        URI uri = new URI("https://example.com/search/C%23%20tips?q=java+url+decoder&tag=net%2Fhttp#section-1");
        // Path: decode each segment if needed
        for (String seg : uri.getRawPath().split("/")) {
            if (seg.isEmpty()) continue;
            String decodedSegment = PercentDecodePath.decodePathComponent(seg);
            System.out.println("Path segment: " + decodedSegment);
        }
        // Query: decode name/value pairs with URLDecoder
        String rawQuery = uri.getRawQuery();
        if (rawQuery != null) {
            for (String pair : rawQuery.split("&", -1)) {
                int i = pair.indexOf('=');
                String k = i >= 0 ? pair.substring(0, i) : pair;
                String v = i >= 0 ? pair.substring(i + 1) : "";
                System.out.println(URLDecoder.decode(k, StandardCharsets.UTF_8)
                                 + " = "
                                 + URLDecoder.decode(v, StandardCharsets.UTF_8));
            }
        }
        // Fragment usually read as-is or percent-decoded if needed:
        String rawFrag = uri.getRawFragment();
        System.out.println("Fragment (raw): " + rawFrag);
    }
}
```