package generic;

/** This class provides a skeletal implementation of a Receiver
 *  that fetches chronologically ordered data.
 *  <p>
 *  To implement a ETReceiver, the programmer needs to extend this class
 *  and provide an implementation for the {@link #getNextFromSource() getNextFromSource}
 *  method.
 * 
 *  @author Luca Fuelbier
 */
public abstract class ETReceiver<E extends ChronologicComparable<E>> {
	
	private ETStabilizationStrategy<E> stabilizationStrategy;
	
	/** Constructs a new ETReceiver with a default stabilization strategy.
	 *  <p>
	 *  Meant to be called by subclass constructors, typically implicit.
	 */
	public ETReceiver() {
		this.stabilizationStrategy = new ETDefaultStabilizationStrategy<>();
	}
	
	/** Constructs a new ETReceiver with the given stabilization strategy.
	 *  <p>
	 *  Meant to be called by subclass constructors.
	 *  
	 *  @param strategy Stabilization strategy
	 */
	public ETReceiver(ETStabilizationStrategy<E> strategy) {
		this.stabilizationStrategy = strategy;
	}
	
	/** Returns the next item from the data source wrapped in a {@link generic.ETResponse}.
	 *  <p>
	 *  This method has to be implemented by the subclass.
	 *  This abstract class will use it as part of the <em>template method pattern</em>.
	 * 
	 *  @return Next data item wrapped in a {@link generic.ETResponse}
	 */
	protected abstract ETResponse<E> getNextFromSource();
	
	/** Returns the next item from the data source wrapped in a {@link generic.ETResponse}.
	 *  If the item in the response is not <strong>null</strong>, it will be stabilized
	 *  according to the currently set {@link generic.ETStabilizationStrategy}.
	 * 
	 *  @return Next stabilized data item wrapped in a {@link generic.ETResponse}
	 */
	public ETResponse<E> getNext() {
		ETResponse<E> response = getNextFromSource();
		
		if(response.getData() == null) {
			return response;
		}
		
		E stabilizedData = stabilizationStrategy.stabilize(response.getData());
		return new ETResponse<E>(response.getType(), stabilizedData);
	}
	
	/** Sets the stabilization strategy.
	 *  <p>
	 *  Stabilization strategies correct the returned data by applying a correcting 
	 *  algorithm to the retrieved item before returning it.
	 * 
	 *  @param strategy Stabilization strategy
	 */
	public void setStabilizationStrategy(ETStabilizationStrategy<E> strategy) {
		this.stabilizationStrategy = strategy;
	}
	
}
