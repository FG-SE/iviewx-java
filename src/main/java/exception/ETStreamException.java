package exception;

public class ETStreamException extends ETException {
	
	public ETStreamException() {
		super();
	}
	
	public ETStreamException(String reason) {
		super(reason);
	}
	
	/** Constructs a new ETRecordingException with a given reason and cause.
	 *  
	 *  @param reason A description of the exception 
	 *  @param cause The cause. (A <strong>null</strong> value is permitted,
	 *               and indicates that the cause is nonexistent or unknown.)
	 */
	public ETStreamException(String reason, Throwable cause) {
		super(reason, cause);
	}

}
