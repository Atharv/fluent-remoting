package com.github.tasubo;

public class FluentRmiServer {

    private final RmiConfig config;

    FluentRmiServer(RmiConfig config) {
        this.config = config;
    }

    public RmiServerBinding bind(Object object) {
        return new RmiServerBinding(config, object);
    }

    public void kill() {
        RmiEndpoint.INSTANCE.unbind();
    }
}
