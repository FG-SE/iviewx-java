package connection;

import java.nio.ByteBuffer;

import iviewxapi.IViewXAPILibrary;
import static iviewxapi.IViewXAPILibrary.*;
import exception.ETErrorHandler;

public class ETIViewXConnection implements ETConnection {
	
	private IViewXAPILibrary iViewXLibrary = IViewXAPILibrary.INSTANCE;

	@Override
	public void connect(String sendIp, int sendPort, String receiveIp, int receivePort) {
		ByteBuffer sendIpBuffer = ByteBuffer.wrap(sendIp.getBytes());
		ByteBuffer receiveIpBuffer = ByteBuffer.wrap(receiveIp.getBytes());
		int status = iViewXLibrary.iV_Connect(sendIpBuffer, sendPort, receiveIpBuffer, receivePort);
		ETErrorHandler.handle(status);
	}

	@Override
	public void connectLocal() {
		int status = iViewXLibrary.iV_ConnectLocal();
		ETErrorHandler.handle(status);
	}

	@Override
	public void disconnect() {
		int status = iViewXLibrary.iV_Disconnect();
		ETErrorHandler.handle(status);
	}

	@Override
	public boolean isConnected() {
		int status = iViewXLibrary.iV_IsConnected();
		return status == RET_SUCCESS;
	}

	@Override
	public void setConnectionTimeout(int seconds) {
		int status = iViewXLibrary.iV_SetConnectionTimeout(seconds);
		ETErrorHandler.handle(status);
	}

	@Override
	public IViewXAPILibrary getIViewXLibrary() {
		return iViewXLibrary;
	}

}
