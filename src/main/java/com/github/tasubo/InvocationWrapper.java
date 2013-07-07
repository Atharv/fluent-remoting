package com.github.tasubo;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InvocationWrapper extends Remote {

    public Object invoke(RemoteInvocation invocation)
            throws RemoteException;
}
