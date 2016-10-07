package sample;

/** Provides access to the current system time.
 * 
 * @author Luca Fuelbier
 */
public class ETSystemClockTimer implements ETTimer{

	/** Returns the current system time in microseconds.
	 * 
	 *  @Return Current system time [us]
	 */
	@Override
	public long getTime() {
		return System.currentTimeMillis() * 1000;
	}
	
}
