package src.examples.counter;

import src.broadcaster.factory.BeanFactory;
import src.examples.counter.bean.CounterBroadcastingClientBean;

public class CounterClientLauncher {
    public static void main(final String[] args) throws RuntimeException {
        BeanFactory.setClientBean(CounterBroadcastingClientBean.getInstance());
        BeanFactory.getClientBean().start(args);
    }
}
