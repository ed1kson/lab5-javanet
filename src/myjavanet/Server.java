package myjavanet;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        int port = 8080;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected!");

                new Thread(new ClientHandler(socket) );
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
