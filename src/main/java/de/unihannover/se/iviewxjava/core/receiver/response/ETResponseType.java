package de.unihannover.se.iviewxjava.core.receiver.response;

/** Type of a {@link ETResponse}.
 * 
 *  @author Luca Fuelbier
 */
public enum ETResponseType {
	
	/** New data is available */
	NEW_DATA,
	
	/** No new data is available */
	NO_NEW_DATA_AVAILABLE,
	
	/** The receivers source is depleted */
	SOURCE_DEPLETED
	
}
