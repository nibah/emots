package match;

/** Represents all objects which create messages to communicate with the user.
 *
 * @author nibah
 */
public interface Messaging {
    /** Adds a new message to the end of the already existing messages.
     * 
     * @param message       a String containing the new message.
     */
    void addMessage(String message);
    
    /** Adds several new messages to the end of the already existing messages.
     * 
     * @param messages      an array containing all of the new messages.
     */
    void addMessages(String[] messages);
    
    /** Fetches a single message specified by the index parameter.
     * 
     * @param index     the index of the message which should be returned
     * @return          a String containing the message with the specified index
     */
    String getMessage(int index);
    
    /** Fetches all the messages with an index between the parameters begin and
     * end (each inclusive). More specifically the message M is included in the
     * result if and only if begin <= M_index <= end.
     * 
     * @param begin         the smallest index the resulting messages may have
     * @param end           the biggest index the resulting messages may have
     * @return an array containing all of the messages which fulfil the requirements
     * described above
     */
    String[] getMessages(int begin, int end);
    
    /** Fetches all the messages saved by this object.
     * 
     * @return an array containing all of the messages.
     */
    String[] getAllMessages();
    
    /** Gets the number of messages saved by this object.
     * 
     * @return the number of messages in total
     */
    int numberOfMessages();
}
