package src.utils;

public class ArgsParser {

    public static String getAtomicServerName(final String[] args) {
        return "ATOMIC_SERVER";
    }

    public static String getNonConsensusServerName(final String[] args) {
        return "NON_CONSENSUS_SERVER";
    }

    public static int getRmiRegistryPort(final String[] args) {
        return 1099;
    }

    public static String getRmiRegistryHost(final String[] args) {
        return "0.0.0.0";
    }
}
