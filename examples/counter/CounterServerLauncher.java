package examples.counter;

import src.bean.BeanFactory;
import src.bean.ConsensusServerBean;
import src.simulation.IServerSimulation;
import src.simulation.ServerSimulation;

public class CounterServerLauncher {
    public static void main(final String[] args) {

        // Initialize simulation
        final IServerSimulation counterServerSimulation = new ServerSimulation();

        // Register simulation to bean
        final ConsensusServerBean consensusServerBean = ConsensusServerBean.getInstance();
        consensusServerBean.setServerSimulation(counterServerSimulation);

        // Register bean to factory
        BeanFactory.setServerBean(ConsensusServerBean.getInstance());

        // Start the simulation
        counterServerSimulation.start(args);

    }
}
