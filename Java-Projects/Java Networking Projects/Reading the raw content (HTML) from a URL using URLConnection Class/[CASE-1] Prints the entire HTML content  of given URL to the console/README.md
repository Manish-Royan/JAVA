## Explanation of the Program
* **Purpose**: This program shows how to connect to a web resource using Java's networking APIs, specifically for reading the raw content (HTML) from a URL. It's a fundamental example of client-side HTTP communication in Java, demonstrating how to fetch data from the internet without using higher-level libraries like Apache HttpClient.

## 🔑 Key Concepts Demonstrated:
* `URL` Class: Represents a Uniform Resource Locator (e.g., a webpage or API endpoint). Here, it's used to point to https://www.facebook.com.
* `URLConnection` Class: An abstract class that provides methods to interact with the URL. It allows opening a connection (`openConnection()`) and connecting to the server (`connect()`). This is essential for making HTTP requests.
* **Reading Content**: The program uses `BufferedReader` wrapped around an `InputStreamReader` to read the response from the URL's input stream line by line. This is a common way to handle text-based responses (like HTML or JSON) from web servers.
* **Error Handling**: A `try-catch` block handles `IOException`, which could occur due to network issues, invalid URLs, or server errors.
* **Resource Management**: The `BufferedReader` is closed after use to free up resources.
* **Output**: When run, it prints the entire HTML content of the Facebook homepage to the console. Note that modern websites often block or limit such scraping due to terms of service or anti-bot measures, so this is more of an educational example than a production-ready tool.

<img width="1812" height="858" alt="Screenshot 2025-10-17 003555" src="https://github.com/user-attachments/assets/16d39314-c229-41f1-95aa-cfc84a96726a" />

## ⚠️ Limitations and Notes:
* This is a basic, synchronous example. For real-world use, consider asynchronous requests (e.g., using **HttpURLConnection** or libraries like OkHttp) and handling headers, timeouts, or HTTPS properly.
* `URLConnection` is a simple way to access servers but doesn't support advanced features like custom headers or multipart requests out of the box.
* **Security**: Always validate URLs and handle potential security risks (e.g., man-in-the-middle attacks).
