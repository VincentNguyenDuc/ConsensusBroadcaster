package src.remote.server.algorithm.atomic;

import src.utils.Tracer;

import java.rmi.RemoteException;

public class BufferRunnable implements Runnable {

    private final AtomicRemoteServer remoteServer;

    public BufferRunnable(final AtomicRemoteServer remoteServer) {
        this.remoteServer = remoteServer;
    }

    @Override
    public void run() {
        while (true) {
            try {
                final String command = this.remoteServer.commandsBoundedBuffer.take();
                this.remoteServer.getClients().forEach(client -> {
                    try {
                        Tracer.sendCommand(this.toString(), client.toString(), command);
                        client.receiveCommand(this.toString(), command);
                    } catch (final RemoteException e) {
                        throw new RuntimeException(e);
                    }
                });
            } catch (final InterruptedException | RemoteException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
