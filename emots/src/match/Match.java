package match;

/**Represents a specific match of the season.
 *
 * @author nibah
 * @author DamnMyCode
 */
public class Match {
    private static int numberOfMatches = 0;
    
    private final int id;
    private final Team home;
    private final Team guest;
    private MatchTactic homeTactic;
    private MatchTactic guestTactic;
    private MatchEventController eventController;
    //private MatchRules matchRules;
    private Result result;
    
    //TODO: update constructor by adding MatchRules to parameters
    
    /**Creates a Match object.
     * 
     * @param home              home team
     * @param guest             guest team
     * @param homeTactic        tactic of home team
     * @param guestTactic       tactic of guest team
     * @param eventController   eventController for this specific match
     */
    public Match (Team home, Team guest, MatchTactic homeTactic, MatchTactic guestTactic, MatchEventController eventController){
        id = numberOfMatches++;
        this.home = home;
        this.guest = guest;
        this.homeTactic = homeTactic;
        this.guestTactic = guestTactic;
        this.eventController = eventController;
        //this.matchRules = matchRules;
        this.result = new Result(this);
    }
    
    /**
     * 
     * @return              Returns an integer containing the ID of this specific match. 
     */
    public int getId(){
        return id;
    }
    
    /**
     * 
     * @return              Returns the Team of the home team.
     */
    public Team getHomeTeam(){
        return home;
    }
    
    /**
     * 
     * @return              Returns the Team of the guest team.
     */
    public Team getGuestTeam(){
        return guest;
    }
    
    /**
     * 
     * @return              Returns the current MatchTactic of the home team.
     */
    public MatchTactic getHomeTactic(){
        return homeTactic;
    }
    
    /**
     * 
     * @return              Returns the current MatchTactic of the guest team. 
     */
    public MatchTactic getGuestTactic(){
        return guestTactic;
    }
    
    /**
     * 
     * @return              Returns the MatchEventController. 
     */
    public MatchEventController getEventController(){
        return eventController;
    }
    
    /**
     * 
     * @return              Returns result of this specific match.
     */
    public Result getResult(){
        return result;
    }
    
    /**
     * 
     */
    public void play(){
        eventController.rollEvent().execute(this);
        
    }
}
