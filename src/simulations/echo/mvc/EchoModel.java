package src.simulations.echo.mvc;

import src.broadcaster.mvc.model.Model;

public class EchoModel extends Model implements IEcho {
    @Override
    public void evaluateCommand(String aCommand) {
        this.echo(aCommand);
    }

    @Override
    public void echo(String aMessage) {
        System.out.println(aMessage);
    }
}
