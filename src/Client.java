import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
	public static void main(String args[]) {
		System.out.println("Client lancé");

		Message rmiServer;
		Registry registry;
		String serverAddress = "";
		try {
			serverAddress = (InetAddress.getLocalHost()).toString();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int serverPort = 7777;
		String text = "Coucou Antoine !";
		System.out.println("sending " + text + " to " + serverAddress + ":"
				+ serverPort);
		try {
			// get the “registry”
			registry = LocateRegistry.getRegistry(serverPort);
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