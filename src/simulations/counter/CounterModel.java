package src.simulations.counter;

import src.broadcaster.mvc.model.BaseModel;

public class CounterModel extends BaseModel {

    @Override
    protected void evaluateCommand() {
        if (super.getCommand().equals("increment")) {
            this.increment();
        }
    }

    @Override
    public void terminate() {
        System.exit(0);
    }

    @Override
    public void increment() {
        final int oldResult = this.getResult();
        this.setResult(oldResult + 1);
        this.getPropertyChangeSupport().firePropertyChange("Increment", oldResult, this.getResult());
    }
}
