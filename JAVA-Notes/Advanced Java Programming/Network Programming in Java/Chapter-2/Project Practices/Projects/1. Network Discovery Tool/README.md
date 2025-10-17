# Building a Network Discovery Tool
➡️ Creating a Network Discovery Tool using Java networking knowledge about InetAddress. This tool will scan our local network and find active devices.

[IMG]

## 📚 Project Overview
The Network Discovery Tool will:
1. Scan your local network to find active devices → Display your available network interfaces
2. Show the IP address and hostname of each device → Scan your local subnet for active devices
3. Display information about the type of address (site-local, link-local, etc.)
4. Allow you to filter the results based on address types

## \# Step-by-Step Implementation
⛓️‍💥 Let's break this down into manageable parts:

### 1. Project Structure Explained
➡️ This is a standard Maven project structure:
```
network-discovery-tool/
├── pom.xml
├── README.md
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── networkdiscovery/
│   │   │           ├── Device.java
│   │   │           ├── NetworkDiscoveryTool.java
│   │   │           └── NetworkScanner.java
│   │   └── resources/
│   └── test/
│       └── java/
│           └── com/
│               └── networkdiscovery/
│                   └── NetworkScannerTest.java
```

1. **Root Directory**: `network-discovery-tool/` - The main project folder
   - **pom.xml**: Maven configuration file that manages dependencies and build settings

2. **Source Code Structure**:
   - **src/main/java/**: Contains all the Java source code
     - **com/networkdiscovery/**: Package structure
       - **Device.java**: Class representing network devices with their properties
       - **NetworkDiscoveryTool.java**: Main class with UI and program entry point
       - **NetworkScanner.java**: Core scanning functionality

3. **Resources Directory**:
   - **src/main/resources/**: For configuration files, properties, etc. (empty in this case)

4. **Test Structure**:
   - **src/test/java/**: Contains test code
     - **com/networkdiscovery/**: Mirror of main package structure
       - **NetworkScannerTest.java**: Tests for the network scanner functionality

### 🖥️ Windows CMD Commands to Create this Project Structure
➡️ Here are the commands to create this structure in Windows Command Prompt:
```bash
REM Create the root project directory
mkdir network-discovery-tool
cd network-discovery-tool

REM Create the source directory structure
mkdir src\main\java\com\networkdiscovery
mkdir src\main\resources
mkdir src\test\java\com\networkdiscovery

REM Create empty files
type nul > pom.xml
type nul > README.md
type nul > src\main\java\com\networkdiscovery\Device.java
type nul > src\main\java\com\networkdiscovery\NetworkDiscoveryTool.java
type nul > src\main\java\com\networkdiscovery\NetworkScanner.java
type nul > src\test\java\com\networkdiscovery\NetworkScannerTest.java
```

### 🪜 Step by Step Execution

1. Open Command Prompt by pressing `Win + R`, typing `cmd`, and pressing Enter
2. Navigate to the directory where you want to create the project
3. Copy and paste each command line by line (or copy the entire block)
4. After executing these commands, you'll have the complete project structure

### 🧾 Create a new Java project with these files:
1. `NetworkScanner.java` - Core scanning functionality
2. `DeviceInfo.java` - Class to store device information
3. `NetworkDiscoveryTool.java` - Main application class

## 2. Implementation
➡️ Let's start with the individual files:
👉 Let's build a Java application with these components:
* **NetworkScanner** - Core class for scanning the network
* **Device** - Class to represent discovered devices
* **Main** - Entry point with a simple UI to show results and filtering options

### 📌 Step 1: Create the Device Class
```java name=DeviceInfo.java
package com.networkdiscovery;

// Import necessary class for handling IP addresses
import java.net.InetAddress; // Provides methods to work with IP addresses and hostnames

// STEP 1: Define the Device class to represent a network device with its properties
// This class encapsulates details about a device discovered on the network, such as IP address, reachability, and hostname
public class Device {
    // Private fields to store device information
    private InetAddress address; // The IP address of the device
    private boolean reachable; // Boolean flag indicating if the device is reachable (active)
    private String hostname; // The hostname associated with the IP address (if resolvable)

    // STEP 2: Constructor to initialize a Device object with address and reachability status
    // Takes an InetAddress and a boolean indicating if the device is reachable
    public Device(InetAddress address, boolean reachable) {
        // Assign the provided IP address to the instance variable
        this.address = address;
        // Assign the provided reachability status to the instance variable
        this.reachable = reachable;

        // STEP 3: Attempt to resolve and set the hostname if the device is reachable
        // Check if the device is reachable before trying to get the hostname
        if (reachable) {
            // Try to get the hostname from the IP address (may throw exceptions if DNS lookup fails)
            try {
                this.hostname = address.getHostName(); // Retrieve the hostname via DNS resolution
            // Catch any exceptions during hostname resolution (e.g., network issues)
            } catch (Exception e) {
                // If hostname resolution fails, set it to "Unknown"
                this.hostname = "Unknown";
            }
        // If the device is not reachable, set hostname to "Unknown" without attempting resolution
        } else {
            this.hostname = "Unknown";
        }
    }

    // STEP 4: Getter methods to access private fields
    // Returns the IP address of the device
    public InetAddress getAddress() {
        return address;
    }

    // Returns whether the device is reachable
    public boolean isReachable() {
        return reachable;
    }

    // Returns the hostname of the device
    public String getHostname() {
        return hostname;
    }

    // STEP 5: Methods to check specific types of IP addresses using InetAddress built-in checks
    // Checks if the IP address is site-local (private network range, e.g., 192.168.x.x)
    public boolean isSiteLocalAddress() {
        return address.isSiteLocalAddress(); // Returns true if it's a site-local address
    }

    // Checks if the IP address is link-local (local network range, e.g., 169.254.x.x)
    public boolean isLinkLocalAddress() {
        return address.isLinkLocalAddress(); // Returns true if it's a link-local address
    }

    // Checks if the IP address is a loopback address (localhost, 127.0.0.1)
    public boolean isLoopbackAddress() {
        return address.isLoopbackAddress(); // Returns true if it's a loopback address
    }

    // Checks if the IP address is a multicast address (used for group communication)
    public boolean isMulticastAddress() {
        return address.isMulticastAddress(); // Returns true if it's a multicast address
    }

    // STEP 6: Override toString method to provide a formatted string representation of the device
    // Builds a human-readable string with device details
    @Override
    public String toString() {
        // Create a StringBuilder to efficiently build the output string
        StringBuilder sb = new StringBuilder();
        // Append the IP address
        sb.append("IP: ").append(address.getHostAddress());
        // Append the hostname
        sb.append(" | Hostname: ").append(hostname);
        // Append the status (Active if reachable, Inactive otherwise)
        sb.append(" | Status: ").append(reachable ? "Active" : "Inactive");

        // Conditionally append address type labels if applicable
        if (isSiteLocalAddress()) sb.append(" | Site-Local"); // Add label if site-local
        if (isLinkLocalAddress()) sb.append(" | Link-Local"); // Add label if link-local
        if (isLoopbackAddress()) sb.append(" | Loopback"); // Add label if loopback
        if (isMulticastAddress()) sb.append(" | Multicast"); // Add label if multicast

        // Return the complete formatted string
        return sb.toString();
    }
}
```

### 📌 Step 2: Create the NetworkScanner Class
#### Now let's create the network scanner that will do the actual device discovery:

```java name=NetworkScanner.java
package com.networkdiscovery;

// Import necessary classes for networking, concurrency, and collections
import java.net.InetAddress; // For working with IP addresses
import java.net.NetworkInterface; // For accessing network interfaces
import java.net.SocketException; // For handling socket-related exceptions
import java.util.ArrayList; // For creating dynamic lists
import java.util.Enumeration; // For iterating over network interfaces
import java.util.List; // For handling lists of devices and interfaces
import java.util.concurrent.ExecutorService; // For managing thread pools
import java.util.concurrent.Executors; // For creating thread pool executors
import java.util.concurrent.Future; // For handling asynchronous task results
import java.util.concurrent.TimeUnit; // For specifying time units (e.g., minutes)
import java.util.concurrent.Callable; // For defining tasks that return results

// STEP 1: Define the NetworkScanner class to handle network scanning operations
// This class performs parallel scanning of IP addresses in the local subnet to discover active devices
public class NetworkScanner {
    // Constants for scan configuration
    private static final int TIMEOUT_MS = 500; // Timeout for reachability checks in milliseconds
    private static final int THREAD_POOL_SIZE = 255; // Number of threads for parallel scanning (matches 256 IPs)

    // STEP 2: Method to scan the network for active devices
    // Scans the local subnet (0-255) in parallel and returns a list of reachable devices
    public List<Device> scanNetwork() {
        // Initialize an empty list to store discovered devices
        List<Device> devices = new ArrayList<>();
        // Wrap the scanning logic in a try-catch to handle exceptions
        try {
            // STEP 3: Determine the local IP and subnet for scanning
            // Get the local host's IP address
            InetAddress localHost = InetAddress.getLocalHost();
            // Extract the IP address as a string
            String ipAddress = localHost.getHostAddress();

            // Parse the IP to get the subnet prefix (e.g., "192.168.1." from "192.168.1.10")
            String subnet = ipAddress.substring(0, ipAddress.lastIndexOf('.') + 1);

            // Print the local IP and scanning range for user feedback
            System.out.println("Local IP: " + ipAddress);
            System.out.println("Scanning subnet: " + subnet + "0 to " + subnet + "255");

            // STEP 4: Set up a thread pool for parallel scanning
            // Create a fixed-size thread pool with the defined number of threads
            ExecutorService executor = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
            // List to hold futures for each scanning task
            List<Future<Device>> futures = new ArrayList<>();

            // STEP 5: Submit scanning tasks for each IP in the subnet (0 to 255)
            // Loop through all possible last octets (0-255)
            for (int i = 0; i < 256; i++) {
                // Use a final variable for the loop index to access in the lambda/anonymous class
                final int finalI = i;
                // Submit a callable task to the executor for each IP
                futures.add(executor.submit(new Callable<Device>() {
                    @Override
                    // Define the task: create an IP, check reachability, and return a Device
                    public Device call() throws Exception {
                        // Construct the full IP address (subnet + last octet)
                        String ip = subnet + finalI;
                        // Get the InetAddress for the IP
                        InetAddress address = InetAddress.getByName(ip);
                        // Check if the address is reachable within the timeout
                        boolean reachable = address.isReachable(TIMEOUT_MS);
                        // Return a new Device object with the address and reachability status
                        return new Device(address, reachable);
                    }
                }));
            }

            // STEP 6: Collect results from all completed tasks
            // Iterate through the futures to get the results
            for (Future<Device> future : futures) {
                // Try to get the result from each future
                try {
                    // Retrieve the Device from the future
                    Device device = future.get();
                    // Add to the list only if the device is reachable
                    if (device.isReachable()) {
                        devices.add(device);
                    }
                // Catch and print any exceptions during result retrieval
                } catch (Exception e) {
                    System.err.println("Error getting scan result: " + e.getMessage());
                }
            }

            // STEP 7: Shut down the executor and wait for tasks to complete
            // Initiate shutdown of the thread pool
            executor.shutdown();
            // Wait up to 1 minute for all tasks to finish
            executor.awaitTermination(1, TimeUnit.MINUTES);

        // STEP 8: Handle any exceptions during the scan
        } catch (Exception e) {
            // Print error message and stack trace for debugging
            System.err.println("Error scanning network: " + e.getMessage());
            e.printStackTrace();
        }

        // Return the list of discovered reachable devices
        return devices;
    }

    // STEP 9: Method to retrieve all network interfaces
    // Returns a list of all available network interfaces on the system
    public List<NetworkInterface> getAllNetworkInterfaces() {
        // Initialize an empty list for interfaces
        List<NetworkInterface> interfaces = new ArrayList<>();
        // Wrap in try-catch for socket exceptions
        try {
            // Get an enumeration of all network interfaces
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            // Iterate through the enumeration and add each to the list
            while (networkInterfaces.hasMoreElements()) {
                interfaces.add(networkInterfaces.nextElement());
            }
        // Catch and print any socket exceptions
        } catch (SocketException e) {
            System.err.println("Error getting network interfaces: " + e.getMessage());
        }
        // Return the list of interfaces
        return interfaces;
    }
}
```
### Step 3: Create the Main Application Class
#### Finally, let's create the main application class:

```java name=NetworkDiscoveryTool.java
package com.networkdiscovery;

// Import necessary classes for network interfaces, collections, user input, and stream operations
import java.net.NetworkInterface; // For representing network interfaces
import java.util.List; // For handling lists of devices and interfaces
import java.util.Scanner; // For reading user input from the console
import java.util.stream.Collectors; // For filtering and collecting streams of devices

// STEP 1: Define the main class for the Network Discovery Tool application
// This class provides a command-line interface to scan the network and filter discovered devices
public class NetworkDiscoveryTool {
    // STEP 2: Main method - entry point of the application
    public static void main(String[] args) {
        // Print the tool's title and separator for user interface
        System.out.println("Network Discovery Tool");
        System.out.println("=====================");

        // Create an instance of NetworkScanner to handle network scanning operations
        NetworkScanner scanner = new NetworkScanner();
        // Create a Scanner for reading user input
        Scanner input = new Scanner(System.in);

        // STEP 3: Retrieve and display available network interfaces
        // Get a list of all network interfaces from the scanner
        List<NetworkInterface> interfaces = scanner.getAllNetworkInterfaces();
        // Print a header for the list of interfaces
        System.out.println("\nAvailable Network Interfaces:");
        // Loop through the list and display each active (up) interface
        for (int i = 0; i < interfaces.size(); i++) {
            NetworkInterface netInterface = interfaces.get(i); // Get the interface at index i
            try {
                // Skip interfaces that are not up (active)
                if (!netInterface.isUp()) continue;
                // Print the interface index, display name, and system name
                System.out.println(i + ": " + netInterface.getDisplayName() +
                        " (" + netInterface.getName() + ")");
            // Catch and print any errors while reading interface details
            } catch (Exception e) {
                System.err.println("Error reading interface: " + e.getMessage());
            }
        }

        // STEP 4: Perform the network scan and display results
        // Print a message indicating the scan is starting
        System.out.println("\nStarting network scan...");
        // Use the scanner to scan the network and get a list of discovered devices
        List<Device> devices = scanner.scanNetwork();

        // Check if any devices were found
        if (devices.isEmpty()) {
            // Print a message if no devices were discovered
            System.out.println("No active devices found.");
            // Exit the program early
            return;
        }

        // Print the count of discovered active devices
        System.out.println("\nDiscovered " + devices.size() + " active devices:");
        // Print each device's details using its toString method
        devices.forEach(System.out::println);

        // STEP 5: Implement a menu-driven filter system for user interaction
        // Initialize a flag to control the exit from the loop
        boolean exit = false;
        // Loop until the user chooses to exit
        while (!exit) {
            // Display filter options menu
            System.out.println("\nFilter Options:");
            System.out.println("1. Show all devices");
            System.out.println("2. Show site-local addresses");
            System.out.println("3. Show link-local addresses");
            System.out.println("4. Show loopback addresses");
            System.out.println("5. Show multicast addresses");
            System.out.println("0. Exit");

            // Prompt the user for input
            System.out.print("Enter your choice: ");
            // Read the user's choice as an integer
            int choice = input.nextInt();

            // Declare a list for filtered results
            List<Device> filtered;
            // Use a switch statement to handle the user's choice
            switch (choice) {
                // Case 0: Exit the program
                case 0:
                    exit = true; // Set exit flag to true
                    break;
                // Case 1: Display all devices
                case 1:
                    System.out.println("\nAll devices:");
                    devices.forEach(System.out::println); // Print all devices
                    break;
                // Case 2: Filter and display site-local devices
                case 2:
                    // Use stream API to filter devices where isSiteLocalAddress is true
                    filtered = devices.stream()
                            .filter(Device::isSiteLocalAddress)
                            .collect(Collectors.toList());
                    // Print the count and details of filtered devices
                    System.out.println("\nSite-local devices (" + filtered.size() + "):");
                    filtered.forEach(System.out::println);
                    break;
                // Case 3: Filter and display link-local devices
                case 3:
                    // Use stream API to filter devices where isLinkLocalAddress is true
                    filtered = devices.stream()
                            .filter(Device::isLinkLocalAddress)
                            .collect(Collectors.toList());
                    // Print the count and details of filtered devices
                    System.out.println("\nLink-local devices (" + filtered.size() + "):");
                    filtered.forEach(System.out::println);
                    break;
                // Case 4: Filter and display loopback devices
                case 4:
                    // Use stream API to filter devices where isLoopbackAddress is true
                    filtered = devices.stream()
                            .filter(Device::isLoopbackAddress)
                            .collect(Collectors.toList());
                    // Print the count and details of filtered devices
                    System.out.println("\nLoopback devices (" + filtered.size() + "):");
                    filtered.forEach(System.out::println);
                    break;
                // Case 5: Filter and display multicast devices
                case 5:
                    // Use stream API to filter devices where isMulticastAddress is true
                    filtered = devices.stream()
                            .filter(Device::isMulticastAddress)
                            .collect(Collectors.toList());
                    // Print the count and details of filtered devices
                    System.out.println("\nMulticast devices (" + filtered.size() + "):");
                    filtered.forEach(System.out::println);
                    break;
                // Default: Handle invalid input
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        // STEP 6: Exit the program gracefully
        // Print a thank you message
        System.out.println("Thank you for using Network Discovery Tool!");
        // Close the input scanner to free resources
        input.close();
    }
}
```
### Step 4: Create a build file (pom.xml)
#### For Maven users, here's a simple pom.xml file:

```xml name=pom.xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.networkdiscovery</groupId>
    <artifactId>network-discovery-tool</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.source>11</maven.compiler.source>
        <maven.compiler.target>11</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-jar-plugin</artifactId>
                <version>3.2.0</version>
                <configuration>
                    <archive>
                        <manifest>
                            <mainClass>com.networkdiscovery.NetworkDiscoveryTool</mainClass>
                        </manifest>
                    </archive>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```



## 3. Next Steps: Building and Running the Project

1. **Add code to the files**: Copy the above Java code into each respective .java file using a text/IDE editor of your choice.

2. **Add the Maven configuration**: Copy the pom.xml content I provided into your empty pom.xml file.
    ### \# Prerequisites: First, make sure you have Maven installed:
    1. **Check if Maven is installed**:
       ```
       mvn -version
       ```

    2. **If not installed**, download and install Maven:
       - Download from: https://maven.apache.org/download.cgi
       - Follow installation instructions: https://maven.apache.org/install.html
       - For Windows: 
         - Download the Binary zip archive
         - Extract it to a directory like `C:\Program Files\Apache\maven`
         - Add the bin directory to your PATH environment variable
         - Restart your command prompt

    ### \# Building the Project: Once Maven is installed, follow these steps:
    1. **Navigate to your project directory**:
       ```
       cd C:\Users\HP\Desktop\LEARING\NetworkingProject\network-discovery-tool
       ```

    2. **Build the project**:
       ```
       mvn clean package
       ```

       👇This command:
       - Cleans any previous builds
       - Compiles your Java code
       - Runs tests (if any)
       - Packages the application into a JAR file

    3. **Verify the build**:
       After a successful build, you should see a message like "BUILD SUCCESS" and the JAR file will be created in the `target` directory.

3. Running the Application: You have two options to run the application:
    ### Option 1: Using Java directly
    ```
    java -jar target\network-discovery-tool-1.0-SNAPSHOT.jar
    ```
    ### Option 2: Using Maven
    ```
    mvn exec:java -Dexec.mainClass="com.networkdiscovery.NetworkDiscoveryTool"
    ```

## \# Network Discovery Tool: How It Works

### Overall Architecture
➡️ This Network Discovery Tool is designed to scan your local network and discover active devices. Here's a step-by-step explanation of how it works:

### 1. Program Structure

The program consists of three main classes:
- **NetworkDiscoveryTool**: The main class with the user interface
- **NetworkScanner**: The core scanning engine
- **Device**: Represents discovered network devices with their properties

### 2. Network Scanning Process

1. **Initialization**:
   - The program starts in `NetworkDiscoveryTool.main()`
   - It displays available network interfaces on your machine
   - It creates an instance of `NetworkScanner`

2. **Determining the Network Range**:
   - The scanner gets your local IP address using `InetAddress.getLocalHost()`
   - It extracts your subnet (e.g., if your IP is 192.168.1.5, the subnet is 192.168.1)
   - It prepares to scan all possible IPs in that subnet (typically 256 addresses from x.x.x.0 to x.x.x.255)

3. **Multithreaded Scanning**:
   - A thread pool is created to scan multiple IPs simultaneously
   - Each thread is assigned an IP address to check
   - This parallel approach makes scanning much faster than checking IPs sequentially

### 3. Device Detection Process

For each IP address in the subnet:

1. The program creates an `InetAddress` object for the IP
2. It calls `address.isReachable(timeout)` to check if the device responds
3. If reachable, it collects device information:
   - IP address
   - Hostname (if resolvable)
   - Address type (site-local, link-local, loopback, multicast)
4. It creates a `Device` object to store this information
5. Only reachable devices are added to the results list

### 4. Results Display and Filtering

1. **Initial Results**:
   - The program displays the count of discovered devices
   - It shows details for each device (IP, hostname, status, address types)

2. **Filtering Options**:
   - The program presents a menu to filter devices by type
   - Options include: all devices, site-local, link-local, loopback, and multicast addresses
   - Filtering is implemented using Java Streams to efficiently select matching devices

### 5. Technical Details

1. **InetAddress Usage**:
   - `isReachable()`: Tests if a device responds to ping
   - `getHostName()`: Attempts to resolve the hostname of an IP
   - `isSiteLocalAddress()`: Checks if an address is private (e.g., 192.168.x.x, 10.x.x.x)
   - `isLinkLocalAddress()`: Identifies link-local addresses (169.254.x.x)
   - `isLoopbackAddress()`: Identifies loopback addresses (127.x.x.x)
   - `isMulticastAddress()`: Identifies multicast addresses (224.0.0.0 to 239.255.255.255)

2. **Multithreading Implementation**:
   - Uses `ExecutorService` with a fixed thread pool size (255 threads)
   - Each scanning task is a `Callable<Device>` that returns a device object
   - `Future<Device>` objects collect results from all threads
   - `awaitTermination()` ensures all scans complete before proceeding

## 📖 Summary of Program Flow

1. Program starts and displays network interfaces
2. Network scan begins by finding your local subnet
3. 256 threads check each possible IP address in parallel
4. Active devices are detected using the `isReachable()` method
5. Device information is collected and stored in `Device` objects
6. Results are displayed showing all discovered devices
7. User can filter results by different address types
8. Program exits when the user selects the exit option

#### 👉 The key Java networking feature used is the `InetAddress` class, which provides methods for working with IP addresses and hostnames, making it possible to discover and analyze devices on your local network.

## 💉 Potential Enhancements
👉 This is just basic version, in future (if I could):
1. Add a graphical user interface (GUI) using JavaFX or Swing
2. Save scan results to a file
3. Add device tracking to detect when new devices join the network
4. Include port scanning to identify open services on discovered devices
5. Add MAC address detection using the NetworkInterface class
6.  Scan custom IP ranges

## 📝 Notes and Considerations

1. **Performance**: The scan might take some time to complete (usually around 30 seconds to 1 minute) since it's checking all 255 possible IP addresses in your subnet.

2. **Permissions**: On some operating systems, you might need elevated privileges to run the scan properly. On Windows, run as Administrator; on Linux/Mac, use sudo.

3. **Accuracy**: Some devices may not respond to the **ping requests** due to firewall settings or other network configurations.