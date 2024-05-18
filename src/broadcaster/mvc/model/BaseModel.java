package src.broadcaster.mvc.model;

import src.broadcaster.utils.BroadcasterConstants;
import src.simulations.counter.ICounter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract class BaseModel implements IModel, ICounter {

    private String command;
    private int result;
    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    @Override
    public void setCommand(final String newCommand) {
        this.command = newCommand;
        this.propertyChangeSupport.firePropertyChange(BroadcasterConstants.COMMAND_PROPERTY, null, newCommand);
        this.evaluateCommand(newCommand);
    }

    @Override
    public String getCommand() {
        return this.command;
    }

    @Override
    public int getResult() {
        return this.result;
    }

    @Override
    public void setResult(final int newResult) {
        final int oldResult = this.result;
        this.result = newResult;
        this.propertyChangeSupport.firePropertyChange(BroadcasterConstants.RESULT, oldResult, newResult);
    }

    @Override
    public PropertyChangeSupport getPropertyChangeSupport() {
        return this.propertyChangeSupport;
    }

    public void addPropertyChangeListener(final PropertyChangeListener aListener) {
        this.propertyChangeSupport.addPropertyChangeListener(aListener);
    }

    public void removePropertyChangeListener(final PropertyChangeListener aListener) {
        this.propertyChangeSupport.removePropertyChangeListener(aListener);
    }
}
