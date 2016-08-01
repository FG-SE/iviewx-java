package connection;

import dll.IViewXAPIDllLibrary;

public interface ETConnection {
	
	// Connection
	public void connect(String sendIp, int sendPort, String receiveIp, int receivePort);
	public void connectLocal();
	public void disconnect();
	public boolean isConnected();
	public void setConnectionTimeout(int seconds);
	
	// JNA mapping of IView X API
	public IViewXAPIDllLibrary getIViewXLibrary();
	
}
