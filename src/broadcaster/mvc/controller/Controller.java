package src.broadcaster.mvc.controller;

import java.rmi.RemoteException;
import java.util.Scanner;

import src.broadcaster.bean.BroadcastingClientBean;
import src.broadcaster.mvc.model.IModel;
import src.broadcaster.remote.client.IRemoteRmiClient;
import src.broadcaster.remote.server.IRemoteRmiServer;

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

                // TODO: Move this into an out coupler
                BroadcastingClientBean clientBean = BroadcastingClientBean.getInstance();
                final IRemoteRmiServer serverProxy = clientBean.getServerProxy();
                final IRemoteRmiClient clientProxy = clientBean.getClientProxy();
                try {
                    serverProxy.broadcast(clientProxy, "increment");
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        scanner.close();
    }
}
