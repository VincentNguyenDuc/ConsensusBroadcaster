package src.simulation;

import src.mvc.model.IModel;

public interface IClientSimulation extends ISimulation {
    IModel getModel();
}
