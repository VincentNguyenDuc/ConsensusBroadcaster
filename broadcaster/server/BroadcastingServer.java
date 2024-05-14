package broadcaster.server;

import broadcaster.bean.BroadcastingBean;
import broadcaster.utils.ArgsProcessor;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class BroadcastingServer extends BroadcastingBean {

    public final static String SERVER = "SERVER";

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
        final Registry rmiRegistry = LocateRegistry.getRegistry(rmiRegistryHost, rmiRegistryPort);
        final IRemoteServer remoteServer = new RemoteServer();
        UnicastRemoteObject.exportObject(remoteServer, 0);
        rmiRegistry.rebind(SERVER, remoteServer);
    }
}
