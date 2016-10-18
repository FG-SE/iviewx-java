package sample;

import exception.ETErrorHandler;
import eye.ETEyeData;
import iviewxapi.IViewXAPILibrary;
import iviewxapi.SampleStruct;
import iviewxapi.EyeDataStruct;

/** Receives eyetracking samples from the RED-m eyetracker.
 *  <p>
 *  Please use the IViewX class as a central access point.
 * 
 *  @author Luca Fuelbier
 */
public class ETIViewXSampleReceiver implements ETSampleReceiver {
	
	private IViewXAPILibrary iView;
	
	private SampleStruct sampleStruct;
	
	/** Constructs a new IView X SampleReceiver that uses the provided IView X SDK binding.
	 * 
	 * @param lib IView X SDK binding for eyetracker communication
	 */
	public ETIViewXSampleReceiver(IViewXAPILibrary lib) {
		iView = lib;
		sampleStruct = new SampleStruct();
	}

	/** Receives a single eyetracking sample from the RED-m eyetracker.
	 *  <p>
	 *  The sample returned will be the one with the most current timestamp. 
	 * 
	 *  @return Eyetracking sample
	 */
	@Override
	public ETSample getSample() {
		int status = iView.iV_GetSample(sampleStruct);
		ETErrorHandler.handle(status);
		return structToSample(sampleStruct);
	}
	
	/** Converts the information of a SampleStruct to a ETSample.
	 * 
	 * @param struct SampleStruct containing sample information
	 * @return Eyetracking sample
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
