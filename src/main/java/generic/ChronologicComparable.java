package generic;

/** This interface imposes a chronological ordering on the objects of each class
 *  that implements it.
 *  <p>
 *  It works a lot like the {@link Comparable} interface for the standard library.
 * 
 *  @author Luca Fuelbier
 *
 *  @param <T> The type of objects that this object may be compared to
 */
public interface ChronologicComparable<T> {
	
	/** Compares this object with the other for chronological order.
	 *  Returns a negative integer, zero, or a positive integer as this
	 *  object originated before, at the same time, or after the other object.
	 *  
	 *  @param other The Object to be compared
	 *  @return A negative integer, zero, or a positive integer as this
	 *          object originated before, at the same time, or after the
	 *          other object.
	 */
	public int chrCompareTo(T other);
	
	/** Compares this object with the other object for chronological order.
	 *  Returns <em>true</em> if this object originated earlier than the other.
	 * 
	 *  @param other The object to be compared
	 * 
	 *  @return <em>true</em> if the object originated earlier
	 */
	public default boolean before(T other) {
		return this.chrCompareTo(other) < 0;
	}
	
	/** Compares this object with the other object for chronological other.
	 *  Returns <em>true</em> if this object originated later than the other.
	 * 
	 *  @param other The object to be compared
	 *  
	 *  @return <em>true</em> if the object originated later
	 */
	public default boolean after(T other) {
		return this.chrCompareTo(other) > 0;
	}
	
}
