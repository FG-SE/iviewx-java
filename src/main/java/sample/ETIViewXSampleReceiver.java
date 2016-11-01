package sample;

import exception.ETErrorHandler;
import eye.ETEyeData;
import iviewxapi.IViewXAPILibrary;
import iviewxapi.SampleStruct;
import iviewxapi.EyeDataStruct;
import static iviewxapi.IViewXAPILibrary.RET_NO_VALID_DATA;

/** Receives eyetracking samples from the RED-m eyetracker.
 *  <p>
 *  Please use the IViewX class as a central access point.
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

	/** Receives a single eyetracking sample from the RED-m eyetracker.
	 *  <p>
	 *  The sample returned will be the one with the most current timestamp. 
	 * 
	 *  @return Eyetracking sample
	 */
	@Override
	public ETSample getSample() {
		int status = iView.iV_GetSample(sampleStruct);
		
		if(status == RET_NO_VALID_DATA)
			return null;
		else
			ETErrorHandler.handle(status);
		
		ETSample nextSample = structToSample(sampleStruct);
		ETSample stabilizedSample = stabilizationStrategy.stabilize(nextSample);
		return stabilizedSample;
	}
	
	/** Sets the sample stabilization strategy.
	 * 
	 *  Stabilization strategies correct the samples returned by applying a correcting 
	 *  algorithm to the retrieved sample before returning it.
	 * 
	 *  @param strategy Sample stabilization strategy
	 */
	@Override
	public void setStabilizationStrategy(ETSampleStabilizationStrategy strategy) {
		stabilizationStrategy = strategy;
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
