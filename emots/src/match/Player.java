package match;

/** Represents a player in the league.
 *
 * @author DamnMyCode
 */
public class Player {
    private static int numberOfPlayers = 0;
    
    private int id;
    private String name;
    private PlayerPosition position;
    private int stars;
    private boolean eligible;
    
    /**
     * 
     * @param name          name
     * @param position      player position
     * @param stars         number of stars
     */
    public Player (String name, PlayerPosition position, int stars){
        id = numberOfPlayers++;
        this.name = name;
        this.position = position;
        this.stars = stars;
        eligible = true;
    }
    
    /**
     * 
     * @return true if the player is eligible, false otherwise
     */
    public boolean isEligible() {
        return eligible;
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
     * @return the name of the player followed by a number of * characters
     * representing the stars of the players.
     */
    public String getNameStars() {
        String starString = "(";
        for (int i = 0; i<stars; i++)
            starString += "*";
        starString += ")";
        return name + starString;
    }
    
    /**
     * 
     * @return              player position 
     */
    public PlayerPosition getPosition (){
        return position;
    }
    
    /**
     * 
     * @return              the amount of stars the player has
     */
    public int getStars() {
        return stars;
    }
    
    /** Sets eligibility of player.
     * 
     * @param eligible      true if player should be eligible next match, false if not.
     */
    public void setEligible (boolean eligible){
        this.eligible = eligible;
    }
    
    @Override
    public String toString(){
        String s = "\nID: " + String.valueOf(id) + "\nName: " + String.valueOf(name) + "\nPlayer position: " + String.valueOf(position) + "\nStars: " + String.valueOf(stars) + "\nEligible: " + String.valueOf(eligible);
        return s;
    }

}
