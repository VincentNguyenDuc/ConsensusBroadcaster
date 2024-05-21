package src.remote.client;

import src.bean.BroadcastingClientBean;
import src.factory.BeanFactory;
import src.remote.server.IRemoteRmiServer;
import src.utils.BroadcasterTracer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ClientOutCoupler implements PropertyChangeListener {
    @Override
    public void propertyChange(final PropertyChangeEvent evt) {

        // Only listen for command property
        if (!BroadcasterTracer.COMMAND_PROPERTY.equals(evt.getPropertyName())) {
            return;
        }

        final String command = (String) evt.getNewValue();

        try {
            final BroadcastingClientBean clientBean = BeanFactory.getClientBean();
            final IRemoteRmiServer serverProxy = clientBean.getServerProxy();
            final IRemoteRmiClient clientProxy = clientBean.getClientProxy();
            serverProxy.broadcast(clientProxy, command);
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }
}
