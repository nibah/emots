package match;

/** Contains statistics about a Match
 *
 * @author nibah
 */
public interface MatchStatistics {
    /** Returns the score of the home team
     * 
     * @return the score of the home team
     */
    public int getHomeGoals();
    
    /** Returns the score of the guest team
     * 
     * @return the score of the guest team
     */
    public int getGuestGoals();
    
    /** Increments the score of the guest team by 1 */
    public void guestScore();
    
    /** Increments the score of the home team by 1 */
    public void homeScore();
}
