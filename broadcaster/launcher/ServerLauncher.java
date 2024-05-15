package broadcaster.launcher;

import broadcaster.bean.BroadcastingServerBean;

public class ServerLauncher {

    public static void main(String[] args) {
        BroadcastingServerBean.getInstance().start(args);
    }
}
