package de.unihannover.se.iviewxjava.exception;

import de.unihannover.se.iviewxjava.iviewxapi.IViewXAPILibrary;

/** Translates status codes returned by the IView X SDK functions to exceptions.
 *  <p>
 *  The IView X SDK is implemented in C and returns a wide array of different
 *  status and error codes. This class reduces code duplication in the library
 *  by translating the error codes to corresponding exceptions derived from
 *  {@link ETException}.
 *  <p>
 *  Status codes which only indicate functional behavior are not handled by
 *  this error handler (excluding <em>RET_SUCCESS</em>). These status codes begin
 *  with the prefix "RET_". See the IView X SDK Manual for a full list of status
 *  and error codes.
 *  <p>
 *  The correct usage of this error handler is to handle the status codes 
 *  that are not critical in the method that calls the IView X SDK and call this 
 *  error handler if all possible non-critical status codes have been checked.
 *  As a convenience the error handler performs a no-op when handling the
 *  <em>RET_SUCCESS</em> status code, so status codes for functions which either
 *  execute successfully or throw a critical error can be handled by a single call
 *  to {@link #handle}.
 */
public class ETErrorHandler {
	
	/** Translates the status code to an exception, or no-ops when the execution
	 *  was successful.
	 * 
	 *  @param status Status code to be handled
	 */
	public static void handle(int status) {
		switch(status) {
			// All status codes besides RET_SUCCESS have to be handled manually
			case IViewXAPILibrary.RET_SUCCESS:
				return;
			/* Error codes that have been commented out are not documented in the SDK Manual,
			 * but are still part of the IView X SDK header file */
			case IViewXAPILibrary.ERR_COULD_NOT_CONNECT :
				throw new ETConnectionException("Failed to establish connection.");
			case IViewXAPILibrary.ERR_NOT_CONNECTED :
				throw new ETConnectionException("No connection established.");
			case IViewXAPILibrary.ERR_NOT_CALIBRATED :
				throw new ETCalibrationException("System is not calibrated.");
			case IViewXAPILibrary.ERR_NOT_VALIDATED :
				throw new ETValidationException("System is not validated.");
			case IViewXAPILibrary.ERR_EYETRACKING_APPLICATION_NOT_RUNNING :
				throw new ETException("No eyetracking application is running.");
			case IViewXAPILibrary.ERR_WRONG_COMMUNICATION_PARAMETER :
				throw new ETConnectionException("Failed to establish connection.");
			case IViewXAPILibrary.ERR_WRONG_DEVICE :
				throw new ETDeviceException("Eyetracking device required for this function is not connected.");
			case IViewXAPILibrary.ERR_WRONG_PARAMETER :
				throw new ETParameterException("Parameter value is out of range.");
			case IViewXAPILibrary.ERR_WRONG_CALIBRATION_METHOD :
				throw new ETCalibrationException("Eyetracking device required for this calibration method is not connected.");
			case IViewXAPILibrary.ERR_CALIBRATION_TIMEOUT :
				throw new ETCalibrationException("Calibration timeout occurred.");
			case IViewXAPILibrary.ERR_TRACKING_NOT_STABLE :
				throw new ETException("Eyetracking is not stable.");
			case IViewXAPILibrary.ERR_INSUFFICIENT_BUFFER_SIZE :
				throw new ETBufferException("Insufficient buffer size.");
			case IViewXAPILibrary.ERR_CREATE_SOCKET :
				throw new ETSocketException("Cannot create socket.");
			case IViewXAPILibrary.ERR_CONNECT_SOCKET :
				throw new ETSocketException("Cannot connect with socket.");
			case IViewXAPILibrary.ERR_BIND_SOCKET :
				throw new ETSocketException("The defined port is blocked.");
			case IViewXAPILibrary.ERR_DELETE_SOCKET :
				throw new ETSocketException("Failed to delete sockets.");
			case IViewXAPILibrary.ERR_NO_RESPONSE_FROM_IVIEWX :
				throw new ETServerException("No respone from server.");
			case IViewXAPILibrary.ERR_INVALID_IVIEWX_VERSION :
				throw new ETServerException("Invalid server version.");
			case IViewXAPILibrary.ERR_WRONG_IVIEWX_VERSION :
				throw new ETServerException("Wrong server version.");
			case IViewXAPILibrary.ERR_ACCESS_TO_FILE :
				throw new ETException("Failed to access log file.");
			case IViewXAPILibrary.ERR_SOCKET_CONNECTION :
				throw new ETSocketException("Socket connection failed.");
			case IViewXAPILibrary.ERR_EMPTY_DATA_BUFFER :
				throw new ETBufferException("Recording buffer is empty.");
			case IViewXAPILibrary.ERR_RECORDING_DATA_BUFFER :
				throw new ETBufferException("Recording is activated.");
			case IViewXAPILibrary.ERR_FULL_DATA_BUFFER :
				throw new ETBufferException("Data buffer is full.");
			case IViewXAPILibrary.ERR_IVIEWX_IS_NOT_READY :
				throw new ETServerException("Server not ready.");
			case IViewXAPILibrary.ERR_PAUSED_DATA_BUFFER :
				throw new ETBufferException("Paused data buffer.");
			case IViewXAPILibrary.ERR_IVIEWX_NOT_FOUND :
				throw new ETServerException("Server not found.");
			case IViewXAPILibrary.ERR_IVIEWX_PATH_NOT_FOUND :
				throw new ETServerException("Path to server not found.");
			case IViewXAPILibrary.ERR_IVIEWX_ACCESS_DENIED :
				throw new ETServerException("Access to server denied.");
			case IViewXAPILibrary.ERR_IVIEWX_ACCESS_INCOMPLETE :
				throw new ETServerException("Access to server incomplete.");
			case IViewXAPILibrary.ERR_IVIEWX_OUT_OF_MEMORY :
				throw new ETServerException("Out of memory.");
		   /* case ERR_MULTIPLE_DEVICES :
			     return; */
			case IViewXAPILibrary.ERR_CAMERA_NOT_FOUND :
				throw new ETDeviceException("Failed to access eyetracking device.");
			case IViewXAPILibrary.ERR_WRONG_CAMERA :
				throw new ETDeviceException("Failed to access eyetracking device.");
			case IViewXAPILibrary.ERR_WRONG_CAMERA_PORT :
				throw new ETDeviceException("Failed to access port connected to eyetracking device.");
			/* case ERR_USB2_CAMERA_PORT :
				return; */
			/* case ERR_USB3_CAMERA_PORT :
				return; */
			case IViewXAPILibrary.ERR_COULD_NOT_OPEN_PORT :
				throw new ETConnectionException("Failed to open port.");
			case IViewXAPILibrary.ERR_COULD_NOT_CLOSE_PORT :
				throw new ETConnectionException("Failed to close port.");
			case IViewXAPILibrary.ERR_AOI_ACCESS :
				throw new ETAoiException("Failed to access AOI data.");
			case IViewXAPILibrary.ERR_AOI_NOT_DEFINED :
				throw new ETAoiException("AOI not defined.");
			case IViewXAPILibrary.ERR_FEATURE_NOT_LICENSED :
				throw new ETException("Failed to access requested feature.");
			case IViewXAPILibrary.ERR_DEPRECATED_FUNCTION :
				throw new ETException("Function is deprecated.");
			case IViewXAPILibrary.ERR_INITIALIZATION :
				throw new ETException("Function or DLL not initialized.");
			/* case ERR_FUNC_NOT_LOADED :
				return; */
		}
	}
}
