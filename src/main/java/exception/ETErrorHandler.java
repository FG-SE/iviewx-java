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
			case RET_SERVER_IS_RUNNING  :
				return;
			case RET_CALIBRATION_NOT_IN_PROGRESS  :
				return;
			case RET_WINDOW_IS_OPEN :
				return;
			case RET_WINDOW_IS_CLOSED :
				return;
			case ERR_COULD_NOT_CONNECT :
				throw new ETConnectionException("Could not connect.");
			case ERR_NOT_CONNECTED :
				throw new ETConnectionException("Not connected.");
			case ERR_NOT_CALIBRATED :
				return;
			case ERR_NOT_VALIDATED :
				return;
			case ERR_EYETRACKING_APPLICATION_NOT_RUNNING :
				return;
			case ERR_WRONG_COMMUNICATION_PARAMETER :
				throw new ETConnectionException("Failed to establish connection.");
			case ERR_WRONG_DEVICE :
				return;
			case ERR_WRONG_PARAMETER :
				throw new ETParameterException("Parameter value is out of range.");
			case ERR_WRONG_CALIBRATION_METHOD :
				return;
			case ERR_CALIBRATION_TIMEOUT :
				return;
			case ERR_TRACKING_NOT_STABLE :
				return;
			case ERR_INSUFFICIENT_BUFFER_SIZE :
				return;
			case ERR_CREATE_SOCKET :
				return;
			case ERR_CONNECT_SOCKET :
				return;
			case ERR_BIND_SOCKET :
				return;
			case ERR_DELETE_SOCKET :
				return;
			case ERR_NO_RESPONSE_FROM_IVIEWX :
				throw new ETServerException("No respone from server.");
			case ERR_INVALID_IVIEWX_VERSION :
				throw new ETServerException("Invalid server version.");
			case ERR_WRONG_IVIEWX_VERSION :
				throw new ETServerException("Wrong server version.");
			case ERR_ACCESS_TO_FILE :
				return;
			case ERR_SOCKET_CONNECTION :
				return;
			case ERR_EMPTY_DATA_BUFFER :
				return;
			case ERR_RECORDING_DATA_BUFFER :
				return;
			case ERR_FULL_DATA_BUFFER :
				return;
			case ERR_IVIEWX_IS_NOT_READY :
				throw new ETServerException("Server not ready.");
			case ERR_PAUSED_DATA_BUFFER :
				return;
			case ERR_IVIEWX_NOT_FOUND :
				throw new ETServerException("Server not found.");
			case ERR_IVIEWX_PATH_NOT_FOUND :
				throw new ETServerException("Path to server not found.");
			case ERR_IVIEWX_ACCESS_DENIED :
				throw new ETServerException("Access to server denied.");
			case ERR_IVIEWX_ACCESS_INCOMPLETE :
				throw new ETServerException("Access to server incomplete.");
			case ERR_IVIEWX_OUT_OF_MEMORY :
				return;
			case ERR_MULTIPLE_DEVICES :
				return;
			case ERR_CAMERA_NOT_FOUND :
				return;
			case ERR_WRONG_CAMERA :
				return;
			case ERR_WRONG_CAMERA_PORT :
				return;
			case ERR_USB2_CAMERA_PORT :
				return;
			case ERR_USB3_CAMERA_PORT :
				return;
			case ERR_COULD_NOT_OPEN_PORT :
				return;
			case ERR_COULD_NOT_CLOSE_PORT :
				return;
			case ERR_AOI_ACCESS :
				return;
			case ERR_AOI_NOT_DEFINED :
				return;
			case ERR_FEATURE_NOT_LICENSED :
				return;
			case ERR_DEPRECATED_FUNCTION :
				return;
			case ERR_INITIALIZATION :
				return;
			case ERR_FUNC_NOT_LOADED :
				return;
		}
	}
}
