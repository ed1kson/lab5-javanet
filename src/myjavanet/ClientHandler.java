package myjavanet;

import java.io.*;
import java.net.Socket;
import java.nio.Buffer;

public class ClientHandler implements Runnable {
    private Socket socket;

    ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
