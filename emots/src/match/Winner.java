package match;

/** Type of the winner of a game
 *
 * @author nibah
 */
public enum Winner {
    HOME, GUEST, DRAW,
    AUTOMATIC_HOME,      //Automatic win of home team if guest team is unable to present a legal Tactic 
    AUTOMATIC_GUEST,     //Automatic win of guest team if home team is unable to present a legal Tactic
    AUTOMATIC_DRAW,      //Automatic draw if both teams are unable to present a legal Tactic
    UNDEFINED          //Undefined winner (i.e. not evaluated yet)
}