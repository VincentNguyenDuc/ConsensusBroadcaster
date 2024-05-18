package src.simulations.counter;

import src.broadcaster.mvc.model.BaseModel;
import src.broadcaster.utils.BroadcasterConstants;

public class CounterModel extends BaseModel {

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
