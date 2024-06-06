package com.jbroadcast.simulation;

import com.jbroadcast.remote.server.algorithm.IRemoteBroadcastingServer;
import com.jbroadcast.remote.server.algorithm.atomic.AtomicRemoteServer;
import com.jbroadcast.remote.server.algorithm.non_consensus.NonConsensusRemoteServer;
import com.jbroadcast.utils.ArgsParser;

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
        final String rmiRegistryHost = ArgsParser.getRmiRegistryHost(args);
        final int rmiRegistryPort = ArgsParser.getRmiRegistryPort(args);
        final String atomicServer = ArgsParser.getAtomicServerName(args);
        final String nonConsensusServer = ArgsParser.getNonConsensusServerName(args);
        final Registry rmiRegistry = LocateRegistry.getRegistry(rmiRegistryHost, rmiRegistryPort);
        final IRemoteBroadcastingServer atomicRemoteServer = new AtomicRemoteServer();
        final IRemoteBroadcastingServer nonConsensusRemoteServer = new NonConsensusRemoteServer();
        UnicastRemoteObject.exportObject(atomicRemoteServer, 0);
        UnicastRemoteObject.exportObject(nonConsensusRemoteServer, 0);
        rmiRegistry.rebind(atomicServer, atomicRemoteServer);
        rmiRegistry.rebind(nonConsensusServer, nonConsensusRemoteServer);
    }
}
