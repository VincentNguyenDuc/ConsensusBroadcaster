package com.jbroadcast.simulation;

import com.jbroadcast.remote.server.algorithm.IRemoteBroadcastingServer;
import com.jbroadcast.remote.server.algorithm.atomic.AtomicRemoteServer;
import com.jbroadcast.remote.server.algorithm.non_consensus.NonConsensusRemoteServer;
import com.jbroadcast.utils.parser.ArgsParser;
import com.jbroadcast.utils.parser.ParserFactory;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerSimulation extends BaseSimulation {
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
        try {
            super.init(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        final ArgsParser argsParser = ParserFactory.getArgsParser();

        final String rmiRegistryHost = argsParser.getRmiRegistryHost();
        final int rmiRegistryPort = argsParser.getRmiRegistryPort();
        final String atomicServer = argsParser.getAtomicServerName();
        final String nonConsensusServer = argsParser.getNonConsensusServerName();
        final Registry rmiRegistry = LocateRegistry.getRegistry(rmiRegistryHost, rmiRegistryPort);
        final IRemoteBroadcastingServer atomicRemoteServer = new AtomicRemoteServer();
        final IRemoteBroadcastingServer nonConsensusRemoteServer = new NonConsensusRemoteServer();
        UnicastRemoteObject.exportObject(atomicRemoteServer, 0);
        UnicastRemoteObject.exportObject(nonConsensusRemoteServer, 0);
        rmiRegistry.rebind(atomicServer, atomicRemoteServer);
        rmiRegistry.rebind(nonConsensusServer, nonConsensusRemoteServer);
    }
}
