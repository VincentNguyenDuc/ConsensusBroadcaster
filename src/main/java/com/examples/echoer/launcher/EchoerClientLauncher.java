package com.examples.echoer.launcher;

import com.examples.echoer.simulation.EchoerClientSimulation;
import com.jbroadcast.bean.BeanFactory;
import com.jbroadcast.bean.ConsensusClientBean;
import com.jbroadcast.simulation.ISimulation;

public class EchoerClientLauncher {
    public static void main(final String[] args) throws RuntimeException {

        // Initialize simulation
        final ISimulation echoerClientSimulation = new EchoerClientSimulation();

        // Register simulation to bean
        final ConsensusClientBean clientBean = ConsensusClientBean.getInstance();
        clientBean.setSimulation(echoerClientSimulation);

        // Register bean to factory
        BeanFactory.setClientBean(clientBean);

        // Start the simulation
        echoerClientSimulation.start(args);
    }
}