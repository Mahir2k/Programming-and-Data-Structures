/**
 * Custom exception for invalid seat numbers
 */
public class InvalidSeatException extends Exception {
    /**
     * Constructing exception with default message
     */
    public InvalidSeatException() {
        super("Invalid Seat Number");
    }

    /**
     * Constructing exception with custom message
     * @param message Detailed error message
     */
    public InvalidSeatException(String message) {
        super(message);
    }
}
