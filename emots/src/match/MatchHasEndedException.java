package match;

/** Exception thrown when a function is trying to modify the results of a match
 * even though the match has already ended.
 *
 * @author nibah
 */
public class MatchHasEndedException extends RuntimeException {
    /** Constructs a new MatchHasEndedException with null as its detail message */
    public MatchHasEndedException() {
        super();
    }
    
    /** Constructs a new MatchHasEndedException with the specified detail message.
     * 
     * @param message the detail message. The detail message is saved for later
     *                retrieval by the Throwable.getMessage() method.
     */
    public MatchHasEndedException(String message) {
        super(message);
    }
}
