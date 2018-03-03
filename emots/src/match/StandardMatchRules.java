package match;

import java.util.ArrayList;
import java.util.List;

/** Represents very simple match rules: constant number of rounds, no tie breaks.
 * Allowed formations are 3-5-2, 3-4-3, 4-5-1, 4-4-2, 4-3-3, 5-3-2, 5-4-1 by default.
 * Uneligible players are not allowed to play.
 *
 * @author nibah
 */
public class StandardMatchRules implements MatchRules {
    private int round;      // the current round the match is in now
    private List<String> illegalTacticMessages;
            /* A list of error messages containing detailed description why
               the last tactic checked with allowedTactic(Tactic) is not legal.
               If the tactic was legal, this list is empty.*/
    private final int maxRounds;    // maximal amount of rounds in a match
    private final int minPlayers;   // minimal number of Players on the field
    private final int maxPlayers;   // maximal number of Players on the field
    private final int maxSubs;
            /* maximal number of substitutions a team is allowed to do during
               one game. */
    private final boolean subBackAllowed;
            /* true if a player can replace another one after being replaced
               herself, false otherwise*/
    private final FormationPattern[] allowedPatterns;
            /* an array containing all of the legal formation patterns */
    
    /** Constructs standard match rules with 6 rounds, a maximal of 3 substitutions
     * with substitute backs being illegal, 9 to 11 players on the field. Uneligible
     * players are not allowed to play. There are no tie-breaks. Allowed formations
     * are: 3-5-2, 3-4-3, 4-5-1, 4-4-2, 4-3-3, 5-3-2, 5-4-1. 
     */
    public StandardMatchRules() {
        maxRounds = 6;
        maxSubs = 3;
        subBackAllowed = false;
        minPlayers = 9;
        maxPlayers = 11;
        allowedPatterns = new FormationPattern[]{
            new StandardFormationPattern(3,5,2),
            new StandardFormationPattern(3,4,3),
            new StandardFormationPattern(4,5,1),
            new StandardFormationPattern(4,4,2),
            new StandardFormationPattern(4,3,3),
            new StandardFormationPattern(5,3,2),
            new StandardFormationPattern(5,4,1)
        };
        round = 1;
        illegalTacticMessages = new ArrayList<>();
    }
    
    /** Constructs standard match rules with no tie breaks. Uneligible players
     * are not allowed to play.
     * 
     * @param allowedFormations     an array containing all legal FormationPatterns
     * @param rounds                the number of rounds in a match
     * @param minPlayers            the minimum number of players on the field
     * @param maxPlayers            the maximum number of players on the field
     * @param maxSubs               the maximum number of substitutions one team
     *                              is allowed to do
     * @param subBackAllowed        true if it should be allowed to a substituted
     *                              player to substitute someone herself
     */
    public StandardMatchRules(FormationPattern[] allowedFormations, int rounds,
            int minPlayers, int maxPlayers, int maxSubs, boolean subBackAllowed) {
        if (allowedFormations == null)
            throw new IllegalArgumentException("null is not a valid parameter");
        if (allowedFormations.length == 0)
            throw new IllegalArgumentException("At least one FormationPattern should be allowed");
        if (rounds < 0)
            throw new IllegalArgumentException("maxRounds should be a positive integer");
        if (minPlayers > maxPlayers)
            throw new IllegalArgumentException("minPlayers should be smaller or equal than maxPlayers");
        if (maxPlayers < 0)
            throw new IllegalArgumentException("maxPlayers should be a positive integer");
        if (maxSubs < 0)
            throw new IllegalArgumentException("maxSubs should be a positive integer");
        
        this.allowedPatterns = allowedFormations;
        this.maxRounds = rounds;
        this.minPlayers = minPlayers;
        this.maxPlayers = maxPlayers;
        this.maxSubs = maxSubs;
        this.subBackAllowed = subBackAllowed;
        round = 1;
        illegalTacticMessages = new ArrayList<>();
    }
    
    /** Returns the current round the match is in.
     * 
     * @return the number of the current round.
     */
    public int getCurrentRound() {return round;}
    
    /** Returns the error messages explaining why the tactic last examined with
     * allowedTactic(MatchTactic) is illegal. If the tactic was legal or if no
     * tactics were examined yet, an empty array will be returned.
     * 
     * @return an array containing all of the error messages
     */
    public String[] getIllegalTacticMessages() {
        return illegalTacticMessages.toArray(new String[0]);
    }
    
    /** Returns how many rounds will be played in all
     * 
     * @return the number of rounds that will be played in all
     */
    public int getMaxRounds() {return maxRounds;}
    
    /** Returns how many players have to be on the field at least for a tactic
     * to be legal.
     * 
     * @return the number of players
     */
    public int getMinPlayers() {return minPlayers;}
    
    /** Returns how many players can be on the field at most for a tactic to
     * be legal.
     * 
     * @return the number of players
     */
    public int getMaxPlayers() {return maxPlayers;}
    
    /** Returns how many substitutions in total are allowed for a team during
     * one match.
     * 
     * @return the number of substitutions
     */
    public int getMaxSubs() {return maxSubs;}
    
    /** Returns all the legal formations patterns
     * 
     * @return an array containing the formation patterns
     */
    public FormationPattern[] getFormationPatterns() {return allowedPatterns;}
    
    /** Examines if a given MatchTactic fulfills the requirements specified in
     * this MatchRules object to be a legal tactic. This method modifies the error
     * messages for later access via getIllegalTacticMessages()
     * 
     * @param tactic    the tactic that should be examined
     * @return true if the tactic is legal, false otherwise
     */
    @Override
    public boolean allowedTactic(MatchTactic tactic) {
        illegalTacticMessages.clear();
        Player[] players = tactic.getPlayers();
        int substitutions = tactic.getNumberOfSubs();
        int playersOnField = players.length;
        boolean allPlayersEligible = allPlayersEligible(players);
        boolean legalNumberOfSubs = legalNumberOfSubstitutions(substitutions);
        boolean legalNumberOfPlayers = legalNumberOfPlayers(playersOnField);
        boolean allowedFormation  = allowedFormationPattern(tactic);
        return  allPlayersEligible &&
                legalNumberOfSubs &&
                legalNumberOfPlayers &&
                allowedFormation;
    }
    
    /** Checks if a given tactic is using one of the allowed FormationPatterns.
     * This method modifies the error messages for later access via
     * getIllegalTacticMessages()
     * 
     * @param tactic the tactic which should be examined
     * @return true if the given tactic is using one of the allowed formation
     *         patterns, false otherwise
     */
    private boolean allowedFormationPattern(MatchTactic tactic) {
        boolean allowed = false;
        for (FormationPattern pattern : allowedPatterns) {
            if (tactic.matchesPattern(pattern)) {
                allowed= true;
                break;
            }
        }
        if (!allowed) {
            illegalTacticMessages.add("This is not an allowed formation.");
            return false;
        }
        return true;
    }
    
    /** Checks if all players in a Player[] array are eligible to play.
     * This method modifies the error messages for later access via 
     * getIllegalTacticMessages() (One message is added for each uneligible
     * player)
     * 
     * @param players       an array containing the players to be examined
     * @return true if all of the players are eligible, false otherwise
     */
    private boolean allPlayersEligible(Player[] players) {
        boolean legal = true;
        for (Player currentPlayer : players)
            if (!currentPlayer.isEligible()) {
                illegalTacticMessages.add(currentPlayer.getName() + 
                                          " is not eligible to play.");
                legal = false;
            }
        return legal;
    }

    /** Determines if a new round can begin, or if the current round was the
     * last one
     * 
     * @return true if the current round was not the last one, false otherwise
     */
    @Override
    public boolean hasNextRound() {
        return round < maxRounds;
    }
    
    /** Checks if a given number is a legal number of players on the field. To
     * decide this, this method compares the parameter playersOnField with the
     * maximal and minimal number of players. This method modifies the error messages
     * for later access via getIllegalTacticMessages()
     * 
     * @param playersOnField    the number of players currently on the field
     * @return true if the number of players is legal, false otherwise
     */
    private boolean legalNumberOfPlayers(int playersOnField) {
        if (playersOnField < minPlayers) {
            illegalTacticMessages.add("Not enough players on the field.");
            return false;
        }
        if (playersOnField > maxPlayers) {
            illegalTacticMessages.add("Too many players on the field.");
            return false;
        }
        return true;
    }
    
    /** Checks if a given number is greater then the maximal amount of
     * substitutions one team is allowed to do during a game.
     * 
     * @param substitutions the number of substitutions that took place already
     * @return true if the substitution limit has not been exceeded, false
     *         otherwise
     */
    private boolean legalNumberOfSubstitutions(int substitutions) {
        if (substitutions > maxSubs) {
            illegalTacticMessages.add("No substitutions left.");
            return false;
        }
        return true;
    }
    
    /** A method to signal the end of a round to the StandardMatchRules object.
     * This should be called at the end of every round. */
    @Override
    public void roundEnd() {
        if (hasNextRound())
            round++;
    }
    
    /** Decides if it is legal for a player to substitute another one even
     * though she herself was substituted before.
     * 
     * @return true if it is legal, false otherwise
     */
    @Override
    public boolean substituteBackAllowed() {
        return subBackAllowed;
    }
    
    @Override
    public String toString() {
        String legalFormationPatterns = new String();
        for (FormationPattern pattern : allowedPatterns)
            legalFormationPatterns += pattern + "\n";
        
        return "Round: " + round + "/" + maxRounds + "\n" +
               "Players: " + minPlayers + " to " + maxPlayers + "\n" +
               "Maximum Substitutions: " + maxSubs + "\n" +
               "Substitute Backs: " + (subBackAllowed ? "allowed" : "banned") + "\n" +
               "Legal Formation Patterns: " + "\n" +
                legalFormationPatterns;
    }
}
