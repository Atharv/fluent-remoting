package com.github.tasubo;

import java.io.Serializable;

public class RemoteInvocation implements Serializable {
    private final String endpoint;
    private final String methodName;
    private final Object[] args;

    public RemoteInvocation(String endpoint, String methodName, Object[] args) {
        this.endpoint = endpoint;
        this.methodName = methodName;
        this.args = args;
    }

    public String getMethodName() {
        return methodName;
    }

    public Object[] getArgs() {
        return args;
    }

    public Class<?>[] getParameterTypes() {
        int length = getArgs().length;
        Class<?>[] parameterTypes = new Class[length];
        int i = 0;
        for (Object arg : getArgs()) {
            parameterTypes[i++] = arg.getClass();
        }
        return parameterTypes;
    }

    public String getEndpoint() {
        return endpoint;
    }
}
