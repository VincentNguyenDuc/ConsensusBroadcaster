package src.remote.server;

import src.remote.client.IRemoteRmiClient;
import src.utils.ConsensusAlgorithm;
import src.utils.IpcMechanism;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRemoteRmiServer extends Remote {
    void registerRmiClient(IRemoteRmiClient aRmiClient) throws RemoteException;

    void unregisterRmiClient(IRemoteRmiClient aRmiClient) throws RemoteException;

    void broadcastCommand(IRemoteRmiClient proposer, String aCommand) throws RemoteException;

    void broadcastIpcMechanism(IRemoteRmiClient proposer, IpcMechanism mechanism) throws RemoteException;

    void broadcastConsensusAlgorithm(IRemoteRmiClient proposer, ConsensusAlgorithm algorithm) throws RemoteException;
}
