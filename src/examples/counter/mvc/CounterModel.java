package src.examples.counter.mvc;

import src.broadcaster.mvc.model.Model;

public class CounterModel extends Model implements ICounter {

    public static final String INCREMENT_COMMAND = "increment";

    @Override
    public void evaluateCommand(final String aCommand) {
        if (INCREMENT_COMMAND.equals(aCommand)) {
            this.increment();
        }
    }

    @Override
    public void increment() {
        super.setResult(this.getResult() + 1);
    }
}