package de.unihannover.se.iviewxjava.iviewx.eyetracker;

import de.unihannover.se.iviewxjava.core.receiver.ETReceiver;
import de.unihannover.se.iviewxjava.core.receiver.response.ETResponse;
import de.unihannover.se.iviewxjava.core.receiver.response.ETResponseType;
import de.unihannover.se.iviewxjava.core.receiver.stabilization.ETStabilizationStrategy;
import de.unihannover.se.iviewxjava.exception.ETConnectionException;
import de.unihannover.se.iviewxjava.exception.ETErrorHandler;
import de.unihannover.se.iviewxjava.exception.ETException;
import de.unihannover.se.iviewxjava.iviewx.IViewX;
import de.unihannover.se.iviewxjava.iviewx.data.ETEyeData;
import de.unihannover.se.iviewxjava.iviewx.data.ETSample;
import de.unihannover.se.iviewxjava.iviewxapi.IViewXAPILibrary;
import de.unihannover.se.iviewxjava.iviewxapi.SampleStruct;
import de.unihannover.se.iviewxjava.iviewxapi.EyeDataStruct;

/** Receives eyetracking samples from the RED-m eyetracker.
 *  <p>
 *  Please use the {@link IViewX} class as a central access point.
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
	 *  wrapped in a {@link ETResponse}.
	 *  <p>
	 *  If no new data is available, this method will return <strong>null</strong>
	 *  as the responses data.
	 *  {@link ETResponseType#NO_NEW_DATA_AVAILABLE NO_NEW_DATA_AVAILABLE}
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
	 *  @throws ETException If an error occurred while retrieving a new sample
	 *  @throws ETConnectionException If no connection could be established to the eyetracker
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
