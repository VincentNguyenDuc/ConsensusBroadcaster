package src.remote.client;

import src.bean.BeanFactory;
import src.bean.ConsensusClientBean;
import src.remote.server.IRemoteRmiServer;
import src.utils.ConsensusAlgorithm;
import src.utils.IpcMechanism;
import src.utils.Tracer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;

public class ClientOutCoupler implements PropertyChangeListener {
    @Override
    public void propertyChange(final PropertyChangeEvent evt) {

        final ConsensusClientBean clientBean = BeanFactory.getClientBean();
        final IRemoteRmiServer serverProxy = clientBean.getServerProxy();
        final IRemoteRmiClient proposerClient = clientBean.getClientProxy();

        switch (evt.getPropertyName()) {
            case Tracer.COMMAND_PROPERTY -> {
                try {
                    final String command = (String) evt.getNewValue();
                    serverProxy.broadcastCommand(proposerClient, command);
                } catch (final Exception e) {
                    throw new RuntimeException(e);
                }
            }
            case Tracer.IPC_MECHANISM_PROPERTY -> {
                final IpcMechanism mechanism = (IpcMechanism) evt.getNewValue();
                // TODO: broadcast ipc mechanism
                try {
                    serverProxy.broadcastIpcMechanism(proposerClient, mechanism);
                } catch (final RemoteException e) {
                    throw new RuntimeException(e);
                }
            }
            case Tracer.CONSENSUS_ALGORITHM_PROPERTY -> {
                // TODO: broadcast consensus algorithm
                final ConsensusAlgorithm algorithm = (ConsensusAlgorithm) evt.getNewValue();
                try {
                    serverProxy.broadcastConsensusAlgorithm(proposerClient, algorithm);
                } catch (final RemoteException e) {
                    throw new RuntimeException(e);
                }
            }
            default -> {
            }
        }
    }
}
