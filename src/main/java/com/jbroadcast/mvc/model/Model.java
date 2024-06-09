package com.jbroadcast.mvc.model;

import com.jbroadcast.bean.BeanFactory;
import com.jbroadcast.bean.ConsensusClientBean;
import com.jbroadcast.utils.Tracer;
import com.jbroadcast.utils.enums.ConsensusAlgorithm;
import com.jbroadcast.utils.enums.IpcMechanism;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;

public abstract class Model<T> implements IModel<T> {

    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    private String command;
    private T result;

    @Override
    public void evaluateCommand(final String aCommand) {

        for (final IpcMechanism mechanism : IpcMechanism.values()) {
            if ((Tracer.IPC_MECHANISM_COMMAND_PREFIX + mechanism.toString().toLowerCase()).equals(aCommand)) {
                this.setIpcMechanism(mechanism);
                return;
            }
        }

        for (final ConsensusAlgorithm algorithm : ConsensusAlgorithm.values()) {
            if ((Tracer.CONSENSUS_ALGORITHM_COMMAND_PREFIX + algorithm.toString().toLowerCase()).equals(aCommand)) {
                this.setConsensusAlgorithm(algorithm);
                return;
            }
        }
    }

    @Override
    public String getCommand() {
        return this.command;
    }

    @Override
    public void setCommand(final String newCommand) {
        this.command = newCommand;
        this.propertyChangeSupport.firePropertyChange(Tracer.COMMAND_PROPERTY, null, newCommand);
        if (BeanFactory.getClientBean().getLocalProcessing()) {
            this.evaluateCommand(newCommand);
        }
    }

    @Override
    public T getResult() {
        return this.result;
    }

    @Override
    public void setResult(final T newResult) {
        final T oldResult = this.result;
        this.result = newResult;
        this.propertyChangeSupport.firePropertyChange(Tracer.RESULT_PROPERTY, oldResult, newResult);
    }

    public void setConsensusAlgorithm(final ConsensusAlgorithm aConsensusAlgorithm) {
        this.propertyChangeSupport.firePropertyChange(Tracer.CONSENSUS_ALGORITHM_PROPERTY, null, aConsensusAlgorithm);
        BeanFactory.getClientBean().setConsensusAlgorithm(aConsensusAlgorithm);
        switch (aConsensusAlgorithm) {
            case NON_CONSENSUS: {
                this.setLocalProcessing(true);
                break;
            }
            case ATOMIC: {
                this.setLocalProcessing(false);
                break;
            }
            default: {
                throw new IllegalArgumentException("Unexpected value: " + aConsensusAlgorithm);
            }
        }
    }

    public void setIpcMechanism(final IpcMechanism anIpcMechanism) {
        this.propertyChangeSupport.firePropertyChange(Tracer.IPC_MECHANISM_PROPERTY, null, anIpcMechanism);
        BeanFactory.getClientBean().setIpcMechanism(anIpcMechanism);
    }

    public void setLocalProcessing(final boolean newValue) {
        this.propertyChangeSupport.firePropertyChange(Tracer.LOCAL_PROCESSING_PROPERTY, null, newValue);
        BeanFactory.getClientBean().setLocalProcessing(newValue);
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
            clientBean.getServerProxy().unregisterRmiClient(clientBean.getClientProxy());
        } catch (final RemoteException e) {
            throw new RuntimeException(e);
        }
        System.exit(0);
    }
}
