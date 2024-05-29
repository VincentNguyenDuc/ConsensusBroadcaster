package src.remote.client;

import src.bean.BeanFactory;
import src.utils.Tracer;

import java.rmi.RemoteException;

public class RemoteRmiClient implements IRemoteRmiClient {

    @Override
    public void receiveCommand(final String sender, final String aCommand) throws RemoteException {
        try {
            Tracer.receiveCommand(sender, this.toString(), aCommand);
            BeanFactory.getClientBean().getSimulation().getModel().evaluateCommand(aCommand);
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }
}
