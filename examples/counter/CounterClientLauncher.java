package examples.counter;

import examples.counter.bean.CounterBroadcastingClientBean;
import src.factory.BeanFactory;

public class CounterClientLauncher {
    public static void main(final String[] args) throws RuntimeException {
        BeanFactory.setClientBean(CounterBroadcastingClientBean.getInstance());
        BeanFactory.getClientBean().start(args);
    }
}
