package src.remote.client;

import src.bean.BeanFactory;

import java.rmi.RemoteException;

public class RemoteRmiClient implements IRemoteRmiClient {

    @Override
    public void rmiReceiveCommand(final String aCommand) throws RemoteException {
        try {
            BeanFactory.getClientBean().getClientSimulation().getModel().evaluateCommand(aCommand);
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }
}
