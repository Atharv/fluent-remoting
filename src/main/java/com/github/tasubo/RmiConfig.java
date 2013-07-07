package com.github.tasubo;

public class RmiConfig {
    private String hostname = "localhost";
    private int port = 1299;

    RmiConfig() {
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getHostname() {
        return hostname;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getPort() {
        return port;
    }

    public void reset() {
        this.hostname = "localhost";
        this.port = 1299;
    }
}
