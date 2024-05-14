package broadcaster;

import broadcaster.client.BroadcastingClient;

public class ClientLauncher {
    public static void main(String[] args) {
        new BroadcastingClient().start(args);
    }
}
