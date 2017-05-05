package de.unihannover.se.iviewxjava.iviewx.eyetracker;

import de.unihannover.se.iviewxjava.exception.ETCalibrationException;
import de.unihannover.se.iviewxjava.exception.ETConnectionException;
import de.unihannover.se.iviewxjava.exception.ETErrorHandler;
import de.unihannover.se.iviewxjava.exception.ETException;
import de.unihannover.se.iviewxjava.iviewx.IViewX;
import de.unihannover.se.iviewxjava.iviewxapi.IViewXAPILibrary;

/** Manages the validation process for the RED-m eyetracker.
 *  <p>
 *  Currently only automatic validation is supported.
 *  <br>
 *  Manipulating the validation settings is not supported yet.
 *  <p>
 *  There can be only one active validation at a time.
 *  <br>
 *  Having multiple instances of this class will result in unintuitive behavior 
 *  because of shared internal state. Please use the {@link IViewX} class
 *  as a central access point.
 * 
 *  @author Luca Fuelbier
 */
public class ETValidationManager {

	private IViewXAPILibrary iView;
	
	/** Constructs a ValidationManager that uses the provided IView X SDK binding.
	 * 
	 *  @param lib IView X SDK binding for eyetracker communication
	 */
	public ETValidationManager(IViewXAPILibrary lib) {
		iView = lib;
	}
	
	/** Starts the automatic validation.
	 *  <p>
	 *  A new window will open, which will present the user with points to fixate on.
	 *  The points will accept automatically if the user fixates on them.
	 *  
	 *  @throws ETException If an error occurred during the validation process
	 *  @throws ETConnectionException If no connection could be established to the eyetracker
	 *  @throws ETCalibrationException If the system has not been calibrated yet
	 */
	public void validate() {
		int status = iView.iV_Validate();
		ETErrorHandler.handle(status);
	}
	
}
