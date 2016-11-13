package sample;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/** List of eyetracking samples sorted by their timestamp.
 *  <p>
 *  This container guarantees a chronologically correct order of held eyetracking samples. 
 *  Only a subset of the typical Java list interface is provided to prevent the user 
 *  from issuing very performance heavy tasks. However a Iterable interface is implemented for 
 *  easy traversal through the list.
 *  <p>
 *  Make sure, that the eyetracking samples, that you add to this container are already 
 *  in order. This has to be because a very crude algorithm is used to maintain chronological 
 *  correctness. Eyetracking samples, that do not match the chronological order of insertion are 
 *  simply not inserted but ignored. This is very fast, but may be undesirable if you have a 
 *  unsorted collection of eyetracking samples. In that case you will have to sort the eyetracking 
 *  samples before adding them to the collection. Since eyetracking sample datasets can become quite 
 *  large think about persistently sorting them beforehand.
 *  <p>
 *  Future versions of this library might include other variations of this class, which automate the 
 *  above process. Keep in mind, that sorting the data beforehand will still provide you with the best 
 *  performance.
 *   
 *  @author Luca Fuelbier
 */
public class ETSortedSampleList implements Iterable<ETSample>{

	private ArrayList<ETSample> samples;
	
	/** Constructs a new sorted eyetracking sample list.
	 *  <p>
	 *  The list is initially empty, samples have to be added manually.
	 */
	public ETSortedSampleList() {
		samples = new ArrayList<ETSample>();
	}
	
	/** Adds the given eyetracking sample to the list, if it fits the chronological order. 
	 *  If the chronological order would be broken by inserting the sample, it is ignored.
	 *  <p>
	 *  A sample fits the chronological order, if the previously inserted sample has a timestamp 
	 *  that comes before the one being inserted. If it is equal-to or greater, the sample will 
	 *  not be inserted and the list will therefore not be altered.
	 * 
	 *  @param sample Eyetracking sample
	 */
	public void addIgnore(ETSample sample) {
		ETSample last = last();
		
		if(last == null)
			samples.add(sample);
		else if(last.getTimestamp() < sample.getTimestamp())
			samples.add(sample);
		else
			return;
	}
	
	/** Returns the size of the list, or 0 if empty.
	 * 
	 *  @return Size of the list
	 */
	public int size() {
		return samples.size();
	}
	
	/** Returns the eyetracking sample at the specified position in the list.
	 * 
	 * @param index Index of the element to return
	 * 
	 * @return Eyetracking sample at the specified position in this list
	 * 
	 * @throws IndexOutOfBoundsException If the index is out of range
	 */
	public ETSample get(int index) throws IndexOutOfBoundsException {
		return samples.get(index);
	}
	
	/** Returns the last eyetracking sample of the list, or null if the list is empty.
	 * 
	 *  @return Eyetracking sample at the last position in the list, or null if the list is empty
	 */
	private ETSample last() {
		if(samples.size() == 0)
			return null;
		else
			return samples.get(samples.size()-1);
	}

	/** Returns an iterator over the elements in this list in proper sequence.
	 *  <p>
	 *  The list can not be manipulated by using the iterator.
	 *  If manipulation is performed, an exception will be thrown.
	 *  
	 *  @return An iterator over the elements in this list in proper sequence
	 */
	@Override
	public Iterator<ETSample> iterator() {
		return Collections.unmodifiableList(samples).iterator();
	}
	
}
