package match;

/** Type of the winner of a game
 *
 * @author nibah
 */
public enum Winner {
    Home, Guest, Draw,
    AutomaticHome,      //Automatic win of home team if guest team is unable to present a legal Tactic 
    AutomaticGuest,     //Automatic win of guest team if home team is unable to present a legal Tactic
    AutomaticDraw,      //Automatic draw if both teams are unable to present a legal Tactic
    Undefined          //Undefined winner (i.e. not evaluated yet)
}