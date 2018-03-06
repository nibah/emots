package match.standard.events;

/** Represents a standard match event in which the guest team is shooting a penalty.
 *
 * @author nibah
 */
public class GuestPenalty extends Penalty {
    
    /** Constructs a Penalty object with the guest team as the penalty shooter
     * 
     * @param name          the name of the event
     * @param description   a short description of the event
     */
    public GuestPenalty(String name, String description) {
        super(name, description, Side.GUEST);
    }
    
    /** Constructs a Penalty object with the guest team as the penalty shooter
        and standard name and description. */
    public GuestPenalty() {
        this("Guest Penalty", "The guest team is awarded with a penalty.");
    }
}