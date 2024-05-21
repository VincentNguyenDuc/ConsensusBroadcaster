package examples.counter;

import examples.counter.bean.CounterClientSimulation;
import src.bean.BeanFactory;
import src.bean.ConsensusClientBean;
import src.simulation.IClientSimulation;

public class CounterClientLauncher {
    public static void main(final String[] args) throws RuntimeException {
        // Initialize simulation
        final IClientSimulation counterClientSimulation = new CounterClientSimulation();

        // Register simulation to bean
        final ConsensusClientBean clientBean = ConsensusClientBean.getInstance();
        clientBean.setClientSimulation(counterClientSimulation);

        // Register bean to factory
        BeanFactory.setClientBean(clientBean);

        // Start the simulation
        counterClientSimulation.start(args);
    }
}
