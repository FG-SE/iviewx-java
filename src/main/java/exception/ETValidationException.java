package exception;

/** The subclass of {@link ETException} that is thrown when a
 *  validation related error occurred.
 *  <p>
 *  Exceptions of this kind might indicate:
 *  <ul>
 *    <li>System is not validated</li>
 *  </ul>
 *  
 *  @author Luca Fuelbier
 */
public class ETValidationException extends ETException {

	/** Constructs a new ETValidationException object. */
	public ETValidationException() {
		super();
	}

	/** Constructs a new ETValidationException with a given reason.
	 * 
	 *  @param reason A description of the exception
	 */
	public ETValidationException(String reason) {
		super(reason);
	}
	
}
