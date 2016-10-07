package sample;

/** Receives eyetracking samples.
 * 
 *  @author Luca Fuelbier
 */
public interface ETSampleReceiver {
	
	/** Retrieves a single eyetracking sample.
	 * 
	 *  @return Eyetracking sample
	 */
	public ETSample getSample();
	
}
