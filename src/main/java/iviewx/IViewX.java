package iviewx;

import iviewxapi.IViewXAPILibrary;
import calibration.ETCalibrationManager;
import calibration.ETIViewXCalibrationManager;
import connection.ETConnectionManager;
import connection.ETIViewXConnectionManager;
import event.ETEventReceiver;
import event.ETIViewXEventReceiver;
import sample.ETSampleReceiver;
import sample.ETIViewXSampleReceiver;
import validation.ETValidationManager;
import validation.ETIViewXValidationManager;

/** The central access point to the part of the Eyetracking API which
 *  uses the SMI IView X Server for eyetracking data retrieval.
 *  
 *  The IViewX class provides methods for connecting to the server, calibrating the 
 *  eyetracking device and validating the calibration results. To receive 
 *  eyetracking samples and events there are methods for getting a 
 *  ETSampleReceiver and a ETEventReceiver.
 *  
 *  The interface of this class is completely static and should be used as the 
 *  sole access point to IViewX backed classes (indicated by the ETIViewX prefix).
 *  
 *  There are a couple things worth noting when working with this class:
 *  - Concurrent access to the methods of this class has not been tested.
 *    It is advised to restrict the access to this classes functionality to 
 *    one thread at a time. Concurrent calls may lead to unknown behavior 
 *    on the eyetracking server side and could break your application.
 *  - While server access from multiple applications (different processes) is 
 *    officially supported according to the IViewX SDK documentation 
 *    (IView X SDK >= 3.4.6, IView X Server >= 2.11.65), it has not been tested.
 *  - This library and its documentation will be updated as the development team 
 *    gains new insight into the functionality of the SMI IView X SDK and the 
 *    IView X Server. Make sure you have the most recent version of the library 
 *    installed and check its documentation - There might have been some updates!
 * 
 *  @author Luca Fuelbier
 */
public final class IViewX {
	
	private static final IViewXAPILibrary lib;
	private static final ETCalibrationManager calibrationManager;
	private static final ETConnectionManager connectionManager;
	private static final ETEventReceiver eventReceiver;
	private static final ETSampleReceiver sampleReceiver;
	private static final ETValidationManager validationManager;
	
	static {
		lib = IViewXAPILibrary.INSTANCE;
		calibrationManager = new ETIViewXCalibrationManager(lib);
		connectionManager = new ETIViewXConnectionManager(lib);
		eventReceiver = new ETIViewXEventReceiver(lib);
		sampleReceiver = new ETIViewXSampleReceiver(lib);
		validationManager = new ETIViewXValidationManager(lib);
	}
	
	private IViewX() { }
	
	/** Connects to an IView X Server.
	 * 
	 *  @see connection.ETIViewXConnectionManager#connect
	 */
	public void connect(String sendIp, int sendPort, String receiveIp, int receivePort) {
		connectionManager.connect(sendIp, sendPort, receiveIp, receivePort);
	}
	
	/** Connect to a locally running IView X Server.
	 * 
	 *  @see connection.ETIViewXConnectionManager#connectLocal 
	 */
	public void connectLocal() {
		connectionManager.connectLocal();
	}
	
	/** Disconnects from the currently connected server.
	 * 
	 *  @see connection.ETIViewXConnectionManager#disconnect
	 */
	public void disconnect() {
		connectionManager.disconnect();
	}

	/** Returns <i>true</i> if a connection has been established.
	 * 
	 *  @see connection.ETIViewXConnectionManager#isConnected
	 */
	public boolean isConnected() {
		return connectionManager.isConnected();
	}

	/** Sets the connection timeout in seconds.
	 * 
	 *  @see connection.ETIViewXConnectionManager#setConnectionTimeout
	 */
	public void setConnectionTimeout(int seconds) {
		connectionManager.setConnectionTimeout(seconds);
	}
	
	/** Starts the automatic calibration.
	 * 
	 *  @see calibration.ETIViewXCalibrationManager#calibrate 
	 */
	public void calibrate() {
		calibrationManager.calibrate();
	}
	
	/** Starts the automatic validation.
	 * 
	 *  @see validation.ETIViewXValidationManager#validate
	 */
	public void validate() {
		validationManager.validate();
	}
	
	/** Returns a ETEventReceiver that receives eyetracking events from the connected
	 *  IView X Server.
	 *  
	 *  Note that the returned ETEventReceiver instance is the same for every call to 
	 *  this method.
	 * 
	 *  @return ETEventReceiver for the connected IView X Server
	 */
	public static ETEventReceiver getEventReceiver() {
		return eventReceiver;
	}
	
	/** Returns a ETSampleReceiver that receives eyetracking samples from the connected
	 *  IView X Server.
	 *  
	 *  Note that the returned ETSampleReceiver instance is the same for every call to 
	 *  this method. This also means, that you only have to set a stabilization strategy 
	 *  once during application startup.
	 * 
	 *  @return ETSampleReceiver for the connected IView X Server
	 */
	public static ETSampleReceiver getSampleReceiver() {
		return sampleReceiver;
	}

}
