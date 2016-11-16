package event;

import java.util.Iterator;

import generic.ETChronologicCollection;

/** Receives eyetracking events from a {@link generic.ETChronologicCollection}.
 *  <p>
 *  If you want to iterate the events from the collection again you will 
 *  have to call the reset() method before requesting a new event.
 *  <p>
 *  A sorted collection is used to make sure, that events are sorted 
 *  beforehand to reduce computation effort while receiving events.
 * 
 *  @author Luca Fuelbier
 */
public class ETPlaybackEventReceiver implements ETEventReceiver {
	
	ETChronologicCollection<ETEvent> events;
	Iterator<ETEvent> eventsIter;
	
	/** Constructs a new Playback EventReceiver.
	 *  <p>
	 *  The event collection will initially be empty and has to be set manually.
	 */
	public ETPlaybackEventReceiver() {
		this(new ETChronologicCollection<>());
	}
	
	/** Constructs a new Playback EventReceiver initializing it 
	 *  with a provided ETChronologicCollection. 
	 * 
	 * @param events Event list
	 */
	public ETPlaybackEventReceiver(ETChronologicCollection<ETEvent> events) {
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
	
	/** Resets the EventReceiver iteration to the beginning of the event collection.
	 *  <p>
	 *  Resetting essentially means, that you will iterate the event collection again. 
	 *  This can be very helpful if you want to analyze the same set of data multiple times.
	 */
	public void reset() {
		eventsIter = events.iterator();
	}
	
	/** Sets the event collection that will be used for sample retrieval.
	 *  <p>
	 *  This will also reset the iteration of the receiver for the new list.
	 * 
	 *  @param events Event list
	 */
	public void setEvents(ETChronologicCollection<ETEvent> events) {
		this.events = events;
		eventsIter = events.iterator();
	}

}
