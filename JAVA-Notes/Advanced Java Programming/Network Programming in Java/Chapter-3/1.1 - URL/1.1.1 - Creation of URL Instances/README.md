# \# Creation of URL Instances

### 📝 Statement 1:
➡️ The `java.net.URL` class represents a Uniform Resource Locator, which is a reference to a web resource. Creating URL instances correctly is fundamental to network programming in Java.  


### 📝 Statement 2:
No public constructors in the classical sense—all are deprecated since JDK 20! Instead, prefer creating via URI parsing then toURL(), or the static URL.of(URI, URLStreamHandler). This shift promotes safer parsing. Constructors/methods throw MalformedURLException for invalid syntax, unknown protocols, or handler issues.

### 📝 Statement 3:
➡️ Java’s URL API provides means to parse, inspect, and open connections to resources identified by URLs, but recent Java guidance deprecates direct use of URL constructors in favor of creating a URI and converting it to a URL for safer parsing and validation