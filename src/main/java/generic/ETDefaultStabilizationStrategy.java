package generic;

/** Implementation of the {@link generic.ETStabilizationStrategy} interface which simply passes
 *  through the data.
 *  <p>
 *  This is used as the default strategy in the SMI eyetracking library.
 *  It is essentially a no-op strategy that doesn't correct the retrieved data.
 * 
 *  @see generic.ETStabilizationStrategy
 *  
 *  @author Luca Fuelbier
 */
public class ETDefaultStabilizationStrategy<E> implements ETStabilizationStrategy<E> {

	/** Simply passes through the data, not performing any stabilization.
	 * 
	 *  @param sample Original data
	 *  
	 *  @return Unmodified data
	 */
	@Override
	public E stabilize(E element) {
		return element;
	}

}
