package iviewx.data;

import core.chronologic.ChronologicComparable;

/** Stores eyetracking sample information.
 *  <p>
 *  Eyetracking samples are snapshots of the information about each eye at
 *  a specific point in time (specified by the timestamp). 
 *  They provide very detailed information about the eyes 
 *  current state, including desktop gaze and spatial position.
 *  <p>
 *  See {@link iviewx.data.ETEyeData} for more detail on the information available for each eye.
 * 
 *  @author Luca Fuelbier
 */
public class ETSample implements ChronologicComparable<ETSample> {
	
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
	
	/** Compares this sample with the other for chronological order.
	 *  Returns a negative integer, zero, or a positive integer as this
	 *  samples timestamp is less than, equal to, or greater than the
	 *  other samples timestamp.
	 *  
	 *  @return A negative integer, zero, or a positive integer as this
	 *          samples timestamp is less than, equal to, or greater than the
	 *          other samples timestamp
	 */
	@Override
	public int chrCompareTo(ETSample other) {
		return Long.compare(timestamp, other.timestamp);
	}
}
