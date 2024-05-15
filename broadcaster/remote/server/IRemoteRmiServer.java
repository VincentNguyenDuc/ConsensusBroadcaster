package broadcaster.remote.server;

import broadcaster.remote.client.IRemoteRmiClient;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRemoteRmiServer extends Remote {
    void registerRmiClient(IRemoteRmiClient aRmiClient) throws RemoteException;
}
