package connection;

public interface ETConnectionManager {
	
	// Connection
	public void connect(String sendIp, int sendPort, String receiveIp, int receivePort);
	public void connectLocal();
	public void disconnect();
	public boolean isConnected();
	public void setConnectionTimeout(int seconds);
	
}
