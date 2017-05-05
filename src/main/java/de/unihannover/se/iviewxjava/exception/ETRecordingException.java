package de.unihannover.se.iviewxjava.exception;

/** The subclass of {@link ETException} that is thrown when a
 *  recording related exception occurred.
 *  
 *  @author Luca Fuelbier
 */
public class ETRecordingException extends ETException {
	
	/** Constructs a new ETRecordingException object. */
	public ETRecordingException() {
		super();
	}
	
	/** Constructs a new ETRecordingException with a given reason.
	 * 
	 *  @param reason A description of the exception
	 */
	public ETRecordingException(String reason) {
		super(reason);
	}
	
	/** Constructs a new ETRecordingException with a given reason and cause.
	 *  
	 *  @param reason A description of the exception 
	 *  @param cause The cause. (A <strong>null</strong> value is permitted,
	 *               and indicates that the cause is nonexistent or unknown.)
	 */
	public ETRecordingException(String reason, Throwable cause) {
		super(reason, cause);
	}
	
}
