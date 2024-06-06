package com.jbroadcast.utils;


public class Tracer {
    public static final String COMMAND_PROPERTY = "command";
    public static final String RESULT_PROPERTY = "result";
    public static final String IPC_MECHANISM_PROPERTY = "ipc_mechanism";
    public static final String CONSENSUS_ALGORITHM_PROPERTY = "consensus_algorithm";
    public static final String LOCAL_PROCESSING_PROPERTY = "local_processing";
    public static final String QUIT = "quit";
    public static final String TERMINATE = "terminate";
    public static final String IPC_MECHANISM_COMMAND_PREFIX = "i ";
    public static final String CONSENSUS_ALGORITHM_COMMAND_PREFIX = "a ";

    public static void receiveCommand(final String from, final String to, final String aCommand) {
        System.out.println(to + ": receive \"" + aCommand + "\" from: " + from);
    }

    public static void sendCommand(final String from, final String to, final String aCommand) {
        System.out.println(from + ": send \"" + aCommand + "\" to: " + to);
    }
}
