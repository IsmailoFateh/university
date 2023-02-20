import java.io.*;
import java.net.*;
import java.util.Scanner;

public class clientobject2 {

	public static void main(String[] args) throws ClassNotFoundException {
		int port = 1286;
		String host = "localhost";
		Scanner scan = new Scanner(System.in);
		try {
			System.out.print("donner la chaine:");
			String chaine3 = scan.next();
			System.out.print("donner la chaine:");
			String chaine4 = scan.next();

			InetAddress adr = InetAddress.getByName(host);

			Socket socket = new Socket(adr, port);
			ObjectOutputStream out2 = new ObjectOutputStream(socket.getOutputStream());

			out2.writeObject(chaine3);
			out2.writeObject(chaine4);

			ObjectInputStream in2 = new ObjectInputStream(socket.getInputStream());

			String clientInput = (String) in2.readObject();
			System.out.println(clientInput);
			out2.flush();

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
