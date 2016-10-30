package exception;

import static iviewxapi.IViewXAPILibrary.*;

public class ETErrorHandler {
	public static void handle(int status) {
		switch(status) {
			case RET_SUCCESS :
				return;
			case RET_NO_VALID_DATA :
				return;
			case RET_CALIBRATION_ABORTED : 
				return;
			case RET_SERVER_IS_RUNNING :
				return;
			case RET_CALIBRATION_NOT_IN_PROGRESS :
				return;
			case RET_WINDOW_IS_OPEN :
				return;
			case RET_WINDOW_IS_CLOSED :
				return;
			case ERR_COULD_NOT_CONNECT :
				throw new ETConnectionException("Failed to establish connection.");
			case ERR_NOT_CONNECTED :
				throw new ETConnectionException("No connection established.");
			case ERR_NOT_CALIBRATED :
				throw new ETCalibrationException("System is not calibrated.");
			case ERR_NOT_VALIDATED :
				throw new ETValidationException("System is not validated.");
			case ERR_EYETRACKING_APPLICATION_NOT_RUNNING :
				return;
			case ERR_WRONG_COMMUNICATION_PARAMETER :
				throw new ETConnectionException("Failed to establish connection.");
			case ERR_WRONG_DEVICE :
				throw new ETDeviceException("Eyetracking device required for this function is not connected.");
			case ERR_WRONG_PARAMETER :
				throw new ETParameterException("Parameter value is out of range.");
			case ERR_WRONG_CALIBRATION_METHOD :
				throw new ETCalibrationException("Eyetracking device required for this calibration method is not connected.");
			case ERR_CALIBRATION_TIMEOUT :
				throw new ETCalibrationException("Calibration timeout occured.");
			case ERR_TRACKING_NOT_STABLE :
				return;
			case ERR_INSUFFICIENT_BUFFER_SIZE :
				throw new ETBufferException("Insufficient buffer size.");
			case ERR_CREATE_SOCKET :
				throw new ETSocketException("Cannot create socket.");
			case ERR_CONNECT_SOCKET :
				throw new ETSocketException("Cannot connect with socket.");
			case ERR_BIND_SOCKET :
				throw new ETSocketException("The defined port is blocked.");
			case ERR_DELETE_SOCKET :
				throw new ETSocketException("Failed to delete sockets.");
			case ERR_NO_RESPONSE_FROM_IVIEWX :
				throw new ETServerException("No respone from server.");
			case ERR_INVALID_IVIEWX_VERSION :
				throw new ETServerException("Invalid server version.");
			case ERR_WRONG_IVIEWX_VERSION :
				throw new ETServerException("Wrong server version.");
			case ERR_ACCESS_TO_FILE :
				return;
			case ERR_SOCKET_CONNECTION :
				throw new ETSocketException("Socket connection failed.");
			case ERR_EMPTY_DATA_BUFFER :
				throw new ETBufferException("Recording buffer is empty.");
			case ERR_RECORDING_DATA_BUFFER :
				throw new ETBufferException("Recording is activated.");
			case ERR_FULL_DATA_BUFFER :
				throw new ETBufferException("Data buffer is full.");
			case ERR_IVIEWX_IS_NOT_READY :
				throw new ETServerException("Server not ready.");
			case ERR_PAUSED_DATA_BUFFER :
				throw new ETBufferException("Paused data buffer.");
			case ERR_IVIEWX_NOT_FOUND :
				throw new ETServerException("Server not found.");
			case ERR_IVIEWX_PATH_NOT_FOUND :
				throw new ETServerException("Path to server not found.");
			case ERR_IVIEWX_ACCESS_DENIED :
				throw new ETServerException("Access to server denied.");
			case ERR_IVIEWX_ACCESS_INCOMPLETE :
				throw new ETServerException("Access to server incomplete.");
			case ERR_IVIEWX_OUT_OF_MEMORY :
				throw new ETServerException("Out of memory.");
		   /* case ERR_MULTIPLE_DEVICES :
			     return; */
			case ERR_CAMERA_NOT_FOUND :
				throw new ETDeviceException("Failed to access eyetracking device.");
			case ERR_WRONG_CAMERA :
				throw new ETDeviceException("Failed to access eyetracking device.");
			case ERR_WRONG_CAMERA_PORT :
				throw new ETDeviceException("Failed to access port connected to eyetracking device.");
			/* case ERR_USB2_CAMERA_PORT :
				return;
			case ERR_USB3_CAMERA_PORT :
				return; */
			case ERR_COULD_NOT_OPEN_PORT :
				throw new ETConnectionException("Failed to open port.");
			case ERR_COULD_NOT_CLOSE_PORT :
				throw new ETConnectionException("Failed to close port.");
			case ERR_AOI_ACCESS :
				throw new ETAoiException("Failed to access AOI data.");
			case ERR_AOI_NOT_DEFINED :
				throw new ETAoiException("AOI not defined.");
			case ERR_FEATURE_NOT_LICENSED :
				return;
			case ERR_DEPRECATED_FUNCTION :
				return;
			case ERR_INITIALIZATION :
				return;
			/* case ERR_FUNC_NOT_LOADED :
				return; */
		}
	}
}
