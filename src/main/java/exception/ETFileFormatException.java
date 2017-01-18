package exception;

/** The subclass of {@link ETException} that is thrown when
 *  a file-formatting related error occurred.
 *  <p>
 *  Exceptions of this kind might indicate:
 *  <ul>
 *    <li>The file is incorrectly formatted and can not be parsed</li>
 *    <li>The file does not have the correct file extension</li>
 *  </ul>
 *  
 *  @author Luca Fuelbier
 */
public class ETFileFormatException extends ETException {
	
	/** Constructs a new ETFileFormatException object. */
	public ETFileFormatException() {
		super();
	}

	/** Constructs a new ETFileFormatException with a given reason.
	 * 
	 *  @param reason A description of the exception
	 */
	public ETFileFormatException(String reason) {
		super(reason);
	}

}
