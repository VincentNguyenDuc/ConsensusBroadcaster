package com.jbroadcast.simulation;

import com.jbroadcast.bean.BeanFactory;
import com.jbroadcast.bean.ConsensusClientBean;
import com.jbroadcast.remote.client.IRemoteRmiClient;
import com.jbroadcast.remote.client.RemoteRmiClient;
import com.jbroadcast.remote.server.algorithm.IRemoteBroadcastingServer;
import com.jbroadcast.utils.enums.ConsensusAlgorithm;
import com.jbroadcast.utils.parser.ArgsParser;
import com.jbroadcast.utils.parser.ParserFactory;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ClientSimulation extends BaseSimulation {

    @Override
    public void start(final String[] args) {
        try {
            this.init(args);
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void init(final String[] args) throws RemoteException, NotBoundException {
        try {
            super.init(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        final ArgsParser argsParser = ParserFactory.getArgsParser();

        // Look up server proxy from RMI registry
        final String rmiRegistryHost = argsParser.getRmiRegistryHost();
        final int rmiRegistryPort = argsParser.getRmiRegistryPort();
        final String atomicServerName = argsParser.getAtomicServerName();
        final String nonConsensusServerName = argsParser.getNonConsensusServerName();
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
