
public interface SetClientThread extends java.rmi.Remote{

	public int connect(String clientNick)  throws java.rmi.RemoteException;
	public String getTopic() throws java.rmi.RemoteException;
}
