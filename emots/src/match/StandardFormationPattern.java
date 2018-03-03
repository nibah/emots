package match;

import java.util.HashMap;

/** Formation pattern using only the following FieldPositions: GOALIE, DEFENDER,
 * MIDFIELDER, ATTACKER. The number of goalies is always 1;
 *
 * @author nibah
 */
public class StandardFormationPattern extends FormationPattern{
    /** Constructs a standard formation pattern using only GOALIEs (always 1),
     * DEFENDERS, MIDFIELDERS and ATTACKERS
     * 
     * @param defenders     the number of defenders
     * @param midfielders   the number of midfielders
     * @param attackers     the number of attackers
     */
    public StandardFormationPattern(int defenders, int midfielders, int attackers) {
        super(new HashMap<>());
        super.addFieldPosition(FieldPosition.GOALIE, 1);
        super.addFieldPosition(FieldPosition.DEFENDER, defenders);
        super.addFieldPosition(FieldPosition.MIDFIELDER, midfielders);
        super.addFieldPosition(FieldPosition.ATTACKER, attackers);
    }
    
    @Override
    public String toString(){
        return numberOf(FieldPosition.DEFENDER) + "-" +
               numberOf(FieldPosition.MIDFIELDER) + "-" +
               numberOf(FieldPosition.ATTACKER);
    }
}
