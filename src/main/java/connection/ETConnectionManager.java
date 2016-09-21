package connection;

/** Manages the connection to the RED-m eyetracker.
 *  <p>
 *  There can only be one active connection at a time.
 *  Having multiple instances of this class will result in unintuitive behavior 
 *  because of shared state. Please use the IViewX class as a central access point.
 * 
 *  @author Luca Fuelbier
 */
public interface ETConnectionManager {
	
	/** Connects to a IView X Server that manages a RED-m eyetracker.
	 * 
	 *  @param sendIp IP on which messages will be send to the server
	 *  @param sendPort Port on which messages will be send to the server
	 *  @param receiveIp IP on which messages will be received from the server
	 *  @param receivePort Port on which messages will be received from the server
	 */
	public void connect(String sendIp, int sendPort, String receiveIp, int receivePort);
	
	/** Connects to locally running IViewX Server that manages a RED-m eyetracker. */
	public void connectLocal();
	
	/** Disconnects from the currently connected IView X Server. */
	public void disconnect();
	
	/** Returns <i>true</i> if the ConnectionManager if the ConnectionManager manages an active connection. */
	public boolean isConnected();
	
	/** Sets the connection timeout in seconds.
	 * 
	 *  @param seconds Number of seconds before timeout will occur
	 */
	public void setConnectionTimeout(int seconds);
	
}
