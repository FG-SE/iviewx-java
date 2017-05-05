package de.unihannover.se.iviewxjava.exception;

/** The subclass of {@link ETException} that is thrown when a
 *  connection related error occurred.
 *  <p>
 *  Exceptions of this kind might indicate:
 *  <ul>
 *    <li>Failed to establish connection</li>
 *    <li>No connection established</li>
 *    <li>Failed to open port</li>
 *    <li>Failed to open port</li>
 *  </ul>
 *  
 *  @author Luca Fuelbier
 */
public class ETConnectionException extends ETException {

	/** Constructs a new ETConnectionException object. */
	public ETConnectionException() {
		super();
	}

	/** Constructs a new ETConnectionException with a given reason.
	 * 
	 *  @param reason A description of the exception
	 */
	public ETConnectionException(String reason) {
		super(reason);
	}
	
}
