package match;

/** Represents the result of a Match. Also contains statistics
 * about the Match.
 *
 * @author nibah
 */
public class Result {
    private Match match;        // the match which results is contained in this object
    private Winner winner;      // the winner of the match
    private boolean ended;      /* true if the match has ended, false otherwise
                                   if ended == true, the object cannot be modified anymore
                                */  
    private MatchStatistics stats; // statistics of the match
    
    /** Constructor using StandardStatistics for the statistics of the match
     * 
     * @param match the Match which results should be contained within this object
     */
    public Result(Match match) {
        this.match = match;
        winner = Winner.UNDEFINED;
        ended = false;
        stats = new StandardMatchStatistics();
    }
    
    /** Returns the Match which results are contained in this object
     * 
     * @return the Match which results are contained in this object
     */
    public Match getMatch() {return match;}
    
    /** Returns the Winner of the Match. The return type is a Winner enum,
     * such as HOME or GUEST, not the actual Team object. If the match has not
     * ended yet, Winner.Undefined will be returned.
     * 
     * @return the Winner of the match
     */
    public Winner getWinner() {return winner;}
    
    //TODO: Getter Method which returns the actual Team object of the winner.
    //public Team getWinnerTeam() {}
    
    /** Returns if a match has ended yet.
     * 
     * @return true if the results have been evaluated, otherwise false
     */
    public boolean isEnded() {return ended;}
    
    /** Returns a reference on the Object containing the statistics of the match
     * 
     * @return the MatchStatistics object containing the statistics of the match
     */
    public MatchStatistics getStatistics() {return stats;}
    
    /** Calculates the winner of the match using the scores provided by the
     * MatchStatistics. The result is saved in the winner argument and can be
     * accessed later with the getWinner() method.
     *
     * @throws MatchHasEndedException if the match has already ended
    */
    public void evaluateWinner() {
        if (ended)
            throw new MatchHasEndedException("Cannot evaluate the winner, beacuse the match has already ended.");
        int homeGoals = stats.getHomeGoals();
        int guestGoals = stats.getGuestGoals();
        if (homeGoals > guestGoals)
            winner = Winner.HOME;
        else if (guestGoals > homeGoals)
            winner = Winner.GUEST;
        else
            winner = Winner.DRAW;
    }
    
    /** Ends the match. After this, the Result object cannot be modified anymore. */
    public void setEnded() {
        ended = true;
    }
    
    /** Determines the winner of the match and ends the mathc. Usually used
     * when one of the teams violate some of the match rules and therefore
     * not allowed to paricipate in the match. To evauate the winner based on
     * the scores, use evaluateWinner() instead.
     * 
     * @param winner    the winner of the game (i.e. AutomaticDraw). Home, Guest
     *                  and Draw are not allowed.
     * 
     * @throws MatchHasEndedException       if the match has already ended
     */
    public void setWinner(Winner winner) {
        if (ended)
            throw new MatchHasEndedException("Cannot change the winner. The match has already ended.");
        if (winner == Winner.HOME || winner == Winner.GUEST || winner == Winner.DRAW)
            throw new IllegalArgumentException(
                    "setWinner should only be used to determine the winner in result "
                  + "of match rule violations. To evaluate the winner based on the scores "
                  + "use the method evaluateWinner() instead.");
        this.winner = winner;
        ended = true;
    }
    
    @Override
    public String toString() {
        return "Results of " + match + "\n" +
               "Match ended: " + (ended ? "yes" : "no") + "\n" +
               "Winner: " + winner + "\n" + //TODO: write team name instead of winner type
               "Statistics: " + stats; 
    }
}
