package match.standard.events;

import match.FieldPosition;
import match.Match;
import match.MatchEvent;
import match.MatchTactic;
import match.Player;

/**Represents a standard MatchEvent in which either every player or those of specific FieldPositions of the home and guest teams will be compared. 
 *
 * @author DamnMyCode
 */
public class HomeVersusGuest implements MatchEvent{

    private String name;
    private String description;
    private FieldPosition homeFieldPosition;
    private FieldPosition guestFieldPosition;
    
    /**Creates a HomeVersusGuest event.
     * 
     * @param name                  The name of the Event
     * @param description           A short description of the Event.
     * @param homeFieldPosition     The FieldPosition of the home team which should be involved in the comparison.
     * @param guestFieldPosition    The FieldPosition of the guest team which should be involved in the comparison.
     */
    public HomeVersusGuest(String name, String description, FieldPosition homeFieldPosition, FieldPosition guestFieldPosition){
        this.homeFieldPosition = homeFieldPosition;
        this.guestFieldPosition = guestFieldPosition;
        this.name = name;
        this.description = description;
    }
    
    /**
     * 
     * @return              Returns a String containing the name of this Event. 
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * 
     * @return              Returns a String containing the description of this Event. 
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**Executes event: compares number of stars of the teams on different/same 
     * FieldPositions and records scoring in match statistics.
     * 
     * @param match              A specific Match of the season.
     */
    @Override
    public void execute(Match match) {
        MatchTactic homeTactic = match.getHomeTactic();
        MatchTactic guestTactic = match.getGuestTactic();
        Player[] homePlayers = homeTactic.getPosition(homeFieldPosition);
        Player[] guestPlayers = guestTactic.getPosition(homeFieldPosition);
        int homeStarsInPosition = 0;
        int guestStarsInPosition = 0;
        
        for(int i=0; i<homePlayers.length; i++){
            homeStarsInPosition += homePlayers[i].getStars();
        }
        for(int i=0; i<guestTactic.getPosition(guestFieldPosition).length; i++){
            guestStarsInPosition += guestPlayers[i].getStars();
        }
        if(homeStarsInPosition > guestStarsInPosition){
            match.getResult().getStatistics().homeScore();
        }
        else if(guestStarsInPosition > homeStarsInPosition){
            match.getResult().getStatistics().guestScore();
        }
        else{
            //TODO: update MatchStatistics and StandardMatchStatistics
        }
    }
}
