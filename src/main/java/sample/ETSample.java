package sample;

import data.ETEyeData;

public class ETSample {
	
	private ETEyeData leftEye;
	private ETEyeData rightEye;
	private long timestamp;
	
	public ETSample(ETEyeData leftEye, ETEyeData rightEye, long timestamp) {
		this.leftEye = leftEye;
		this.rightEye = rightEye;
		this.timestamp = timestamp;
	}

	// Eye Data
	public ETEyeData getLeftEye() {
		return leftEye;
	}
	
	public ETEyeData getRightEye() {
		return rightEye;
	}
	
	// Timing
	public long getTimestamp() {
		return timestamp;
	}
	
	public static ETSample createDefault() {
		return new ETSample(ETEyeData.createDefault(), ETEyeData.createDefault(), 0);
	}
	
}
