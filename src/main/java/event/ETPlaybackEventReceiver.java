package event;

import java.util.Iterator;

/** Receives eyetracking events from a ETSortedEventList.
 *  <p>
 *  If you want to iterate the events from the list again you will 
 *  have to call the reset() method before requesting a new event.
 *  <p>
 *  A sorted event list is used to make sure, that events are sorted 
 *  beforehand to reduce computation effort while receiving events.
 * 
 *  @author Luca Fuelbier
 */
public class ETPlaybackEventReceiver implements ETEventReceiver {
	
	ETSortedEventList events;
	Iterator<ETEvent> eventsIter;
	
	/** Constructs a new Playback EventReceiver.
	 *  <p>
	 *  The event list will initially be empty and has to be set manually.
	 */
	public ETPlaybackEventReceiver() {
		this(new ETSortedEventList());
	}
	
	/** Constructs a new Playback EventReceiver initializing it 
	 *  with a provided ETSortedEventList. 
	 * 
	 * @param events Event list
	 */
	public ETPlaybackEventReceiver(ETSortedEventList events) {
		this.events = events;
		eventsIter = events.iterator();
	}

	/** Retrieves a single eyetracking event from the event list.
	 *  <p>
	 *  The event returned will be the next one in the list, or 
	 *  null if the list is fully iterated or empty.
	 *  <p>
	 *  Note that this class does a full iteration of the event list. 
	 *  Timestamps do not matter.
	 * 
	 *  @return Eyetracking event
	 */
	@Override
	public ETEvent getEvent() {
		if(eventsIter.hasNext())
			return eventsIter.next();
		else
			return null;
	}
	
	/** Resets the EventReceiver to the beginning of the event list.
	 *  <p>
	 *  Resetting essentially means, that you will iterate the event list again. 
	 *  This can be very helpful if you want to analyze the same set of data multiple times.
	 */
	public void reset() {
		eventsIter = events.iterator();
	}
	
	/** Sets the event list that will be used for sample retrieval.
	 * 
	 *  @param events Event list
	 */
	public void setEvents(ETSortedEventList events) {
		this.events = events;
		eventsIter = events.iterator();
	}

}
