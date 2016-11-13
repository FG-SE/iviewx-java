package calibration;

/** Manages the calibration process for an eyetracker.
 * 
 *  @author Luca Fuelbier
 */
public interface ETCalibrationManager {
	
	/** Starts the automatic calibration.
	 * 
	 *  @throws exception.ETException If an error occurred during the calibration process
	 */
	public void calibrate();
	
}
