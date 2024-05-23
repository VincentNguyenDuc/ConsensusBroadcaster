package src.remote.server;

import src.remote.client.IRemoteRmiClient;
import src.utils.ConsensusAlgorithm;
import src.utils.IpcMechanism;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class RemoteRmiServer implements IRemoteRmiServer {
    List<IRemoteRmiClient> rmiClients = new ArrayList<IRemoteRmiClient>();

    @Override
    public void registerRmiClient(final IRemoteRmiClient aRmiClient) {
        this.rmiClients.add(aRmiClient);
        System.out.println("Registered: " + aRmiClient);
    }

    @Override
    public void unregisterRmiClient(final IRemoteRmiClient aRmiClient) throws RemoteException {
        this.rmiClients.removeIf(aRmiClient::equals);
        System.out.println("Unregistered: " + aRmiClient);
    }

    @Override
    public void broadcastCommand(final IRemoteRmiClient proposer, final String aCommand) throws RemoteException {
        for (final IRemoteRmiClient client : this.rmiClients) {
            if (!proposer.equals(client)) {
                client.receiveCommand(aCommand);
            }
        }
    }

    @Override
    public void broadcastIpcMechanism(final IRemoteRmiClient proposer, final IpcMechanism mechanism) throws RemoteException {
        for (final IRemoteRmiClient client : this.rmiClients) {
            if (!proposer.equals(client)) {
                client.receiveIpcMechanism(mechanism);
            }
        }
    }

    @Override
    public void broadcastConsensusAlgorithm(final IRemoteRmiClient proposer, final ConsensusAlgorithm algorithm) throws RemoteException {
        for (final IRemoteRmiClient client : this.rmiClients) {
            if (!proposer.equals(client)) {
                client.receiveConsensusAlgorithm(algorithm);
            }
        }
    }
}
