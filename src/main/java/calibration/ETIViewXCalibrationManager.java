package calibration;

import exception.ETErrorHandler;
import iviewxapi.IViewXAPILibrary;

/** Manages the calibration process for the RED-m eyetracker.
 *  <p>
 *  Currently only automatic calibration is supported.
 *  <br>
 *  Manipulating the calibration settings is not supported yet.
 *  <p>
 *  There can be only one active calibration at a time.
 *  <br>
 *  Having multiple instances of this class will result in unintuitive behavior 
 *  because of shared internal state. Please use the {@link iviewx.IViewX} class
 *  as a central access point.
 * 
 *  @author Luca Fuelbier
 */
public class ETIViewXCalibrationManager implements ETCalibrationManager {
	
	private IViewXAPILibrary iView;
	
	/** Constructs a CalibrationManager that uses the provided IView X SDK binding.
	 * 
	 *  @param lib IView X SDK binding for eyetracker communication
	 */
	public ETIViewXCalibrationManager(IViewXAPILibrary lib) {
		iView = lib;
	}
	
	/** Starts the automatic calibration.
	 *  <p>
	 *  A new window will open, which will present the user with points to fixate on.
	 *  The user might be required to hit the Spacebar button when fixating the first point 
	 *  to start the calibration process.
	 *  
	 *  @throws exception.ETException If an error occurred during the calibration process
	 *  @throws exception.ETConnectionException If no connection could be established to the eyetracker
	 */
	@Override
	public void calibrate() {
		int status = iView.iV_Calibrate();
		ETErrorHandler.handle(status);
	}
	
}
