package broadcaster.server;

import broadcaster.utils.ArgsProcessor;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class BroadcastingServer {

    public final static String SERVER = "SERVER";

    public void start(String[] args) throws RemoteException {
        this.init(args);
    }

    public void init(String[] args) throws RemoteException {
        final String rmiRegistryHost = ArgsProcessor.getRmiRegistryHost(args);
        final int rmiRegistryPort = ArgsProcessor.getRmiRegistryPort(args);
        final Registry rmiRegistry = LocateRegistry.getRegistry(rmiRegistryHost, rmiRegistryPort);
        final IRemoteServer remoteServer = new RemoteServer();
        UnicastRemoteObject.exportObject(remoteServer, 0);
        rmiRegistry.rebind(SERVER, remoteServer);
    }
}
