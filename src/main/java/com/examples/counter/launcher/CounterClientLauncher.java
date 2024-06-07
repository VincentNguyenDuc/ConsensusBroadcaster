package com.examples.counter.launcher;

import com.examples.counter.simulation.CounterClientSimulation;
import com.jbroadcast.bean.BeanFactory;
import com.jbroadcast.bean.ConsensusClientBean;
import com.jbroadcast.simulation.ISimulation;

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
