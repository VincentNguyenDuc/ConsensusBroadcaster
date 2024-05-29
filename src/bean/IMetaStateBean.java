package src.bean;

import src.utils.ConsensusAlgorithm;
import src.utils.IpcMechanism;

public interface IMetaStateBean {
    IpcMechanism getIpcMechanism();

    void setIpcMechanism(final IpcMechanism anIpcMechanism);

    ConsensusAlgorithm getConsensusAlgorithm();

    void setConsensusAlgorithm(final ConsensusAlgorithm aConsensusAlgorithm);
}
