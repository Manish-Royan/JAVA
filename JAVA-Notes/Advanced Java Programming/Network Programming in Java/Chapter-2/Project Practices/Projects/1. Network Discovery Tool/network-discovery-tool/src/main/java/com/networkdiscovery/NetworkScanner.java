package com.networkdiscovery;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Callable;

public class NetworkScanner {
    private static final int TIMEOUT_MS = 500; // Timeout in milliseconds
    private static final int THREAD_POOL_SIZE = 255; // Number of threads in the pool

    public List<Device> scanNetwork() {
        List<Device> devices = new ArrayList<>();
        try {
            // Get local IP address and subnet
            InetAddress localHost = InetAddress.getLocalHost();
            String ipAddress = localHost.getHostAddress();

            // Parse the IP address to get subnet
            String subnet = ipAddress.substring(0, ipAddress.lastIndexOf('.') + 1);

            System.out.println("Local IP: " + ipAddress);
            System.out.println("Scanning subnet: " + subnet + "0 to " + subnet + "255");

            // Create a thread pool for parallel scanning
            ExecutorService executor = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
            List<Future<Device>> futures = new ArrayList<>();

            // Submit tasks to scan each IP in the subnet
            for (int i = 0; i < 256; i++) {
                final int finalI = i;
                futures.add(executor.submit(new Callable<Device>() {
                    @Override
                    public Device call() throws Exception {
                        String ip = subnet + finalI;
                        InetAddress address = InetAddress.getByName(ip);
                        boolean reachable = address.isReachable(TIMEOUT_MS);
                        return new Device(address, reachable);
                    }
                }));
            }

            // Get results from all threads
            for (Future<Device> future : futures) {
                try {
                    Device device = future.get();
                    if (device.isReachable()) {
                        devices.add(device);
                    }
                } catch (Exception e) {
                    System.err.println("Error getting scan result: " + e.getMessage());
                }
            }

            // Shutdown the executor
            executor.shutdown();
            executor.awaitTermination(1, TimeUnit.MINUTES);

        } catch (Exception e) {
            System.err.println("Error scanning network: " + e.getMessage());
            e.printStackTrace();
        }

        return devices;
    }

    public List<NetworkInterface> getAllNetworkInterfaces() {
        List<NetworkInterface> interfaces = new ArrayList<>();
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                interfaces.add(networkInterfaces.nextElement());
            }
        } catch (SocketException e) {
            System.err.println("Error getting network interfaces: " + e.getMessage());
        }
        return interfaces;
    }
}