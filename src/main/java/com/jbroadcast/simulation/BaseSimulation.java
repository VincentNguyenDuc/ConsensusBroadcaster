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

        JCommander
            .newBuilder()
            .addObject(ParserFactory.getArgsParser())
            .build()
            .parse(args);
    }
}
