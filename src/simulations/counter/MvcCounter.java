package src.simulations.counter;

import src.broadcaster.mvc.controller.Controller;
import src.broadcaster.mvc.controller.IController;
import src.broadcaster.mvc.model.IModel;
import src.broadcaster.mvc.view.IView;
import src.broadcaster.mvc.view.View;

public class MvcCounter {
    public static void main(String[] args) {
        // Instantiate the model
        final IModel model = new CounterModel();

        // Instantiate the view
        final IView view = new View();

        // Make the view an observable of the model
        try {
            model.addPropertyChangeListener(view);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Instantiate the controller
        final IController controller = new Controller(model);
        controller.processCommands();

        System.exit(0);
    }
}
