package match.standard;

/** Excpetion thrown when a player is searched for in a collection, but the
 * collection does not contain such player
 *
 * @author nibah
 */
public class NoSuchEventException extends RuntimeException{
    /** Constructs a new NoSuchPlayerException with null as its detail message */
    public NoSuchEventException() {
        super();
    }
    
    /** Constructs a new NoSuchEventException with the specified detail message.
     * 
     * @param message the detail message. The detail message is saved for later
     *                retrieval by the Throwable.getMessage() method.
     */
    public NoSuchEventException(String message) {
        super(message);
    }
}
