package exception;

/** The subclass of {@link ETException} that is thrown when a
 *  Area of Interest (AOI) related error occurred.
 *  <p>
 *  Exceptions of this kind might indicate:
 *  <ul>
 *    <li>AOI data could not be accessed</li>
 *    <li>AOI has not been defined</li>
 *  </ul>
 *  
 *  @author Luca Fuelbier
 */
public class ETAoiException extends ETException {

	/** Constructs a new ETAoiException object. */
	public ETAoiException() {
		super();
	}

	/** Constructs a new ETAoiException with a given reason.
	 * 
	 *  @param reason A description of the exception
	 */
	public ETAoiException(String reason) {
		super(reason);
	}
	
}
