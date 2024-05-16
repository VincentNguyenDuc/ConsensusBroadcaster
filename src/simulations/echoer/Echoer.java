package src.simulations.echoer;

public class Echoer implements IEchoer {
    @Override
    public void echo(String aMessage) {
        System.out.println(aMessage);
    }
}
