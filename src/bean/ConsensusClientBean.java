package src.bean;

import src.remote.client.IRemoteRmiClient;
import src.remote.server.IRemoteRmiServer;
import src.simulation.IClientSimulation;

public class ConsensusClientBean extends MetaStateBean {

    protected static ConsensusClientBean CLIENT_BEAN = new ConsensusClientBean();
    private IRemoteRmiServer serverProxy;
    private IRemoteRmiClient clientProxy;
    private IClientSimulation clientSimulation;

    protected ConsensusClientBean() {
    }

    public static ConsensusClientBean getInstance() {
        return CLIENT_BEAN;
    }

    public IRemoteRmiServer getServerProxy() {
        return this.serverProxy;
    }

    public void setServerProxy(final IRemoteRmiServer aServerProxy) {
        this.serverProxy = aServerProxy;
    }

    public IRemoteRmiClient getClientProxy() {
        return this.clientProxy;
    }

    public void setClientProxy(final IRemoteRmiClient aClientProxy) {
        this.clientProxy = aClientProxy;
    }

    public IClientSimulation getClientSimulation() {
        return this.clientSimulation;
    }

    public void setClientSimulation(final IClientSimulation aClientSimulation) {
        this.clientSimulation = aClientSimulation;
    }
}
