import java.rmi.Remote;
import java.util.LinkedList;

public interface FluxServeur extends Remote {
	
	public void envMessage(String sc) throws java.rmi.RemoteException;
	public LinkedList<Message> syncServ(int lastMessage) throws java.rmi.RemoteException;
	
}
