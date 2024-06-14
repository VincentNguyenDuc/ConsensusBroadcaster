package com.examples.counter.mvc;

import com.jbroadcast.mvc.model.Model;

public class CounterModel extends Model implements ICounter {

    public static final String INCREMENT_COMMAND = "increment";
    public static final String COUNTER_PROPERTY = "counter";
    private int counter = 0;

    @Override
    public void evaluateCommand(final String aCommand) {
        super.evaluateCommand(aCommand);
        if (INCREMENT_COMMAND.equals(aCommand)) {
            this.increment();
        }
    }

    @Override
    public void increment() {
        final int newValue = this.counter + 1;
        this.getPropertyChangeSupport().firePropertyChange(COUNTER_PROPERTY, this.counter, newValue);
        this.counter += newValue;        
    }
}
