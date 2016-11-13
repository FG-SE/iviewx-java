package event;

/** Receives eyetracking events.
 * 
 *  @author Luca Fuelbier
 */
public interface ETEventReceiver {

	/** Retrieves a single eyetracking event.
	 *  <p>
	 *  This method can return <strong>null</strong>, which indicates that no new event data is available.
	 * 
	 *  @return Eyetracking event
	 *  
	 *  @throws exception.ETException If an error occured while retrieving a new event
	 */
	public ETEvent getEvent();
	
}
