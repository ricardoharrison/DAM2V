import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Scanner;

public class MulticastReceive {

    public static void main(String[] args) {
        try {

            String interfaceName = "";
            Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
            for (NetworkInterface netInt : Collections.list(nets)) {
                System.out.println(netInt);
            }
            Scanner in = new Scanner(System.in);
            System.out.println("Especifica el nombre del interfaz");
            interfaceName = in.nextLine();

            NetworkInterface netIf = NetworkInterface.getByName(interfaceName);
            System.out.println(netIf);

            int port = 1234;

            InetAddress multicastAddress = InetAddress.getByName("230.0.0.1");
            InetSocketAddress group = new InetSocketAddress(multicastAddress, port);
            MulticastSocket multicastSocket = new MulticastSocket(port);

            multicastSocket.joinGroup(group, netIf);

            /* Código de lectura */
            byte[] buf = new byte[1000];
            DatagramPacket receivedPacket = new DatagramPacket(buf, buf.length);

            while (true) {
                multicastSocket.receive(receivedPacket);
                System.out.println(receivedPacket.getAddress() + ": "
                        + new String(receivedPacket.getData(), 0, receivedPacket.getLength()));
            }

            // Lo dejo cuando quiera
            // multicastSocket.leaveGroup(group, netIf);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}