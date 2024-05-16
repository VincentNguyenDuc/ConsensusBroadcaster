package src.broadcaster.remote.server;

import src.broadcaster.remote.client.IRemoteRmiClient;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class RemoteRmiServer implements IRemoteRmiServer {
    List<IRemoteRmiClient> rmiClients = new ArrayList<IRemoteRmiClient>();

    @Override
    public void registerRmiClient(IRemoteRmiClient aRmiClient) {
        this.rmiClients.add(aRmiClient);
        System.out.println("Hello from client: " + aRmiClient.toString());
    }

    @Override
    public void broadcast(IRemoteRmiClient proposer, String aCommand) throws RemoteException {
        for (IRemoteRmiClient client: this.rmiClients) {
            if (!proposer.equals(client)) {
                client.rmiReceiveCommand(aCommand);
            }
        }
    }
}
