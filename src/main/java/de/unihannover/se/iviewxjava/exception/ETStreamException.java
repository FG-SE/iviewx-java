package de.unihannover.se.iviewxjava.exception;

/** The subclass of {@link ETException} that is thrown when a
 *  stream related error occurred.
 *  
 *  @author Luca Fuelbier
 */
public class ETStreamException extends ETException {
	
	/** Constructs a new ETStreamException object. */
	public ETStreamException() {
		super();
	}
	
	/** Constructs a new ETStreamException with a given reason.
	 * 
	 *  @param reason A description of the exception
	 */
	public ETStreamException(String reason) {
		super(reason);
	}
	
	/** Constructs a new ETStreamException with a given reason and cause.
	 *  
	 *  @param reason A description of the exception 
	 *  @param cause The cause. (A <strong>null</strong> value is permitted,
	 *               and indicates that the cause is nonexistent or unknown.)
	 */
	public ETStreamException(String reason, Throwable cause) {
		super(reason, cause);
	}

}
