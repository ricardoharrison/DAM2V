import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

/**
 * ChatIpServer
 */
public class ChatIpServer {

    private static final int MAX_LENGTH = 65535;
    private static final int PORT = 9876;
    static InetAddress ip;
    static ArrayList<InetAddress> ipList = new ArrayList<>();

    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(PORT); // Abre el socket en el puerto 9876
            byte[] receivedData = new byte[MAX_LENGTH];

            while (true) {
                DatagramPacket receivedPacket = new DatagramPacket(receivedData, receivedData.length);
                socket.receive(receivedPacket); // Espera y recibe el paquete
                ip = receivedPacket.getAddress(); // Extrae info de la ip que envía el mensaje

                if (!ipList.contains(ip)) {
                    System.out.println("Usuario con ip " + ip + " se ha conectado.");
                    ipList.add(ip);
                }

                // Extrae la información del paquete
                String message = new String(receivedPacket.getData(), 0, receivedPacket.getLength());

                if (message.equals("exit")) {
                    System.out.println("Usuario con ip " + ip + " se ha desconectado.");
                    ipList.remove(ip);
                } else {
                    System.out.println(ip + ": " + message);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}