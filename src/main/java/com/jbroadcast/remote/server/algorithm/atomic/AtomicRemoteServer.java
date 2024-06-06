package com.jbroadcast.remote.server.algorithm.atomic;

import com.jbroadcast.remote.client.IRemoteRmiClient;
import com.jbroadcast.remote.server.ipc.rmi.RemoteRmiServer;
import com.jbroadcast.utils.Tracer;

import java.rmi.RemoteException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class AtomicRemoteServer extends RemoteRmiServer implements IAtomicBroadcastingServer {

    private final int BUFFER_SIZE = 10;
    private final BlockingQueue<String> commandsBoundedBuffer = new ArrayBlockingQueue<String>(this.BUFFER_SIZE);
    private final Thread aThread = new Thread(new BufferRunnable(this));

    public AtomicRemoteServer() {
        this.aThread.start();
    }

    @Override
    public void broadcastCommand(final IRemoteRmiClient proposer, final String aCommand) throws RemoteException {
        Tracer.receiveCommand(proposer.toString(), this.toString(), aCommand);
        this.commandsBoundedBuffer.add(aCommand);
        Tracer.sendCommand(this.toString(), this.aThread.toString(), aCommand);
    }

    @Override
    public BlockingQueue<String> getBuffer() throws RemoteException {
        return this.commandsBoundedBuffer;
    }
}
