package com.jbroadcast.simulation;

import com.jbroadcast.mvc.model.IModel;

public interface ISimulation {

    void start();

    void init() throws Exception;

    IModel getModel();
}
