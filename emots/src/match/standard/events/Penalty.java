package match.standard.events;

import java.util.Date;
import java.util.Random;
import match.FieldPosition;
import match.Match;
import match.MatchEvent;
import match.Player;

/** Represents a standard match event in which the goalie of a team will be
 * compared with a randomly selected opposing player.
 *
 * @author nibah
 */
public class Penalty implements MatchEvent {
    final boolean homePenalty;
    final String name;
    final String description;
    
    /** Constructs a penalty event.
     * 
     * @param name              the name of the event
     * @param description       a short textual description of the event
     * @param homePenalty       true if the home team is shooting the penalty,
     *                          false otherwise
     */
    public Penalty(String name, String description, boolean homePenalty){
        if (name == null || description == null)
            throw new IllegalArgumentException("null is not a valid argument.");
        this.name = name;
        this.description = description;
        this.homePenalty = homePenalty;
    }

    /**
     * 
     * @return              Returns a String containing the name of this object. 
     */
    @Override
    public String getName() {return name;}

    /**
     * 
     * @return              Returns a String containing the description of this object. 
     */
    @Override
    public String getDescription() {return description;}

    /** Executes a penalty. A goalie from the defending team and an opposing player
     * from the other team are randomly selected. If there are no suitable players,
     * an exception is thrown. This events modifies the statistics of the Match
     * specified in the match argument accordingly.
     * 
     * @param match     the match the event should be executed on. The statistics of
     *                  the match will be modified accordingly.
     * @throws UnsupportedTacticException   if there are no goalies on the defending
     *                                      team, or if there are no players at all
     *                                      on the opposing team
     * @throws IllegalArgumentException     if the match parameter is null
     */
    @Override
    public void execute(Match match) {
        if (match == null)
            throw new IllegalArgumentException("null is not a valid argument");
        
        Date date = new Date();
        Random rand = new Random(date.getTime());        
        Player[] goalies = homePenalty ?
                           match.getGuestTactic().getPosition(FieldPosition.GOALIE) :
                           match.getHomeTactic().getPosition(FieldPosition.GOALIE);
        Player[] shootingCandidates = homePenalty ?
                                      match.getHomeTactic().getPlayers() :
                                      match.getGuestTactic().getPlayers();
        
        if (goalies.length == 0)
            throw new UnsupportedTacticException("The penalty cannot be executed. "
                    + "There are no goalies playing in the "
                    + (homePenalty? "guest" : "home") + " team.");
        if (shootingCandidates.length == 0)
            throw new UnsupportedTacticException("The penalty cannot be executed. "
                    + "There are no players playing in the "
                    + (homePenalty? "home" : "guest") + " team.");
        
        Player goalie = goalies.length == 1 ?
                        goalies[0] :
                        goalies[rand.nextInt(goalies.length)];
        Player shooter = shootingCandidates[rand.nextInt(shootingCandidates.length)];
        if (shooter.getStars() > goalie.getStars()) {
            if (homePenalty) {
                match.getResult().getStatistics().homeScore();
            } else {
                match.getResult().getStatistics().guestScore();
            }
        }
    }
    
}
