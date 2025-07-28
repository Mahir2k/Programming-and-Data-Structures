/**
 * Exception class used for Date and Time formats
 */
public class FormatMismatchException extends Exception{
    /**
     * Default constructor
     */
    public FormatMismatchException(){
        super("Invalid Date or Time");
    }
    /**
     * Constructor with one arg
     * @param message the message of the exception
     */
    public FormatMismatchException(String message){
        super(message);
    }
}