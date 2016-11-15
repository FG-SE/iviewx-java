package sample;

import java.util.Iterator;

/** Receives eyetracking samples.
 *  <p>
 *  This interface extends the {@link java.util.Iterator} interface.
 *  Its implementations can therefore be used like any other iterator.
 * 
 *  @author Luca Fuelbier
 */
public interface ETSampleReceiver extends Iterator<ETSample> {
	
	/** Sets the sample stabilization strategy.
	 *  <p>
	 *  Stabilization strategies correct the samples returned by applying a correcting 
	 *  algorithm to the retrieved sample before returning it.
	 * 
	 *  @param strategy Sample stabilization strategy
	 */
	public void setStabilizationStrategy(ETSampleStabilizationStrategy strategy);
	
}
