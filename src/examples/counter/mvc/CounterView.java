package src.examples.counter.mvc;

import src.broadcaster.mvc.view.IView;

import java.beans.PropertyChangeEvent;

public class CounterView implements IView {

    @Override
    public void propertyChange(final PropertyChangeEvent anEvent) {
        System.out.println(anEvent);
    }
}
