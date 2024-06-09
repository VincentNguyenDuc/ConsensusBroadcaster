package com.examples.counter.launcher;

import java.rmi.registry.LocateRegistry;
import java.util.Scanner;

import com.beust.jcommander.JCommander;
import com.jbroadcast.utils.parser.ArgsParser;
import com.jbroadcast.utils.parser.ParserFactory;

public class CounterRegistryLauncher {
    public static void main(final String[] args) {

        try {
            ParserFactory.setArgsParser(ArgsParser.getInstance());
            final ArgsParser argsParser = ParserFactory.getArgsParser();

            JCommander
                .newBuilder()
                .addObject(argsParser)
                .build()
                .parse(args);

            final String rmiRegistryHost = argsParser.getRmiRegistryHost();

            System.setProperty("java.rmi.server.hostname", rmiRegistryHost);

            // Create the RMI registry
            final int port = argsParser.getRmiRegistryPort();
            LocateRegistry.createRegistry(port);

            System.out.println("RMI Registry is running at port: " + port);

            try (Scanner scanner = new Scanner(System.in)) {
                scanner.nextLine();
            }

        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

}
