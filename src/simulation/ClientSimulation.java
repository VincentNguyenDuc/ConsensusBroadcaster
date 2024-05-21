package src.simulation;

import src.bean.BeanFactory;
import src.bean.ConsensusClientBean;
import src.mvc.model.IModel;
import src.remote.client.IRemoteRmiClient;
import src.remote.client.RemoteRmiClient;
import src.remote.server.IRemoteRmiServer;
import src.utils.ArgsProcessor;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ClientSimulation implements IClientSimulation {

    protected IModel model;

    public void start(final String[] args) {
        try {
            this.init(args);
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void init(final String[] args) throws RemoteException, NotBoundException {
        // Look up server proxy from RMI registry
        final String rmiRegistryHost = ArgsProcessor.getRmiRegistryHost(args);
        final int rmiRegistryPort = ArgsProcessor.getRmiRegistryPort(args);
        final String rmiServerName = ArgsProcessor.getServerName(args);
        final Registry rmiRegistry = LocateRegistry.getRegistry(rmiRegistryHost, rmiRegistryPort);

        final IRemoteRmiServer serverProxy = (IRemoteRmiServer) rmiRegistry.lookup(rmiServerName);

        // Initialize and export client proxy
        final IRemoteRmiClient clientProxy = new RemoteRmiClient();
        UnicastRemoteObject.exportObject(clientProxy, 0);

        // Register client proxy to server
        serverProxy.registerRmiClient(clientProxy);

        ConsensusClientBean clientBean = BeanFactory.getClientBean();
        clientBean.setClientProxy(clientProxy);
        clientBean.setServerProxy(serverProxy);
    }

    @Override
    public IModel getModel() {
        return this.model;
    }
}
