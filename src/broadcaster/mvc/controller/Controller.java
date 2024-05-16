package src.broadcaster.mvc.controller;

import java.util.Scanner;

import src.broadcaster.mvc.model.IModel;

public class Controller implements IController {

    private final IModel model;

    public Controller(final IModel aModel) {
        this.model = aModel;
    }

    @Override
    public void processCommands() {
        final Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Please enter a command: ");
            final String userInput = scanner.nextLine().toLowerCase();
            if ("quit".equals(userInput)) {
                try {
                    this.model.terminate();
                    System.out.println("Terminate");
                    break;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            try {
                this.model.setCommand(userInput);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        scanner.close();
    }
}
