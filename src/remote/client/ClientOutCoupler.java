package src.remote.client;

import src.bean.BeanFactory;
import src.bean.ConsensusClientBean;
import src.remote.server.IRemoteBroadcastingServer;
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
        final IRemoteBroadcastingServer serverProxy = clientBean.getServerProxy();
        final IRemoteRmiClient proposerClient = clientBean.getClientProxy();
        try {
            final String command = (String) evt.getNewValue();
            serverProxy.broadcastCommand(proposerClient, command);
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }
}

