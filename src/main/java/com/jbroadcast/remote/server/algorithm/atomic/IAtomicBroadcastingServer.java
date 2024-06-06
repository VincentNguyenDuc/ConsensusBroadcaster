package com.jbroadcast.remote.server.algorithm.atomic;

import com.jbroadcast.remote.server.algorithm.IRemoteBroadcastingServer;

import java.rmi.RemoteException;
import java.util.concurrent.BlockingQueue;

public interface IAtomicBroadcastingServer extends IRemoteBroadcastingServer {
    BlockingQueue<String> getBuffer() throws RemoteException;
}
