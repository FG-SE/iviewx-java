package sample;

/** Provides access to the current system time.
 * 
 * @author Luca Fuelbier
 */
public class ETSystemClockTimer implements ETTimer{

	/** Returns the current system time in milliseconds.
	 * 
	 *  @Return Current system time [ms]
	 */
	@Override
	public long getTime() {
		return System.currentTimeMillis();
	}
	
}
