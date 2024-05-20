package src.simulations.counter;

import src.broadcaster.bean.BroadcastingServerBean;
import src.broadcaster.factory.BeanFactory;

public class CounterServerLauncher {
    public static void main(String[] args) {
        BeanFactory.setServerBean(BroadcastingServerBean.getInstance());
        BeanFactory.getServerBean().start(args);
    }
}
