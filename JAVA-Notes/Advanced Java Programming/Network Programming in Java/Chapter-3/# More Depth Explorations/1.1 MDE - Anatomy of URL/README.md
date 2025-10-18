# Anatomy of URL (component and sementics)

### `protocol://userInfo@host:port/path?query#fragment`

### **Core Structure and Syntax:** 
➡️ A URL follows RFC 2396 (amended by RFC 2732 for IPv6), with this general form:  
  `scheme://authority/path?query#fragment`  
  - **Scheme (Protocol):** Required, like "http", "https", "file", "ftp", "jar". Determines how the resource is accessed.  
  - **Authority:** Optional; includes userinfo@host:port (e.g., "user:pass@example.com:8080"). Host can be a hostname, IPv4, or IPv6 (bracketed like [2001:db8::1]). Port defaults to scheme-specific (e.g., 80 for http) or -1 if omitted.  
  - **Path:** Resource location on the host (e.g., "/docs/index.html"). Can be absolute (starts with "/") or relative.  
  - **Query:** Optional parameters after "?" (e.g., "search=java&lang=en").  
  - **Fragment (Ref):** Optional anchor after "#" (e.g., "#section2")—points to a subsection, like a page jump in HTML. Not sent to the server; client-side only.  

***

### \# A full HTTP URL follows this structure:

`scheme://[userinfo@]host[:port][/path][?query][#fragment]`

L📌 et's break this down with an example: `https://john.doe:password@www.example.com:8080/products/search?id=123&lang=en#reviews`

*   **Scheme (or Protocol):** `https://`
    *   The "how." It specifies the protocol to be used for accessing the resource. Common examples are `http`, `https`, `ftp`, `file`, `jar`.

*   **User Info:** `john.doe:password@`
    *   (Optional and rare) Contains a username and optional password for authentication. **This is highly discouraged** as it puts credentials in plain text.

*   **Host (or Authority):** `www.example.com`
    *   The "where." This is the domain name or IP address of the server hosting the resource. It's the destination machine on the network.

*   **Port:** `:8080`
    *   (Optional) The specific "door" or network port on the server to connect to. If omitted, it defaults to the standard port for the scheme (e.g., 80 for `http`, 443 for `https`).

*   **Path:** `/products/search`
    *   The "what." It specifies the exact location of the resource on the server, like a file path on a local computer.

*   **Query:** `?id=123&lang=en`
    *   (Optional) A set of key-value pairs that sends extra parameters to the server. It starts with a `?` and pairs are separated by `&`. Often used for filtering, searching, or passing data.

*   **Fragment (or Ref):** `#reviews`
    *   (Optional) An identifier that points to a specific section *within* the resource. It starts with a `#`. The fragment is handled entirely by the client (e.g., the web browser) and is **never sent to the server**. It's used to jump to a specific part of an HTML page.

## Dissecting the Syntax: `protocol://userInfo@host:port/path?query#fragment`
➡️ This is the formal structure. Let's go through each piece as the text explains it. Let's use their example as our guide: `http://www.ibiblio.org/javafaq/javatutorial.html`


#### a. `protocol` (or `scheme`)
> "Here the protocol is another word for what was called the scheme... (Scheme is the word used in the URI RFC. Protocol is the word used in the Java documentation.)"

*   **What it is:** The communication language. It defines the rules for how the client and server will exchange data.
*   **Examples:**
    *   `http`: The standard language of the web.
    *   `https`: A secure, encrypted version of HTTP.
    *   `ftp`: File Transfer Protocol, for uploading/downloading files.
    *   `file`: To access a file on your own local computer's filesystem.
*   **Analogy:** This is like choosing how you'll communicate. Are you going to send a formal letter (`http`), a secure registered letter (`https`), or a parcel (`ftp`)?

#### b. The `authority` (userInfo, host, port)
> "Together, the userInfo, host, and port constitute the authority."

The authority section answers the question: "Who has the resource?"

*   **`host`:** The most important part. It's the name (`www.ibiblio.org`) or IP address (`185.199.108.153`) of the server computer.
    *   **Analogy:** This is the street address of the building you want to contact.

*   **`port`:** An optional number specifying which "door" on the server to knock on. Each service on a server listens on a different port.
    *   If omitted, it uses the default for the protocol (e.g., `http` -> port 80, `https` -> port 443).
    *   **Analogy:** If the host is the building's address, the port is the specific apartment number or office suite. For common services like the web, everyone knows to go to the main lobby (port 80/443), so you don't need to specify it.

*   **`userInfo`:** Optional login credentials. This is almost never used in modern web applications because it's insecure to put a password directly in a URL.
    *   **Analogy:** This is like writing "Attention: John Doe, Password: 1234" on the outside of the envelope. It's not a secure practice.

#### c. `path`
> "The path points to a particular resource on the specified server. It often looks like a filesystem path... However, it may or may not actually map to a filesystem on the server."

*   **What it is:** It specifies the exact resource you want from that server.
*   **The "Document Root" Concept:** This is a critical security and organization feature. The text explains it perfectly:
    *   A web server doesn't show its entire hard drive to the world (e.g., `C:\Windows` or `/etc/passwd`).
    *   Instead, the administrator configures a specific folder to be the "public" folder, known as the **document root**.
    *   If the document root is `/var/public/html`, and the URL path is `/javafaq/javatutorial.html`, the server will look for the file at `/var/public/html/javafaq/javatutorial.html`.
    *   To the outside world, `/` in the URL means the start of the public web content, not the start of the server's entire filesystem.
*   **Analogy:** The document root is like the public lobby of a large company. The `path` is the instruction to "go to the 'javafaq' department and ask for the 'javatutorial.html' file." You can't ask for files from the CEO's private office (which is outside the public lobby).

#### d. `query`
> "The query string provides additional arguments for the server. It’s commonly used only in http URLs, where it contains form data..."

*   **What it is:** A way to pass extra information to the resource you are requesting. It's a set of key-value pairs.
*   **Example:** In `https://www.google.com/search?q=java+url+class`, the query is `q=java+url+class`. You are telling the `/search` resource that the term you want to search for (`q`) is "java url class".
*   **Analogy:** If the path is the form you want to fill out (e.g., a search form), the query string is the information you've written into the form's fields.

#### e. `fragment`
> "Finally, the fragment references a particular part of the remote resource... If the remote resource is HTML, the fragment identifier names an anchor..."

*   **What it is:** A client-side bookmark.
*   **Crucial Detail:** The fragment part (everything after the `#`) is **never sent to the server**.
*   When your browser receives the full HTML page, it then looks at the fragment and automatically scrolls down to the element with that ID (e.g., `<h2 id="fragment">...`).
*   **Analogy:** The server sends you a whole book. The fragment is a sticky note you've placed on the envelope that says "start reading at page 50." The delivery person (server) doesn't care about the sticky note; they just deliver the book. You, the reader (client), see the note and open the book to the correct page.
***