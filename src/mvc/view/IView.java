package src.mvc.view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public interface IView extends PropertyChangeListener {
    void propertyChange(PropertyChangeEvent anEvent);
}