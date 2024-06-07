package com.examples.counter.launcher;

import com.beust.jcommander.JCommander;
import com.examples.counter.simulation.CounterClientSimulation;
import com.jbroadcast.bean.BeanFactory;
import com.jbroadcast.bean.ConsensusClientBean;
import com.jbroadcast.simulation.ISimulation;
import com.jbroadcast.utils.parser.ArgsParser;
import com.jbroadcast.utils.parser.ParserFactory;

public class CounterClientLauncher {
    public static void main(final String[] args) throws RuntimeException {

        ParserFactory.setArgsParser(ArgsParser.getInstance());

        final ArgsParser argsParser = ParserFactory.getArgsParser();

        JCommander
            .newBuilder()
            .addObject(argsParser)
            .build()
            .parse(args);

        // Initialize simulation
        final ISimulation counterClientSimulation = new CounterClientSimulation();

        // Register simulation to bean
        final ConsensusClientBean clientBean = ConsensusClientBean.getInstance();
        clientBean.setSimulation(counterClientSimulation);

        // Register bean to factory
        BeanFactory.setClientBean(clientBean);

        // Start the simulation
        counterClientSimulation.start();
    }
}
