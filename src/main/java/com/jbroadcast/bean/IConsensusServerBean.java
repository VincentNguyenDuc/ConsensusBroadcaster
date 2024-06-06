package com.jbroadcast.bean;

import com.jbroadcast.remote.client.IRemoteRmiClient;

import java.util.ArrayList;

public interface IConsensusServerBean extends IConsensusBean {
    ArrayList<IRemoteRmiClient> getClients();
}
