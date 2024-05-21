package examples.counter.bean;

import examples.counter.mvc.CounterModel;
import examples.counter.mvc.CounterView;
import src.bean.BroadcastingClientBean;
import src.mvc.controller.Controller;
import src.mvc.controller.IController;
import src.mvc.view.IView;
import src.remote.client.ClientOutCoupler;

import java.beans.PropertyChangeListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

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
    public void init(final String[] args) throws NotBoundException, RemoteException {
        super.init(args);
        this.model = new CounterModel();

        // Instantiate the view
        final IView view = new CounterView();
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
