package com.examples;

import java.rmi.registry.LocateRegistry;
import java.util.Scanner;

import com.beust.jcommander.JCommander;
import com.jbroadcast.utils.parser.ArgsParser;
import com.jbroadcast.utils.parser.ParserFactory;

public class RegistryLauncher {
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
            final int port = argsParser.getRmiRegistryPort();

            System.setProperty("java.rmi.server.hostname", rmiRegistryHost);

            // Create the RMI registry
            LocateRegistry.createRegistry(port);
            
            showRegistryInfo(rmiRegistryHost, port);

            try (Scanner scanner = new Scanner(System.in)) {
                scanner.nextLine();
            }

        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void showRegistryInfo(final String host, final int port) {
        System.out.println("Registry Host: " + host);
        System.out.println("Registry Port: " + port);
    }

}
