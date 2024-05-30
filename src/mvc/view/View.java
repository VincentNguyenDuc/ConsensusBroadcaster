package src.mvc.view;

import java.beans.PropertyChangeEvent;

public class View implements IView {
    @Override
    public void propertyChange(final PropertyChangeEvent anEvent) {
        System.out.println(anEvent);
    }
}
