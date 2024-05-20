package src.simulations.echo;

import src.broadcaster.factory.BeanFactory;
import src.simulations.echo.bean.EchoBroadcastingClientBean;

public class EchoClientLauncher {
    public static void main(final String[] args) throws RuntimeException {
        BeanFactory.setClientBean(EchoBroadcastingClientBean.getInstance());
        BeanFactory.getClientBean().start(args);
    }
}
