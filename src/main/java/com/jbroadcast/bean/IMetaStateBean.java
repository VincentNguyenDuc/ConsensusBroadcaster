package com.jbroadcast.bean;

import com.jbroadcast.utils.enums.ConsensusAlgorithm;
import com.jbroadcast.utils.enums.IpcMechanism;

public interface IMetaStateBean {
    IpcMechanism getIpcMechanism();

    void setIpcMechanism(final IpcMechanism anIpcMechanism);

    ConsensusAlgorithm getConsensusAlgorithm();

    void setConsensusAlgorithm(final ConsensusAlgorithm aConsensusAlgorithm);

    boolean getLocalProcessing();

    void setLocalProcessing(final boolean newValue);
}
