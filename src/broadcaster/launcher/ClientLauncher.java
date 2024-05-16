package src.broadcaster.launcher;

import src.broadcaster.bean.BroadcastingClientBean;

public class ClientLauncher {
    public static void main(String[] args) {
        BroadcastingClientBean.getInstance().start(args);
    }
}
