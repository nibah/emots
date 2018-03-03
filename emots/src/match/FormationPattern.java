package match;

import java.util.Map;
import java.util.Map.Entry;

/** Represents a formation pattern such as 4-4-2 or 3-4-3. Every FieldPosition is
 * assigned a number which determines how many players play simultaneously on that
 * position.
 *
 * @author nibah
 */
public class FormationPattern {
    private Map<FieldPosition, Integer> pattern;
            /* Each FieldPosition is mapped to the number of players who play
               on that position. */
    
    /** Constructs a formation pattern based on the parameter
     * 
     * @param pattern       a Map mapping each FieldPosition to the number of players
     *                      who play on that position. If a FieldPosition is not used
     *                      in this formation pattern, it does not have to be mapped.
     */
    public FormationPattern(Map<FieldPosition, Integer> pattern) {
        this.pattern = pattern;
    }
    
    /** Returns the formation pattern this object is representing
     * 
     * @return a Map<FieldPosition, Integer> containing the formation pattern
     */
    public Map<FieldPosition, Integer> getPattern() {return pattern;}
    
    /** Counts the number of players playing on a given FieldPosition.
     * Sub-FieldPositions are not counted. To count Sub-FieldPositions use
     * numberOf(FieldPosition fieldPosition, true).
     * 
     * @param fieldPosition     the field position
     * @return the number of players who play on the given field position
     */
    public int numberOf(FieldPosition fieldPosition) {
        if (pattern.containsKey(fieldPosition))
            return pattern.get(fieldPosition);
        return 0;
    }
    
    /** Counts the number of players playing on a given FieldPosition. If
     * countSubpositions is true, Sub-Fieldpositions are also counted.
     * 
     * @param fieldPosition         the field position
     * @param countSubpositions     if true, sub-FieldPositions are also counted
     * @return the number of players
     */
    public int numberOf(FieldPosition fieldPosition, boolean countSubpositions) {
        if (!countSubpositions)
            return numberOf(fieldPosition);
        int players = 0;
        for (Entry<FieldPosition, Integer> entry : pattern.entrySet()) {
            FieldPosition currentPosition = entry.getKey();
            int currentPlayers = entry.getValue();
            if (currentPosition.isA(fieldPosition))
                players += currentPlayers;
        }
        return players;
    }
    
    /** Returns the total number of players playing in this formation pattern.
     * Usually 11.
     * 
     * @return      the number of players
     */
    public int numberOfPlayers() {
        int players = 0;
        for (Integer currentPlayers : pattern.values())
            players += currentPlayers;
        return players;
    }
    
    /** Adds a field position and the number of players playing on that position
     * to the already existing pattern. Should only be used from child classes.
     * 
     * @param position          the new field position
     * @param numberOfPlayers   the number of players playing on the new
     *                          field position
     */
    protected void addFieldPosition(FieldPosition position, int numberOfPlayers) {
        pattern.put(position, numberOfPlayers);
    }
    
    @Override
    public String toString() {
        String output = new String();
        for (Entry<FieldPosition, Integer> entry : pattern.entrySet()) {
            FieldPosition position = entry.getKey();
            int numberOfPlayers = entry.getValue();
            output += position + ": " + numberOfPlayers + "\n";
        }
        return output.substring(0, output.length()-1);
    }
}
