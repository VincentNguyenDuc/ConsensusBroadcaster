package src.bean;

import src.remote.client.IRemoteRmiClient;

import java.util.ArrayList;

public interface IConsensusServerBean extends IConsensusBean {
    ArrayList<IRemoteRmiClient> getClients();
}
