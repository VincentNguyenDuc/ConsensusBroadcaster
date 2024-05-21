package examples.counter.mvc;

import src.mvc.view.IView;

import java.beans.PropertyChangeEvent;

public class CounterView implements IView {

    @Override
    public void propertyChange(final PropertyChangeEvent anEvent) {
        System.out.println(anEvent);
    }
}
