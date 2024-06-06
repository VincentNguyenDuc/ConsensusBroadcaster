package com.jbroadcast.remote.server.ipc.rmi;

import com.jbroadcast.remote.client.IRemoteRmiClient;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRemoteRmiServer extends Remote {
    void registerRmiClient(IRemoteRmiClient aRmiClient) throws RemoteException;

    void unregisterRmiClient(IRemoteRmiClient aRmiClient) throws RemoteException;

}
