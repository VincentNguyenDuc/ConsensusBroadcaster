package src.mvc.model;

import src.bean.BeanFactory;
import src.bean.ConsensusClientBean;
import src.utils.ConsensusAlgorithm;
import src.utils.IpcMechanism;
import src.utils.Tracer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;

public abstract class Model implements IModel {

    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    private String command;
    private int result;

    @Override
    public void evaluateCommand(final String aCommand) {
        // TODO: Parse commands instead of switch-case
        switch (aCommand) {
            case "i nio": this.setIpcMechanism(IpcMechanism.NIO);
            case "i rmi": this.setIpcMechanism(IpcMechanism.RMI);
        }

        switch (aCommand) {
            case "a non_consensus": this.setConsensusAlgorithm(ConsensusAlgorithm.NON_CONSENSUS);
            case "a atomic": this.setConsensusAlgorithm(ConsensusAlgorithm.ATOMIC);
            case "a two_phase": this.setConsensusAlgorithm(ConsensusAlgorithm.TWO_PHASE);
            case "a paxos": this.setConsensusAlgorithm(ConsensusAlgorithm.PAXOS);
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
        this.propertyChangeSupport.firePropertyChange(Tracer.RESULT_PROPERTY, oldResult, newResult);
    }

    public void setConsensusAlgorithm(final ConsensusAlgorithm aConsensusAlgorithm) {
        final ConsensusAlgorithm oldAlgorithm = BeanFactory.getClientBean().getConsensusAlgorithm();
        try {
            BeanFactory.getClientBean().setConsensusAlgorithm(aConsensusAlgorithm);
            this.propertyChangeSupport.firePropertyChange(Tracer.CONSENSUS_ALGORITHM_PROPERTY, oldAlgorithm, aConsensusAlgorithm);
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setIpcMechanism(final IpcMechanism anIpcMechanism) {
        final IpcMechanism oldMechanism = BeanFactory.getClientBean().getIpcMechanism();
        try {
            BeanFactory.getClientBean().setIpcMechanism(anIpcMechanism);
            this.propertyChangeSupport.firePropertyChange(Tracer.IPC_MECHANISM_PROPERTY, oldMechanism, anIpcMechanism);
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
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
