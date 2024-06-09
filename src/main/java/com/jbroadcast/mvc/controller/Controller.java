package com.jbroadcast.mvc.controller;

import com.jbroadcast.mvc.model.IModel;
import com.jbroadcast.utils.Tracer;

import java.util.Scanner;

public class Controller implements IController {

    private final IModel model;

    public Controller(final IModel aModel) {
        this.model = aModel;
    }

    @Override
    public void processCommands() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("Please enter a command: ");
                final String userInput = scanner.nextLine().toLowerCase();
                if (Tracer.QUIT.equals(userInput)) {
                    try {
                        this.model.terminate();
                        System.out.println(Tracer.TERMINATE);
                        break;
                    } catch (final Exception e) {
                        throw new RuntimeException(e);
                    }
                }

                try {
                    this.model.setCommand(userInput);
                } catch (final Exception e) {
                    throw new RuntimeException(e);
                }
            }
            scanner.close();
        }
    }
}
