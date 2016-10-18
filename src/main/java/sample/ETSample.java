package sample;

import eye.ETEyeData;

/** Stores eyetracking sample information.
 *  <p>
 *  Eyetracking samples are snapshots of the information about each eye at
 *  a specific point in time (specified by the timestamp). 
 *  Eyetracking samples provide very detailed information about the eyes 
 *  current state, including desktop gaze and spatial position.
 *  <p>
 *  See ETEyeData for more detail on the available information.
 * 
 *  @author Luca Fuelbier
 */
public class ETSample {
	
	private ETEyeData leftEye;
	private ETEyeData rightEye;
	private long timestamp;
	
	/** Constructs a new eyetracking sample.
	 * 
	 * @param leftEye Eyetracking information of the left eye
	 * @param rightEye Eyetracking information of the right eye
	 * @param timestamp Timestamp of the sample [us]
	 */
	public ETSample(ETEyeData leftEye, ETEyeData rightEye, long timestamp) {
		this.leftEye = leftEye;
		this.rightEye = rightEye;
		this.timestamp = timestamp;
	}

	/** Returns the eyetracking information of the left eye.
	 * 
	 *  @return Eyetracking information of the left eye
	 */
	public ETEyeData getLeftEye() {
		return leftEye;
	}

	/** Returns the eyetracking information of the right eye.
	 * 
	 *  @return Eyetracking information of the right eye
	 */
	public ETEyeData getRightEye() {
		return rightEye;
	}
	
	/** Returns the timestamp of the sample in microseconds.
	 * 
	 *  @return Timestamp of the sample [us]
	 */
	public long getTimestamp() {
		return timestamp;
	}	
}
