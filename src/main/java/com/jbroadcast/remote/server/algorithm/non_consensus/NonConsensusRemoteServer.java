package com.jbroadcast.remote.server.algorithm.non_consensus;

import com.jbroadcast.bean.BeanFactory;
import com.jbroadcast.remote.client.IRemoteRmiClient;
import com.jbroadcast.remote.server.algorithm.IRemoteBroadcastingServer;
import com.jbroadcast.remote.server.ipc.rmi.RemoteRmiServer;

import java.rmi.RemoteException;

public class NonConsensusRemoteServer extends RemoteRmiServer implements IRemoteBroadcastingServer {

    @Override
    public void broadcastCommand(final IRemoteRmiClient proposer, final String aCommand) throws RemoteException {
        for (final IRemoteRmiClient client : BeanFactory.getServerBean().getClients()) {
            if (!proposer.equals(client)) {
                client.receiveCommand(proposer.toString(), aCommand);
            }
        }
    }
}
