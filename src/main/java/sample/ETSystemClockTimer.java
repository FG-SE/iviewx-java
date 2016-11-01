package sample;

/** Provides access to the current system time.
 * 
 * @author Luca Fuelbier
 */
public class ETSystemClockTimer implements ETTimer {

	/** Returns the current system time in microseconds.
	 * 
	 *  @return Current system time [us]
	 */
	@Override
	public long getTimeMicro() {
		// System.currentTimeMillis() performs very fast and is accurate enough
		return System.currentTimeMillis() * 1000;
	}
	
}
