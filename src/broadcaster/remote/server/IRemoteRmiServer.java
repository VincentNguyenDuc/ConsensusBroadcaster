package src.broadcaster.remote.server;

import src.broadcaster.remote.client.IRemoteRmiClient;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRemoteRmiServer extends Remote {
    void registerRmiClient(IRemoteRmiClient aRmiClient) throws RemoteException;
    void broadcast(IRemoteRmiClient proposer, String aCommand) throws RemoteException;
}
