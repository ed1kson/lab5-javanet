package myudp;

import java.io.IOException;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        String hostname = "localhost";
        int serverPort = 8081;

        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress address = InetAddress.getByName(hostname);

            byte[] message = ("howyoudoin").getBytes();
            DatagramPacket request = new DatagramPacket(message, message.length, address, serverPort);
            socket.send(request);

            byte[] buffer = new byte[1024];
            DatagramPacket response = new DatagramPacket(buffer, buffer.length);
            socket.receive(response);

            String result = new String(response.getData(), 0, response.getLength());
            System.out.println("Response from server: \n" + result);

        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
