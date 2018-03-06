package match.standard.events;

import java.util.Date;
import java.util.Random;
import match.Match;
import match.MatchEvent;
import match.Player;

/** Represents a hars foul where one of the players is penalized with a red card.
 *
 * @author nibah
 */
public class RedCard implements MatchEvent {
    private final String name;          // the name of the event
    private final String description;   // a short textual description of the event
    private final Side side;            // the side of the team receiving the red card
    private final Date date = new Date();                   // for randomisation
    private final Random rand = new Random(date.getTime()); // for randomisation
    
    /** Constructs an event in which one of the players on the field is penalised
     * with a red card. It can be specified if the home or guest team should receive
     * the red card, or if it should be randomised.
     * 
     * @param name              the name of the veent
     * @param description       a short description of the event
     * @param team              Side.HOME if the home team is receiving the red card,
     *                          Side.GUEST if the guest team is receiving the red card,
     *                          Side.RANDOM for random Side selection,
     *                          anything else will also cause a random Side selection
     * @throws IllegalArgumentException if at least one of the parameters is null
     */
    public RedCard(String name, String description, Side team) {
        if (name == null || description == null || team == null)
            throw new IllegalArgumentException("null is not a valid parameter.");
        this.name = name;
        this.description = description;
        this.side = team;
    }
    
    /** Constructs an event in which a random player on the field is penalised
     * with a red card. For deterministic side selection use
     * RedCard(String name, String description, Side team).
     * 
     * @param name          the name of the event
     * @param description   a short description of the event
     */
    public RedCard(String name, String description) {
        this(name, description, Side.RANDOM);
    }

    /** Returns the name of the event
     * 
     * @return a String containing the name of the event
     */
    @Override
    public String getName() {return name;}

    /** Returns a short description of the calculation and effects
     * of the event.
     * 
     * @return a String containing the description of the event
     */
    @Override
    public String getDescription() {return description;}

    /** Penalises a random player on the field with a red card. If a certain side
     * was specified in the constructor of this event, only players on that side
     * of the pitch can be penalised. This event updates the match statistics of the
     * given Match accordingly and modifies the eligible status of the penalised
     * player.
     * 
     * @param match         the match this event should be executed on.
     * @throes IllegalArgumentException if the match parameter is null
     */
    @Override
    public void execute(Match match) {
        if (match == null)
            throw new IllegalArgumentException("null is not a valid argument.");
        
        boolean homeCard;
        switch (side) {
            case HOME       : homeCard = true; break;
            case GUEST      : homeCard = false; break;
            case RANDOM     : //fall through
            default         : homeCard = rand.nextBoolean(); break;
        }
        Player[] players = homeCard ?
                           match.getHomeTactic().getPlayers() :
                           match.getGuestTactic().getPlayers();
        Player penalised = players[rand.nextInt(players.length)];
        penalised.setEligible(false);
        String message = "Red Card" + "\n" +
                         penalised.getName() + " has to leave the field.";
        match.getResult().getStatistics().getMatchMessages().addMessage(message);
    }
}
