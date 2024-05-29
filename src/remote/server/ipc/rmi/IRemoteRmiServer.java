package src.remote.server.ipc.rmi;

import src.remote.client.IRemoteRmiClient;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IRemoteRmiServer extends Remote {
    void registerRmiClient(IRemoteRmiClient aRmiClient) throws RemoteException;

    void unregisterRmiClient(IRemoteRmiClient aRmiClient) throws RemoteException;

    List<IRemoteRmiClient> getClients() throws RemoteException;
}
