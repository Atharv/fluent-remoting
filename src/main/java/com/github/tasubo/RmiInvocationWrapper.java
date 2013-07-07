package com.github.tasubo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.rmi.RemoteException;
import java.util.Map;

class RmiInvocationWrapper implements InvocationWrapper {

    private final Map<String, Object> endpoints;

    public RmiInvocationWrapper(Map<String, Object> endpoints) {
        this.endpoints = endpoints;
    }

    @Override
    public Object invoke(RemoteInvocation invocation) throws RemoteException {
        try {
            Object targetObject = endpoints.get(invocation.getEndpoint());
            Method method = targetObject.getClass().getMethod(invocation.getMethodName(), invocation.getParameterTypes());
            return method.invoke(targetObject, invocation.getArgs());
        } catch (NoSuchMethodException e) {
            throw new RemoteException("call error", e);
        } catch (InvocationTargetException e) {
            throw new RemoteException("call error", e);
        } catch (IllegalAccessException e) {
            throw new RemoteException("call error", e);
        }
    }
}
