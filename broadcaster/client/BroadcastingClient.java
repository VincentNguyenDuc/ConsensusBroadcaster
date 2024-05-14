package broadcaster.client;

import broadcaster.bean.BroadcastingBean;
import broadcaster.server.BroadcastingServer;
import broadcaster.server.IRemoteServer;
import broadcaster.utils.ArgsProcessor;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class BroadcastingClient extends BroadcastingBean {

    public void start(String[] args) {
        try {
            this.init(args);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void init(String[] args) throws RemoteException, NotBoundException {
        // Look up server proxy from RMI registry
        final String rmiRegistryHost = ArgsProcessor.getRmiRegistryHost(args);
        final int rmiRegistryPort = ArgsProcessor.getRmiRegistryPort(args);
        final Registry rmiRegistry = LocateRegistry.getRegistry(rmiRegistryHost, rmiRegistryPort);
        final IRemoteServer serverProxy = (IRemoteServer) rmiRegistry.lookup(BroadcastingServer.SERVER);

        // Initialize and export client proxy
        final IRemoteClient aRmiClient = new RemoteClient();
        UnicastRemoteObject.exportObject(aRmiClient, 0);

        // Register client proxy to server
        serverProxy.registerRmiClient(aRmiClient);
    }
}
