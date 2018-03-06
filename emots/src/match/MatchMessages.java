package match;

import java.util.ArrayList;
import java.util.List;

/** An object collecting all of the messages concerning a given Match to
 * communicate with the user.
 *
 * @author nibah
 */
public class MatchMessages implements Messaging {
    private List<String> messages;      // a list containing all of the messages
    
    /** Constructs an object collecting messages about a given Match */
    public MatchMessages() {
        messages = new ArrayList<>();
    }

    /** Adds a new message to the end of the already existing messages.
     * 
     * @param message       a String containing the new message.
     */
    @Override
    public void addMessage(String message) {
        messages.add(message);
    }

    /** Adds several new messages to the end of the already existing messages.
     * 
     * @param messages      an array containing all of the new messages.
     * @throws IllegalArgumentException if messages is null
     */
    @Override
    public void addMessages(String[] messages) {
        if (messages == null)
            throw new IllegalArgumentException("null is not a valid parameter.");
        for (String message : messages)
            addMessage(message);
    }

    /** Fetches a single message specified by the index parameter.
     * 
     * @param index     the index of the message which should be returned
     * @return          a String containing the message with the specified index
     * @throws IllegalArgumentException if index is smaller than 0 or greater than
     *         the biggest message index
     */
    @Override
    public String getMessage(int index) {
        if (index >= messages.size() || index < 0)
            throw new IllegalArgumentException("There is no message with the given index");
        return messages.get(index);
    }

    /** Fetches all the messages with an index between the parameters begin and
     * end (each inclusive). More specifically the message M is included in the
     * result if and only if begin <= M_index <= end.
     * 
     * @param begin         the smallest index the resulting messages may have
     * @param end           the biggest index the resulting messages may have
     * @return an array containing all of the messages which fulfil the requirements
     * described above
     * @throws IllegalArgumentException if begin is greater than end, or if the interval
     *                                  specified by begin and end is not a sub-interval
     *                                  of the message-indexes
     */
    @Override
    public String[] getMessages(int begin, int end) {
        if (begin > end)
            throw new IllegalArgumentException("The begin parameter should be smaller "
                                             + "than the end parameter");
        if (begin < 0 || end >= messages.size())
            throw new IllegalArgumentException("The given begin-end interval is not "
                    + "a subset of the index-range of the messages.");
        List<String> result = new ArrayList<>();
        for (int i = begin; i<=end; i++)
            result.add(messages.get(i));
        return result.toArray(new String[0]);
    }

    /** Fetches all the messages saved by this object.
     * 
     * @return an array containing all of the messages.
     */
    @Override
    public String[] getAllMessages() {
        return messages.toArray(new String[0]);
    }

    /** Gets the number of messages saved by this object.
     * 
     * @return the number of messages in total
     */
    @Override
    public int numberOfMessages() {
        return messages.size();
    }
    
    @Override
    public String toString() {
        String output = new String();
        for (String message : getAllMessages())
            output += message + "\n";
        output = output.substring(output.length()-1);
        return "Match Messages (" + numberOfMessages() + ")" + "\n" +
                output;
    }
}
