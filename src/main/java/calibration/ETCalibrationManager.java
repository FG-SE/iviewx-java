package calibration;

/** Manages the calibration process for an eyetracker.
 *  <p>
 *  Currently only supports automatic calibration (opens a separate window).
 *  Custom calibration might get implemented in the future.
 * 
 *  @author Luca Fuelbier
 */
public interface ETCalibrationManager {
	
	/** Starts the automatic calibration. */
	public void calibrate();
	
}
