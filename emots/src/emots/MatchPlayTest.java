package emots;

import match.standard.events.GuestPenalty;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import match.*;
import match.standard.*;
import match.standard.events.*;

public class MatchPlayTest {
    private String fileName = "data/players";
    
    public void test() throws IOException {
        // Read the tams from a file
        List<String> lines = Files.readAllLines(Paths.get(fileName));
        List<Player> players = new ArrayList<>();
        for (String line : lines) {
            String[] parsed = line.split(" ");
            PlayerPosition pos = null;
            switch(parsed[1].toLowerCase()) {
                case "goalie"       : pos = PlayerPosition.GOALIE; break;
                case "defender"     : pos = PlayerPosition.DEFENDER; break;
                case "midfielder"   : pos = PlayerPosition.MIDFIELDER; break;
                case "attacker"     : pos = PlayerPosition.ATTACKER; break;
                case "rookie"       : pos = PlayerPosition.ROOKIE; break;
            }
            players.add(new Player(parsed[0],pos,Integer.parseInt(parsed[2])));
        }
        Player[] plyrs = players.toArray(new Player[0]);
        Team avas = new Team("Avas Alpacas", Arrays.copyOf(plyrs, 15));
        Team pampa = new Team("Pampa Pickles", Arrays.copyOfRange(plyrs, 16, 30));
        
        // Create match tactics
        MatchTactic avasTactic = new MatchTactic(form442(avas));
        MatchTactic pampaTactic = new MatchTactic(form343(pampa));
        System.out.println(avasTactic+"\n");
        System.out.println(pampaTactic+"\n");
        
        //Create match events and event controller
        StandardMatchEventController ec = new StandardMatchEventController();
        ec.addEvent(new HomeVersusGuest("","",FieldPosition.ATTACKER,FieldPosition.ATTACKER));
        ec.addEvent(new HomeVersusGuest("","",FieldPosition.DEFENDER,FieldPosition.DEFENDER));
        ec.addEvent(new HomeVersusGuest("","",FieldPosition.MIDFIELDER,FieldPosition.MIDFIELDER));
        ec.addEvent(new HomeVersusGuest("","",FieldPosition.PLAYER,FieldPosition.PLAYER));
//        ec.addEvent(new GuestPenalty());
//        ec.addEvent(new Penalty("","",Side.HOME));
        
        
        //Create match and play match
        Match match = new Match(
                avas, pampa,
                avasTactic, pampaTactic,
                new StandardMatchRules(),
                ec
        );
        match.play();
        String[] msgs = match.getResult().getStatistics().getMatchMessages().getAllMessages();
        for (String message : msgs)
            System.out.println(message);
        System.out.println(match.getResult());
    }
    
    private Map<Player, FieldPosition> form442(Team team) {
        Player[] players = team.getPlayers();
        Map<Player, FieldPosition> formation = new HashMap<>();
        formation.put(players[0], FieldPosition.GOALIE);
        for(int i = 2; i<6; i++)
            formation.put(players[i], FieldPosition.DEFENDER);
        for (int i = 7; i<11; i++)
            formation.put(players[i], FieldPosition.MIDFIELDER);
        for (int i = 11; i<13; i++)
            formation.put(players[i], FieldPosition.ATTACKER);
        return formation;
    }
    
    private Map<Player, FieldPosition> form343(Team team) {
        Player[] players = team.getPlayers();
        Map<Player, FieldPosition> formation = new HashMap<>();
        formation.put(players[0], FieldPosition.GOALIE);
        for (int i = 2; i<5; i++)
            formation.put(players[i], FieldPosition.DEFENDER);
        for (int i = 6; i<10; i++)
            formation.put(players[i], FieldPosition.MIDFIELDER);
        for (int i = 11; i<14; i++)
            formation.put(players[i], FieldPosition.ATTACKER);
        return formation;
    }
}
