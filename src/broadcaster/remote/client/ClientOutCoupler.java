package src.broadcaster.remote.client;

import src.broadcaster.bean.BroadcastingClientBean;
import src.broadcaster.remote.server.IRemoteRmiServer;
import src.broadcaster.utils.BroadcasterConstants;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;

public class ClientOutCoupler implements PropertyChangeListener {
    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        // Only listen for command property
        if (!BroadcasterConstants.COMMAND_PROPERTY.equals(evt.getPropertyName())) {
            return;
        }

        final String command = (String) evt.getNewValue();

        BroadcastingClientBean clientBean = BroadcastingClientBean.getInstance();
        final IRemoteRmiServer serverProxy = clientBean.getServerProxy();
        final IRemoteRmiClient clientProxy = clientBean.getClientProxy();
        try {
            serverProxy.broadcast(clientProxy, command);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
