package src.remote.server;

import src.remote.client.IRemoteRmiClient;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRemoteRmiServer extends Remote {
    void registerRmiClient(IRemoteRmiClient aRmiClient) throws RemoteException;

    void unregisterRmiClient(IRemoteRmiClient aRmiClient) throws RemoteException;
}
