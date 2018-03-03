package match;

import java.util.Arrays;

/** Represents a team of players with owned chance cards.
 *
 * @author DamnMyCode
 */
public class Team {
    private static int numberOfTeams = 0;
    
    private int id;
    private String name;
    private Player[] players;
    //private ChanceCard[] chanceCards; //TODO: create reference
    
    /**
     * 
     * @param name          the name of the team
     * @param players       an array containing the players of the team
     */
    public Team (String name, Player[] players){ //TODO: update constructor (chanceCards)
        id = numberOfTeams++;
        this.name = name;
        this.players = players;
    }
    
    /**
     * 
     * @return              id 
     */
    public int getId (){
        return id;
    }
    
    /**
     * 
     * @return              name 
     */
    public String getName (){
        return name;
    }
    
    /**
     * 
     * @return              array of players 
     */
    public Player[] getPlayers (){
        return players;
    }
    
    //TODO: create method public void removeChanceCard ()

    //TODO: add chanceCards to String s
    @Override
    public String toString(){
        String playerIds = "";
        
        for(int i=0; i<players.length; i++){
            playerIds = playerIds + String.valueOf(players[i].getId());
            if( players.length - 1 != i)
            {
            playerIds = playerIds + ", ";
            }
        }
        
        String s = "\nID: " + String.valueOf(id) + "\nName: " + String.valueOf(name) + "\nPlayers: " + playerIds;
        return s;
    }
    
}
