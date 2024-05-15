package simulations.counter;

import broadcaster.processor.ICommandProcessor;

public class Counter implements ICounter, ICommandProcessor {

    private int value;

    @Override
    public void increment() {
        this.value++;
    }

    @Override
    public int getValue() {
        return this.value;
    }

    @Override
    public void processCommand(String aCommand) {
        if ("increment".equals(aCommand)) {
            this.value++;
        } else if ("get".equals(aCommand)) {
            System.out.println(this.value);
        }
    }
}
