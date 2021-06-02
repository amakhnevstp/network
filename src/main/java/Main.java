import java.io.*;
import java.net.*;


public class Main {

    //МЭЙН одного проекта - сервер
    public static void main(String[] args) throws IOException {

        int port = 8081;

        ServerSocket serverSocket = new ServerSocket(port);
        Socket clientSocket = serverSocket.accept(); // ждем подключения
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        System.out.println("New connection accepted");
        final String name = in.readLine();
        out.println(String.format("Hi %s, your port is %d", name, clientSocket.getPort()));

    }

    //МЭЙН другого проекта - клиент
    public static void main(String[] args) {
        String host= "localhost";
        int port= 8081;
        try (Socket clientSocket = new Socket(host, port);
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            out.println("TEST LOCALHOST CONNECTION");
            String resp = in.readLine();
            System.out.println(resp);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
