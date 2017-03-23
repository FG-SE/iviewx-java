package generic;

import java.util.Iterator;

/** Receives chronologically ordered data from a {@link generic.ETChronologicCollection}.
 *  <p>
 *  If you want to iterate the data from the collection again you will 
 *  have to call the {@link #reset() reset} method before requesting a new item.
 *  <p>
 *  A {@link generic.ETChronologicCollection} is used to make sure, that events are sorted 
 *  beforehand, to reduce computation effort while receiving events.
 * 
 *  @author Luca Fuelbier
 */
public class ETPlaybackReceiver<E extends ChronologicComparable<E>> extends ETReceiver<E> {

	ETChronologicCollection<E> elements;
	Iterator<E> elementsIter;
	ETStabilizationStrategy<E> stabilizationStrategy;
	
	/** Constructs a new ETPlaybackReceiver with a empty collection and a
	 *  default stabilization strategy.
	 */
	public ETPlaybackReceiver() {
		this(new ETChronologicCollection<>(), new ETDefaultStabilizationStrategy<>());
	}
	
	/** Constructs a new ETPlaybackReceiver with the given collection and
	 *  stabilization strategy.
	 * 
	 *  @param elements Collection containing the data that will be fetched
	 *  @param strategy Stabilization strategy
	 */
	public ETPlaybackReceiver(ETChronologicCollection<E> elements, ETStabilizationStrategy<E> strategy) {
		super(strategy);
		
		this.elements = elements;
		elementsIter = elements.iterator();
	}
	
	/** Implementation of the {@link generic.ETReceiver#getNextFromSource() getNextFromSource} method.
	 *  New items will be fetched from the supplied collection and wrapped in a {@link generic.ETResponse}.
	 * 
	 *  @return Next data item in the collection wrapped in a {@link generic.ETResponse}
	 */
	@Override
	protected ETResponse<E> getNextFromSource() {
		if(elementsIter.hasNext()) {
			return new ETResponse<E>(ETResponseType.NEW_DATA, elementsIter.next());
		}
		else {
			return new ETResponse<E>(ETResponseType.SOURCE_DEPLETED, null);
		}
	}
	
	/** Resets the collection iteration.
	 *  <p>
	 *  Resetting essentially means, that you will iterate the collection again from the beginning. 
	 *  This can be very helpful if you want to analyze the same set of data multiple times.
	 */
	public void reset() {
		elementsIter = elements.iterator();
	}
	
	/** Sets the collection that will be used for data retrieval.
	 *  <p>
	 *  This will also reset the iteration of the receiver.
	 * 
	 *  @param elements Collection of data
	 */
	public void setElements(ETChronologicCollection<E> elements) {
		this.elements = elements;
		elementsIter = elements.iterator();
	}

}
