package com.jbroadcast.remote.client;

import com.jbroadcast.bean.BeanFactory;
import com.jbroadcast.bean.ConsensusClientBean;
import com.jbroadcast.remote.server.algorithm.IRemoteBroadcastingServer;
import com.jbroadcast.utils.Tracer;

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

