package src.simulations.counter.bean;

import src.broadcaster.bean.BroadcastingClientBean;
import src.broadcaster.mvc.controller.Controller;
import src.broadcaster.mvc.controller.IController;
import src.broadcaster.mvc.view.IView;
import src.broadcaster.remote.client.ClientOutCoupler;
import src.simulations.counter.mvc.CounterModel;
import src.simulations.counter.mvc.CounterView;

import java.beans.PropertyChangeListener;

public class CounterBroadcastingClientBean extends BroadcastingClientBean {
    protected CounterBroadcastingClientBean() {
    }

    public static BroadcastingClientBean getInstance() {
        if (CLIENT_BEAN == null) {
            CLIENT_BEAN = new CounterBroadcastingClientBean();
        }
        return CLIENT_BEAN;
    }

    @Override
    public void start(final String[] args) {
        super.start(args);
        this.model = new CounterModel();
        // Instantiate the view
        final IView view = new CounterView();
        final PropertyChangeListener outCoupler = new ClientOutCoupler();

        // Make the view an observable of the model
        try {
            this.model.addPropertyChangeListener(view);
            this.model.addPropertyChangeListener(outCoupler);
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }

        // Instantiate the controller
        final IController controller = new Controller(model);
        controller.processCommands();
    }
}
