package emots;

import java.util.HashMap;
import java.util.Map;
import match.FieldPosition;
import match.Match;
import match.MatchTactic;
import match.Player;
import match.PlayerPosition;
import match.Team;
import match.standard.StandardMatchEventController;
import match.standard.events.HomeVersusGuest;

/**
 *
 * @author DamnMyCode
 */
public class TestMatchEvent {
    public TestMatchEvent(){
        Player testPlayer1 = new Player("Pablo Escobar", PlayerPosition.GOALIE, 5);
        Player testPlayer2 = new Player("Donald Trump", PlayerPosition.ATTACKER, 2);
        
        Player[] players1 = {testPlayer1};
        Team testTeam1 = new Team("Pampa Pickles", players1);
        Player[] players2 = {testPlayer2};
        Team testTeam2 = new Team("Avas Alpacas", players2);
        Map<Player, FieldPosition> map1 = new HashMap<>();
        map1.put(testPlayer1, FieldPosition.GOALIE);
        Map<Player, FieldPosition> map2 = new HashMap<>();
        map2.put(testPlayer2, FieldPosition.ATTACKER);
        StandardMatchEventController controller = new StandardMatchEventController();
        
        MatchTactic homeTactics = new MatchTactic(map1);
        MatchTactic guestTactics = new MatchTactic(map2);
        Match match = new Match(testTeam1, testTeam2, homeTactics, guestTactics, controller);
        
        HomeVersusGuest versusEvent = new HomeVersusGuest("name", "description", FieldPosition.GOALIE, FieldPosition.ATTACKER);
        controller.addEvent(versusEvent);
        match.play();
        System.out.println("Guest: " + match.getResult().getStatistics().getGuestGoals());
        System.out.println("Home: " + match.getResult().getStatistics().getHomeGoals());
    }
}
