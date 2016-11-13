package exception;

/** The subclass of {@link ETException} that is thrown when a
 *  device specific error occurred.
 *  <p>
 *  Exceptions of this kind might indicate:
 *  <ul>
 *    <li>Eyetracking device needed for a specific function is not connected</li>
 *    <li>Failed to access eyetracking device</li>
 *    <li>Failed to access port connected to eyetracking device</li>
 *  </ul>
 *  
 *  @author Luca Fuelbier
 */
public class ETDeviceException extends ETException {

	/** Constructs a new ETDeviceException object. */
	public ETDeviceException() {
		super();
	}

	/** Constructs a new ETDeviceException with a given reason.
	 * 
	 *  @param reason A description of the exception
	 */
	public ETDeviceException(String reason) {
		super(reason);
	}
	
}
