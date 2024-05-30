package src.remote.client;

import src.bean.BeanFactory;
import src.bean.ConsensusClientBean;
import src.remote.server.algorithm.IRemoteBroadcastingServer;
import src.utils.Tracer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ClientOutCoupler implements PropertyChangeListener {
    @Override
    public void propertyChange(final PropertyChangeEvent evt) {

        if (!Tracer.COMMAND_PROPERTY.equals(evt.getPropertyName())) return;

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

