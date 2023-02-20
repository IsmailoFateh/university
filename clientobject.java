import java.io.*;
import java.net.*;
import java.util.Scanner;

public class clientobject {

	public static void main(String[] args) throws ClassNotFoundException {
		int port = 1286;
		String host = "localhost";
		Scanner scan = new Scanner(System.in);
		try {
			System.out.print("donner la chaine:");
			String chaine1 = scan.next();
			System.out.print("donner la chaine:");
			String chaine2 = scan.next();

			InetAddress adr = InetAddress.getByName(host);

			Socket socket = new Socket(adr, port);
			// le flux envoye par le client
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

			out.writeObject(chaine1);
			out.writeObject(chaine2);

			// recuper le flux envoye par le serveur
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

			String clientInput = (String) in.readObject();
			System.out.println(clientInput);
			out.flush();

			System.out.println("Server IP address: " + socket.getInetAddress().getHostAddress());
			System.out.println("Client IP address: " + InetAddress.getLocalHost().getHostAddress());
			System.out.println("Client port: " + socket.getLocalPort());

			socket.close();

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
