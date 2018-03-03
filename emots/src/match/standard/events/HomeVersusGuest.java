package match.standard.events;

import match.FieldPosition;
import match.Match;
import match.MatchEvent;
import match.MatchTactic;

/**Represents a MatchEvent in which either every player or those of specific FieldPositions of the home and guest teams will be compared. 
 *
 * @author DamnMyCode
 */
public class HomeVersusGuest implements MatchEvent{

    private String name;
    private String description;
    private FieldPosition homeFieldPosition;
    private FieldPosition guestFieldPosition;
    
    public HomeVersusGuest(FieldPosition homeFieldPosition, FieldPosition guestFieldPosition){
        this.homeFieldPosition = homeFieldPosition;
        this.guestFieldPosition = guestFieldPosition;
    }
    
    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void execute(Match match) {
        MatchTactic homeTactic = match.getHomeTactic();
        MatchTactic guestTactic = match.getGuestTactic();
        
        for(int i=0; i<homeTactic.getPosition(homeFieldPosition).length; i++){
            homeTactic.getPosition(homeFieldPosition)[i].
        }
    }

}
