package src.simulation;

import src.remote.server.IRemoteRmiServer;
import src.remote.server.RemoteRmiServer;
import src.utils.ArgsProcessor;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerSimulation implements IServerSimulation {
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
