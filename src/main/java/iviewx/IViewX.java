package iviewx;

import iviewxapi.IViewXAPILibrary;
import iviewx.data.ETEvent;
import iviewx.data.ETSample;
import iviewx.eyetracker.ETCalibrationManager;
import iviewx.eyetracker.ETConnectionManager;
import iviewx.eyetracker.ETEventReceiver;
import iviewx.eyetracker.ETSampleReceiver;
import iviewx.eyetracker.ETValidationManager;
import core.receiver.ETReceiver;

/** The central access point to the part of the Eyetracking API which
 *  uses the SMI IView X Server for eyetracking data retrieval.
 *  <p>
 *  The IViewX class provides methods for connecting to the server, calibrating the 
 *  eyetracking device and validating the calibration results. To receive 
 *  eyetracking samples and events there are methods for getting a 
 *  ETSampleReceiver and a ETEventReceiver.
 *  <p>
 *  The interface of this class is completely static and should be used as the 
 *  sole access point to IViewX backed classes (indicated by the ETIViewX prefix).
 *  <p>
 *  There are a couple things worth noting when working with this class:
 *  <ul>
 *  	<li> 
 *  		The services provided by this class should only be used by
 *  		a single thread at a time. The SMI eyetracking library provides
 *  		a couple of abstractions that make it possible to work
 *  		concurrently with these services, but they too will only
 *  		use a single service on a single thread. If you pass any
 *  		of these services to multiple abstractions, or use it concurrently
 *  		from multiple threads, correct behavior is not guaranteed.
 *    	</li>
 *    	<li>
 *  		While server access from multiple applications (different processes) is 
 *    		officially supported according to the IViewX SDK documentation 
 *    		(IView X SDK &gt;= 3.4.6, IView X Server &gt;= 2.11.65), it has not been tested.
 *    	</li>
 *    	<li>
 *  		This library and its documentation will be updated as the development team 
 *    		gains new insight into the functionality of the SMI IView X SDK and the 
 *    		IView X Server. Make sure you have the most recent version of the library 
 *    		installed and check its documentation - There might have been some updates!
 *    	</li>
 *  </ul>
 * 
 *  @author Luca Fuelbier
 */
public final class IViewX {
	
	private static final IViewXAPILibrary lib;
	private static final ETCalibrationManager calibrationManager;
	private static final ETConnectionManager connectionManager;
	private static final ETReceiver<ETEvent> eventReceiver;
	private static final ETReceiver<ETSample> sampleReceiver;
	private static final ETValidationManager validationManager;
	
	static {
		// Statically initializes IViewX backed classes for singleton-like behavior
		lib = IViewXAPILibrary.INSTANCE;
		calibrationManager = new ETCalibrationManager(lib);
		connectionManager = new ETConnectionManager(lib);
		eventReceiver = new ETEventReceiver(lib);
		sampleReceiver = new ETSampleReceiver(lib);
		validationManager = new ETValidationManager(lib);
	}
	
	/** Constructs a new IViewX object.
	 *  <p>
	 *  This constructor is private to prohibit the user from creating an actual
	 *  IViewX object. The IViewX class provides a completely static interface and
	 *  is supposed to be used through the class interface alone.
	 */
	private IViewX() { }
	
	/** Connects to an IView X Server.
	 * 
	 *  @see iviewx.eyetracker.ETConnectionManager#connect
	 *  
	 *  @param sendIp IP adress of sender
	 *  @param sendPort Port of sender
	 *  @param receiveIp IP adress of receiver
	 *  @param receivePort Port of receiver
	 */
	public static void connect(String sendIp, int sendPort, String receiveIp, int receivePort) {
		connectionManager.connect(sendIp, sendPort, receiveIp, receivePort);
	}
	
	/** Connect to a locally running IView X Server.
	 * 
	 *  @see iviewx.eyetracker.ETConnectionManager#connectLocal 
	 */
	public static void connectLocal() {
		connectionManager.connectLocal();
	}
	
	/** Disconnects from the currently connected server.
	 * 
	 *  @see iviewx.eyetracker.ETConnectionManager#disconnect
	 */
	public static void disconnect() {
		connectionManager.disconnect();
	}

	/** Returns <i>true</i> if a connection has been established.
	 * 
	 *  @see iviewx.eyetracker.ETConnectionManager#isConnected
	 *  
	 *  @return <i>true</i> if a connection has been established, else <i>false</i>
	 */
	public static boolean isConnected() {
		return connectionManager.isConnected();
	}

	/** Sets the connection timeout in seconds.
	 * 
	 *  @see iviewx.eyetracker.ETConnectionManager#setConnectionTimeout
	 *  
	 *  @param seconds Number of seconds before timeout will occur
	 */
	public static void setConnectionTimeout(int seconds) {
		connectionManager.setConnectionTimeout(seconds);
	}
	
	/** Starts the automatic calibration.
	 * 
	 *  @see iviewx.eyetracker.ETCalibrationManager#calibrate 
	 */
	public static void calibrate() {
		calibrationManager.calibrate();
	}
	
	/** Starts the automatic validation.
	 * 
	 *  @see iviewx.eyetracker.ETValidationManager#validate
	 */
	public static void validate() {
		validationManager.validate();
	}
	
	/** Returns a ETEventReceiver that receives eyetracking events from the connected
	 *  IView X Server.
	 *  <p>
	 *  Note that the returned ETEventReceiver instance is the same for every call to 
	 *  this method.
	 * 
	 *  @return ETEventReceiver for the connected IView X Server
	 */
	public static ETReceiver<ETEvent> getEventReceiver() {
		return eventReceiver;
	}
	
	/** Returns a ETSampleReceiver that receives eyetracking samples from the connected
	 *  IView X Server.
	 *  <p>
	 *  Note that the returned ETSampleReceiver instance is the same for every call to 
	 *  this method. This also means, that you only have to set a stabilization strategy 
	 *  once during application startup.
	 * 
	 *  @return ETSampleReceiver for the connected IView X Server
	 */
	public static ETReceiver<ETSample> getSampleReceiver() {
		return sampleReceiver;
	}

}
