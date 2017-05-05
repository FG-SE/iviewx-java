package de.unihannover.se.iviewxjava.exception;

/** The subclass of {@link ETException} that is thrown when a
 *  buffer related error occurred.
 *  <p>
 *  Exceptions of this kind might indicate:
 *  <ul>
 *    <li>Insufficient buffer size</li>
 *    <li>Buffer is empty</li>
 *    <li>Buffer is full</li>
 *    <li>Buffer is blocked</li>
 *  </ul>
 *  
 *  @author Luca Fuelbier
 */
public class ETBufferException extends ETException {

	/** Constructs a new ETBufferException object. */
	public ETBufferException() {
		super();
	}

	/** Constructs a new ETBufferException with a given reason.
	 * 
	 *  @param reason A description of the exception
	 */
	public ETBufferException(String reason) {
		super(reason);
	}
	
}
