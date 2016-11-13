package sample;

/** Implementation of the {@link ETSampleStabilizationStrategy} interface which simply passes
 *  through the eyetracking sample.
 *  <p>
 *  This is used as the default strategy in the SMI eyetracking library.
 *  It is essentially a no-op strategy that doesn't correct the retrieved samples.
 * 
 *  @see ETSampleStabilizationStrategy
 *  
 *  @author Luca Fuelbier
 */
public class ETPassthroughSampleStabilizationStrategy implements ETSampleStabilizationStrategy {

	/** Simply passes through the sample, not performing any stabilization.
	 * 
	 *  @param sample Original eyetracking sample
	 *  
	 *  @return Unmodified eyetracking sample
	 */
	@Override
	public ETSample stabilize(ETSample sample) {
		return sample;
	}

}
