import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class MultiClient {
	
    public static void main(String[] args) {
        int port = 1286;
        
        try {
        	
            ServerSocket s = new ServerSocket(port);
            System.out.println("Le serveur est en attente");

            while (true) {
                Socket socket = s.accept();
                System.out.println("Nouveau client connectÃ© : " + socket.getInetAddress().getHostAddress());
               ClientCons t= new ClientCons(socket);
               t.start();
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
               
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                
                
                String chaine1 = (String) in.readObject();
                String chaine2 = (String) in.readObject();

               
				if (chaine1.contains (chaine2)) {
                    out.writeObject(chaine1 + " contient "+chaine2);
                    out.flush();
                } else {
                    out.writeObject(chaine1 + " ne contient pas "+chaine2);
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