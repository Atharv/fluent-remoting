package com.github.tasubo;

public class RmiServerBinding {
    private final Object object;

    RmiServerBinding(RmiConfig rmiConfig, Object object) {
        this.object = object;
        RmiEndpoint.INSTANCE.start(rmiConfig);
    }

    public void to(final String name) {
        RmiEndpoint.INSTANCE.add(name, object);
    }

}
