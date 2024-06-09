package com.jbroadcast.simulation;

import com.jbroadcast.mvc.model.IModel;

public interface ISimulation {

    void start(String[] args);

    void init(String[] args) throws Exception;

    IModel getModel();
}
