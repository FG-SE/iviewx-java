package connection;

import java.nio.ByteBuffer;

import iviewxapi.IViewXAPILibrary;
import static iviewxapi.IViewXAPILibrary.*;
import exception.ETErrorHandler;

public class ETIViewXConnectionManager implements ETConnectionManager {
	
	private IViewXAPILibrary iView;
	
	public ETIViewXConnectionManager(IViewXAPILibrary lib) {
		iView = lib;
	}

	@Override
	public void connect(String sendIp, int sendPort, String receiveIp, int receivePort) {
		ByteBuffer sendIpBuffer = ByteBuffer.wrap(sendIp.getBytes());
		ByteBuffer receiveIpBuffer = ByteBuffer.wrap(receiveIp.getBytes());
		int status = iView.iV_Connect(sendIpBuffer, sendPort, receiveIpBuffer, receivePort);
		ETErrorHandler.handle(status);
	}

	@Override
	public void connectLocal() {
		int status = iView.iV_ConnectLocal();
		ETErrorHandler.handle(status);
	}

	@Override
	public void disconnect() {
		int status = iView.iV_Disconnect();
		ETErrorHandler.handle(status);
	}

	@Override
	public boolean isConnected() {
		int status = iView.iV_IsConnected();
		return status == RET_SUCCESS;
	}

	@Override
	public void setConnectionTimeout(int seconds) {
		int status = iView.iV_SetConnectionTimeout(seconds);
		ETErrorHandler.handle(status);
	}

}
