package src.simulation;

import src.mvc.model.IModel;

public interface ISimulation {

    void start(String[] args);

    void init(String[] args) throws Exception;

    IModel getModel();
}
