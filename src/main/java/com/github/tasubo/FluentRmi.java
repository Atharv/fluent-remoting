package com.github.tasubo;

public class FluentRmi {

    private static final RmiConfig CONFIG = new RmiConfig();

    public static RmiConfig config() {
        return CONFIG;
    }

    public static FluentRmiServer server() {
        return new FluentRmiServer(CONFIG);
    }

    public static FluentRmiClient client() {
        return new FluentRmiClient(CONFIG);
    }
}
