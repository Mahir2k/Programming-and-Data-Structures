/**
 * The Scalable interface defines a contract for objects that can be scaled.
 */
public interface Scalable {
    
    /**
     * Scales the object by a given factor.
     *
     * @param f The scaling factor to apply. A value greater than 1 increases the size,
     *          while a value between 0 and 1 decreases the size.
     */
    public void scale(double f);
}
