package sample.experimental;

/** Timer that returns timestamps.
 * 
 * @author Luca Fuelbier
 */
public interface ETTimer {

	/** Returns a timestamp in microseconds.
	 * 
	 *  @return Timestamp [us]
	 */
	public long getTimeMicro();
	
}
