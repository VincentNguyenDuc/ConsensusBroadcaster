package src.broadcaster;

import src.broadcaster.utils.ArgsProcessor;

import java.rmi.registry.LocateRegistry;
import java.util.Scanner;

public class RegistryLauncher {
    public static void main(final String[] args) {

        try {

            System.setProperty("java.rmi.server.hostname", "0.0.0.0");

            // Create the RMI registry
            final int port = ArgsProcessor.getRmiRegistryPort(args);
            LocateRegistry.createRegistry(port);

            System.out.println("RMI Registry is running at port: " + port);

            // Keep the process alive
            final Scanner scanner = new Scanner(System.in);
            scanner.nextLine();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
