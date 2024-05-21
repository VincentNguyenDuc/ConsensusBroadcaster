package src.broadcaster.mvc.model;

import src.broadcaster.bean.BroadcastingClientBean;
import src.broadcaster.factory.BeanFactory;
import src.broadcaster.utils.BroadcasterConstants;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;

public abstract class Model implements IModel {

    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    private String command;
    private int result;

    @Override
    public String getCommand() {
        return this.command;
    }

    @Override
    public void setCommand(final String newCommand) {
        this.command = newCommand;
        this.propertyChangeSupport.firePropertyChange(BroadcasterConstants.COMMAND_PROPERTY, null, newCommand);
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
        this.propertyChangeSupport.firePropertyChange(BroadcasterConstants.RESULT_PROPERTY, oldResult, newResult);
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
            final BroadcastingClientBean clientBean = BeanFactory.getClientBean();
            clientBean.getServerProxy().unregisterRmiClient(
                    clientBean.getClientProxy()
            );
        } catch (final RemoteException e) {
            throw new RuntimeException(e);
        }
        System.exit(0);
    }
}
