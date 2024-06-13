package com.examples.echoer.launcher;

import com.jbroadcast.bean.BeanFactory;
import com.jbroadcast.bean.ConsensusServerBean;
import com.jbroadcast.simulation.ISimulation;
import com.jbroadcast.simulation.ServerSimulation;

public class EchoerServerLauncher {
    public static void main(final String[] args) {

        // Initialize simulation
        final ISimulation echoerServerSimulation = new ServerSimulation();

        // Register simulation to bean
        final ConsensusServerBean consensusServerBean = ConsensusServerBean.getInstance();
        consensusServerBean.setSimulation(echoerServerSimulation);

        // Register bean to factory
        BeanFactory.setServerBean(ConsensusServerBean.getInstance());

        // Start the simulation
        echoerServerSimulation.start(args);
    }
}