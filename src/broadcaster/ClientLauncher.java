package src.broadcaster;

import src.broadcaster.bean.BroadcastingClientBean;

public class ClientLauncher {
    public static void main(String[] args) {
        BroadcastingClientBean.getInstance().start(args);
    }
}
