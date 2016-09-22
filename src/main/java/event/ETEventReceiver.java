package event;

/** Receives eyetracking events from an eyetracker.
 * 
 *  @author Luca Fuelbier
 */
public interface ETEventReceiver {

	/** Retrieves a single eyetracking event.
	 * 
	 *  @return Eyetracking event
	 */
	public ETEvent getEvent();
	
}
