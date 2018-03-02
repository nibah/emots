package match;

/** Represents a player in the league.
 *
 * @author DamnMyCode
 */
public class Player {
    
    private int id;
    private String name;
    private PlayerPosition position;
    private int stars;
    private boolean eligible;
    
    /**
     * 
     * @param id            id number
     * @param name          name
     * @param position      player position
     * @param stars         number of stars
     */
    public Player (int id, String name, PlayerPosition position, int stars){
        this.id = id;
        this.name = name;
        this.position = position;
        this.stars = stars;
        eligible = true;
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
     * @return              player position 
     */
    public PlayerPosition getPosition (){
        return position;
    }
    
    /** Sets eligibility of player.
     * 
     * @param bln           true if player should be eligible next match, false if not.
     */
    public void setEligible (boolean bln){
        this.eligible = bln;
    }
    
    @Override
    public String toString(){
        String s = "\nID: " + String.valueOf(id) + "\nName: " + String.valueOf(name) + "\nPlayer position: " + String.valueOf(position) + "\nStars: " + String.valueOf(stars) + "\nEligible: " + String.valueOf(eligible);
        return s;
    }

}
