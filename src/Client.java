import java.rmi.*;

public class Client {
	public static void main(String args[]) {
		try {
			System.out.println("Client lancé");
			// Récupération d'un stub sur l'objet serveur.
			Message obj = (Message) Naming.lookup("//127.0.0.1/mon_serveur");
			// Appel d'une méthode sur l'objet distant.
			obj.sayHello();
		} catch (Exception exc) {
		}
	}
}