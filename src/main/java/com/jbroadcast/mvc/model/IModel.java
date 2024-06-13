package com.jbroadcast.mvc.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public interface IModel<T> {
    void evaluateCommand(final String aCommand);

    String getCommand() throws Exception;

    void setCommand(final String newInput) throws Exception;

    T getResult() throws Exception;

    void setResult(T aResult) throws Exception;

    PropertyChangeSupport getPropertyChangeSupport() throws Exception;

    void addPropertyChangeListener(final PropertyChangeListener aListener) throws Exception;

    void removePropertyChangeListener(final PropertyChangeListener aListener) throws Exception;

    void terminate() throws Exception;
}
