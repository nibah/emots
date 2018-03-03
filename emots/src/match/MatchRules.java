package match;

/** Represents the rule set of a Match
 *
 * @author nibah
 */
public interface MatchRules {
    /** Decides if a given tactic is legal according to the rules. (i.e. are
     * there enough players on the field?)
     * 
     * @param tactic        the tactics which should be examined
     * @return true if the tactic matches the rules, false otherwise
     */
    boolean allowedTactic(MatchTactic tactic);
    
    /** Decides if a Match can proceed in the next round or not.
     * 
     * @return true if the current round was not the last one, false otherwise
     */
    boolean hasNextRound();
    
    /** A method to signal the end of a round to the MatchRules object. This
        should be called at the end of every round. */
    void roundEnd();
    
    /** Decides if it is legal for a player to go back on the field after she
     * was substituted.
     * 
     * @return true if it is allowed, false otherwise
     */
    boolean substituteBackAllowed();
}
