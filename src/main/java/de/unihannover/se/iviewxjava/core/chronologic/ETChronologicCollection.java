package de.unihannover.se.iviewxjava.core.chronologic;

import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collections;

/** Collection containing chronologically sorted elements. 
 *  <p>
 *  Elements of this Collection must implement the {@link ChronologicComparable} interface.
 * 
 *  @author Luca Fuelbier
 *
 *  @param <E> The type of objects that will be held in this collection
 */
public class ETChronologicCollection<E extends ChronologicComparable<E>> extends AbstractCollection<E> {
	
	private ArrayList<E> elements;
	
	/** Constructs a new ETChronologicCollection.
	 *  <p>
	 *  The collection will initially be empty. 
	 */
	public ETChronologicCollection() {
		elements = new ArrayList<>();
	}
	
	/** Adds an element to the collection.
	 *  <p>
	 *  Adding to this collection is very fast if the added elements are already in chronological order (O(1)).
	 *  If they are not, adding will run in O(n) in the worst case scenario.
	 *  <p>
	 *  Adding elements that originated at the same moment in time will retain the order in which elements
	 *  were added.
	 *  <p>
	 *  Chronological correct order is guaranteed after this method has been called.
	 *  
	 *  @param element Element that will be added to the collection
	 *  
	 *  @return <em>true</em> (as specified by {@link java.util.Collection#add(Object) Collection.add(E)})
	 */
	@Override
	public boolean add(E element) {
		// Collection empty
		if(elements.isEmpty()) {
			elements.add(element);
			return true;
		}
		
		// Element is chronologically last in the collection
		boolean chronologicallyLast = last().chrCompareTo(element) <= 0;
		if(chronologicallyLast) {
			elements.add(element);
			return true;
		}
		
		// Element is chronologically in the middle of the collection
		for(int i = elements.size()-2; i >= 0; i--) {
			if(elements.get(i).chrCompareTo(element) <= 0) {
				elements.add(i+1, element);
				return true;
			}
		}
		
		// Element is chronologically first in the collection
		elements.add(0, element);
		return true;
	}
	
	/** Removes all of the elements from this collection. */
	@Override
	public void clear() {
		elements.clear();
	}

	/** Returns an iterator over the elements contained in this collection.
	 * 
	 *  @return Iterator over the elements contained in this collection
	 */
	@Override
	public Iterator<E> iterator() {
		return Collections.unmodifiableList(elements).iterator();
	}

	/** Returns the number of elements in this collection.
	 *  
	 *  @return The number of elements in this collection
	 */
	@Override
	public int size() {
		return elements.size();
	}
	
	/** Returns the last element of this collection.
	 * 
	 *  @return Last element of this collection
	 */
	private E last() {
		return elements.get(elements.size()-1);
	}

}
