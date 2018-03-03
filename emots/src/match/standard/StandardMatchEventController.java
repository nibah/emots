package match.standard;

import match.standard.NoSuchEventException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import match.MatchEvent;
import match.MatchEventController;

/**Represents the standard match event controller
 *
 * @author DamnMyCode
 */
public class StandardMatchEventController implements MatchEventController{

    private Set<MatchEvent> events;
    private int numberOfEvents;
    private Random rand = new Random();
    
    public StandardMatchEventController(Set<MatchEvent> events){
        this.events = events;
        numberOfEvents = events.size();
    }
    
    /**Creates a StandardMatchEventController without any Events.
     * 
     */
    public StandardMatchEventController(){
        events = new HashSet<>();
        numberOfEvents = 0;
    }
    
    /**
     * 
     * @return              Returns a Set of the contained Events.
     */
    public Set<MatchEvent> getEvents(){
        return events;
    }
    /**
     * 
     * @param event         Any MatchEvent
     * @return              True if event is contained in the Set of MatchEvents, false if not.
     */
    public boolean containsEvent(MatchEvent event){
        if(events.contains(event))
            return true;
        else
            return false;
    }
    
    /**Sets the Set of MatchEvents equal to parameter events.
     * 
     * @param events        A Set of MatchEvents
     */
    public void setEvents(Set<MatchEvent> events){
        this.events = events;
        numberOfEvents = events.size();
    }
    
    /**Adds an Event to the Set of MatchEvents.
     * 
     * @param event         Any MatchEvent
     */
    public void addEvent(MatchEvent event){
        events.add(event);
        numberOfEvents++;
    }
    
    /**Removes an Event from the Set of MatchEvents.
     * 
     * @param event         Any MatchEvent
     */
    public void removeEvent(MatchEvent event){
        if(!events.contains(event))
            throw new NoSuchEventException("There is no such MatchEvent in this Set of Events.");
        else
            events.remove(event);
            numberOfEvents--;
    }
    
    /**
     * 
     * @return              Returns a random MatchEvent from the Set of MatchEvents. 
     */
    @Override
    public MatchEvent rollEvent() {
        int i = rand.nextInt(numberOfEvents);
        return events.toArray(new MatchEvent[0])[i];
    }

}
