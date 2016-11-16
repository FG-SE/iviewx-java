package generic;

/** This interface imposes a chronological ordering on the objects of each class
 *  that implements it.
 *  <p>
 *  It extends {@link Comparable} with the methods {@link #before(TimeComparable) before}
 *  and {@link #after(TimeComparable) after}.
 * 
 *  @author Luca Fuelbier
 *
 *  @param <T> The type of objects that this object may be compared to
 */
public interface TimeComparable<T> extends Comparable<T> {
	
	/** Compares this object with the other object for chronological order.
	 *  Returns <em>true</em> if this object happened earlier than the other.
	 * 
	 *  @param other The object to be compared
	 * 
	 *  @return <em>true</em> if the object happened earlier
	 */
	public default boolean before(T other) {
		return this.compareTo(other) < 0;
	}
	
	/** Compares this object with the other object for chronological other.
	 *  Returns <em>true</em> if this object happened later than the other.
	 * 
	 *  @param other The object to be compared
	 *  
	 *  @return <em>true</em> if the object happened later
	 */
	public default boolean after(T other) {
		return this.compareTo(other) > 0;
	}
	
}
