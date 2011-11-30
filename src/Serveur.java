import java.net.InetAddress;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Serveur extends UnicastRemoteObject implements Message {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String message;

	// Implémentation du constructeur
	public Serveur(String msg) throws java.rmi.RemoteException {
		message = msg;
	}

	// Implémentation de la méthode distante
	public void sayHello() throws java.rmi.RemoteException {
		System.out.println(message);
	}

	/**
	 * @param args
	 */
	public static void main(String args[]) {
		int port = 7777;
		String URL;
		
		
		try {
			// Création du serveur de nom - rmiregistry
			Registry registry = LocateRegistry.createRegistry(port);
			// Création d ’une instance de l’objet serveur
			Message obj = new Serveur("Coucou Antoine");
			// Calcul de l’URL du serveur
			URL = "//" + InetAddress.getLocalHost().getHostName() + ":" + port
					+ "/mon_serveur";
			Naming.rebind(URL, obj);
		} catch (Exception exc) {
		}
		
		System.out.println("Serveur lancé");
	}
}
