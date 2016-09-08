package data;

import connection.ETConnection;
import exception.ETErrorHandler;
import iviewxapi.SampleStruct;
import iviewxapi.EyeDataStruct;

public class ETIViewXSampleReceiver implements ETSampleReceiver {
	
	private ETConnection connection;
	private SampleStruct sampleStruct;
	
	public ETIViewXSampleReceiver(ETConnection connection) {
		this.connection = connection;
		sampleStruct = new SampleStruct();
	}

	@Override
	public ETSample getSample() {
		int status = connection.getIViewXLibrary().iV_GetSample(sampleStruct);
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
