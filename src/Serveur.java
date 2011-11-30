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
	private String message;
	private String InetAdresse;
	private Registry registry;
	private int port;

	// Implémentation du constructeur
	public Serveur() throws java.rmi.RemoteException {
		try {

			// get the address of this host.

			InetAdresse = (InetAddress.getLocalHost()).toString();

		}

		catch (Exception e) {

			throw new RemoteException("can't get inet address.");

		}

		port = 3232; // this port(registry’s port)

		System.out.println("this address=" + InetAdresse + ",port=" + port);

		try {

			// create the registry and bind the name and object.

			registry = LocateRegistry.createRegistry(port);

			registry.rebind("rmiServer", this);

		}

		catch (RemoteException e) {

			throw e;

		}
	}

	// Implémentation de la méthode distante
	public void sayHello(String message) throws java.rmi.RemoteException {
		System.out.println(message);
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
