package connection;

import java.nio.ByteBuffer;

import iviewxapi.IViewXAPILibrary;
import static iviewxapi.IViewXAPILibrary.*;
import exception.ETErrorHandler;

/** Manages a connection to a RED-m eyetracker.
 *  <p>
 *  There can only be one active connection at a time.
 *  Having multiple instances of this class will result in unintuitive behavior 
 *  because of shared state. Please use the IViewX class as a central access point.
 * 
 *  @author Luca Fuelbier
 */
public class ETIViewXConnectionManager implements ETConnectionManager {
	
	private IViewXAPILibrary iView;
	
	public ETIViewXConnectionManager(IViewXAPILibrary lib) {
		iView = lib;
	}

	/** Connects to a IView X Server that manages a RED-m eyetracker.
	 * 
	 *  @param sendIp IP on which messages will be send to the server
	 *  @param sendPort Port on which messages will be send to the server
	 *  @param receiveIp IP on which messages will be received from the server
	 *  @param receivePort Port on which messages will be received from the server
	 */
	@Override
	public void connect(String sendIp, int sendPort, String receiveIp, int receivePort) {
		ByteBuffer sendIpBuffer = ByteBuffer.wrap(sendIp.getBytes());
		ByteBuffer receiveIpBuffer = ByteBuffer.wrap(receiveIp.getBytes());
		int status = iView.iV_Connect(sendIpBuffer, sendPort, receiveIpBuffer, receivePort);
		ETErrorHandler.handle(status);
	}

	/** Connects to locally running IView X Server that manages a RED-m eyetracker. */
	@Override
	public void connectLocal() {
		int status = iView.iV_ConnectLocal();
		ETErrorHandler.handle(status);
	}

	/** Disconnects from the currently connected server. */
	@Override
	public void disconnect() {
		int status = iView.iV_Disconnect();
		ETErrorHandler.handle(status);
	}

	/** Returns <i>true</i> if the ETIViewXConnectionManager manages a active connection.
	 * 
	 * 	@return <i>true</i> if the ETIViewXConnectionManager manages a active connection
	 */
	@Override
	public boolean isConnected() {
		int status = iView.iV_IsConnected();
		return status == RET_SUCCESS;
	}

	/** Sets the connection timeout in seconds.
	 * 
	 *  @param seconds Number of seconds before timeout will occur
	 */
	@Override
	public void setConnectionTimeout(int seconds) {
		int status = iView.iV_SetConnectionTimeout(seconds);
		ETErrorHandler.handle(status);
	}

}
