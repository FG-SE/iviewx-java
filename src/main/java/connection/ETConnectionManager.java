package connection;

/** Manages a connection to an eyetracker.
 * 
 *  @author Luca Fuelbier
 */
public interface ETConnectionManager {
	
	/** Connects to a server that manages an eyetracker.
	 * 
	 *  @param sendIp IP on which messages will be send to the server
	 *  @param sendPort Port on which messages will be send to the server
	 *  @param receiveIp IP on which messages will be received from the server
	 *  @param receivePort Port on which messages will be received from the server
	 *  
	 *  @throws exception.ETException If an error occurred while connecting
	 */
	public void connect(String sendIp, int sendPort, String receiveIp, int receivePort);
	
	/** Connects to locally running server that manages an eyetracker.
	 *  
	 *  @throws exception.ETException If an error occurred while connecting
	 */
	public void connectLocal();
	
	/** Disconnects from the currently connected server.
	 * 
	 *  @throws exception.ETException If an error occurred while disconnecting
	 */
	public void disconnect();
	
	/** Returns <i>true</i> if the ETConnectionManager manages a active connection.
	 * 
	 * 	@return <i>true</i> if the ETConnectionManager manages a active connection
	 */
	public boolean isConnected();
	
	/** Sets the connection timeout in seconds.
	 * 
	 *  @param seconds Number of seconds before timeout will occur
	 *  
	 *  @throws exception.ETException If an error occurred while setting the new timeout
	 */
	public void setConnectionTimeout(int seconds);
	
}
