package src.bean;

import src.remote.client.IRemoteRmiClient;
import src.remote.server.IRemoteBroadcastingServer;
import src.simulation.ISimulation;

public class ConsensusClientBean extends MetaStateBean implements IConsensusBean {

    protected static ConsensusClientBean CLIENT_BEAN = new ConsensusClientBean();
    private IRemoteBroadcastingServer serverProxy;
    private IRemoteRmiClient clientProxy;
    private ISimulation clientSimulation;

    protected ConsensusClientBean() {
    }

    public static ConsensusClientBean getInstance() {
        return CLIENT_BEAN;
    }

    public IRemoteBroadcastingServer getServerProxy() {
        return this.serverProxy;
    }

    public void setServerProxy(final IRemoteBroadcastingServer aServerProxy) {
        this.serverProxy = aServerProxy;
    }

    public IRemoteRmiClient getClientProxy() {
        return this.clientProxy;
    }

    public void setClientProxy(final IRemoteRmiClient aClientProxy) {
        this.clientProxy = aClientProxy;
    }

    @Override
    public ISimulation getSimulation() {
        return this.clientSimulation;
    }

    @Override
    public void setSimulation(final ISimulation aSimulation) {
        this.clientSimulation = aSimulation;
    }
}
