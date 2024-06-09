package com.examples.counter.launcher;

import com.jbroadcast.bean.BeanFactory;
import com.jbroadcast.bean.ConsensusServerBean;
import com.jbroadcast.simulation.ISimulation;
import com.jbroadcast.simulation.ServerSimulation;

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
