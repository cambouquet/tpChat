import java.net.InetAddress;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class Serveur extends UnicastRemoteObject implements SetClientThread {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String logMessage;
	private static String topic;
	private LinkedList<String> listeKeywords;
	private Registry registry;
	private String inetAdresse;
	private int setPort;
	private HashMap<Integer,String> listeClients;
	private static int lastClientID = 1336;

	// Implémentation du constructeur
	public Serveur() throws java.rmi.RemoteException {
		try {

			// get the address of this host.

			inetAdresse = (InetAddress.getLocalHost()).toString();

		}

		catch (Exception e) {

			throw new RemoteException("can't get inet address.");

		}

		setPort = 80; // this port(registry’s port)

		System.out.println("this address=" + inetAdresse + ",port=" + setPort);

		try {

			// create the registry and bind the name and object.

			registry = LocateRegistry.createRegistry(setPort);

			registry.rebind("rmiServer", this);
		}

		catch (RemoteException e) {

			throw e;

		}
		
		listeClients = new HashMap<Integer, String>();
	}

	// Implémentation de la méthode distante
	public int connect(String clientNick) throws java.rmi.RemoteException {
		//Vérification du pseudo
		if (listeClients.containsValue(clientNick)) {
			String clientNickOld = clientNick;			
			clientNick = clientNick + "_";
			while (listeClients.containsValue(clientNick)) {
				clientNick = clientNick + "_";
			}
			System.out.println("Pseudo " + clientNickOld + " déjà utilisé. Vous êtes enregistré en tant que " + clientNick);
		}
		
		//Attribution d'un clientID
		int currentClientID = lastClientID + 1;
		listeClients.put(currentClientID, clientNick);
		System.out.println("Client " + clientNick + " d'ID " + currentClientID);
		return currentClientID;
	}
	
	public String getTopic() throws java.rmi.RemoteException {
		return topic;
	}
	
	public String getNick(int clientId) throws java.rmi.RemoteException {
		String currentClientNick = listeClients.get(clientId);
		return currentClientNick;
	}
	
	public Message send()
	{
		// TOOD
		return new Message();
	}

	public void disconnect(int clientId)
	{
		String currentClientNick = listeClients.get(clientId);
		System.out.println("Client "+ currentClientNick + " déconnecté.");
		listeClients.remove(clientId);
	}
	
	public LinkedList<String> who()
	{
		return new LinkedList<String>();
	}
	
	/**
	 * @param args
	 */
	public static void main(String args[]) {
		try {

			Serveur s = new Serveur();

		}

		catch (Exception e) {

			e.printStackTrace();

			System.exit(1);

		}
		System.out.println("Message of the Day: ");
		Scanner sc = new Scanner(System.in);
		topic = sc.nextLine();
		System.out.println("Serveur lancé");
	}
}
