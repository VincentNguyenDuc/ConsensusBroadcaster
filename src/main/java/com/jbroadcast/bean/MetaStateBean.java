package com.jbroadcast.bean;

import com.jbroadcast.utils.ConsensusAlgorithm;
import com.jbroadcast.utils.IpcMechanism;

public abstract class MetaStateBean implements IMetaStateBean {
    private IpcMechanism ipcMechanism = IpcMechanism.RMI;
    private ConsensusAlgorithm consensusAlgorithm = ConsensusAlgorithm.NON_CONSENSUS;
    private boolean localProcessing = true;

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

    public boolean getLocalProcessing() {
        return this.localProcessing;
    }

    public void setLocalProcessing(final boolean newValue) {
        this.localProcessing = newValue;
    }
}
