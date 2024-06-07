package com.examples.counter.simulation;

import com.examples.counter.mvc.CounterModel;
import com.jbroadcast.mvc.controller.Controller;
import com.jbroadcast.mvc.controller.IController;
import com.jbroadcast.mvc.view.IView;
import com.jbroadcast.mvc.view.View;
import com.jbroadcast.remote.client.ClientOutCoupler;
import com.jbroadcast.simulation.ClientSimulation;

import java.beans.PropertyChangeListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class CounterClientSimulation extends ClientSimulation {
    public CounterClientSimulation() {
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void init() throws NotBoundException, RemoteException {
        super.init();
        this.model = new CounterModel();

        // Instantiate the view
        final IView view = new View();
        final PropertyChangeListener outCoupler = new ClientOutCoupler();

        // Add listeners to model
        try {
            this.model.addPropertyChangeListener(view);
            this.model.addPropertyChangeListener(outCoupler);
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }

        // Instantiate the controller
        final IController controller = new Controller(this.model);
        controller.processCommands();
    }

}
