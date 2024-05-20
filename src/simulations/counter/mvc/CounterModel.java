package src.simulations.counter.mvc;

import src.broadcaster.mvc.model.Model;
import src.broadcaster.utils.BroadcasterConstants;

public class CounterModel extends Model implements ICounter {

    @Override
    public void evaluateCommand(final String aCommand) {
        if (BroadcasterConstants.INCREMENT_COMMAND.equals(aCommand)) {
            this.increment();
        }
    }

    @Override
    public void terminate() {
        System.exit(0);
    }

    @Override
    public void increment() {
        super.setResult(this.getResult() + 1);
    }
}
