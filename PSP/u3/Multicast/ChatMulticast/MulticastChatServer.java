import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastChatServer {
    private static final int MAX_LENGTH = 65535;
    private static final int PORT = 8000;
    private static final String MULTICAST_ADDRESS = "230.0.0.1";

    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(PORT); // Open socket on port 8000
            MulticastSocket multicastSocket = new MulticastSocket(); // Create multicast socket

            InetAddress groupAddress = InetAddress.getByName(MULTICAST_ADDRESS);
            multicastSocket.joinGroup(groupAddress); // Join the multicast group

            byte[] receivedData = new byte[MAX_LENGTH];

            while (true) {
                DatagramPacket receivedPacket = new DatagramPacket(receivedData, receivedData.length);
                socket.receive(receivedPacket); // Wait and receive packet

                // Extract information from the packet
                String message = new String(receivedPacket.getData(), 0, receivedPacket.getLength());

                // Create a new packet to multicast the message
                DatagramPacket packetSent = new DatagramPacket(message.getBytes(), message.length(), groupAddress,
                        PORT);

                multicastSocket.send(packetSent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
