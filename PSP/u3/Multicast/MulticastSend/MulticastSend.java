import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Scanner;

public class MulticastSend {

    public static void main(String[] args) {
        try {
            String interfaceName = "";

            Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
            for (NetworkInterface netInt : Collections.list(nets)) {
                System.out.println(netInt);
            }
            Scanner scanner = new Scanner(System.in);
            System.out.println("Especifica el nombre del interfaz"); // portatil = wlan1
            interfaceName = scanner.nextLine();

            NetworkInterface netIf = NetworkInterface.getByName(interfaceName);
            System.out.println(netIf);
            int port = 1234;
            InetAddress multicastAddress = InetAddress.getByName("230.0.0.1");
            InetSocketAddress group = new InetSocketAddress(multicastAddress, port);
            MulticastSocket multicastSocket = new MulticastSocket(port);
            multicastSocket.joinGroup(group, netIf);
            System.out.println("Preparado para enviar mensajes:");

            while (true) {
                /* Código de envío */
                String msg = scanner.nextLine();
                byte[] msgBytes = msg.getBytes();
                DatagramPacket sentPacket = new DatagramPacket(msgBytes, msgBytes.length, group);
                multicastSocket.send(sentPacket);
            }

            // Lo dejo cuando quiera
            // s.leaveGroup(group, netIf);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}