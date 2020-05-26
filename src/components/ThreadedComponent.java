package components;

public interface ThreadedComponent extends Runnable {
    public void pause();
    public void resume();
    public void stop();
}
