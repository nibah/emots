package match;

/** Very simply statistics, only containing the goals of the teams
 *
 * @author nibah
 */
public class StandardMatchStatistics implements MatchStatistics{
    private int homeGoals;
    private int guestGoals;
    
    /** Standard constructor initialising home and guest score with 0
     * 
     */
    StandardMatchStatistics () {
        homeGoals = 0;
        guestGoals = 0;
    }
    
    /** Returns the score of the home team
     * 
     * @return the score of the home team
     */
    @Override
    public int getHomeGoals() {return homeGoals;}
    
    /** Returns the score of the guest team
     * 
     * @return the score of the guest team
     */
    @Override
    public int getGuestGoals() {return guestGoals;}
    
    /** Increments the score of the guest team by 1 */
    @Override
    public void guestScore() {
        guestGoals++;
    }
    
    /** Increments the score of the home team by 1 */
    @Override
    public void homeScore() {
        homeGoals++;
    }
    
    @Override
    public String toString() {
        return homeGoals + " - " + guestGoals;
    }
}
