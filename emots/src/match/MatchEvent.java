package match;

/** Represents an event in a match round
 *
 * @author nibah
 */
public interface MatchEvent {
    
    /** Returns the name of the event
     * 
     * @return a String containing the name of the event
     */
    String getName();
    
    /** Returns a short description of the calculation and effects
     * of the event.
     * 
     * @return a String containing the description of the event
     */
    String getDescription();
    
    /** Starts the calculation of the event on the given match and
     * modifies the Match object accordingly
     * 
     * @param match the match the event should be calculated on
     */
    void execute(Match match);
}
