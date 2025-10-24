# \# Resolution rules and path canonicalization (dot-segments, slashes, base path quirks)
Core rule: when spec is relative, the constructor merges the spec with the base path and then removes dot-segments ("." and "..") per RFC-style normalization so that sequences like "/a/b/../c" become "/a/c". Edge detail: if the base path does not end with a slash, the last segment is treated as a file and is replaced during merge, so "base.../dir" + "file" resolves differently than "base.../dir/" + "file". Network implications: resolution is purely lexical and deterministic—no DNS or I/O—but produced URLs may later trigger DNS or network I/O when used for connections.

## ⛓️‍💥 Let’s break it down above statement

### 🔍 Core Concept: Relative URL Resolution

➡️ When you create a relative URL using:

```java
new URL(base, spec)
```

➡️ Java follows **standard resolution rules** (like a browser does):
1. **Merge the relative spec with the base URL’s path**
2. **Normalize the path** by removing dot-segments:
   - `"."` means “current directory” → ignored
   - `".."` means “go up one level” → removes the previous segment

### 📌 Example of Dot-Segment Normalization

#### 📥Input:
```
Base path:   /a/b/
Relative:    ../c
```

#### 🔖Result:
```
Merged path: /a/b/../c
Normalized:  /a/c
```

### 👉 ✅ This is called **path canonicalization** — making the path clean and direct.


### ⚠️ Edge Detail: Slash Behavior

#### Rule:
- If the **base path ends with a slash**, it’s treated as a **directory**
- If it **doesn’t end with a slash**, the last segment is treated as a **file** and gets **replaced**

### 🔍 Comparison:

| Base URL | Relative Spec | Result |
|----------|----------------|--------|
| `https://example.com/dir` | `"file.html"` | `https://example.com/file.html` |
| `https://example.com/dir/` | `"file.html"` | `https://example.com/dir/file.html` |

#### 👉 ✅That trailing slash **matters** — it changes how the path is merged.


### 📌Example

```java
import java.net.URL;

public class PathResolutionDemo {
    public static void main(String[] args) throws Exception {
        // Base without trailing slash → treated as file
        URL base1 = new URL("https://example.com/dir");
        URL resolved1 = new URL(base1, "file.html");

        // Base with trailing slash → treated as directory
        URL base2 = new URL("https://example.com/dir/");
        URL resolved2 = new URL(base2, "file.html");

        // Dot-segment resolution
        URL base3 = new URL("https://example.com/a/b/");
        URL resolved3 = new URL(base3, "../c");

        // Display results
        System.out.println("Base 1:     " + base1);
        System.out.println("Resolved 1: " + resolved1); // → https://example.com/file.html

        System.out.println("Base 2:     " + base2);
        System.out.println("Resolved 2: " + resolved2); // → https://example.com/dir/file.html

        System.out.println("Base 3:     " + base3);
        System.out.println("Resolved 3: " + resolved3); // → https://example.com/a/c
    }
}
```

### 🌐 Network Implications

- This resolution is **purely lexical** — it’s just string manipulation.
- ✅ No DNS lookup, no HTTP request, no network activity.
- ⚠️ But if you later call `openConnection()` or `openStream()`, **then** network I/O begins.

### ✅ Summary

| Feature | Behavior |
|--------|----------|
| Dot-segments (`.` / `..`) | Removed during normalization |
| Trailing slash | Determines if base is a file or directory |
| Resolution | Lexical only — no network |
| Network I/O | Happens only when connecting, not during construction |


## Big idea in plain terms
- You have a base address (the page you’re on) and a link that might be “short” (relative).
- To turn that short link into a full address, Java (per RFC 3986) combines it with the base in a purely textual way, then cleans the path by removing “.” and “..” segments.
- Whether the base ends with a slash matters: ending with “/” means “this is a directory”; without “/” means “this is a file”. That changes how merging works.
- Resolution doesn’t touch the network. Only later, when you open a connection, could DNS/network I/O happen.

### The merge-and-clean algorithm (hierarchical URIs)
➡️ When spec is relative (no scheme like http:, no authority like //host):
1. Pick starting path:
   - If spec path starts with “/”: replace the base path entirely with the spec path (root‑relative).
   - Else if base path ends with “/”: append spec path to the base path (same directory).
   - Else (base path doesn’t end with “/”): drop the last segment (treated as a file) from base path, then append spec path.

2. Remove dot segments (canonicalization):
   - “.” means “stay here” → remove it.
   - “..” means “go up one directory” → remove the “..” and the preceding segment.
   - Example: /a/b/../c → /a/c; /a/./b → /a/b; /a/b/../../ → /
   - You cannot go “above” root: /../x becomes /x.

3. Merge/override other parts:
   - If the relative spec has a query, it replaces the base’s query.
   - If it has a fragment (#frag), it sets/overrides the fragment.
   - If the spec is just “?q=1” (query-only), keep the base path and replace only the query.
   - If the spec is just “#id” (fragment-only), keep base path and query, replace only the fragment.
   - If the spec is empty “”, you keep everything (including query) from the base; only the fragment might be cleared depending on form.

4. Special relative forms:
   - Absolute-path (starts with “/”): relative to site root (ignore base path).
   - Scheme-relative (starts with “//host/path”): reuse base scheme, replace authority/path with given ones.
   - Fully absolute (has scheme): ignore the base entirely (it’s already absolute).

### Why the trailing slash matters (directory vs file)
- **Base without slash (treated as a file)**:
  - Base: https://example.com/dir/page.html + "img.png" → https://example.com/dir/img.png
- **Base with slash (treated as a directory)**:
  - Base: https://example.com/dir/ + "img.png" → https://example.com/dir/img.png
- **Different result if the “last segment” is replaced**:
  - Base: https://example.com/dir/page + "file" → https://example.com/dir/file
  - Base: https://example.com/dir/ + "file" → https://example.com/dir/file
➡️ They look the same in these examples, but the important distinction is: when the base doesn’t end with “/”, the last segment is dropped before appending. If the base does end with “/”, nothing is dropped—your relative is appended under that directory.

### Root-relative vs directory-relative
- **Directory-relative**:
  - Base: https://example.com/a/b/c.html
  - Link: "d/e.html"
  - Result: https://example.com/a/d/e.html
- **Root-relative**:
  - Base: https://example.com/a/b/c.html
  - Link: "/d/e.html"
  - Result: https://example.com/d/e.html  (starts from the site root)

### Dot-segment cleanup (normalization) examples
- a/./b → a/b
- a/b/../c → a/c
- /a/b/../../d → /d
- ../x against site root → /x (can’t go above /)

### 📝Notes:
- Normalization removes only “.” and “..” segments. It does not generally collapse multiple slashes “//” inside the path (that’s scheme-specific and usually left as-is).
- Trailing slash significance is preserved. For example, resolving “./” under a directory typically retains the trailing slash.

### Query and fragment behavior
- Base: https://example.com/a/b?x=1#top
  - Spec: "" → https://example.com/a/b?x=1#top (unchanged)
  - Spec: "?y=2" → https://example.com/a/b?y=2 (query replaced, fragment cleared unless present)
  - Spec: "#sec2" → https://example.com/a/b?x=1#sec2 (fragment replaced only)
  - Spec: "c#s" → https://example.com/a/c#s (path changed, new fragment; base query cleared unless spec sets one)
  - Spec: "/c?z=3#s" → https://example.com/c?z=3#s (root-relative path, new query+fragment)

### Purely lexical, no network I/O
- Resolution is just string processing following RFC 3986 rules—no DNS lookups, no server calls.
- Later, if you use the resulting URL to open a connection (openStream/openConnection), that’s when DNS/network I/O can occur.

### How to do this safely in Java (modern approach)
- Prefer URI for parsing, normalization, and resolution; convert to URL only when you need to connect.

### 📌Example:
```java
import java.net.URI;
import java.net.URL;

public class ResolveDemo {
    public static void main(String[] args) throws Exception {
        URI base = new URI("https://example.com/a/b/c.html?x=1");
        // Directory-relative
        URI r1 = base.resolve("d/e.html").normalize(); // https://example.com/a/d/e.html
        // Root-relative
        URI r2 = base.resolve("/d/e.html").normalize(); // https://example.com/d/e.html
        // Dot-segments
        URI r3 = base.resolve("../img/../css/./main.css").normalize(); // https://example.com/a/css/main.css
        // Query-only
        URI r4 = base.resolve("?y=2"); // https://example.com/a/b/c.html?y=2
        // Fragment-only
        URI r5 = base.resolve("#section1"); // https://example.com/a/b/c.html?x=1#section1

        // Convert to URL only when you need to connect:
        URL url = r3.toURL();
        System.out.println(r1);
        System.out.println(r2);
        System.out.println(r3);
        System.out.println(r4);
        System.out.println(r5);
    }
}
```

### 📝A few edge/nuance notes
- Opaque URIs (like mailto:) don’t participate in these hierarchical resolution rules.
- If the base has no path, it’s treated as “/” when needed.
- You can’t normalize away more “..” than there are segments before root—excess “..” just clamp at “/”.
- URI.normalize() removes dot segments only; it doesn’t percent‑decode or validate resource existence.
- For file: URIs and Windows drive letters, additional scheme-specific rules apply; stick to HTTP(S) for predictable web behavior.

### 💡If you remember only three things
1) Relative paths merge with the base path; then “.” and “..” are removed.
2) A trailing slash on the base means “directory”; without it means “file”—which affects how the last segment is handled.
3) It’s all deterministic string math—no network involved—until you actually try to connect.
***


# \# Encoding, IPv6, authority, and handlers — interplay with constructors
Encoding: constructors accept literal characters; however, reserved or unsafe characters (spaces, non-ASCII) should be percent-encoded for correctness—prefer creating a URI from components and converting to URL to get strict encoding and normalization behavior. IPv6: textual absolute URLs include IPv6 addresses in brackets when a port is present, but the stored host value excludes brackets; relative resolution does not change bracket semantics in host literals. Authority and userInfo: absolute constructors may include userinfo@host:port in the authority; relative constructors inherit authority from the base only when the relative spec omits an authority. Custom handlers: constructors that accept a URLStreamHandler let you override parsing/openConnection semantics for that URL instance without registering a global factory.

## ⛓️‍💥 Let’s break it down above statement

### 📚 Overview
- Old direct URL constructors are deprecated (JDK 20+). Prefer: build a URI (strict parsing/normalization), then call toURL() when you actually need a URL.
- The topics below (encoding, IPv6, authority/userInfo, handlers) all affect how strings are parsed and resolved into a concrete URL you can connect to.

### 1. Encoding (spaces, reserved, non‑ASCII)
- Why it matters: A URL is ASCII-only. Characters like space, “#”, “?”, “é”, “你” must be percent‑encoded to be valid in the right component.
- Unsafe but sometimes “accepted”: new URL("http://ex.com/a file?q=a b") may appear to work inconsistently across versions/handlers.
- Safe pattern: construct a URI from components (it encodes pieces correctly), then convert to URL.

#### 📌Examples:
```java
// BAD/fragile: literal spaces and non-ASCII in a single string
// new URL("https://example.com/a file/ça?q=x y"); // Deprecated pattern, may misbehave

// GOOD: use URI components to ensure strict encoding
URI uri = new URI(
    "https",                 // scheme
    "user:pa ss",            // userInfo (will be encoded)
    "example.com",           // host
    443,                     // port
    "/a file/ç",             // path (spaces/non-ASCII properly percent-encoded)
    "q=x y&tag=∑",           // query (encoded per component context)
    "frag ment"              // fragment
);
URL url = uri.toURL();       // Use URL only when you need to connect
```

#### 📝Notes:
- URI(String scheme, String userInfo, String host, int port, String path, String query, String fragment) encodes each component safely.
- URLEncoder is for form bodies/query parameter values using application/x-www-form-urlencoded (it turns space into “+”), which is different from generic URI percent-encoding. Only use URLEncoder for HTML form encoding, not for arbitrary URI parts.

### 2. IPv6 in URLs (brackets and getters)
- Text form in URLs: IPv6 literals go in square brackets to disambiguate colons.
  - Example: https://[2001:db8::1]:8443/path
  - Brackets are part of the textual authority syntax, not part of the host itself.
- What Java returns:
  - getHost() → the raw address without brackets (e.g., "2001:db8::1")
  - getAuthority() → includes brackets and port (e.g., "[2001:db8::1]:8443")
- Relative resolution: purely textual; the brackets stay with the authority and are unaffected when you resolve relative paths against that base.

#### 📌Examples:
```java
URL u = new URI("https://[2001:db8::1]:8443/a/b").toURL();
System.out.println(u.getHost());      // 2001:db8::1
System.out.println(u.getAuthority()); // [2001:db8::1]:8443
System.out.println(u.getPath());      // /a/b
```

### 3. Authority and userInfo (what inherits, what replaces)
- Authority = userInfo@host:port
- In a relative reference:
  - If it starts with “//…”, it supplies a new authority (host/port/userinfo) and replaces the base authority.
  - If it does not include an authority, the base authority is inherited.
  - Query and fragment may be replaced independently by the relative spec.

Examples (base = https://user:pw@hostA:8443/dir/page.html?x=1):
```java
URI base = new URI("https://user:pw@hostA:8443/dir/page.html?x=1");

// Inherit authority; change path
System.out.println(base.resolve("img/logo.png"));
// https://user:pw@hostA:8443/dir/img/logo.png

// Replace authority (scheme-relative)
System.out.println(base.resolve("//hostB:8080/new/path"));
// https://hostB:8080/new/path

// Query-only update (keep path and authority)
System.out.println(base.resolve("?q=java"));
// https://user:pw@hostA:8443/dir/page.html?q=java

// Fragment-only update (keep path, query, authority)
System.out.println(base.resolve("#top"));
// https://user:pw@hostA:8443/dir/page.html?x=1#top
```

#### 📝 Security note: userInfo in URLs (user:password@…) is discouraged (leaks credentials in logs/history). Prefer HTTP auth headers or OAuth.

### 4. Handlers (custom protocol or behavior)
- What they are: URLStreamHandler is the per-protocol “driver” that parses the URL and implements openConnection().
- Why you’d use one: to support a custom scheme (e.g., asset:, memory:, myproto:) or to override parsing/connection behavior for a specific URL instance without installing a global factory.
- How to use now (post‑JDK 20 deprecations): create a URI, then use URL.of(URI, URLStreamHandler).

#### 📌Example skeleton:
```java
import java.io.IOException;
import java.net.*;

public class MyHandler extends URLStreamHandler {
    @Override protected URLConnection openConnection(URL u) throws IOException {
        // Implement how to connect (or return a custom URLConnection)
        return new URL("data:text/plain,hello").openConnection();
    }
    @Override protected void parseURL(URL u, String spec, int start, int limit) {
        // Optionally customize parsing; otherwise, call super.parseURL(...)
        super.parseURL(u, spec, start, limit);
    }
}

URI uri = new URI("myproto://resource/path");
URL url = URL.of(uri, new MyHandler());   // Per-instance handler, no global factory
URLConnection conn = url.openConnection(); // Uses MyHandler
```

#### 📌Notes:
- setURLStreamHandlerFactory(...) is global and can be set only once per JVM; often undesirable in libraries. A per-instance handler avoids that.
- If no handler exists for a scheme, creating/using the URL will lead to MalformedURLException or failure at openConnection().

➡️ Putting it together: which constructor/pattern when?
- Validation/encoding: Construct a URI from components → strict, safe, no network I/O.
- Need a URL to connect: uri.toURL() (or URL.of(uri, handler) for custom protocols).
- IPv6: always bracket the address in textual URLs; Java’s getHost strips brackets, getAuthority shows them.
- Authority inheritance: relative refs inherit base authority unless they specify “//…”, which replaces it.
***