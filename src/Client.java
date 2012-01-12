import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class Client  extends UnicastRemoteObject {
	private int clientId;
	private String clientNick;
	private int lastMessage;
	private SetClientThread serveurConnection;
	private static Registry registry;
	
	public Client()  throws java.rmi.RemoteException
	{
		System.out.println("Pseudo : ");
		Scanner sc = new Scanner(System.in);
		clientNick = sc.nextLine();
		
		System.out.println("Connection au serveur en temps que " + clientNick + "...");
		handshake();
	}
	
	public void handshake() {
		// look up the remote object
					serveurConnection = (SetClientThread) (registry.lookup("rmiServer"));
					// call the remote method
					clientId = serveurConnection.connect(clientNick);
					registry.rebind("rmiClient" + clientId, this);
	}
	
	public void run() {
		//TODO
	}
	
	public static void main(String args[]) {
		System.out.println("Client lancé");

		System.out.println("Pseudo : ");
		Scanner sc = new Scanner(System.in);
		String serverAddress = sc.nextLine();

		int serverPort = 80;
		
		try {
			// get the “registry”
			registry = LocateRegistry.getRegistry(serverPort);
		} catch (RemoteException e) {
			System.err.println("Erreur dans la communication");
			e.printStackTrace();
		}
		
		try {
			new Client();
		} catch (RemoteException e) {
			System.err.println("Erreur lors de la création du client");
		}
	}
}