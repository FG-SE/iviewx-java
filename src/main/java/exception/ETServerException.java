package exception;

/** The subclass of {@link ETException} that is thrown when a
 *  server related error occurred.
 *  <p>
 *  Exceptions of this kind might indicate:
 *  <ul>
 *    <li>No response from server</li>
 *    <li>Invalid server version</li>
 *    <li>Wrong server version</li>
 *    <li>Server not ready</li>
 *    <li>Server not found</li>
 *    <li>Path to server not found</li>
 *    <li>Access to server denied</li>
 *    <li>Access to server incomplete</li>
 *    <li>Server out of memory</li>
 *  </ul>
 *  
 *  @author Luca Fuelbier
 */
public class ETServerException extends ETException {

	/** Constructs a new ETServerException object. */
	public ETServerException() {
		super();
	}
	
	/** Constructs a new ETServerException with a given reason.
	 * 
	 *  @param reason A description of the exception
	 */
	public ETServerException(String reason) {
		super(reason);
	}
	
}
