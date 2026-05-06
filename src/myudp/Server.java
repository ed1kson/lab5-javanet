package myudp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.sql.SQLOutput;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Server {
    private static Set<String> registry = Collections.synchronizedSet(new HashSet<>());

    public static void main(String[] args) {
        int port = 8081;

        try (DatagramSocket socket = new DatagramSocket(port)) {
            byte[] buffer = new byte[1024];

            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                InetAddress clientAdress = packet.getAddress();
                int clientPort = packet.getPort();
                String coordiantes = clientAdress.getHostAddress() + ":" + clientPort;

                registry.add(coordiantes);
                System.out.println("registered: " + coordiantes);


                StringBuilder sb = new StringBuilder("All registered devices: ");
                for ( String coor : registry ) {
                    sb.append("\n\t").append(coor);
                }

                byte[] response = sb.toString().getBytes();
                DatagramPacket responsePacket = new DatagramPacket(response, response.length, clientAdress, clientPort);
                socket.send(responsePacket);
            }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
