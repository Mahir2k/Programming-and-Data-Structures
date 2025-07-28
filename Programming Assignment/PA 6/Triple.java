/**
 * Generic Triple class to encapsulate three values.
 * <p>
 * This class provides methods to access and modify its three elements,
 * along with a formatted toString method.
 * <p>
 * Time Complexity:
 *  - All methods run in constant time, O(1).
 * 
 * @param <E1> the type of the first element
 * @param <E2> the type of the second element
 * @param <E3> the type of the third element
 * @version 1.0
 */
public class Triple <E1, E2, E3>{
    private E1 first;
    private E2 second;
    private E3 third;

    /**
     * Constructor with parameters.
     * 
     * @param f initial value for the first element
     * @param s initial value for the second element
     * @param t initial value for the third element
     */
    public Triple(E1 f, E2 s, E3 t){
        this.first = f;
        this.second = s;
        this.third = t;
    }
    
    /**
     * Accessor for the first element.
     * 
     * @return the first element
     */
    public E1 getFirst(){
        return first;
    }
    
    /**
     * Accessor for the second element.
     * 
     * @return the second element
     */
    public E2 getSecond(){
        return second;
    }

    /**
     * Accessor for the third element.
     * 
     * @return the third element
     */
    public E3 getThird() {
        return third;
    }
    
    /**
     * Mutator for the first element.
     * 
     * @param f new value for the first element
     */
    public void setFirst(E1 f){
        this.first = f;
    }

    /**
     * Mutator for the second element.
     * 
     * @param s new value for the second element
     */
    public void setSecond(E2 s) {
        this.second = s;
    }

    /**
     * Mutator for the third element.
     * 
     * @param t new value for the third element
     */
    public void setThird(E3 t) {
        this.third = t;
    }
    
    /**
     * Returns a string representation of the Triple.
     * 
     * @return formatted string representing the Triple
     */
    public String toString(){
        return "(" + first.toString() + ", " + second.toString() + ", " + third.toString() + ")";
    }
}
