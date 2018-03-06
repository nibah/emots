package match.standard.events;

/** Represents a standard match event in which the home team is shooting a penalty.
 *
 * @author nibah
 */
public class HomePenalty extends Penalty {
    
    /** Constructs a Penalty object with the home team as the penalty shooter
     * 
     * @param name          the name of the event
     * @param description   a short description of the event
     */
    public HomePenalty(String name, String description) {
        super(name, description, Side.HOME);
    }
    
    /** Constructs a Penalty object with the home team as the penalty shooter
        and standard name and description. */
    public HomePenalty() {
        this("Home Penalty", "The home team is awarded with a penalty.");
    }
}
