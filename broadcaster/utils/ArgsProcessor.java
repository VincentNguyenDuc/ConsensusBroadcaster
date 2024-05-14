package broadcaster.utils;

public class ArgsProcessor {

    public static String getServerName(String[] args) {
        return "";
    }

    public static int getRmiRegistryPort(String[] args) {
        return 1099;
    }

    public static String getRmiRegistryHost(String[] args) {
        // TODO: Parse Rmi registry host string here
        return "127.0.0.1";
    }
}
