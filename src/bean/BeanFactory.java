package src.bean;

public class BeanFactory {

    private static ConsensusClientBean clientBean = null;
    private static ConsensusServerBean serverBean = null;

    public static ConsensusClientBean getClientBean() throws RuntimeException {
        if (clientBean == null) {
            throw new RuntimeException("Client Bean is Null");
        }
        return clientBean;
    }

    public static void setClientBean(final ConsensusClientBean aClientBean) {
        clientBean = aClientBean;
    }

    public static ConsensusServerBean getServerBean() throws RuntimeException {
        if (serverBean == null) {
            throw new RuntimeException("Server Bean is Null");
        }
        return serverBean;
    }

    public static void setServerBean(final ConsensusServerBean aServerBean) {
        serverBean = aServerBean;
    }
}
