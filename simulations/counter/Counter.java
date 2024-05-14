package simulations.counter;

public class Counter implements ICounter {

    private int value;

    @Override
    public void increment() {
        this.value++;
    }

    @Override
    public int getValue() {
        return this.value;
    }
}
