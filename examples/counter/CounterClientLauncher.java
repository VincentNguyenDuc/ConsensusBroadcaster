package examples.counter;

import examples.counter.simulation.CounterClientSimulation;
import src.bean.BeanFactory;
import src.bean.ConsensusClientBean;
import src.simulation.ISimulation;

public class CounterClientLauncher {
    public static void main(final String[] args) throws RuntimeException {
        // Initialize simulation
        final ISimulation counterClientSimulation = new CounterClientSimulation();

        // Register simulation to bean
        final ConsensusClientBean clientBean = ConsensusClientBean.getInstance();
        clientBean.setSimulation(counterClientSimulation);

        // Register bean to factory
        BeanFactory.setClientBean(clientBean);

        // Start the simulation
        counterClientSimulation.start(args);
    }
}
