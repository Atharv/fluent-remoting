package com.github.tasubo;

import java.rmi.ConnectException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import static com.github.tasubo.FluentRmi.config;

public class RmiEndpoint {

    private static final Logger LOG = Logger.getLogger(RmiEndpoint.class.getName());

    private static final Map<String, Object> endpoints = new HashMap<String, Object>();
    public static final String ENDPOINT_NAME = "rmiCallWrapperProxy";
    private boolean started = false;

    public static final RmiEndpoint INSTANCE = new RmiEndpoint();
    private RmiInvocationWrapper rmiWrapper;

    private RmiEndpoint() {
    }

    public void add(String name, Object object) {
        endpoints.put(name, object);
    }

    public void start(RmiConfig rmiConfig) {
        if (started) {
            return;
        }

        try {
            rmiWrapper = new RmiInvocationWrapper(endpoints);

            int port = rmiConfig.getPort();
            String hostname = rmiConfig.getHostname();

            Remote stub = UnicastRemoteObject.exportObject(rmiWrapper, port);
            Registry registry = LocateRegistry.createRegistry(port);
            registry.rebind(ENDPOINT_NAME, stub);
        } catch (RemoteException e) {
            throw new FluentRmiException(e);
        }

        started = true;

    }

    public void unbind() {
        try {
            Registry registry = LocateRegistry.getRegistry();
            registry.unbind(ENDPOINT_NAME);
            UnicastRemoteObject.unexportObject(rmiWrapper, false);
        } catch (ConnectException e) {
            LOG.warning("Couldn't unbind endpoint for RMI. Is RMI server running?");
        } catch (RemoteException e) {
            throw new FluentRmiException(e);
        } catch (NotBoundException e) {
            throw new FluentRmiException(e);
        } finally {
            started = false;
        }
    }
}
