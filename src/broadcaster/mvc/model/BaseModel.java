package src.broadcaster.mvc.model;

import src.simulations.counter.ICounter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract class BaseModel implements IModel, ICounter {

    private String command = "";
    private int result;
    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    protected abstract void evaluateCommand();

    @Override
    public void setCommand(final String newCommand) {
        this.command = newCommand;
        this.propertyChangeSupport.firePropertyChange("NewCommand", null, newCommand);
        this.evaluateCommand();
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
    public void setResult(final int aResult) {
        this.result = aResult;
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
