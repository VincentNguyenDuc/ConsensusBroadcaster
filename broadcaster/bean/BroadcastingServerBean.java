package broadcaster.bean;

import broadcaster.remote.server.IRemoteRmiServer;
import broadcaster.remote.server.RemoteRmiServer;
import broadcaster.utils.ArgsProcessor;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class BroadcastingServerBean extends BroadcastingBean {

    private static final BroadcastingServerBean SERVER_BEAN = new BroadcastingServerBean();

    private BroadcastingServerBean() {}

    public static BroadcastingServerBean getInstance() {
        return SERVER_BEAN;
    }

    @Override
    public void start(String[] args) {
        try {
            this.init(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void init(String[] args) throws RemoteException {
        final String rmiRegistryHost = ArgsProcessor.getRmiRegistryHost(args);
        final int rmiRegistryPort = ArgsProcessor.getRmiRegistryPort(args);
        final String rmiServerName = ArgsProcessor.getServerName(args);
        final Registry rmiRegistry = LocateRegistry.getRegistry(rmiRegistryHost, rmiRegistryPort);
        final IRemoteRmiServer remoteServer = new RemoteRmiServer();
        UnicastRemoteObject.exportObject(remoteServer, 0);
        rmiRegistry.rebind(rmiServerName, remoteServer);
    }
}
