package sample;

/** Strategy for stabilizing retrieved eyetracking samples.
 *  <p>
 *  This interface is used by the rest of the Eyetracking library as 
 *  part of the Strategy pattern. If you want to define a eyetracking sample 
 *  stabilization strategy, subclass this interface, implement your algorithm 
 *  in a overriden version of the getStabilizedSample() method and pass your 
 *  finished strategy to a SampleReceiver accepting the interface.
 * 
 *  @author Luca Fuelbier
 */
public interface ETSampleStabilizationStrategy {
	
	/** Returns a stabilized eyetracking sample, computed from the one provided.
	 * 
	 * @param sample Original eyetracking sample
	 * @return Stabilized eyetracking sample
	 */
	public ETSample getStabilizedSample(ETSample sample);
	
}
