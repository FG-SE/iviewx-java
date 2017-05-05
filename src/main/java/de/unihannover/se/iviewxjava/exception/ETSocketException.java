package de.unihannover.se.iviewxjava.exception;

/** The subclass of {@link ETException} that is thrown when a
 *  socket related error occurred.
 *  <p>
 *  Exceptions of this kind might indicate:
 *  <ul>
 *    <li>Can not create socket</li>
 *    <li>Can not connect with socket</li>
 *    <li>The defined port is blocked</li>
 *    <li>Failed to delete socket</li>
 *  </ul>
 *  
 *  @author Luca Fuelbier
 */
public class ETSocketException extends ETException {

	/** Constructs a new ETSocketException object. */
	public ETSocketException() {
		super();
	}

	/** Constructs a new ETSocketException with a given reason.
	 * 
	 *  @param reason A description of the exception
	 */
	public ETSocketException(String reason) {
		super(reason);
	}
	
}
