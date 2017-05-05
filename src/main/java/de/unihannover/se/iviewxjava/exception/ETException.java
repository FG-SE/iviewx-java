package de.unihannover.se.iviewxjava.exception;

/** An exception that provides information on an eyetracker or
 *  eyetracking server related error.
 *  <p>
 *  This class serves as the base class for all other custom exceptions
 *  used by this library. Most of the thrown exceptions are specialized
 *  subclasses of this one, but some general errors are managed by this 
 *  class.
 *  <p>
 *  Since this class is the base class of all other custom exceptions, 
 *  you can effectively filter the errors by catching specific exceptions
 *  first and catch exceptions of type ETException as the last resort.
 *  
 *  @author Luca Fuelbier
 */
public class ETException extends RuntimeException {
	
	/** Constructs a new ETException object. */
	public ETException() {
		super();
	}

	/** Constructs a new ETException with a given reason.
	 *  
	 *  @param reason A description of the exception 
	 */
	public ETException(String reason) {
		super(reason);
	}
	
	/** Constructs a new ETException with a given reason and cause.
	 *  
	 *  @param reason A description of the exception 
	 *  @param cause The cause. (A <strong>null</strong> value is permitted,
	 *               and indicates that the cause is nonexistent or unknown.)
	 */
	public ETException(String reason, Throwable cause) {
		super(reason, cause);
	}
	
}