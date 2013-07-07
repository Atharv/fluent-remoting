package com.github.tasubo;

public class FluentRmiClient {
    private final RmiConfig config;

    FluentRmiClient(RmiConfig config) {
        this.config = config;
    }

    public ClientEndpoint get(String endpointName) {
        return new ClientEndpoint(config, endpointName);
    }
}
