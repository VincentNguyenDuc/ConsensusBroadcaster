package com.jbroadcast.simulation;

import com.beust.jcommander.JCommander;
import com.jbroadcast.mvc.model.IModel;
import com.jbroadcast.utils.parser.ArgsParser;
import com.jbroadcast.utils.parser.ParserFactory;

public abstract class BaseSimulation implements ISimulation {
    protected IModel model;

    public IModel getModel() {
        return this.model;
    }

    @Override
    public void init(final String[] args) throws Exception {
        ParserFactory.setArgsParser(ArgsParser.getInstance());
        final ArgsParser argsParser = ParserFactory.getArgsParser();

        final JCommander jct = JCommander
                .newBuilder()
                .addObject(argsParser)
                .build();

        jct.parse(args);

        if (argsParser.isHelp()) {
            jct.usage();
            System.exit(0);
        }
    }
}
