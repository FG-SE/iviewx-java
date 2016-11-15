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

	/** Retrieves the next eyetracking event from the event list.
	 * 
	 *  @return Eyetracking event
	 *  
	 *  @throws java.util.NoSuchElementException If the receiver has no more events
	 */
	@Override
	public ETEvent next() {
		return eventsIter.next();
	}
	
	/** Returns <em>true</em> if the event receiver has more events.
	 * 
	 *  @return <em>true</em> if the receiver has more events
	 */
	@Override
	public boolean hasNext() {
		return eventsIter.hasNext();
	}
	
	/** Resets the EventReceiver iteration to the beginning of the event list.
	 *  <p>
	 *  Resetting essentially means, that you will iterate the event list again. 
	 *  This can be very helpful if you want to analyze the same set of data multiple times.
	 */
	public void reset() {
		eventsIter = events.iterator();
	}
	
	/** Sets the event list that will be used for sample retrieval.
	 *  <p>
	 *  This will also reset the iteration of the receiver for the new list.
	 * 
	 *  @param events Event list
	 */
	public void setEvents(ETSortedEventList events) {
		this.events = events;
		eventsIter = events.iterator();
	}

}
