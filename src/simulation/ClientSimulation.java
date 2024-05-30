package src.simulation;

import src.bean.BeanFactory;
import src.bean.ConsensusClientBean;
import src.remote.client.IRemoteRmiClient;
import src.remote.client.RemoteRmiClient;
import src.remote.server.algorithm.IRemoteBroadcastingServer;
import src.utils.ArgsParser;
import src.utils.ConsensusAlgorithm;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ClientSimulation extends BaseSimulation {


    public void start(final String[] args) {
        try {
            this.init(args);
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void init(final String[] args) throws RemoteException, NotBoundException {
        // Look up server proxy from RMI registry
        final String rmiRegistryHost = ArgsParser.getRmiRegistryHost(args);
        final int rmiRegistryPort = ArgsParser.getRmiRegistryPort(args);
        final String atomicServerName = ArgsParser.getAtomicServerName(args);
        final String nonConsensusServerName = ArgsParser.getNonConsensusServerName(args);
        final Registry rmiRegistry = LocateRegistry.getRegistry(rmiRegistryHost, rmiRegistryPort);

        final IRemoteBroadcastingServer atomicServerProxy = (IRemoteBroadcastingServer) rmiRegistry.lookup(atomicServerName);
        final IRemoteBroadcastingServer nonConsensusServerProxy = (IRemoteBroadcastingServer) rmiRegistry.lookup(nonConsensusServerName);

        // Initialize and export client proxy
        final IRemoteRmiClient clientProxy = new RemoteRmiClient();
        UnicastRemoteObject.exportObject(clientProxy, 0);

        // Register client proxy to server
        atomicServerProxy.registerRmiClient(clientProxy);

        final ConsensusClientBean clientBean = BeanFactory.getClientBean();
        clientBean.setClientProxy(clientProxy);
        clientBean.setServerProxy(ConsensusAlgorithm.ATOMIC, atomicServerProxy);
        clientBean.setServerProxy(ConsensusAlgorithm.NON_CONSENSUS, nonConsensusServerProxy);
    }

}
