package src.bean;

import src.simulation.IServerSimulation;

public class ConsensusServerBean extends MetaStateBean {

    protected static ConsensusServerBean SERVER_BEAN;
    private IServerSimulation serverSimulation;

    protected ConsensusServerBean() {
    }

    public static ConsensusServerBean getInstance() {
        if (SERVER_BEAN == null) {
            SERVER_BEAN = new ConsensusServerBean();
        }
        return SERVER_BEAN;
    }

    public IServerSimulation getServerSimulation() {
        return this.serverSimulation;
    }

    public void setServerSimulation(final IServerSimulation aServerSimulation) {
        this.serverSimulation = aServerSimulation;
    }
}
