package event;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collections;

/** List of eyetracking events sorted by their start- and end-timestamps.
 *  <p>
 *  This container guarantees a chronologically correct order of held eyetracking events.
 *  <p>
 *  Only a subset of the typical Java list interface is provided to prevent the user 
 *  from issuing very performance heavy tasks. However a Iterable interface is implemented for 
 *  easy traversal through the list.
 *  <p>
 *  Make sure, that the eyetracking events that you add to this container are already 
 *  in order. This has to be because a very crude algorithm is used to maintain chronological 
 *  correctness. Eyetracking events that do not match the chronological order of insertion are 
 *  simply not inserted but ignored. This is very fast, but may be undesirable if you have a 
 *  unsorted collection of eyetracking events. In that case you will have to sort the eyetracking 
 *  events before adding them to the collection. Since eyetracking event datasets can become quite 
 *  large think about persistently sorting them beforehand.
 *  <p>
 *  Future versions of this library might include other variations of this class, which automate the 
 *  above process. Keep in mind, that sorting the data beforehand will still provide you with the best 
 *  performance.
 *   
 *  @author Luca Fuelbier
 */
public class ETSortedEventList implements Iterable<ETEvent> {
	
	private ArrayList<ETEvent> events;
	
	/** Constructs a new sorted eyetracking event list.
	 *  <p>
	 *  The list is initially empty, events have to be added manually.
	 */
	public ETSortedEventList() {
		events = new ArrayList<ETEvent>();
	}
	
	/** Adds the given eyetracking event to the list, if it fits the chronological order. 
	 *  If the chronological order would be broken by inserting the event, it is ignored.
	 *  <p>
	 *  A event fits the chronological order, if the previously inserted event has a end timestamp 
	 *  that comes before or at the same time as the start-timestamp of the event that is being inserted. 
	 *  If it is greater, the event will not be inserted and the list will therefore not be altered.
	 * 
	 *  @param event Eyetracking event
	 */
	public void addIgnore(ETEvent event) {
		ETEvent last = last();
		
		if(last == null)
			events.add(event);
		else if(last.getEndTime() <= event.getStartTime())
			events.add(event);
		else
			return;
	}
	
	/** Returns the size of the list, or 0 if empty.
	 * 
	 *  @return Size of the list
	 */
	public int size() {
		return events.size();
	}
	
	/** Returns the eyetracking event at the specified position in the list.
	 * 
	 * @param index Index of the element to return
	 * 
	 * @return Eyetracking event at the specified position in this list
	 * 
	 * @throws IndexOutOfBoundsException If the index is out of range
	 */
	public ETEvent get(int index) throws IndexOutOfBoundsException {
		return events.get(index);
	}
	
	/** Returns the last eyetracking event of the list, or null if the list is empty.
	 * 
	 *  @return Eyetracking event at the last position in the list, or null if the list is empty
	 */
	private ETEvent last() {
		if(events.size() == 0)
			return null;
		else
			return events.get(events.size()-1);
	}

	/** Returns an iterator over the elements in this list in proper sequence.
	 *  <p>
	 *  The list can not be manipulated by using the iterator.
	 *  If manipulation is performed, an exception will be thrown. 
	 *  
	 *  @return An iterator over the elements in this list in proper sequence
	 */
	@Override
	public Iterator<ETEvent> iterator() {
		return Collections.unmodifiableList(events).iterator();
	}

}
