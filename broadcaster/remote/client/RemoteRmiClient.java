package broadcaster.remote.client;


import broadcaster.bean.BroadcastingClientBean;

import java.rmi.RemoteException;

public class RemoteRmiClient implements IRemoteRmiClient {

    @Override
    public void rmiReceiveCommand(String aCommand) throws RemoteException {
        BroadcastingClientBean.getInstance().getCommandProcessor().processCommand(aCommand);
    }
}
