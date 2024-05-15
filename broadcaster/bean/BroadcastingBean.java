package broadcaster.bean;

import broadcaster.processor.ICommandProcessor;

public abstract class BroadcastingBean {

    private ICommandProcessor commandProcessor;

    public abstract void start(String[] args);

    protected abstract void init(String[] args) throws Exception;

    public ICommandProcessor getCommandProcessor() {
        return this.commandProcessor;
    }

    public void setCommandProcessor(ICommandProcessor aCommandProcessor) {
        this.commandProcessor = aCommandProcessor;
    }
}
