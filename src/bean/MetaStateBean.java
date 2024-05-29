package src.bean;

import src.utils.ConsensusAlgorithm;
import src.utils.IpcMechanism;

public abstract class MetaStateBean implements IMetaStateBean {
    private IpcMechanism ipcMechanism = IpcMechanism.RMI;
    private ConsensusAlgorithm consensusAlgorithm = ConsensusAlgorithm.NON_CONSENSUS;

    public IpcMechanism getIpcMechanism() {
        return this.ipcMechanism;
    }

    public void setIpcMechanism(final IpcMechanism anIpcMechanism) {
        this.ipcMechanism = anIpcMechanism;
    }

    public ConsensusAlgorithm getConsensusAlgorithm() {
        return this.consensusAlgorithm;
    }

    public void setConsensusAlgorithm(final ConsensusAlgorithm aConsensusAlgorithm) {
        this.consensusAlgorithm = aConsensusAlgorithm;
    }
}
