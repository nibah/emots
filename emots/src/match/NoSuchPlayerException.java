package match;

/** Excpetion thrown when a player is searched for in a collection, but the
 * collection does not contain such player
 *
 * @author nibah
 */
public class NoSuchPlayerException extends RuntimeException{
    /** Constructs a new NoSuchPlayerException with null as its detail message */
    public NoSuchPlayerException() {
        super();
    }
    
    /** Constructs a new NoSuchPlayerException with the specified detail message.
     * 
     * @param message the detail message. The detail message is saved for later
     *                retrieval by the Throwable.getMessage() method.
     */
    public NoSuchPlayerException(String message) {
        super(message);
    }
}
