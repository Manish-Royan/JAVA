# Java URL Constructors Simplified

## 1. URL(String spec) - The "All-in-One" Constructor

**Simply put:** This is like giving an address to a taxi driver all at once.

```java
URL website = new URL("https://www.google.com/search?q=java");
```

**What it does:**
- Takes the entire URL as one string
- Breaks it into pieces (website name, path, etc.)
- No actual internet connection happens yet

**When to use it:** When you already have the complete URL as a string.

**Watch out:** It doesn't handle spaces and special characters very well. Like a taxi driver who gets confused by unclear handwriting.

## 2. URL(URL context, String spec) - The "Relative Address" Constructor

**Simply put:** This is like telling someone "Go to the mall, then find the food court."

```java
URL mall = new URL("https://www.amazon.com/electronics/");
URL specificDepartment = new URL(mall, "computers/laptops");
// Results in: https://www.amazon.com/electronics/computers/laptops
```

**What it does:**
- Takes a "starting point" URL (the mall)
- Adds a relative path to it (the food court)
- Figures out the final location

**Special features:**
- If you say "../" it means "go up one folder"
- If you give a complete address instead of a relative one, it ignores the starting point

**Example with "../":**
```java
URL base = new URL("https://www.github.com/Manish-Bishwakarma/project/");
URL parent = new URL(base, "../");  // Goes up one level
// Results in: https://www.github.com/Manish-Bishwakarma/
```

## 3. URL(URL context, String spec, URLStreamHandler handler) - The "Custom Navigation" Constructor

**Simply put:** Like the previous one, but you also bring your own special guide who knows secret paths.

```java
URL base = new URL("https://api.github.com/");
URL withCustomHandler = new URL(base, "repos", mySpecialHandler);
```

**What it does:**
- Works like the previous constructor for building the address
- But when you try to connect, it uses your custom handler
- The handler is like your personal internet navigator that follows your special rules

**When to use:** When you need to create your own protocol or customize how Java connects to websites.

## 4. URL(String protocol, String host, String file) - The "Component Builder" Constructor

**Simply put:** Building an address piece by piece - like saying "I want to go to the city of New York, on 5th Avenue."

```java
URL site = new URL("https", "www.oracle.com", "/java/technologies/downloads/");
```

**What it does:**
- Takes three separate pieces:
  1. Protocol: "https" (how to get there)
  2. Host: "www.oracle.com" (which website)
  3. File: "/java/technologies/downloads/" (which page)
- Combines them into a complete URL

**Port behavior:** Uses the default port for the protocol (443 for https, 80 for http)

## 5. URL(String protocol, String host, int port, String file) - The "Specific Port" Constructor

**Simply put:** Same as above, but you also specify which door to enter through (the port).

```java
URL localServer = new URL("http", "localhost", 8080, "/api/users");
```

**What it does:**
- Same as the previous one but adds a specific port number
- Use -1 if you want to use the default port

**Example with default port:**
```java
URL site = new URL("https", "www.microsoft.com", -1, "/en-us/windows/");
// Uses default port 443 for HTTPS
```

## 6. URL(String protocol, String host, int port, String file, URLStreamHandler handler) - The "Full Control" Constructor

**Simply put:** You specify everything - the address, the door to enter, AND you bring your own special guide.

```java
URL fullControl = new URL("https", "api.github.com", 443, "/repos", myCustomHandler);
```

**What it does:**
- Builds the URL from all the separate pieces
- Uses your custom handler when connecting
- Gives you maximum control over both the address and how to connect

**When to use:** Advanced scenarios where you need both a custom-built URL and special connection behavior.

## Summary with Real-World Analogy

Think of a URL like a postal address:
- The protocol (https://) is like choosing between "road" or "air mail"
- The host (google.com) is the city
- The port (443) is like a specific entrance door
- The path (/search) is the street address
- The query (?q=java) is special instructions for the recipient

#### 👉 The different constructors are just different ways to write this address - all at once, or piece by piece!