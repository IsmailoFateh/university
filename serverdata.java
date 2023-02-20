import java.io.*;
import java.net.*;

public class serverdata {
	public static void main(String[] args) {

		int port = 1222;
		try {

			ServerSocket ss = new ServerSocket(port);
			System.out.println("Le serveur est en attente");
			Socket soc = ss.accept();

			DataInputStream in = new DataInputStream(soc.getInputStream());
			DataOutputStream out = new DataOutputStream(soc.getOutputStream());

			String chaine1 = in.readUTF();
			String chaine2 = in.readUTF();

			if (chaine1.contains(chaine2)) {
				out.writeUTF(chaine1 + " contient " + chaine2);
				out.flush();
			} else {
				out.writeUTF(chaine1 + " ne contient pas " + chaine2);
				out.flush();
			}

			System.out.println("Accepted connection from " + soc.getInetAddress());
			System.out.println("Client IP address: " + soc.getInetAddress().getHostAddress());
			System.out.println("Client port: " + soc.getPort());

			ss.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
