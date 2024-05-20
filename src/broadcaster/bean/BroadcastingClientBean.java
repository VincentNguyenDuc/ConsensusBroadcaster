package src.broadcaster.bean;

import src.broadcaster.mvc.model.IModel;
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

    protected static BroadcastingClientBean CLIENT_BEAN;
    protected IModel model;
    private IRemoteRmiServer serverProxy;
    private IRemoteRmiClient clientProxy;

    protected BroadcastingClientBean() {
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
        this.clientProxy = new RemoteRmiClient();
        UnicastRemoteObject.exportObject(this.clientProxy, 0);

        // Register client proxy to server
        this.serverProxy.registerRmiClient(this.clientProxy);
    }

    public IRemoteRmiServer getServerProxy() {
        return this.serverProxy;
    }

    public IRemoteRmiClient getClientProxy() {
        return this.clientProxy;
    }

    public IModel getModel() {
        return this.model;
    }
}
