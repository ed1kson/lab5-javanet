package mytcp;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private Socket socket;

    ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.flush();
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            String name = in.readUTF();
            int len = in.readInt();
            byte[] bytes = new byte[len];
            in.readFully(bytes);

            class MyClassLoader extends ClassLoader {
                public Class<?> defineClass(String name, byte[] b) {
                    return defineClass(name, b, 0, b.length);
                }
            }

            MyClassLoader loader = new MyClassLoader();
            loader.defineClass(name, bytes);

            Task task = (Task) in.readObject();

            Response response = new MyResponse(task);

            out.writeObject(response);
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
