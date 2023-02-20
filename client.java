import java.net.*;
import java.util.Scanner;
import java.io.*;

public class client {
  public static void main(String[] args) {
    int port = 1234;
    String host = "localhost";
    Scanner scan = new Scanner(System.in);
    try {
      System.out.print("donner une phrase:");
      String chaine1 = scan.next();
      System.out.print("donner la chaine:");
      String chaine2 = scan.next();
      InetAddress adr = InetAddress.getByName(host);

      Socket socket = new Socket(adr, port);

      InputStreamReader reader = new InputStreamReader(socket.getInputStream());
      BufferedReader in = new BufferedReader(reader);
      PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
      BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

      String clientInput1 = console.readLine();
      out.println(chaine1 + " " + clientInput1);
      out.flush();

      String clientInput2 = console.readLine();
      out.println(chaine2 + " " + clientInput2);
      out.flush();

      String serverResponse = in.readLine();

      System.out.println("mon adress client:" + socket.getLocalAddress() + " " + socket.getLocalPort());
      System.out.println("mon adress serveur:" + socket.getInetAddress() + " " + socket.getPort());
      System.out.println("Server Response: " + serverResponse);

      socket.close();

    } catch (UnknownHostException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}