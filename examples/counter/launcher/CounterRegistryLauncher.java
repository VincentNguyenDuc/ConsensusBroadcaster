package examples.counter.launcher;

import src.utils.ArgsProcessor;

import java.rmi.registry.LocateRegistry;
import java.util.Scanner;

public class CounterRegistryLauncher {
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

        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

}