# URL (Uniform Resource Identifier)

## 1. What is a URI? (And what is a "Resource"?)

### Explanation
*   **URI (Uniform Resource Identifier):** Think of a URI as a **unique ID card** for anything in the world. This ID card is just a string of text.
*   **Resource:** This is the **thing** that the ID card identifies. It can be a webpage, a PDF file, an email address (`mailto:someone@example.com`), a book's ISBN number (`urn:isbn:156592870`), or even an abstract concept.

### ⛓️‍💥 Let's Breakdown the statement
➡️ *A URI is the **name/address** of something. A Resource is the **thing** with that name/address.*
#### What is a "Representation"?
⤵️You never actually get the "resource" itself. Instead, you get a **representation** of it.

*   **Example from the text:** The Universal Declaration of Human Rights is a single *resource*.
*   When you visit its URI, you can get different *representations*:
    *   A plain text version.
    *   A PDF version.
    *   An English version.
    *   A French version.

#### 💭Think of it like this: **You** are a resource. A photo of you, your driver's license, and a video of you are all different **representations** of you.

## 2. The Basic Structure of a URI

### Explanation
⤵️ Every URI has two main parts, separated by a colon (`:`):

`scheme` : `scheme-specific-part`

*   **Scheme:** This tells you the **type** of ID it is, or the "language" to use. It sets the rules for the rest of the string.
    *   `http`: This is a web address.
    *   `mailto`: This is an email address.
    *   `file`: This is a file on your local computer.
    *   `urn`: This is a permanent, unique name for something (like a book's ISBN).

| Scheme  | What It Means                        | Example                                     |
|---------|--------------------------------------|---------------------------------------------|
| `http`  | A webpage (Hypertext Transfer Protocol) | `http://www.google.com`                     |
| `https` | A secure webpage                     | `https://github.com`                        |
| `mailto`| An email address                     | `mailto:hello@example.com`                  |
| `file`  | A file on your computer              | `file:///C:/Users/Manish/Documents/notes.txt` |
| `ftp`   | A file on an FTP server              | `ftp://ftp.example.com/files/archive.zip`   |
| `urn`   | A Unique Resource Name               | `urn:isbn:156592870`                        |

## 3. The Common Hierarchical Structure of URIs

### Explanation
⤵️ Many URIs, especially web addresses, follow a structure that looks like a mailing address.

`//Authority` / `Path` ? `Query`

#### 💭 Let's use an analogy for the URI `https://www.amazon.com/s?k=java+programming`:

| Part        | Analogy                                   | Example from URI                      | Explanation                                                              |
|-------------|-------------------------------------------|---------------------------------------|--------------------------------------------------------------------------|
| `Authority` | The main building or organization         | `www.amazon.com`                      | The server responsible for the resource. It can include a user, password, or port. |
| `Path`      | The specific department or office room    | `/s`                                  | The path to the resource on that server. It's like a folder path.        |
| `Query`     | The specific instructions you give inside | `k=java+programming`                  | A set of key-value pairs that ask for a specific version of the resource. |

*   **Not all URIs are hierarchical:** A URI like `urn:isbn:156592870` just has a scheme (`urn`) and a scheme-specific part (`isbn:156592870`). It doesn't have an authority or path because it's just a name, not a location.

## 4. Encoding Rules: Keeping URIs "Safe"

### Explanation
➡️ URIs can only contain a limited set of "safe" characters. If you need to include an "unsafe" character (like a space, a non-English letter, or a special symbol), you must **encode** it.

*   **Safe Characters (don't need encoding):** `A-Z`, `a-z`, `0-9`, and `- _ . ~`
*   **Reserved Characters (punctuation with special jobs):** `/ ? : @ & = + $ ,`
*   **Unsafe Characters (must be encoded):** Spaces, `á`, `木`, and any reserved character not being used for its special job.

### 🔹 **Encoding Rule:** An unsafe character is replaced by a percent sign (`%`) followed by its two-digit hexadecimal code (from its UTF-8 representation).

*   A space becomes `%20`.
*   The Chinese character `木` becomes `%E6%9C%A8`.
*   A `/` *inside a filename* becomes `%2F` (e.g., `Java%20I%2FO`).

**IRI vs. URI:**
*   **IRI (Internationalized Resource Identifier):** A modern address that allows non-ASCII characters directly (e.g., `https://example.com/café`). Easier for humans to read.
*   **URI:** The classic, safe version where all special characters are percent-encoded (e.g., `https://example.com/caf%C3%A9`). All software understands this.


## 5. 📌Example
➡️ In Java, the `java.net.URI` class is perfect for working with these concepts. It parses a URI string into its different components, and it correctly handles encoding.

⤵️ This example shows how to create a `URI` object and inspect all its parts.

```java name=UriComponentsExample.java
import java.net.URI;
import java.net.URISyntaxException;

public class UriComponentsExample {
    public static void main(String[] args) {
        // A complex URI with many parts
        String uriString = "https://john.doe:password@www.example.co.uk:8080/forum/questions/?tag=networking&order=newest#top";

        try {
            // Create a URI object. Java's URI class understands all the rules.
            URI uri = new URI(uriString);

            System.out.println("URI: " + uri.toString() + "\n");

            System.out.println("--- URI Components ---");
            System.out.println("Scheme:      " + uri.getScheme());
            System.out.println("Authority:   " + uri.getAuthority());
            System.out.println("User Info:   " + uri.getUserInfo());
            System.out.println("Host:        " + uri.getHost());
            System.out.println("Port:        " + uri.getPort());
            System.out.println("Path:        " + uri.getPath());
            System.out.println("Query:       " + uri.getQuery());
            System.out.println("Fragment:    " + uri.getFragment()); // The part after '#'

        } catch (URISyntaxException e) {
            System.err.println("Error parsing URI string: " + e.getMessage());
        }
    }
}
```

### 💡 Output of the Code:

```
URI: https://john.doe:password@www.example.co.uk:8080/forum/questions/?tag=networking&order=newest#top

--- URI Components ---
Scheme:      https
Authority:   john.doe:password@www.example.co.uk:8080
User Info:   john.doe:password
Host:        www.example.co.uk
Port:        8080
Path:        /forum/questions/
Query:       tag=networking&order=newest
Fragment:    top
```

## \# The Core Idea: `URI` is a Blueprint, `URL` is a Tool

### Explanation
➡️ The most important difference between `URI` and `URL` is their job:

*   **`java.net.URI` (The Blueprint):**
    *   Its only job is to **understand the structure of a web address**. It's like a blueprint for a house.
    *   It can parse, validate, and manipulate the *text* of an address.
    *   It **does not** connect to the internet or fetch any data. It's purely for working with the string itself.

*   **`java.net.URL` (The Tool):**
    *   Its job is to **connect to an address and get data**. It's like the construction crew that uses the blueprint to actually build the house.
    *   It needs to know *how* to communicate (e.g., via HTTP) and is designed for I/O (Input/Output).

### **Why this matters:** Use `URI` when you just need to build, check, or take apart an address. Only convert it to a `URL` at the very last moment when you're ready to make a network connection.

### 📌Example
⤵️This code shows that creating a `URI` does nothing but parse text, while creating a `URL` might try to resolve the host (a network-related action).

```java name=UriVsUrl.java
import java.net.URI;
import java.net.URL;

public class UriVsUrl {
    public static void main(String[] args) {
        String address = "https://example.com/path";

        // 1. Using URI: Just a blueprint. No network activity.
        try {
            URI uri = new URI(address);
            System.out.println("✅ URI created successfully. It's just a structured string.");
            System.out.println("   Scheme: " + uri.getScheme());
            System.out.println("   Host:   " + uri.getHost());
        } catch (Exception e) {
            System.err.println("Error with URI: " + e.getMessage());
        }

        System.out.println("\n----------------------------------\n");

        // 2. Using URL: A tool for connection.
        try {
            // A URL requires a handler for its protocol (like http).
            URL url = new URL(address);
            System.out.println("✅ URL created successfully. It's ready for I/O.");
            // You can now use it to open a connection.
            // url.openStream();
        } catch (Exception e) {
            System.err.println("Error with URL: " + e.getMessage());
        }
    }
}
```

## \3 URI Types: Absolute, Relative, Opaque, and Hierarchical

### Explanation
➡️ The `URI` class can handle four main types of addresses:

1.  **Absolute URI:**
    *   **What it is:** A complete address that starts with a `scheme`.
    *   **Example:** `https://www.google.com` (The scheme is `https`).

2.  **Relative URI:**
    *   **What it is:** An incomplete address with **no scheme**. It only makes sense when combined with an absolute URI.
    *   **Example:** `page2.html` or `/images/logo.png`.

3.  **Opaque URI:**
    *   **What it is:** An absolute URI that **cannot be broken down** into smaller parts like path or query. The part after the scheme does *not* start with a `/`.
    *   **Think of it as a "black box" ID.**
    *   **Example:** `mailto:someone@example.com`. You can't ask for the "path" of an email address. The whole thing after `mailto:` is the scheme-specific part. Another example is `urn:isbn:0451450523`.

4.  **Hierarchical URI:**
    *   **What it is:** The opposite of opaque. It's an address that **can be broken down** into authority, path, query, etc. Most web addresses are hierarchical.
    *   **Example:** `https://example.com/search?q=java`.

### 📌Example
⤵️ This code demonstrates how Java sees these different types.

```java name=UriTypes.java
import java.net.URI;

public class UriTypes {
    public static void main(String[] args) throws Exception {
        URI absolute = new URI("https://example.com/path");
        URI relative = new URI("/path/page.html");
        URI opaque = new URI("mailto:test@example.com");

        System.out.println("--- URI Types ---");
        System.out.println("Absolute URI:     " + absolute + " -> isAbsolute? " + absolute.isAbsolute());
        System.out.println("Relative URI:     " + relative + " -> isAbsolute? " + relative.isAbsolute());
        System.out.println("Opaque URI:       " + opaque + " -> isOpaque? " + opaque.isOpaque());
        System.out.println("Hierarchical URI: " + absolute + " -> isOpaque? " + absolute.isOpaque()); // Hierarchical is the opposite of Opaque

        // Resolving a relative URI against an absolute one
        URI resolved = absolute.resolve(relative);
        System.out.println("\nResolving '" + relative + "' against '" + absolute + "' -> " + resolved);
    }
}
```

### Fun Fact & Deviations: URI vs. URL vs. URN
*   **URI is the Super-Category:** It's the general term for any identifier string.
*   **URL (Locator):** A type of URI that tells you **WHERE** a resource is located. It's an address. Example: `https://example.com/index.html`.
*   **URN (Name):** A type of URI that tells you **WHAT** a resource is, permanently and uniquely, regardless of where it is. It's a name, not an address. Example: `urn:isbn:0451450523` (the name for a specific book).

#### ➡️ **All URLs and URNs are URIs, but not all URIs are URLs.** A `urn:` is a URI, but you can't connect to it, so it's not a URL.

#### ➡️ **Deviations from RFC:** The Java `URI` class is slightly more flexible than the official standard (RFC). It allows some empty parts and uses `UTF-8` by default for Unicode, which is a good, modern choice.

### Modern Industry Note (The 2025 Context)
➡️ In modern software development (as of 2025), the `URI` class is more important than ever.

*   **Microservices (e.g., Spring Boot):** Developers use `URI` to define and validate the addresses of their API endpoints. It helps ensure that all parts of a distributed system are communicating with correctly formatted addresses. `URI` templates are used to create flexible endpoint definitions.
*   **Cloud-Native Apps (e.g., Kubernetes):** Services in the cloud discover each other using URIs. The `URI` class is perfect for managing these internal service addresses reliably.
*   **Modern HTTP Clients:** The new `java.net.http.HttpClient` (introduced in Java 11) works directly with `URI` objects. This is the recommended way to make safe, modern, asynchronous web requests.

### 📌Example (Modern `HttpClient`)
⤵️This code shows the modern way to make a web request using `HttpClient` and `URI`.

```java name=ModernHttpClientExample.java
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ModernHttpClientExample {
    public static void main(String[] args) {
        // Create a modern, reusable HTTP client
        HttpClient client = HttpClient.newHttpClient();

        try {
            // 1. Create a URI object. This is the safe, required way.
            URI uri = new URI("https://httpbin.org/get");

            // 2. Build an HTTP request using the URI
            HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();

            System.out.println("Sending request to: " + uri);

            // 3. Send the request and get the response
            // The client handles the connection, not the URI.
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("Status Code: " + response.statusCode());
            System.out.println("Response Body (first 150 chars):");
            System.out.println(response.body().substring(0, 150) + "...");

        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}
```


# \# Creation of URI Instances
➡️ All constructors parse/validate per RFC, quoting illegal chars (UTF-8 → % escapes). Throw `URISyntaxException` for syntax errors, `NullPointerException` for nulls.

- **`URI(String str)`**: Parses full string. Flexible—allows empty parts, Unicode.
- **`URI(String scheme, String ssp, String fragment)`**: For opaque/hierarchical via scheme-specific part.
- **`URI(String scheme, String authority, String path, String query, String fragment)`**: Hierarchical with pre-built authority.
- **`URI(String scheme, String userInfo, String host, int port, String path, String query, String fragment)`**: Full hierarchical breakdown. Parses authority on creation.
- **`URI(String scheme, String host, String path, String fragment)`**: Simplified—no userInfo/port/query.

- **Static Factory: `static URI create(String str)`**: Like the string constructor but throws `IllegalArgumentException` (for trusted inputs).

**Aspect: Parsing Rules:** Allows deviations like empty relative paths or authorities. For server-based authority, call `parseServerAuthority()` post-creation.

## 📌 Example (Creation Variants):**
```java
import java.net.*;

public class URICreationDemo {
    public static void main(String[] args) {
        try {
            // Full string
            URI full = new URI("https://example.com/path?query#frag");
            System.out.println(full);  // https://example.com/path?query#frag

            // Components
            URI comp = new URI("https", "user@host:443", "/path", "query", "frag");
            System.out.println(comp);  // https://user@host:443/path?query#frag

            // Opaque
            URI opaque = new URI("mailto", "user@example.com", null);
            System.out.println(opaque);  // mailto:user@example.com

            // Static create
            URI created = URI.create("file:///tmp/file.txt");
            System.out.println(created);  // file:///tmp/file.txt
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
```

## URI Components
➡️ Every `URI` breaks into up to 9 components. Undefined ones are `null` (strings) or -1 (port); empty strings (`""`) are distinct and valid.

- **Scheme:** Protocol (e.g., "http", "file"). Alphanumeric + ".-+". Case-insensitive in comparisons. Getter: `getScheme()`.
- **Scheme-Specific Part (SSP):** Everything after scheme, before fragment. Always non-null (empty if absent). Raw/decoded getters: `getRawSchemeSpecificPart()` / `getSchemeSpecificPart()`.
- **Authority:** Server info (e.g., "user:pass@host:port"). Can be server-based (parsable) or registry-based (opaque string). Raw/decoded: `getRawAuthority()` / `getAuthority()`.
- **User Info:** Authentication (e.g., "user:pass"). Part of authority. Raw/decoded: `getRawUserInfo()` / `getUserInfo()`.
- **Host:** Domain/IP (IPv4 dotted, IPv6 bracketed like "[2001:db8::1]"). No encoding needed. Getter: `getHost()`.
- **Port:** Integer (-1 if undefined). Getter: `getPort()`.
- **Path:** Resource location (e.g., "/dir/file"). Empty or absolute/relative. Raw/decoded: `getRawPath()` / `getPath()`.
- **Query:** Parameters (e.g., "key=val&other=2"). Raw/decoded: `getRawQuery()` / `getQuery()`.
- **Fragment:** Anchor (e.g., "section"). Client-side. Raw/decoded: `getRawFragment()` / `getFragment()`.

**Aspect: Raw vs. Decoded:** Raw keeps % escapes (e.g., "%20" for space); decoded uses UTF-8 to convert (e.g., to actual space). Use raw for storage/transmission, decoded for display/processing.

## 📌 Example (Accessing Components):**
```java
import java.net.*;

public class URIComponentsDemo {
    public static void main(String[] args) {
        try {
            URI uri = new URI("http://user:pass@example.com:8080/path?query=val#frag");
            System.out.println("Scheme: " + uri.getScheme());  // http
            System.out.println("Authority: " + uri.getAuthority());  // user:pass@example.com:8080
            System.out.println("Host: " + uri.getHost());  // example.com
            System.out.println("Port: " + uri.getPort());  // 8080
            System.out.println("Path: " + uri.getPath());  // /path
            System.out.println("Query: " + uri.getQuery());  // query=val
            System.out.println("Fragment: " + uri.getFragment());  // frag
            System.out.println("Raw Path: " + uri.getRawPath());  // /path (if no escapes)
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
```
### 🗝️ Key Instance Methods
These handle manipulation, conversion, and checks. Most return new URIs (immutability!).

- **`URI parseServerAuthority()`**: Validates/parses authority as server-based (throws `URISyntaxException` if not). Returns this URI if successful.
- **`URI normalize()`**: Removes "."/".." from paths (RFC algorithm). No-op for opaque. Returns normalized copy.
- **`URI resolve(URI uri)`**: Resolves relative `uri` against this base. Handles fragments specially.
- **`URI resolve(String str)`**: Convenience—parses `str` then resolves.
- **`URI relativize(URI uri)`**: Inverse: Makes `uri` relative to this (if same scheme/authority).
- **`URL toURL()`**: Converts to `URL` (absolute URIs only; throws exceptions if invalid).
- **`String toString()`**: Canonical string (with escapes).
- **`String toASCIIString()`**: ASCII-only (encodes non-ASCII).

## 📌 Example (Manipulation):**
```java
import java.net.*;

public class URIManipDemo {
    public static void main(String[] args) throws URISyntaxException {
        URI base = new URI("http://example.com/a/b/./c/../d/");
        URI norm = base.normalize();  // http://example.com/a/b/d/

        URI rel = new URI("../e?f#g");
        URI resolved = base.resolve(rel);  // http://example.com/a/e?f#g

        URI abs = new URI("http://example.com/a/e?f#g");
        URI relativized = base.relativize(abs);  // ../e?f#g

        System.out.println(norm);
        System.out.println(resolved);
        System.out.println(relativized);
    }
}
```

## 1. The Three Main Parts of a URI

### Explanation
➡️ At the highest level, Java's `URI` class sees an address as having up to three main pieces, like a sentence with a beginning, a middle, and an end.

`scheme` : `scheme-specific-part` # `fragment`

1.  **`scheme`:** The "type" of the address (e.g., `http`, `mailto`, `file`). If this is missing, the URI is **relative**.
2.  **`scheme-specific-part`:** Everything between the scheme's colon (`:`) and the fragment's hash (`#`). This is the main content of the URI.
3.  **`fragment`:** An optional "bookmark" at the end, starting with a hash (`#`). It points to a specific section within the resource.

#### 👉 The `URI` class gives you methods to get these three main parts.

## 2. Getting the Parts: `getFoo()` vs. `getRawFoo()`

### Explanation
➡️ When you get a part of a URI, you have two choices: get the "clean" version or the "raw" version.

*   **`getFoo()` (Clean/Decoded):**
    *   This method **decodes** any percent-encoded characters.
    *   It turns codes like `%20` back into a space.
    *   Use this when you want the human-readable value.

*   **`getRawFoo()` (Raw/Encoded):**
    *   This method gives you the part **exactly as it appears** in the URI string, with all the `%` codes intact.
    *   Use this when you need the original, encoded string for transport or logging.

**Why no `getRawScheme()`?** The `scheme` part is not allowed to have any special characters, so a "raw" version is never needed.

### 📌Example
⤵️ This code shows the difference between getting the raw and decoded query.

```java name=GetVsGetRaw.java
import java.net.URI;

public class GetVsGetRaw {
    public static void main(String[] args) throws Exception {
        // This URI has an encoded space (%20) in its query part.
        URI uri = new URI("https://example.com/search?q=Java%20Programming");

        System.out.println("URI: " + uri);
        System.out.println("------------------------------------");

        // getQuery() -> Decodes the %20 into a space.
        String decodedQuery = uri.getQuery();
        System.out.println("Decoded Query (getQuery):      " + decodedQuery);

        // getRawQuery() -> Leaves the %20 as is.
        String rawQuery = uri.getRawQuery();
        System.out.println("Raw Query (getRawQuery):         " + rawQuery);
    }
}
```

## 3. Hierarchical vs. Opaque URIs: Deeper Parsing

### Explanation
➡️ The `scheme-specific-part` can be either a structured address or just a "blob" of text.

*   **Hierarchical URI (`isOpaque()` is `false`):**
    *   This is a structured address that can be broken down further into authority, path, and query.
    *   Most web addresses (`http`, `ftp`, `file`) are hierarchical.
    *   You can use methods like `getHost()`, `getPath()`, and `getQuery()` on them.

*   **Opaque URI (`isOpaque()` is `true`):**
    *   This is an address that **cannot** be broken down further. It's a "black box."
    *   Examples include `mailto:someone@example.com` and `urn:isbn:12345`. You can't ask for the "path" of an email address.
    *   For opaque URIs, you can only get the scheme, the full scheme-specific part, and the fragment.

#### ➡️ The `URISplitter` example from your text is a perfect demonstration of this, checking `isOpaque()` first before trying to get detailed parts.

## 4. Resolving Relative URIs: `resolve()` and `relativize()`
➡️ This is like giving directions. You can combine a base location with a relative path to get a full address, or figure out the relative path between two full addresses.

*   **`resolve(relative)` (Combine):**
    *   Takes a base URI (like `https://example.com/docs/`) and a relative URI (like `images/pic.png`).
    *   It **combines** them to produce a full, absolute URI (`https://example.com/docs/images/pic.png`).

*   **`relativize(absolute)` (Find the Difference):**
    *   Takes two absolute URIs that share a common base.
    *   It calculates the **relative path** from one to the other.
    *   Example: Relativizing `.../docs/images/pic.png` against `.../docs/` gives you `images/pic.png`.

### 📌Example

```java name=ResolveAndRelativize.java
import java.net.URI;

public class ResolveAndRelativize {
    public static void main(String[] args) throws Exception {
        URI baseUri = new URI("https://www.example.com/app/v1/");
        URI fullUri = new URI("https://www.example.com/app/v1/data/report.json");

        // 1. Relativize: Find the path from the base to the full URI.
        URI relativePath = baseUri.relativize(fullUri);
        System.out.println("Relative path from base to full: " + relativePath);

        // 2. Resolve: Combine the base with the relative path to get the full URI back.
        URI resolvedUri = baseUri.resolve(relativePath);
        System.out.println("Resolved URI from base + relative: " + resolvedUri);
    }
}
```

### 5. Equality and Comparison: What Makes Two URIs Equal?

#### Simple Explanation

Comparing two URIs is smarter than just comparing their strings. The `equals()` method follows specific rules:

*   **Case-Insensitive Parts:** The `scheme` and `host` are compared without caring about case.
    *   `http://EXAMPLE.COM` is equal to `http://example.com`.
*   **Case-Sensitive Parts:** The `path` and `query` are case-sensitive.
    *   `.../MyFile.html` is **not** equal to `.../myfile.html`.
*   **Encoding Matters:** An encoded character is not considered equal to its decoded version.
    *   `.../A` is **not** equal to `.../%41`.

#### 👉 The `compareTo()` method allows you to sort a list of URIs in a predictable order, comparing each part one by one (scheme, then authority, then path, etc.).

## 6. String Representations: `toString()` vs. `toASCIIString()`
➡️ When you want to print a URI as a string, you have two options with very different purposes.

*   **`toString()` (For Humans):**
    *   This returns a string that might contain un-encoded, non-ASCII characters (like `é` or `ü`).
    *   The result is an **IRI** (Internationalized Resource Identifier).
    *   It's easier for people to read, but it might **not be a valid URI** for use in programs.

*   **`toASCIIString()` (For Machines):**
    *   This returns a string that is **always a valid, ASCII-only URI**.
    *   It ensures all special characters are percent-encoded.
    *   **This is the method you should almost always use** when you need a string representation of the URI to send over a network or pass to another program.

### 📌Example

```java name=ToStringVsToAscii.java
import java.net.URI;

public class ToStringVsToAscii {
    public static void main(String[] args) throws Exception {
        // A URI containing a non-ASCII character in the path.
        URI internationalUri = new URI("https", "example.com", "/résumé", null);

        // toString() keeps the 'é' character. This is an IRI.
        String forDisplay = internationalUri.toString();
        System.out.println("toString() (for display):      " + forDisplay);

        // toASCIIString() encodes the 'é' to '%C3%A9'. This is a valid URI.
        String forTransport = internationalUri.toASCIIString();
        System.out.println("toASCIIString() (for transport): " + forTransport);
    }
}
```