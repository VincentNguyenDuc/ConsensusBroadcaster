package com.jbroadcast.remote.client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRemoteRmiClient extends Remote {
    void receiveCommand(String sender, String aCommand) throws RemoteException;
}
