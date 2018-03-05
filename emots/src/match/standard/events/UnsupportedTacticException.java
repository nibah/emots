package match.standard.events;

/** Exception thrown when a MatchEvent fails to execute due to a MatchTactic
 * object not matching the expectations of the event (i.e. a needed FieldPosition
 * is not used in the given MatchTactic)
 *
 * @author nibah
 */
public class UnsupportedTacticException extends RuntimeException {
    /** Constructs a new UnsupportedTacticException with null as its detail message */
    public UnsupportedTacticException() {
        super();
    }
    
    /** Constructs a new UnsupportedTacticException with the specified detail message.
     * 
     * @param message the detail message. The detail message is saved for later
     *                retrieval by the Throwable.getMessage() method.
     */
    public UnsupportedTacticException(String message) {
        super(message);
    }
}
