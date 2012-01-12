import java.net.InetAddress;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;

public class Serveur extends UnicastRemoteObject implements SetClientThread {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String logMessage;
	private String topic;
	private LinkedList<String> listeKeywords;
	private Registry registry;
	private String inetAdresse;
	private int setPort;

	// Implémentation du constructeur
	public Serveur() throws java.rmi.RemoteException {
		try {

			// get the address of this host.

			inetAdresse = (InetAddress.getLocalHost()).toString();

		}

		catch (Exception e) {

			throw new RemoteException("can't get inet address.");

		}

		setPort = 7777; // this port(registry’s port)

		System.out.println("this address=" + inetAdresse + ",port=" + setPort);

		try {

			// create the registry and bind the name and object.

			registry = LocateRegistry.createRegistry(setPort);

			registry.rebind("rmiServer", this);

		}

		catch (RemoteException e) {

			throw e;

		}
	}

	// Implémentation de la méthode distante
	public int connect(int clientId) throws java.rmi.RemoteException {
		// TODO
		return 0;
	}
	
	public Message send()
	{
		// TOOD
		return new Message();
	}

	public void disconnect(int clientId)
	{
		// TODO
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

		System.out.println("Serveur lancé");
	}
}
