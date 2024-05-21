package src.mvc.model;

import src.bean.BeanFactory;
import src.bean.ConsensusClientBean;
import src.utils.BroadcasterTracer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;

public abstract class Model implements IModel {

    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    private String command;
    private int result;

    @Override
    public void evaluateCommand(final String aCommand) {

    }

    @Override
    public String getCommand() {
        return this.command;
    }

    @Override
    public void setCommand(final String newCommand) {
        this.command = newCommand;
        this.propertyChangeSupport.firePropertyChange(BroadcasterTracer.COMMAND_PROPERTY, null, newCommand);
        this.evaluateCommand(newCommand);
    }

    @Override
    public int getResult() {
        return this.result;
    }

    @Override
    public void setResult(final int newResult) {
        final int oldResult = this.result;
        this.result = newResult;
        this.propertyChangeSupport.firePropertyChange(BroadcasterTracer.RESULT_PROPERTY, oldResult, newResult);
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

    @Override
    public void terminate() {
        try {
            final ConsensusClientBean clientBean = BeanFactory.getClientBean();
            clientBean.getServerProxy().unregisterRmiClient(
                    clientBean.getClientProxy()
            );
        } catch (final RemoteException e) {
            throw new RuntimeException(e);
        }
        System.exit(0);
    }
}
