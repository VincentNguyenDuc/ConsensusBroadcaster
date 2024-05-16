package src.broadcaster.bean;

import src.broadcaster.mvc.controller.IController;

public abstract class BroadcastingBean {

    private IController controller;

    public abstract void start(String[] args);

    protected abstract void init(String[] args) throws Exception;

    public IController getController() {
        return this.controller;
    }

    public void setController(IController aController) {
        this.controller = aController;
    }
}
