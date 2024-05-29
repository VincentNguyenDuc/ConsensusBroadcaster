package src.remote.server.ipc.rmi;

import src.remote.client.IRemoteRmiClient;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class RemoteRmiServer implements IRemoteRmiServer {
    final protected List<IRemoteRmiClient> rmiClients = new ArrayList<IRemoteRmiClient>();

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
    public List<IRemoteRmiClient> getClients() throws RemoteException {
        return this.rmiClients;
    }
}
