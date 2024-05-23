package src.simulation;

import src.mvc.model.IModel;

public abstract class BaseSimulation implements ISimulation {
    protected IModel model;

    public IModel getModel() {
        return this.model;
    }
}
