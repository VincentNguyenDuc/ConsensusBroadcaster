package src.bean;

import src.remote.server.IRemoteRmiServer;
import src.remote.server.RemoteRmiServer;
import src.utils.ArgsProcessor;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class BroadcastingServerBean implements IBroadcastingBean {

    protected static BroadcastingServerBean SERVER_BEAN;

    protected BroadcastingServerBean() {
    }

    public static BroadcastingServerBean getInstance() {
        if (SERVER_BEAN == null) {
            SERVER_BEAN = new BroadcastingServerBean();
        }
        return SERVER_BEAN;
    }

    @Override
    public void start(final String[] args) {
        try {
            this.init(args);
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void init(final String[] args) throws RemoteException {
        final String rmiRegistryHost = ArgsProcessor.getRmiRegistryHost(args);
        final int rmiRegistryPort = ArgsProcessor.getRmiRegistryPort(args);
        final String rmiServerName = ArgsProcessor.getServerName(args);
        final Registry rmiRegistry = LocateRegistry.getRegistry(rmiRegistryHost, rmiRegistryPort);
        final IRemoteRmiServer remoteServer = new RemoteRmiServer();
        UnicastRemoteObject.exportObject(remoteServer, 0);
        rmiRegistry.rebind(rmiServerName, remoteServer);
    }
}
