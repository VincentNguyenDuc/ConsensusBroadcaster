package src.remote.server.algorithm.non_consensus;

import src.bean.BeanFactory;
import src.remote.client.IRemoteRmiClient;
import src.remote.server.algorithm.IRemoteBroadcastingServer;
import src.remote.server.ipc.rmi.RemoteRmiServer;

import java.rmi.RemoteException;

public class NonConsensusRemoteServer extends RemoteRmiServer implements IRemoteBroadcastingServer {

    @Override
    public void broadcastCommand(final IRemoteRmiClient proposer, final String aCommand) throws RemoteException {
        for (final IRemoteRmiClient client : BeanFactory.getServerBean().getClients()) {
            if (!proposer.equals(client)) {
                client.receiveCommand(proposer.toString(), aCommand);
            }
        }
    }
}
