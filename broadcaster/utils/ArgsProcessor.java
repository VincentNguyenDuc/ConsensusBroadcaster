package broadcaster.utils;

public class ArgsProcessor {

    public static String getServerName(String[] args) {
        return "SERVER";
    }

    public static int getRmiRegistryPort(String[] args) {
        return 1099;
    }

    public static String getRmiRegistryHost(String[] args) {
        return "127.0.0.1";
    }
}
