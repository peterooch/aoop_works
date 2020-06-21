package components.builders;

import components.*;

/**
 * Builder Interface
 * 
 * @author Baruch Rutman, ID 206119109, Campus Be'er Sheva
 */
public interface Builder {
    /** Generates a Map and attaches stuff to the supplied <code>Driving</code> object
     * @param driving Driving object to attach variables to
     */
    public Map buildMap(Driving driving);
}