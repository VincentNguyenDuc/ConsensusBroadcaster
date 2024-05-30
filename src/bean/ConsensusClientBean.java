package src.bean;

import src.remote.client.IRemoteRmiClient;
import src.remote.server.algorithm.IRemoteBroadcastingServer;
import src.simulation.ISimulation;
import src.utils.ConsensusAlgorithm;

import java.util.HashMap;
import java.util.Map;

public class ConsensusClientBean extends MetaStateBean implements IConsensusBean {

    protected static ConsensusClientBean CLIENT_BEAN = new ConsensusClientBean();
    private final Map<ConsensusAlgorithm, IRemoteBroadcastingServer> serverProxyMap = new HashMap<>();
    private IRemoteRmiClient clientProxy;
    private ISimulation clientSimulation;

    protected ConsensusClientBean() {
    }

    public static ConsensusClientBean getInstance() {
        return CLIENT_BEAN;
    }

    public IRemoteBroadcastingServer getServerProxy() {
        return this.serverProxyMap.get(this.getConsensusAlgorithm());
    }

    public void setServerProxy(final ConsensusAlgorithm algorithm, final IRemoteBroadcastingServer aServerProxy) {
        this.serverProxyMap.put(algorithm, aServerProxy);
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
