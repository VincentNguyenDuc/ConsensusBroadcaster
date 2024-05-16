package src.broadcaster.bean;

import src.broadcaster.remote.client.IRemoteRmiClient;
import src.broadcaster.remote.client.RemoteRmiClient;
import src.broadcaster.remote.server.IRemoteRmiServer;
import src.broadcaster.utils.ArgsProcessor;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class BroadcastingClientBean extends BroadcastingBean {

    private static final BroadcastingClientBean CLIENT_BEAN = new BroadcastingClientBean();
    private IRemoteRmiServer serverProxy;

    private BroadcastingClientBean() {
    }

    public static BroadcastingClientBean getInstance() {
        return CLIENT_BEAN;
    }

    public void start(String[] args) {
        try {
            this.init(args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    protected void init(String[] args) throws RemoteException, NotBoundException {
        // Look up server proxy from RMI registry
        final String rmiRegistryHost = ArgsProcessor.getRmiRegistryHost(args);
        final int rmiRegistryPort = ArgsProcessor.getRmiRegistryPort(args);
        final String rmiServerName = ArgsProcessor.getServerName(args);
        final Registry rmiRegistry = LocateRegistry.getRegistry(rmiRegistryHost, rmiRegistryPort);
        this.serverProxy = (IRemoteRmiServer) rmiRegistry.lookup(rmiServerName);

        // Initialize and export client proxy
        final IRemoteRmiClient aRmiClient = new RemoteRmiClient();
        UnicastRemoteObject.exportObject(aRmiClient, 0);

        // Register client proxy to server
        this.serverProxy.registerRmiClient(aRmiClient);
    }

    public IRemoteRmiServer getServerProxy() {
        return this.serverProxy;
    }
}
