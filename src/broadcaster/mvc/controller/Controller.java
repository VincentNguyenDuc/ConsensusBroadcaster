package src.broadcaster.mvc.controller;

import src.broadcaster.mvc.model.IModel;
import src.broadcaster.utils.BroadcasterConstants;

import java.util.Scanner;

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
            if (BroadcasterConstants.QUIT_COMMAND.equals(userInput)) {
                try {
                    this.model.terminate();
                    System.out.println(BroadcasterConstants.TERMINATE);
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
