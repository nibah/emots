package match;

/** Represents the position taken by players on the field
 *
 * @author nibah
 */
public enum FieldPosition {
   GOALIE, DEFENDER, MIDFIELDER, ATTACKER, RIGHT_WINGER, LEFT_WINGER;
   
   /** Decides if a FieldPosition is a part of another FieldPosition, such as
    * LEFT_DEFENDER is part of DEFENDER
    * 
    * @param fieldPosition the "parent" FieldPosition
    * @return true if this Object is a child of fieldPosition, false otherwise
    */
   public boolean isA(FieldPosition fieldPosition) {
       switch (fieldPosition) {
           case GOALIE      : return this == GOALIE;
           case DEFENDER    : return this == DEFENDER;
           case MIDFIELDER  : return this == MIDFIELDER ||
                                     this == LEFT_WINGER ||
                                     this == RIGHT_WINGER;
           case ATTACKER    : return this == ATTACKER;
           default          : return false;
       }
   }
}
