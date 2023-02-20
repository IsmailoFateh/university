
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class server2client {
    public static void main(String[] args) throws ClassNotFoundException {
        int port = 1286;
        Scanner scan = new Scanner(System.in);
        try {

            ServerSocket ss = new ServerSocket(port);
            System.out.println("Le serveur est en attente");
            Socket soc1 = ss.accept();
            Socket soc2 = ss.accept();

            System.out.println("Accepted connection from client 1: " + soc1.getInetAddress());
            System.out.println("Client 1 IP address: " + soc1.getInetAddress().getHostAddress());
            System.out.println("Client 1 port: " + soc1.getPort());

            System.out.println("Accepted connection from client 2: " + soc2.getInetAddress());
            System.out.println("Client 2 IP address: " + soc2.getInetAddress().getHostAddress());
            System.out.println("Client 2 port: " + soc2.getPort());
            // premier client

            ObjectInputStream in1 = new ObjectInputStream(soc1.getInputStream());
            ObjectOutputStream out1 = new ObjectOutputStream(soc1.getOutputStream());

            Object chaine1 = (String) in1.readObject();
            Object chaine2 = (String) in1.readObject();
            if (((String) chaine2).contains((String) chaine1)) {
                out1.writeObject(chaine2 + " contient " + chaine1);
                out1.flush();
            } else {
                out1.writeObject(chaine2 + " ne contient pas " + chaine1);
                out1.flush();
            }
            // deuxeme client

            ObjectInputStream in2 = new ObjectInputStream(soc2.getInputStream());
            ObjectOutputStream out2 = new ObjectOutputStream(soc2.getOutputStream());

            Object chaine4 = (String) in2.readObject();
            Object chaine3 = (String) in2.readObject();
            if (((String) chaine4).contains((String) chaine3)) {
                out2.writeObject(chaine4 + " contient " + chaine3);
                out2.flush();
            } else {
                out2.writeObject(chaine4 + " ne contient pas " + chaine3);
                out2.flush();
            }

            ss.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}