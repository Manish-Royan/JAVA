package com.networkdiscovery;

import java.net.InetAddress;

public class Device {
    private InetAddress address;
    private boolean reachable;
    private String hostname;

    public Device(InetAddress address, boolean reachable) {
        this.address = address;
        this.reachable = reachable;

        // Get hostname if available
        if (reachable) {
            try {
                this.hostname = address.getHostName();
            } catch (Exception e) {
                this.hostname = "Unknown";
            }
        } else {
            this.hostname = "Unknown";
        }
    }

    public InetAddress getAddress() {
        return address;
    }

    public boolean isReachable() {
        return reachable;
    }

    public String getHostname() {
        return hostname;
    }

    public boolean isSiteLocalAddress() {
        return address.isSiteLocalAddress();
    }

    public boolean isLinkLocalAddress() {
        return address.isLinkLocalAddress();
    }

    public boolean isLoopbackAddress() {
        return address.isLoopbackAddress();
    }

    public boolean isMulticastAddress() {
        return address.isMulticastAddress();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("IP: ").append(address.getHostAddress());
        sb.append(" | Hostname: ").append(hostname);
        sb.append(" | Status: ").append(reachable ? "Active" : "Inactive");

        if (isSiteLocalAddress()) sb.append(" | Site-Local");
        if (isLinkLocalAddress()) sb.append(" | Link-Local");
        if (isLoopbackAddress()) sb.append(" | Loopback");
        if (isMulticastAddress()) sb.append(" | Multicast");

        return sb.toString();
    }
}