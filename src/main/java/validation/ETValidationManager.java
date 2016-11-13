package validation;

/** Manages the validation process for an eyetracker.
 * 
 * @author Luca Fuelbier
 */
public interface ETValidationManager {
	
	/** Starts the automatic validation.
	 * 
	 *  @throws exception.ETException If an error occurred during the validation process
	 */
	public void validate();
	
}
