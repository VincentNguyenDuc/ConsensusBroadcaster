package broadcaster.server;

import java.rmi.RemoteException;

public class ServerLauncher {

    public static void main(String[] args) throws RemoteException {
        new BroadcastingServer().start(args);
    }
}
