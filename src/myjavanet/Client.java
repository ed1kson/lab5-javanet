package myjavanet;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Client {
    public static void main(String[] args) {
        String hostname = "localhost";
        int port = 8080;
        try (Socket socket = new Socket(hostname, port)) {
            System.out.println("Connected!");
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.flush();
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            System.out.println("streams connected!");

            String classname = "myjavanet.FactorialTask";
            byte[] classBytes = Files.readAllBytes(Paths.get("out/production/lab5-javanet/myjavanet/FactorialTask.class"));

            out.writeUTF(classname);
            out.writeInt(classBytes.length);
            out.write(classBytes);
            out.writeObject(new FactorialTask(25));
            System.out.println("Class loaded!");

            Response response = (Response) in.readObject();
            System.out.println("Result: " + response.output());
            System.out.println("Execution time: " + response.executionTime());
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}








