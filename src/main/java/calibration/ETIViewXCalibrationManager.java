package calibration;

import exception.ETErrorHandler;
import iviewxapi.IViewXAPILibrary;

/** Manages the calibration process for the RED-m eyetracker.
 *  <p>
 *  Currently only supports automatic calibration.
 *  Custom calibration and advanced calibration settings might get added in the future.
 *  <p>
 *  There can only be one active calibration at a time. 
 *  Having multiple instances of this class will result in unintuitive behavior 
 *  because of shared state. Please use the IViewX class as a central access point.
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
	 *  The user might be required to hit the Spacebar button when fixating the first point.
	 *  <p>
	 *  Manipulating the calibration settings is not supported yet, but might be added in 
	 *  a future version.
	 *  
	 *  @throws ETException If there was a problem with the EyeTracker, or the IView X Server
	 */
	@Override
	public void calibrate() {
		int status = iView.iV_Calibrate();
		ETErrorHandler.handle(status);
	}
	
}
