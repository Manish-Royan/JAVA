package com.networkdiscovery;

import java.net.NetworkInterface;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class NetworkDiscoveryTool {
    public static void main(String[] args) {
        System.out.println("Network Discovery Tool");
        System.out.println("=====================");

        NetworkScanner scanner = new NetworkScanner();
        Scanner input = new Scanner(System.in);

        // Display network interfaces
        List<NetworkInterface> interfaces = scanner.getAllNetworkInterfaces();
        System.out.println("\nAvailable Network Interfaces:");
        for (int i = 0; i < interfaces.size(); i++) {
            NetworkInterface netInterface = interfaces.get(i);
            try {
                if (!netInterface.isUp()) continue;
                System.out.println(i + ": " + netInterface.getDisplayName() +
                        " (" + netInterface.getName() + ")");
            } catch (Exception e) {
                System.err.println("Error reading interface: " + e.getMessage());
            }
        }

        System.out.println("\nStarting network scan...");
        List<Device> devices = scanner.scanNetwork();

        if (devices.isEmpty()) {
            System.out.println("No active devices found.");
            return;
        }

        System.out.println("\nDiscovered " + devices.size() + " active devices:");
        devices.forEach(System.out::println);

        // Filter options
        boolean exit = false;
        while (!exit) {
            System.out.println("\nFilter Options:");
            System.out.println("1. Show all devices");
            System.out.println("2. Show site-local addresses");
            System.out.println("3. Show link-local addresses");
            System.out.println("4. Show loopback addresses");
            System.out.println("5. Show multicast addresses");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");
            int choice = input.nextInt();

            List<Device> filtered;
            switch (choice) {
                case 0:
                    exit = true;
                    break;
                case 1:
                    System.out.println("\nAll devices:");
                    devices.forEach(System.out::println);
                    break;
                case 2:
                    filtered = devices.stream()
                            .filter(Device::isSiteLocalAddress)
                            .collect(Collectors.toList());
                    System.out.println("\nSite-local devices (" + filtered.size() + "):");
                    filtered.forEach(System.out::println);
                    break;
                case 3:
                    filtered = devices.stream()
                            .filter(Device::isLinkLocalAddress)
                            .collect(Collectors.toList());
                    System.out.println("\nLink-local devices (" + filtered.size() + "):");
                    filtered.forEach(System.out::println);
                    break;
                case 4:
                    filtered = devices.stream()
                            .filter(Device::isLoopbackAddress)
                            .collect(Collectors.toList());
                    System.out.println("\nLoopback devices (" + filtered.size() + "):");
                    filtered.forEach(System.out::println);
                    break;
                case 5:
                    filtered = devices.stream()
                            .filter(Device::isMulticastAddress)
                            .collect(Collectors.toList());
                    System.out.println("\nMulticast devices (" + filtered.size() + "):");
                    filtered.forEach(System.out::println);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        System.out.println("Thank you for using Network Discovery Tool!");
        input.close();
    }
}

/*
Run Program:
1. cd C:\Users\HP\Desktop\LEARING\NetworkingProject\network-discovery-tool
2. java -jar target\network-discovery-tool-1.0-SNAPSHOT.jar
3. Using MAVEN: mvn exec:java -Dexec.mainClass="com.networkdiscovery.NetworkDiscoveryTool"
 */