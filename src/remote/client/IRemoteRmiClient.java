package src.remote.client;

import src.utils.ConsensusAlgorithm;
import src.utils.IpcMechanism;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRemoteRmiClient extends Remote {
    void receiveCommand(String aCommand) throws RemoteException;

    void receiveIpcMechanism(IpcMechanism mechanism) throws RemoteException;

    void receiveConsensusAlgorithm(ConsensusAlgorithm algorithm) throws RemoteException;
}
