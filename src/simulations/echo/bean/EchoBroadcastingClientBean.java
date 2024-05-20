package src.simulations.echo.bean;

import src.broadcaster.bean.BroadcastingClientBean;
import src.broadcaster.mvc.controller.Controller;
import src.broadcaster.mvc.controller.IController;
import src.broadcaster.mvc.view.IView;
import src.broadcaster.mvc.view.View;
import src.broadcaster.remote.client.ClientOutCoupler;
import src.simulations.echo.mvc.EchoModel;

import java.beans.PropertyChangeListener;

public class EchoBroadcastingClientBean extends BroadcastingClientBean {
    protected EchoBroadcastingClientBean() {}

    public static BroadcastingClientBean getInstance() {
        if (CLIENT_BEAN == null) {
            CLIENT_BEAN = new EchoBroadcastingClientBean();
        }
        return CLIENT_BEAN;
    }

    @Override
    public void start(String[] args) {
        super.start(args);
        this.model = new EchoModel();
        // Instantiate the view
        final IView view = new View();
        final PropertyChangeListener outCoupler = new ClientOutCoupler();

        // Make the view an observable of the model
        try {
            this.model.addPropertyChangeListener(view);
            this.model.addPropertyChangeListener(outCoupler);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Instantiate the controller
        final IController controller = new Controller(model);
        controller.processCommands();
    }
}
