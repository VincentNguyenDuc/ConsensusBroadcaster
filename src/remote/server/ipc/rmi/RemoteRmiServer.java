package src.remote.server.ipc.rmi;

import src.bean.BeanFactory;
import src.remote.client.IRemoteRmiClient;

import java.rmi.RemoteException;

public class RemoteRmiServer implements IRemoteRmiServer {

    @Override
    public void registerRmiClient(final IRemoteRmiClient aRmiClient) {
        BeanFactory.getServerBean().getClients().add(aRmiClient);
        System.out.println("Registered: " + aRmiClient);
    }

    @Override
    public void unregisterRmiClient(final IRemoteRmiClient aRmiClient) throws RemoteException {
        BeanFactory.getServerBean().getClients().removeIf(aRmiClient::equals);
        System.out.println("Unregistered: " + aRmiClient);
    }
}
