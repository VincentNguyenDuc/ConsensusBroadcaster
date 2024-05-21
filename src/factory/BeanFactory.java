package src.factory;

import src.bean.BroadcastingClientBean;
import src.bean.BroadcastingServerBean;

public class BeanFactory {

    private static BroadcastingClientBean clientBean = null;
    private static BroadcastingServerBean serverBean = null;

    public static BroadcastingClientBean getClientBean() throws RuntimeException {
        if (clientBean == null) {
            throw new RuntimeException("Client Bean is Null");
        }
        return clientBean;
    }

    public static void setClientBean(final BroadcastingClientBean aClientBean) {
        clientBean = aClientBean;
    }

    public static BroadcastingServerBean getServerBean() throws RuntimeException {
        if (serverBean == null) {
            throw new RuntimeException("Server Bean is Null");
        }
        return serverBean;
    }

    public static void setServerBean(final BroadcastingServerBean aServerBean) {
        serverBean = aServerBean;
    }
}
