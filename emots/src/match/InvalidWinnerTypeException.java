package match;

/** Exception thrown when a method is called with an invalid type of the Winner
 * enum, usually Home, Guest or Draw.
 *
 * @author nibah
 */
public class InvalidWinnerTypeException extends RuntimeException {
    /** Constructs a new InvalidWinnerTypeException with null as its detail message */
    public InvalidWinnerTypeException() {
        super();
    }
    
    /** Constructs a new InvalidWinnerTypeException with the specified detail message.
     * 
     * @param message the detail message. The detail message is saved for later
     *                retrieval by the Throwable.getMessage() method.
     */
    public InvalidWinnerTypeException(String message) {
        super(message);
    }
}
