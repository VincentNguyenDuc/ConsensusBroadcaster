package src.bean;

import src.remote.client.IRemoteRmiClient;
import src.simulation.ISimulation;

import java.util.ArrayList;

public class ConsensusServerBean extends MetaStateBean implements IConsensusServerBean {

    protected static ConsensusServerBean SERVER_BEAN;
    private ISimulation serverSimulation;
    private final ArrayList<IRemoteRmiClient> clients = new ArrayList<IRemoteRmiClient>();

    protected ConsensusServerBean() {
    }

    public static ConsensusServerBean getInstance() {
        if (SERVER_BEAN == null) {
            SERVER_BEAN = new ConsensusServerBean();
        }
        return SERVER_BEAN;
    }

    public ISimulation getSimulation() {
        return this.serverSimulation;
    }

    public void setSimulation(final ISimulation aSimulation) {
        this.serverSimulation = aSimulation;
    }

    @Override
    public ArrayList<IRemoteRmiClient> getClients() {
        return this.clients;
    }
}
