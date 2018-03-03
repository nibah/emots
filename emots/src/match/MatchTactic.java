package match;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/** Represents the match-tactics of a team.
 *
 * @author nibah
 */
public class MatchTactic {
    private Map<Player, FieldPosition> formation;
            /* Represents the formation the players play on the field. */
    private int numberOfSubs;    // The number of substitutions which already took place
    private int numberOfChanges; /* The number of onfield changes which already took place,
                                   excluding numberOfSubs */
    
    /** Constructs a MatchTactic using the given HashMap<Player, FieldPostion>
     * as the formation. This constructor does not validate if that is a legal
     * formation. The number of substitutions and changes are initialised with 0.
     * 
     * @param formation     the formation of the players
     */
    public MatchTactic(Map<Player, FieldPosition> formation) {
        this.formation = formation;
        numberOfSubs = 0;
        numberOfChanges = 0;
    }
    
    //TODO: Constructor which creates a MatchTactic based on a given TeamTactic
    //public MatchTactic(TeamTactic tactic) {};
    
    /** Returns the HasMap<Player, FieldPosition> containing the formation of
     * the players on the field.
     * 
     * @return the HashMap<Player, FieldPosition> containing the formation
     */
    public Map<Player, FieldPosition> getFormation() {return formation;}
    
    /** Returns the number of substitutions, which already took place
     * 
     * @return the number of used substitutions
     */
    public int getNumberOfSubs() {return numberOfSubs;}
    
    /** Returns the number of changes, which already took place
     * 
     * @return the number of used changes
     */
    public int getNumberOfChanges() {return numberOfChanges;}
    
    //TODO
    //public FormationPattern convertToPattern(){};
    
    /** Changes the FieldPosition of a single player. As a result, the number
     * of changes will be incremented by 1.
     * 
     * @param player        the player whose FieldPosition should be changed
     * @param newPosition   the new FieldPosition of the player
     * 
     * @throws NoSuchPlayerException    if the player is not on the field
     * @throes NullPointerException     if the player is null
     */
    public void changePosition(Player player, FieldPosition newPosition) {
        if (player == null)
            throw new NullPointerException("null is not a valid parameter.");
        if (!formation.containsKey(player))
            throw new NoSuchPlayerException("The player, is not on the field. "
                                            + "To substitute, use substitute(Player down, Player up)");
        formation.put(player, newPosition);
        numberOfChanges++;
    }
    
    /** Returns all of the players that are currently playing on the field.
     * 
     * @return an array containing all of the players currently playing
     */
    public Player[] getPlayers() {
        return formation.keySet().toArray(new Player[0]);
    }
    
    /** Looks for the players who are currently playing on the given
     * FieldPosition or on any sub-FieldPosition of it.
     * 
     * @param fieldPosition     the desired field position of the players
     * @return an array containing all of the Players playing on the given FieldPosition
     */
    public Player[] getPosition(FieldPosition fieldPosition) {
        List<Player> players = new ArrayList<>();
        for (Entry<Player, FieldPosition> entry : formation.entrySet()) {
            Player currentPlayer = entry.getKey();
            FieldPosition currentFieldPosition = entry.getValue();
            if (currentFieldPosition.isA(fieldPosition))
                players.add(currentPlayer);
        }
        return players.toArray(new Player[0]);
    }
    
    /** Looks for all the players, which have the given PlayerPosition. As such,
     * the result does not directly have anything to do with the formation.
     * 
     * @param playerPosition    the desired PlayerPosition of the players
     * @return an array containing all of the Players having the fiven PlayerPosition
     */
    public Player[] getPosition(PlayerPosition playerPosition) {
        List<Player> players = new ArrayList<>();
        for (Player currentPlayer : formation.keySet())
            if (currentPlayer.getPosition() == playerPosition)
                players.add(currentPlayer);
        return players.toArray(new Player[0]);
    }
    
    public boolean matchesPattern(FormationPattern pattern) {
        if (pattern.numberOfPlayers() != getPlayers().length)
            return false;
        for (FieldPosition fieldPosition : pattern.getPattern().keySet())
            if (pattern.numberOf(fieldPosition) != this.numberOf(fieldPosition, true))
                return false;
        return true;
    }
    
    public int numberOf(FieldPosition fieldPosition) {
        int count = 0;
        for (FieldPosition currentPosition : formation.values())
            if (currentPosition == fieldPosition)
                count++;
        return count;
    }
    
    public int numberOf(FieldPosition fieldPosition, boolean countSubpositions) {
        if (!countSubpositions)
            return numberOf(fieldPosition);
        int count = 0;
        for (FieldPosition currentPosition : formation.values())
            if (currentPosition.isA(fieldPosition))
                count++;
        return count;
    }
    
    /** Substitues a player on the field with another player on the bench. As a
     * result, the number of substitutes will be incremented by 1.
     * 
     * @param down      the player who should leave the field
     * @param up        the player on the bench who should substitute the other one
     * 
     * @throws NullPointerException             if down or up is null
     * @throws NoSuchPlayerException            if the down player is not on the field
     * @throws InvalidSubstitutionException     if the up player is already on the field
     */
    public void substitute(Player down, Player up) {
        if (down == null || up == null)
            throw new NullPointerException("null is not a valid parameter.");
        if (!formation.containsKey(down))
            throw new NoSuchPlayerException("The player, which should have been "
                                            + "substituted is not on the field");
        if (formation.containsKey(up))
            throw new InvalidSubstitutionException("The player is already on the field");
        FieldPosition position = formation.remove(down);
        formation.put(up, position);
        numberOfSubs++;
    }
    
    /** Switches the FieldPosition of two players, who are currently on the field
     * 
     * @param from      the first player
     * @param to        the second player
     * 
     * @throws NoSuchPlayerException    if at least one of the players
     *                                  is not currently on the field
     * @throes NullPointerException     if at least one of the players is null
     */
    public void switchPlayers(Player from, Player to) {
        if (from == null || to == null)
            throw new NullPointerException("null is not a valid parameter");
        if (!formation.containsKey(from) || !formation.containsKey(to))
            throw new NoSuchPlayerException("The player is not on the field");
        FieldPosition temp = formation.get(from);
        formation.put(from, formation.get(to));
        formation.put(to, temp);
        numberOfChanges++;
    }
    
    @Override
    public String toString() {
        String formationString = new String();
        for (Entry<Player, FieldPosition> entry : formation.entrySet()) {
            Player player = entry.getKey();
            FieldPosition position = entry.getValue();
            formationString += player.getName() + " - " + position + ",\n";
        }
        formationString = formationString.substring(0, formationString.length()-2);
        
        return "MATCH TACTICS" + "\n" +
               formationString + "\n" +
               "Substitutions used: " + numberOfSubs + "\n" +
               "Changes used: " + numberOfChanges;
    }
}
