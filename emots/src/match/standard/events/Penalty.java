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
    private final Side side;                // the side of the team shooting the penalty
    private final String name;              // name of the event
    private final String description;       // a short description of the event
    private final Date date = new Date();                       //used for randomisation
    private final Random rand = new Random(date.getTime());     //used for randomisation
    
    /** Constructs a penalty event.
     * 
     * @param name              the name of the event
     * @param description       a short textual description of the event
     * @param side              Side.HOME if the home team is shooting the penalty,
     *                          Side.GUEST if the guest team is shooting the penalty,
     *                          Side.RANDOM for random Side selection,
     *                          anything else also causes a random Side selection
     * @throws IllegalArgumentException if at least one of the parameters is null
     */
    public Penalty(String name, String description, Side side){
        if (name == null || description == null || Side == null)
            throw new IllegalArgumentException("null is not a valid argument.");
        this.name = name;
        this.description = description;
        this.side = side;
    }
    
    /** Constructs a penalty event with random side selection.
     * 
     * @param name              the name of the event
     * @param description       a short textual description of the event
     * @throws IllegalArgumentException if at least one of the parameters is null
     */
    public Penalty(String name, String description) {
        this(name, description, Side.RANDOM);
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

    /** 
     * 
     * @return              Returns the side of the team which player is shooting the penalty
     */
    public Side getSide() {return side;}
    /** Executes a penalty. A goalie from the defending team and an opposing player
     * from the other team are randomly selected. If there are no suitable players,
     * an exception is thrown. This events modifies statistics of the Match specified
     * in the match argument accordingly.
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
        
        boolean homePenalty;
        switch (side) {
            case HOME        : homePenalty = true; break;
            case GUEST       : homePenalty = false; break;
            case RANDOM      : //fall through
            default          : homePenalty = rand.nextBoolean();
        }
        Player[] goalies = homePenalty ?
                           match.getGuestTactic().getPosition(FieldPosition.GOALIE) :
                           match.getHomeTactic().getPosition(FieldPosition.GOALIE);
        Player[] shootingCandidates = homePenalty ?
                                      match.getHomeTactic().getPlayers() :
                                      match.getGuestTactic().getPlayers();
        String message = (homePenalty ? "Home Penalty" : "Guest Penalty") + "\n";
        
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
        message += shooter.getNameStars() + " VS " + goalie.getNameStars() + "\n";
        if (shooter.getStars() > goalie.getStars()) {
            if (homePenalty) {
                match.getResult().getStatistics().homeScore();
            } else {
                match.getResult().getStatistics().guestScore();
            }
            message += "Penalty successful";
        } else {
            message += "Penalty unsuccessful";
        }
        match.getResult().getStatistics().getMatchMessages().addMessage(message);
    }
}
