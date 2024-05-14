package broadcaster;

import broadcaster.server.BroadcastingServer;

public class ServerLauncher {

    public static void main(String[] args) {
        new BroadcastingServer().start(args);
    }
}
