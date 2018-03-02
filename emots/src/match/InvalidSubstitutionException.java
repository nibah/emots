package match;

/** Exception thrown when a fuction tries to make an invalid substitution on a
 * Tactic.
 *
 * @author nibah
 */
public class InvalidSubstitutionException extends RuntimeException {
    /** Constructs a new InvalidSubstitutionException with null as its detail message */
    public InvalidSubstitutionException() {
        super();
    }
    
    /** Constructs a new InvalidSubstitutionException with the specified detail message.
     * 
     * @param message the detail message. The detail message is saved for later
     *                retrieval by the Throwable.getMessage() method.
     */
    public InvalidSubstitutionException(String message) {
        super(message);
    }
}
