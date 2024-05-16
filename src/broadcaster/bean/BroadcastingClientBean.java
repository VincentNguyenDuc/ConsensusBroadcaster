package src.broadcaster.bean;

import src.broadcaster.mvc.controller.Controller;
import src.broadcaster.mvc.controller.IController;
import src.broadcaster.mvc.model.IModel;
import src.broadcaster.mvc.view.IView;
import src.broadcaster.mvc.view.View;
import src.broadcaster.remote.client.IRemoteRmiClient;
import src.broadcaster.remote.client.RemoteRmiClient;
import src.broadcaster.remote.server.IRemoteRmiServer;
import src.broadcaster.utils.ArgsProcessor;
import src.simulations.counter.CounterModel;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class BroadcastingClientBean extends BroadcastingBean {

    private static final BroadcastingClientBean CLIENT_BEAN = new BroadcastingClientBean();
    private IRemoteRmiServer serverProxy;
    private IRemoteRmiClient clientProxy;
    private IModel model;

    private BroadcastingClientBean() {}

    public static BroadcastingClientBean getInstance() {
        return CLIENT_BEAN;
    }

    public void start(String[] args) {
        try {
            this.init(args);
            this.model = new CounterModel();

            // Instantiate the view
            View view = new View();

            // Make the view an observable of the model
            try {
                this.model.addPropertyChangeListener(view);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            // Instantiate the controller
            final IController controller = new Controller(model);
            controller.processCommands();
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
