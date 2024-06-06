package com.jbroadcast.remote.server.ipc.rmi;

import com.jbroadcast.bean.BeanFactory;
import com.jbroadcast.remote.client.IRemoteRmiClient;

import java.rmi.RemoteException;

public class RemoteRmiServer implements IRemoteRmiServer {

    @Override
    public void registerRmiClient(final IRemoteRmiClient aRmiClient) {
        BeanFactory.getServerBean().getClients().add(aRmiClient);
        System.out.println("Registered: " + aRmiClient);
    }

    @Override
    public void unregisterRmiClient(final IRemoteRmiClient aRmiClient) throws RemoteException {
        BeanFactory.getServerBean().getClients().removeIf(aRmiClient::equals);
        System.out.println("Unregistered: " + aRmiClient);
    }
}
