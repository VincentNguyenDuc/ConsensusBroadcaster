package src.broadcaster.bean;

public abstract class BroadcastingBean {

    public abstract void start(String[] args);

    protected abstract void init(String[] args) throws Exception;
}
