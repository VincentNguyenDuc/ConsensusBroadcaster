package broadcaster.server;

import broadcaster.client.IRemoteClient;

import java.util.ArrayList;
import java.util.List;

public class RemoteServer implements IRemoteServer {
    List<IRemoteClient> rmiClients = new ArrayList<IRemoteClient>();

    @Override
    public void registerRmiClient(IRemoteClient aRmiClient) {
        this.rmiClients.add(aRmiClient);
        System.out.println("Hello from client: " + aRmiClient.toString());
    }
}
