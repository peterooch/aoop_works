package components;

/**
 * ThreadedComponent (extension of Runnable) interface, specifies methods to
 * run, pause, resume and stop threaded objects because these functions dont
 * really exist in the JCL
 * 
 * @author Baruch Rutman, ID 206119109, Campus Be'er Sheva
 * @author Asaf Bereby, ID 208058412, Campus Be'er Sheva
 */
public interface ThreadedComponent extends Runnable {
    /** Pause the objects thread */
    public void pause();

    /** Resume the objects thread */
    public void resume();

    /** Terminate the objects thread */
    public void stop();
}
