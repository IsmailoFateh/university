import java.io.*;
import java.net.*;
import java.util.Scanner;

public class multiserver {
	
    public static void main(String[] args) {
        int port = 1286;
        
        try {
        	
            try (ServerSocket s = new ServerSocket(port)) {
                System.out.println("Le serveur est en attente");

                while (true) {
                    Socket socket = s.accept();
                    System.out.println("Nouveau client connecté : " + socket.getInetAddress().getHostAddress());
                   ClientCons t= new ClientCons(socket);
                   t.start();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ClientCons extends Thread {
        private Socket socket;

        ClientCons(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
            	Scanner scan=new Scanner(System.in);
                System.out.print("donne moi une phrase");
                String chaine1=scan.next();
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

                String chaine2 = (String) in.readObject();

               
				if (chaine2.contains (chaine1)) {
                    out.writeObject(chaine2 + " contient "+chaine1);
                    out.flush();
                } else {
                    out.writeObject(chaine2 + " ne contient pas "+chaine1);
                    out.flush();
                }
              System.out.println("Client address: " + socket.getRemoteSocketAddress());
  		      System.out.println("My address: " + socket.getLocalAddress() + " " + socket.getLocalPort());
                socket.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}