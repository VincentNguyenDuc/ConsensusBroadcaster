package src.remote.client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRemoteRmiClient extends Remote {
    void rmiReceiveCommand(String aCommand) throws RemoteException;
}
