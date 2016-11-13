package sample;

/** Receives eyetracking samples.
 * 
 *  @author Luca Fuelbier
 */
public interface ETSampleReceiver {
	
	/** Retrieves a single eyetracking sample.
	 * 
	 *  @return Eyetracking sample
	 *  
	 *  @throws exception.ETException If an error occurred while retrieving a new sample
	 */
	public ETSample getSample();
	
	/** Sets the sample stabilization strategy.
	 *  <p>
	 *  Stabilization strategies correct the samples returned by applying a correcting 
	 *  algorithm to the retrieved sample before returning it.
	 * 
	 *  @param strategy Sample stabilization strategy
	 */
	public void setStabilizationStrategy(ETSampleStabilizationStrategy strategy);
	
}
