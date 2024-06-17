package com.jbroadcast.utils.parser;

import com.beust.jcommander.Parameter;

public class ArgsParser {

    @Parameter(names = "--rmi-registry-port", description = "Port of RMI Registry")
    private Integer rmiRegistryPort = 1099;

    @Parameter(names = "--rmi-registry-host", description = "Host of RMI Registry")
    private String rmiRegistryHost = "0.0.0.0";

    @Parameter(names = "--atomic-server-name", description = "Name of Atomic Server")
    private String atomicServerName = "ATOMIC_SERVER";

    @Parameter(names = "--non-consensus-server-name", description = "Name of Non-Consensus Server")
    private String nonConsensusServerName = "NON_CONSENSUS_SERVER";

    @Parameter(names = { "--help"}, description = "Show these information", help = true)
    private boolean help = false;

    public boolean isHelp() {
        return help;
    }

    private ArgsParser() {
    }

    private static final ArgsParser ARGS_PARSER = new ArgsParser();

    public static ArgsParser getInstance() {
        return ARGS_PARSER;
    }

    public String getAtomicServerName() {
        return this.atomicServerName;
    }

    public String getNonConsensusServerName() {
        return this.nonConsensusServerName;
    }

    public int getRmiRegistryPort() {
        return this.rmiRegistryPort;
    }

    public String getRmiRegistryHost() {
        return this.rmiRegistryHost;
    }
}
