package com.github.tasubo;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientEndpoint {
    private final RmiConfig config;
    private final String endpointName;

    ClientEndpoint(RmiConfig config, String endpointName) {
        this.config = config;
        this.endpointName = endpointName;
    }

    public <T> T as(Class<T> tClass) {
        try {
            Registry registry = LocateRegistry.getRegistry(config.getHostname(), config.getPort());
            Remote lookup = registry.lookup(RmiEndpoint.ENDPOINT_NAME);
            InvocationWrapper cast = (InvocationWrapper) lookup;

            return newProxyInstance(cast, tClass, endpointName);
        } catch (RemoteException e) {
            throw new FluentRmiException(e);
        } catch (NotBoundException e) {
            throw new FluentRmiException(e);
        }
    }

    private <T> T newProxyInstance(InvocationWrapper wrapper, Class<T> tClass, String endpointName) {
        Enhancer e = new Enhancer();

        e.setSuperclass(tClass);
        e.setCallback(new MethodCallDelegator(wrapper, endpointName));
        T bean = (T) e.create();

        return bean;
    }

    private static class MethodCallDelegator implements MethodInterceptor {

        private final InvocationWrapper object;
        private final String endpointName;

        public MethodCallDelegator(InvocationWrapper objectToProxy, String endpointName) {
            this.object = objectToProxy;
            this.endpointName = endpointName;
        }

        @Override
        public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
            RemoteInvocation invocation = new RemoteInvocation(endpointName, method.getName(), args);
            return object.invoke(invocation);
        }
    }
}
