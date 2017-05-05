package de.unihannover.se.iviewxjava.exception;

/** The subclass of {@link ETException} that is thrown when a
 *  parameter related error occurred.
 *  <p>
 *  Exceptions of this kind might indicate:
 *  <ul>
 *    <li>Parameter value is out of range</li>
 *    <li>Parameter value is not valid</li>
 *  </ul>
 *  
 *  @author Luca Fuelbier
 */
public class ETParameterException extends ETException {
	
	/** Constructs a new ETParameterException object. */
	public ETParameterException() {
		super();
	}
	
	/** Constructs a new ETParameterException with a given reason.
	 * 
	 *  @param reason A description of the exception
	 */
	public ETParameterException(String reason) {
		super(reason);
	}
	
}
