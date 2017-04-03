package iviewx.eyetracker;

import core.receiver.ETReceiver;
import core.receiver.response.ETResponse;
import core.receiver.response.ETResponseType;
import core.receiver.stabilization.ETStabilizationStrategy;
import exception.ETErrorHandler;
import iviewx.data.ETEyeData;
import iviewx.data.ETSample;
import iviewxapi.IViewXAPILibrary;
import iviewxapi.SampleStruct;
import iviewxapi.EyeDataStruct;

/** Receives eyetracking samples from the RED-m eyetracker.
 *  <p>
 *  Please use the {@link iviewx.IViewX} class as a central access point.
 * 
 *  @author Luca Fuelbier
 */
public class ETSampleReceiver extends ETReceiver<ETSample> {
	
	private IViewXAPILibrary iView;
	
	private SampleStruct sampleStruct;
	
	/** Constructs a new IView X SampleReceiver that uses the provided IView X SDK binding.
	 * 
	 * @param lib IView X SDK binding for eyetracker communication
	 */
	public ETSampleReceiver(IViewXAPILibrary lib) {
		super();
		iView = lib;
		sampleStruct = new SampleStruct();
	}
	
	/** Constructs a new IView X SampleReceiver that uses the provided IView X SDK binding 
	 *  and the provided stabilization strategy.
	 * 
	 * @param lib IView X SDK binding for eyetracker communication
	 * @param strategy Sample stabilization strategy
	 */
	public ETSampleReceiver(IViewXAPILibrary lib, ETStabilizationStrategy<ETSample> strategy) {
		super(strategy);
		iView = lib;
		sampleStruct = new SampleStruct();
	}
	
	/** Retrieves a single eyetracking sample from the RED-m eyetracke,
	 *  wrapped in a {@link core.receiver.response.ETResponse}.
	 *  <p>
	 *  If no new data is available, this method will return <strong>null</strong>
	 *  as the responses data.
	 *  {@link core.receiver.response.ETResponseType#NO_NEW_DATA_AVAILABLE NO_NEW_DATA_AVAILABLE}
	 *  indicates one of the following scenarios:
	 *  <ul>
	 *    <li>There is no new data available because the eyetracker has not computed new data yet</li>
	 *    <li>The users gaze is not in tracking range</li>
	 *    <li>The user is not properly tracked by the eyetracker</li>
	 *  </ul>
	 *  <p>
	 *  If your eyetracking environment is properly set up, the most likely reason is that
	 *  your application is polling faster than the eyetrackers refresh rate.
	 *  Simply keep polling until a new sample is registered, or introduce wait times
	 *  between consecutive polls.
	 * 
	 *  @return Eyetracking sample
	 *  
	 *  @throws exception.ETException If an error occurred while retrieving a new sample
	 *  @throws exception.ETConnectionException If no connection could be established to the eyetracker
	 */
	@Override
	protected ETResponse<ETSample> getNextFromSource() {
		int status = iView.iV_GetSample(sampleStruct);
		
		if(status == IViewXAPILibrary.RET_NO_VALID_DATA)
			return new ETResponse<ETSample>(ETResponseType.NO_NEW_DATA_AVAILABLE, null);
		else
			ETErrorHandler.handle(status);
		
		ETSample receivedSample = structToSample(sampleStruct);
		return new ETResponse<ETSample>(ETResponseType.NEW_DATA, receivedSample);
	}
	
	/** Converts the information of a SampleStruct to a ETSample.
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
