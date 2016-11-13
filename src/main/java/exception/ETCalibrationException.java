package exception;

/** The subclass of {@link ETException} that is thrown when a
 *  calibration related error occurred.
 *  <p>
 *  Exceptions of this kind might indicate:
 *  <ul>
 *    <li>System is not calibrated</li>
 *    <li>Calibration could not be performed with connected eyetracking device</li>
 *    <li>Calibration timeout occurred</li>
 *  </ul>
 *  
 *  @author Luca Fuelbier
 */
public class ETCalibrationException extends ETException {
	
	/** Constructs a new ETCalibrationException object. */
	public ETCalibrationException() {
		super();
	}

	/** Constructs a new ETCalibrationException with a given reason.
	 * 
	 *  @param reason A description of the exception
	 */
	public ETCalibrationException(String reason) {
		super(reason);
	}
	
}
