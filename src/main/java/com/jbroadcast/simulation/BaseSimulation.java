package com.jbroadcast.simulation;

import com.jbroadcast.mvc.model.IModel;

public abstract class BaseSimulation implements ISimulation {
    protected IModel model;

    public IModel getModel() {
        return this.model;
    }
}
