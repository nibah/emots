package match;

/** Controls the selection of MatchEvents during a match
 *
 * @author nibah
 */
public interface MatchEventController {
    
    /** Returns a (usually randomized) MatchEvent
     * 
     * @return the rolled MatchEvent
     */
    MatchEvent rollEvent();
}
