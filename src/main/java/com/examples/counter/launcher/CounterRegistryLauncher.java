package com.examples.counter.launcher;

import com.jbroadcast.utils.ArgsParser;

import java.rmi.registry.LocateRegistry;
import java.util.Scanner;

public class CounterRegistryLauncher {
    public static void main(final String[] args) {

        try {

            System.setProperty("java.rmi.server.hostname", "0.0.0.0");

            // Create the RMI registry
            final int port = ArgsParser.getRmiRegistryPort(args);
            LocateRegistry.createRegistry(port);

            System.out.println("RMI Registry is running at port: " + port);

            try (// Keep the process alive
            Scanner scanner = new Scanner(System.in)) {
                scanner.nextLine();
            }

        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

}
