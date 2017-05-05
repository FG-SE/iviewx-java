package de.unihannover.se.iviewxjava.core.receiver.response;

import de.unihannover.se.iviewxjava.core.receiver.ETReceiver;

/** Wraps the response of a {@link ETReceiver},
 *  adding additional information.
 *  <p>
 *  Besides the data, a response also contains a
 *  {@link ETResponseType}, making it easier
 *  to use the data returned by a receiver, if an
 *  edge case occurs.
 *  <p>
 *  A usual pattern is to return actual data together
 *  with the {@link ETResponseType#NEW_DATA NEW_DATA},
 *  or <strong>null</strong> together with a reasonable
 *  response type.
 *  
 *  @see ETResponseType
 * 
 *  @author Luca Fuelbier
 */
public class ETResponse<E> {
	
	private ETResponseType type;
	private E data;
	
	/** Constructs a new ETResponse with the given type and data.
	 * 
	 *  @param type Response type
	 *  @param data Response data
	 */
	public ETResponse(ETResponseType type, E data) {
		this.type = type;
		this.data = data;
	}
	
	/** Returns the type of this response.
	 * 
	 *  @return Type of this response
	 */
	public ETResponseType getType() {
		return type;
	}
	
	/** Returns the data of this response.
	 * 
	 *  @return Data of this response
	 */
	public E getData() {
		return data;
	}
	
}
