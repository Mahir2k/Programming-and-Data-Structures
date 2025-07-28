/**
 * A custom exception thrown when the format of a string
 * (e.g., date, time, or line of text) does not match the
 * expected pattern or range.
 */
public class FormatMismatchException extends Exception {
    
    /**
     * Default no-arg constructor.
     */
    public FormatMismatchException() {
        super();
    }
    
    /**
     * Constructor that accepts a custom message.
     * @param message The exception message.
     */
    public FormatMismatchException(String message) {
        super(message);
    }
}




