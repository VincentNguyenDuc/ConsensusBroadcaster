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

public class EchoerClientSimulation extends ClientSimulation {

    @Override
    public void start(final String[] args) {
        super.start(args);
    }

    @Override
    public void init(final String[] args) throws NotBoundException, RemoteException {
        super.init(args);
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
