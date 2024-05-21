package examples.counter;

import src.bean.BroadcastingServerBean;
import src.factory.BeanFactory;

public class CounterServerLauncher {
    public static void main(final String[] args) {
        BeanFactory.setServerBean(BroadcastingServerBean.getInstance());
        BeanFactory.getServerBean().start(args);
    }
}
