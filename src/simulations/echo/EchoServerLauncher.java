package src.simulations.echo;

import src.broadcaster.bean.BroadcastingServerBean;
import src.broadcaster.factory.BeanFactory;

public class EchoServerLauncher {
    public static void main(final String[] args) throws RuntimeException {
        BeanFactory.setServerBean(BroadcastingServerBean.getInstance());
        BeanFactory.getServerBean().start(args);
    }
}
