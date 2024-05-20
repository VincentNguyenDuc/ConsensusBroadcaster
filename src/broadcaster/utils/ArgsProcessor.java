package src.broadcaster.utils;

public class ArgsProcessor {

    public static String getServerName(final String[] args) {
        return "SERVER";
    }

    public static int getRmiRegistryPort(final String[] args) {
        return 1099;
    }

    public static String getRmiRegistryHost(final String[] args) {
        return "0.0.0.0";
    }
}
