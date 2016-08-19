package connection;

import java.nio.ByteBuffer;

import iviewxapi.IViewXAPILibrary;
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
		System.out.println(status);
	}

	@Override
	public void disconnect() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isConnected() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setConnectionTimeout(int seconds) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IViewXAPILibrary getIViewXLibrary() {
		return iViewXLibrary;
	}

}
