package src.remote.server;

import src.remote.client.IRemoteRmiClient;
import src.utils.ConsensusAlgorithm;
import src.utils.IpcMechanism;

import java.rmi.RemoteException;

public class NonConsensusRemoteServer extends RemoteRmiServer implements IRemoteBroadcastingServer {

    @Override
    public void broadcastCommand(final IRemoteRmiClient proposer, final String aCommand) throws RemoteException {
        for (final IRemoteRmiClient client : this.rmiClients) {
            if (!proposer.equals(client)) {
                client.receiveCommand(aCommand);
            }
        }
    }
}
