package com.jbroadcast.remote.server.algorithm;

import com.jbroadcast.remote.client.IRemoteRmiClient;
import com.jbroadcast.remote.server.ipc.rmi.IRemoteRmiServer;

import java.rmi.RemoteException;

public interface IRemoteBroadcastingServer extends IRemoteRmiServer {

    void broadcastCommand(IRemoteRmiClient proposer, String aCommand) throws RemoteException;

}
