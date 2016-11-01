package sample;

public class ETPassthroughSampleStabilizationStrategy implements ETSampleStabilizationStrategy {

	/** Simply passes through the sample, not performing any stabilization.
	 * 
	 *  @param sample Original eyetracking sample
	 *  @return Unmodified eyetracking sample
	 */
	@Override
	public ETSample stabilize(ETSample sample) {
		return sample;
	}

}
