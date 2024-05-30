package src.remote.server.algorithm;

import src.remote.client.IRemoteRmiClient;
import src.remote.server.ipc.rmi.IRemoteRmiServer;

import java.rmi.RemoteException;

public interface IRemoteBroadcastingServer extends IRemoteRmiServer {

    void broadcastCommand(IRemoteRmiClient proposer, String aCommand) throws RemoteException;

}
