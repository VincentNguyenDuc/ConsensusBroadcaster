package src.remote.server;

import src.remote.client.IRemoteRmiClient;

import java.rmi.RemoteException;

public interface IRemoteBroadcastingServer extends IRemoteRmiServer {

    void broadcastCommand(IRemoteRmiClient proposer, String aCommand) throws RemoteException;

}
