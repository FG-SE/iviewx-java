package generic;

/** Strategy for stabilizing retrieved data.
 *  <p>
 *  This interface is used by the rest of the SMI eyetracking library as 
 *  part of the <em>strategy pattern</em>. If you want to define a 
 *  stabilization strategy, subclass this interface, implement your algorithm 
 *  in a overriden version of the {@link #stabilize(Object) stabilize} method
 *  and pass your finished strategy to a {@link generic.ETReceiver} accepting
 *  the interface.
 * 
 *  @author Luca Fuelbier
 */
public interface ETStabilizationStrategy<E> {
	
	/** Stabilizes the provided data and returns it.
	 * 
	 *  @param element Original data
	 *  
	 *  @return Stabilized data
	 */
	public E stabilize(E element);
	
}
