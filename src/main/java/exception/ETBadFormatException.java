package exception;

/** The subclass of {@link ETException} that is thrown when
 *  a formatting related error occurred.
 *  <p>
 *  Exceptions of this kind might indicate:
 *  <ul>
 *    <li>The file is incorrectly formatted and can not be parsed</li>
 *  </ul>
 *  
 *  @author Luca Fuelbier
 */
public class ETBadFormatException extends ETException {
	
	/** Constructs a new ETBadFormatException object. */
	public ETBadFormatException() {
		super();
	}

	/** Constructs a new ETBadFormatException with a given reason.
	 * 
	 *  @param reason A description of the exception
	 */
	public ETBadFormatException(String reason) {
		super(reason);
	}

}
