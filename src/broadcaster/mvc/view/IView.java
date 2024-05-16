package src.broadcaster.mvc.view;

import src.broadcaster.remote.client.IRemoteRmiClient;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public interface IView extends PropertyChangeListener {
    void propertyChange(PropertyChangeEvent anEvent);
}
