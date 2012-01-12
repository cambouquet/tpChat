import java.rmi.AccessException;
import java.rmi.NotBoundException;
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
	private String serverTopic;
	
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
					try {
						serveurConnection = (SetClientThread) (registry.lookup("rmiServer"));
						
						// call the remote method
						clientId = serveurConnection.connect(clientNick);
						
						// Register for the server
						registry.rebind("rmiClient" + clientId, this);
					} catch (AccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (NotBoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("Connecté avec l'ID " + clientId + " et le pseudo " + clientNick);
					
					// Obtain the server topic
					try {
						serverTopic = serveurConnection.getTopic();
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("MOTD: " + serverTopic);
	}
	
	public void run() {
		//TODO
	}
	
	public static void main(String args[]) {
		System.out.println("Client lancé");

		System.out.println("IP serveur: ");
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