package broadcaster.server;

import broadcaster.client.IRemoteClient;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRemoteServer extends Remote {
    void registerRmiClient(IRemoteClient aRmiClient) throws RemoteException;
}
