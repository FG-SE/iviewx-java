package event;

import generic.ChronologicComparable;
import eye.ETEye;

/** Stores eyetracking event information.
 *  <p>
 *  Eyetracking events are fixation events.
 *  <br>
 *  Fixation means a fixation of a screen coordinate for a given timeframe.
 * 
 *  @author Luca Fuelbier
 */
public class ETEvent implements ChronologicComparable<ETEvent> {
	
	private long startTime;
	private long endTime;
	private ETEye eye;
	private double posX;
	private double posY;
	
	/** Constructs a new eyetracking event information object.
	 * 
	 * @param startTime Start time of the event [us]
	 * @param endTime End time of the event [us]
	 * @param eye Related eye ({@link eye.ETEye#LEFT}/{@link eye.ETEye#RIGHT})
	 * @param posX Horizontal position of the fixation [px]
	 * @param posY Vertical position of the fixation [px]
	 */
	public ETEvent(long startTime, long endTime, ETEye eye, double posX, double posY) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.eye = eye;
		this.posX = posX;
		this.posY = posY;
	}
	
	/** Returns the start time of the event in microseconds.
	 * 
	 *  @return The start time of the event in us
	 */
	public long getStartTime() {
		return startTime;
	}
	
	/** Returns the end time of the event in microseconds.
	 * 
	 *  @return The end time of the event in us
	 */
	public long getEndTime() {
		return endTime;
	}
	
	/** Returns the duration of the event in microseconds.
	 * 
	 *  @return The duration of the event in us
	 */
	public long getDuration() {
		return endTime - startTime;
	}
	
	/** Returns the eye related to the event.
	 *  <p>
	 *  Possible values are {@link eye.ETEye#LEFT} and {@link eye.ETEye#RIGHT}.
	 * 
	 *  @return Eye related to the event
	 */
	public ETEye getEye() {
		return eye;
	}
	
	/** Returns the horizontal position of the fixation in pixel.
	 * 
	 *  @return The horizontal position of the fixation in px
	 */
	public double getPositionX() {
		return posX;
	}
	
	/** Returns the vertical position of the fixation in pixel.
	 * 
	 *  @return The vertical position of the fixation in px
	 */
	public double getPositionY() {
		return posY;
	}
	
	/** Compares this event with the other for chronological order.
	 *  Returns a negative integer, zero, or a positive integer as this
	 *  events start timestamp is less than, equal to, or greater than the
	 *  other events start timestamp.
	 *  <p>
	 *  <strong>Note:</strong> This imposes a chronological order based on
	 *  when the events started, not when they ended or how long they lasted.
	 *  
	 *  @return A negative integer, zero, or a positive integer as this
	 *          events start timestamp is less than, equal to, or greater
	 *          than the other events start timestamp
	 */
	@Override
	public int chrCompareTo(ETEvent other) {
		return Long.compare(startTime, other.startTime);
	}
	
}
