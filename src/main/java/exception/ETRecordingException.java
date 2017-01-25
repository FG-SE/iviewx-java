package exception;

public class ETRecordingException extends ETException {
	
	public ETRecordingException() {
		super();
	}
	
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
