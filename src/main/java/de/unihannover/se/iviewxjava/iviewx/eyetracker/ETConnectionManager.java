package de.unihannover.se.iviewxjava.iviewx.eyetracker;

import java.nio.ByteBuffer;

import de.unihannover.se.iviewxjava.exception.*;
import de.unihannover.se.iviewxjava.iviewx.IViewX;
import de.unihannover.se.iviewxjava.iviewxapi.IViewXAPILibrary;

import static de.unihannover.se.iviewxjava.iviewxapi.IViewXAPILibrary.RET_SUCCESS;

/** Manages a connection to a RED-m eyetracker.
 *  <p>
 *  There can only be one active connection at a time.
 *  <br>
 *  Having multiple instances of this class will result in unintuitive behavior 
 *  because of shared internal state. Please use the {@link IViewX} class
 *  as a central access point.
 * 
 *  @author Luca Fuelbier
 */
public class ETConnectionManager {
	
	private IViewXAPILibrary iView;
	
	/** Constructs a IView X ConnectionManager that uses the provided IView X SDK binding.
	 * 
	 *  @param lib IView X SDK binding for eyetracker communication
	 */
	public ETConnectionManager(IViewXAPILibrary lib) {
		iView = lib;
	}

	/** Connects to a IView X Server that manages a RED-m eyetracker.
	 * 
	 *  @param sendIp IP on which messages will be send to the server
	 *  @param sendPort Port on which messages will be send to the server
	 *  @param receiveIp IP on which messages will be received from the server
	 *  @param receivePort Port on which messages will be received from the server
	 *  
	 *  @throws ETException If an error occurred while connecting
	 *  @throws ETServerException If the IView X Server could not be found
	 *  @throws ETParameterException If one or more parameter values are invalid
	 *  @throws ETConnectionException If no connection could be established
	 */
	public void connect(String sendIp, int sendPort, String receiveIp, int receivePort) {
		ByteBuffer sendIpBuffer = ByteBuffer.wrap(sendIp.getBytes());
		ByteBuffer receiveIpBuffer = ByteBuffer.wrap(receiveIp.getBytes());
		
		int status = iView.iV_Connect(sendIpBuffer, sendPort, receiveIpBuffer, receivePort);
		ETErrorHandler.handle(status);
	}

	/** Connects to locally running IView X Server that manages a RED-m eyetracker.
	 * 
 	 *  @throws ETException If an error occurred while connecting
	 *  @throws ETServerException If the IView X Server could not be found
	 *  @throws ETConnectionException If no connection could be established
	 */
	public void connectLocal() {
		int status = iView.iV_ConnectLocal();
		ETErrorHandler.handle(status);
	}

	/** Disconnects from the currently connected server.
	 * 
	 *  @throws ETException If an error occurred while disconnecting
	 *  @throws ETSocketException If the used sockets could not be deleted
	 */
	public void disconnect() {
		int status = iView.iV_Disconnect();
		ETErrorHandler.handle(status);
	}

	/** Returns <i>true</i> if the ETIViewXConnectionManager manages a active connection.
	 * 
	 * 	@return <i>true</i> if the ETIViewXConnectionManager manages a active connection
	 */
	public boolean isConnected() {
		int status = iView.iV_IsConnected();
		return status == RET_SUCCESS;
	}

	/** Sets the connection timeout in seconds.
	 * 
	 *  @param seconds Number of seconds before timeout will occur
	 *  
	 *  @throws ETException If an error occurred while setting the new timeout
	 *  @throws ETParameterException If the new timeout value is out of range
	 */
	public void setConnectionTimeout(int seconds) {
		int status = iView.iV_SetConnectionTimeout(seconds);
		ETErrorHandler.handle(status);
	}

}
