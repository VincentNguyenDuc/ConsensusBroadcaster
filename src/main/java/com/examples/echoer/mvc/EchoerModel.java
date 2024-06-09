package com.examples.echoer.mvc;

import com.jbroadcast.mvc.model.Model;

public class EchoerModel extends Model<String> implements IEchoer {

    public static final String ECHO_COMMAND = "echo:";

    @Override
    public void evaluateCommand(final String aCommand) {
        super.evaluateCommand(aCommand);
        final String[] messages = aCommand.split(" ", 2);
        if (ECHO_COMMAND.equals(messages[0])) {
            this.echo(messages[1]);
        }
    }

    @Override
    public void echo(final String message) {
        super.setResult(message);
    }
}
