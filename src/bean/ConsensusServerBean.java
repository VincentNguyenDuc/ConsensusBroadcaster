package src.bean;

import src.simulation.ISimulation;

public class ConsensusServerBean extends MetaStateBean implements IConsensusBean {

    protected static ConsensusServerBean SERVER_BEAN;
    private ISimulation serverSimulation;

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
}
