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
	 */
	public void connect(String sendIp, int sendPort, String receiveIp, int receivePort);
	
	/** Connects to locally running server that manages an eyetracker. */
	public void connectLocal();
	
	/** Disconnects from the currently connected server. */
	public void disconnect();
	
	/** Returns <i>true</i> if the ETConnectionManager manages a active connection.
	 * 
	 * 	@return <i>true</i> if the ETConnectionManager manages a active connection
	 */
	public boolean isConnected();
	
	/** Sets the connection timeout in seconds.
	 * 
	 *  @param seconds Number of seconds before timeout will occur
	 */
	public void setConnectionTimeout(int seconds);
	
}
