package examples.counter.launcher;

import src.bean.BeanFactory;
import src.bean.ConsensusServerBean;
import src.simulation.ISimulation;
import src.simulation.ServerSimulation;

public class CounterServerLauncher {
    public static void main(final String[] args) {

        // Initialize simulation
        final ISimulation counterServerSimulation = new ServerSimulation();

        // Register simulation to bean
        final ConsensusServerBean consensusServerBean = ConsensusServerBean.getInstance();
        consensusServerBean.setSimulation(counterServerSimulation);

        // Register bean to factory
        BeanFactory.setServerBean(ConsensusServerBean.getInstance());

        // Start the simulation
        counterServerSimulation.start(args);

    }
}
