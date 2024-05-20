package src.broadcaster.factory;

import src.broadcaster.bean.BroadcastingClientBean;
import src.broadcaster.bean.BroadcastingServerBean;

public class BeanFactory {

    private static BroadcastingClientBean clientBean = null;
    private static BroadcastingServerBean serverBean = null;

    public static void setClientBean(BroadcastingClientBean aClientBean) {
        clientBean = aClientBean;
    }

    public static BroadcastingClientBean getClientBean() throws RuntimeException {
        if (clientBean == null) {
            throw new RuntimeException("Client Bean is Null");
        }
        return clientBean;
    }

    public static void setServerBean(BroadcastingServerBean aServerBean) {
        serverBean = aServerBean;
    }

    public static BroadcastingServerBean getServerBean() throws RuntimeException {
        if (serverBean == null) {
            throw new RuntimeException("Server Bean is Null");
        }
        return serverBean;
    }
}
