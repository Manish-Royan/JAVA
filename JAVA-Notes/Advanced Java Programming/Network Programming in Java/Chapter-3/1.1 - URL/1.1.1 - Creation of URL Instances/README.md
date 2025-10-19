# \# Creation of URL Instances

### 📝 Statement 1:
➡️ The `java.net.URL` class represents a Uniform Resource Locator, which is a reference to a web resource. Creating URL instances correctly is fundamental to network programming in Java.  

### 📝 [Statement 2](https://github.com/Manish-Royan/JAVA/tree/main/JAVA-Notes/Advanced%20Java%20Programming/Network%20Programming%20in%20Java/Chapter-3/%23%20More%20Depth%20Explorations/1.3%20MDE%20-%20No%20Public%20Constructor%20in%20URL%20Class):
➡️ No public constructors in the classical sense—all are deprecated since JDK 20! Instead, prefer creating via URI parsing then `toURL()`, or the static `URL.of(URI, URLStreamHandler)`. This shift promotes safer parsing. Constructors/methods throw `MalformedURLException` for invalid syntax, unknown protocols, or handler issues.

### 📝 Statement 3:
➡️ Java’s URL API provides means to parse, inspect, and open connections to resources identified by URLs, but recent Java guidance deprecates direct use of URL constructors in favor of creating a URI and converting it to a URL for safer parsing and validation

