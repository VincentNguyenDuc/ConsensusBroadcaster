package src.broadcaster.remote.client;

import src.broadcaster.bean.BroadcastingClientBean;

import java.rmi.RemoteException;

public class RemoteRmiClient implements IRemoteRmiClient {

    @Override
    public void rmiReceiveCommand(final String aCommand) throws RemoteException {
        try {
            BroadcastingClientBean.getInstance().getModel().evaluateCommand(aCommand);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
