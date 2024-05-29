package src.remote.server.algorithm.atomic;

import src.remote.client.IRemoteRmiClient;
import src.remote.server.algorithm.IRemoteBroadcastingServer;
import src.remote.server.ipc.rmi.RemoteRmiServer;
import src.utils.Tracer;

import java.rmi.RemoteException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class AtomicRemoteServer extends RemoteRmiServer implements IRemoteBroadcastingServer {

    public static final int BUFFER_SIZE = 10;
    public final BlockingQueue<String> commandsBoundedBuffer = new ArrayBlockingQueue<String>(BUFFER_SIZE);
    public final Thread aThread = new Thread(new BufferRunnable(this));

    public AtomicRemoteServer() {
        this.aThread.start();
    }

    @Override
    public void broadcastCommand(final IRemoteRmiClient proposer, final String aCommand) throws RemoteException {
        Tracer.receiveCommand(proposer.toString(), this.toString(), aCommand);
        this.commandsBoundedBuffer.add(aCommand);
        Tracer.sendCommand(this.toString(), this.aThread.toString(), aCommand);
    }
}
