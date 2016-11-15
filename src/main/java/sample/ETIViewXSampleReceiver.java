package sample;

import exception.ETErrorHandler;
import eye.ETEyeData;
import iviewxapi.IViewXAPILibrary;
import iviewxapi.SampleStruct;
import iviewxapi.EyeDataStruct;
import static iviewxapi.IViewXAPILibrary.RET_NO_VALID_DATA;

/** Receives eyetracking samples from the RED-m eyetracker.
 *  <p>
 *  Please use the {@link iviewx.IViewX} class as a central access point.
 * 
 *  @author Luca Fuelbier
 */
public class ETIViewXSampleReceiver implements ETSampleReceiver {
	
	private IViewXAPILibrary iView;
	
	private SampleStruct sampleStruct;
	private ETSampleStabilizationStrategy stabilizationStrategy;
	
	/** Constructs a new IView X SampleReceiver that uses the provided IView X SDK binding.
	 * 
	 * @param lib IView X SDK binding for eyetracker communication
	 */
	public ETIViewXSampleReceiver(IViewXAPILibrary lib) {
		iView = lib;
		sampleStruct = new SampleStruct();
		stabilizationStrategy = new ETPassthroughSampleStabilizationStrategy();
	}
	
	/** Constructs a new IView X SampleReceiver that uses the provided IView X SDK binding 
	 *  and the provided stabilization strategy.
	 * 
	 * @param lib IView X SDK binding for eyetracker communication
	 * @param strategy Sample stabilization strategy
	 */
	public ETIViewXSampleReceiver(IViewXAPILibrary lib, ETSampleStabilizationStrategy strategy) {
		iView = lib;
		sampleStruct = new SampleStruct();
		stabilizationStrategy = strategy;
	}

	/** Retrieves a single eyetracking sample from the RED-m eyetracker.
	 *  <p>
	 *  The sample returned will be the one with the most current timestamp. 
	 *  <p>
	 *  This method can return <strong>null</strong>, which indicates one of the following scenarios:
	 *  <ul>
	 *    <li>There is no new data available because the eyetracker has not computed new data yet</li>
	 *    <li>The users gaze is not in tracking range</li>
	 *    <li>The user is not properly tracked by the eyetracker</li>
	 *  </ul>
	 *  <p>
	 *  If your eyetracking environment is properly set up, a <strong>null</strong> most likely means that
	 *  your application is polling faster than the eyetrackers refresh rate. Simply keep polling until
	 *  a new sample is registered.
	 *  <p>
	 *  To check if there are still samples available, call {@link #hasNext() hasNext}.
	 * 
	 *  @return Eyetracking sample
	 *  
	 *  @throws exception.ETException If an error occurred while retrieving a new sample
	 *  @throws exception.ETConnectionException If no connection could be established to the eyetracker
	 */
	@Override
	public ETSample next() {
		int status = iView.iV_GetSample(sampleStruct);
		
		if(status == RET_NO_VALID_DATA)
			return null;
		else
			ETErrorHandler.handle(status);
		
		ETSample nextSample = structToSample(sampleStruct);
		ETSample stabilizedSample = stabilizationStrategy.stabilize(nextSample);
		return stabilizedSample;
	}
	
	/** Returns <em>true</em> if the sample receiver has more samples.
	 *  <p>
	 *  Since the eyetracker can produce samples endlessly, this method will always return true.
	 *  A return of <em>true</em> does not however indicate that new data is available.
	 *  It only indicates that the eyetracker is trying to retrieve a new sample at the moment.
	 *  <p>
	 *  This behavior might change in a later release of the eyetracking library, for example by 
	 *  supporting pausing and resuming the eyetracking process.
	 * 
	 *  @return <em>true</em> if the receiver has more samples
	 */
	@Override
	public boolean hasNext() {
		return true;
	}
	
	/** Sets the sample stabilization strategy.
	 *  <p>
	 *  Stabilization strategies correct the samples returned by applying a correcting 
	 *  algorithm to the retrieved sample before returning it.
	 * 
	 *  @param strategy Sample stabilization strategy
	 */
	@Override
	public void setStabilizationStrategy(ETSampleStabilizationStrategy strategy) {
		stabilizationStrategy = strategy;
	}
	
	/** Converts the information of a SampleStruct to a {@link ETSample}.
	 * 
	 *  @param struct SampleStruct containing sample information
	 *  
	 *  @return Eyetracking sample
	 */
	private ETSample structToSample(SampleStruct struct) {
		EyeDataStruct leftEye = struct.leftEye;
		EyeDataStruct rightEye = struct.rightEye;
		
		ETEyeData left = new ETEyeData(leftEye.diam, leftEye.eyePositionX,
									   leftEye.eyePositionY, leftEye.eyePositionZ,
									   leftEye.gazeX, leftEye.gazeY);
		ETEyeData right = new ETEyeData(rightEye.diam, rightEye.eyePositionX,
										rightEye.eyePositionY, rightEye.eyePositionZ,
			   							rightEye.gazeX, rightEye.gazeY);
		
		return new ETSample(left, right, struct.timestamp);
	}

}
