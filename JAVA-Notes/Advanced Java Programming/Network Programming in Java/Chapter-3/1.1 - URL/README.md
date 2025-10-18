# URL (Uniform Resource Locator)

## 📚 Overview of URL 
* A URL is a specific type of Uniform Resource Identifier (URI) that provides a way to locate a resource on the internet by describing its primary access mechanism (e.g., its network location).

Of course, Manish. That's a fantastic paragraph that uses a very effective analogy to capture the essence of the URL vs. URI distinction. Let's break it down and explore each part in more detail.

The statement is making a crucial 

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


---

## 💡 The Core Idea: A Set of Instructions

➡️ "The network location in a URL usually includes the protocol..., the hostname..., and the path... This specifies that there is a file called ***javatutorial.html*** in a directory called javafaq on the server ***www.ibiblio.org***, and that this file can be accessed via the HTTP protocol."

➡️ This is the high-level summary. Think of a URL not as a single name, but as a sequence of instructions for a client:

1.  **How to communicate?** Use the `HTTP` protocol.
2.  **Who to talk to?** Find the computer on the internet named `www.ibiblio.org`.
3.  **What to ask for?** Once connected, request the resource located at the path `/javafaq/javatutorial.html`.


## \# The Anatomy of a URL (The Concept)
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
