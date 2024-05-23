package src.remote.client;

import src.bean.BeanFactory;
import src.utils.ConsensusAlgorithm;
import src.utils.IpcMechanism;

import java.rmi.RemoteException;

public class RemoteRmiClient implements IRemoteRmiClient {

    @Override
    public void receiveCommand(final String aCommand) throws RemoteException {
        try {
            BeanFactory.getClientBean().getSimulation().getModel().evaluateCommand(aCommand);
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void receiveIpcMechanism(final IpcMechanism mechanism) throws RemoteException {
        BeanFactory.getClientBean().setIpcMechanism(mechanism);
        System.out.println(mechanism);
    }

    @Override
    public void receiveConsensusAlgorithm(final ConsensusAlgorithm algorithm) throws RemoteException {
        BeanFactory.getClientBean().setConsensusAlgorithm(algorithm);
        System.out.println(algorithm);
    }
}
