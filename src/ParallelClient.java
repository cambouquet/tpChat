import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;


public class ParallelClient extends UnicastRemoteObject implements FluxServeur, Runnable {
	protected ParallelClient() throws RemoteException {
		super();
		// Register for the client
		Registry registry;
		try {
			// get the “registry”
			registry = LocateRegistry.getRegistry(80);
			registry.rebind("rmiClient" + clientId, this);
		} catch (RemoteException e) {
			System.err.println("Erreur dans la communication");
			e.printStackTrace();
		}
	}

	private int clientId;

	@Override
	public void envMessage(String sc) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public LinkedList<Message> syncServ(int lastMessage) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	
}
