import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
	public static void main(String args[]) {
		System.out.println("Client lancé");

		Message rmiServer;
		Registry registry;
		String serverAddress = "Camille-VAIO/130.66.208.97";
		String serverPort = "7777";
		String text = "Coucou Antoine !";
		System.out.println("sending " + text + " to " + serverAddress + ":"
				+ serverPort);
		try {
			// get the “registry”
			registry = LocateRegistry.getRegistry(serverAddress, (new Integer(
					serverPort)).intValue());
			// look up the remote object
			rmiServer = (Message) (registry.lookup("rmiServer"));
			// call the remote method
			rmiServer.sayHello(text);
		} catch (RemoteException e) {
			System.err.println("Erreur dans la communication");
			e.printStackTrace();
			
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
}