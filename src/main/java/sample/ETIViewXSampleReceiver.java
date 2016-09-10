package sample;

import data.ETEyeData;
import exception.ETErrorHandler;
import iviewxapi.IViewXAPILibrary;
import iviewxapi.SampleStruct;
import iviewxapi.EyeDataStruct;

public class ETIViewXSampleReceiver implements ETSampleReceiver {
	
	private IViewXAPILibrary iView;
	
	private SampleStruct sampleStruct;
	
	public ETIViewXSampleReceiver(IViewXAPILibrary lib) {
		iView = lib;
		sampleStruct = new SampleStruct();
	}

	@Override
	public ETSample getSample() {
		int status = iView.iV_GetSample(sampleStruct);
		ETErrorHandler.handle(status);
		return structToSample(sampleStruct);
	}
	
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
