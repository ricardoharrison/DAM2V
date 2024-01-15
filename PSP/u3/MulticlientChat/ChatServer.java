import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.HashMap;
import java.util.Map;

public class ChatServer {
    String ip;
    static Integer port = 8000;
    String msg;
    final static String DESC_MSG = "DESC";
    final static int MAX_LENGTH = (int) (Math.pow(2, 14));
    Map<String, String> userMap = new HashMap<>();

    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(port); // Abre el socket en el puerto 8000
            byte[] receivedData = new byte[MAX_LENGTH];

            while (true) {
                DatagramPacket receivedPacket = new DatagramPacket(receivedData, receivedData.length);
                socket.receive(receivedPacket); // Espera y recibe el paquete

                // Extrae la información del paquete
                String message = new String(receivedPacket.getData(), 0, receivedPacket.getLength());
                String[] decodedMessage = message.split(";");

                if (decodedMessage[0].equals("")) {
                    decodedMessage[0] = "GUEST";
                }

                if (decodedMessage[1].equals(DESC_MSG)) {
                    System.out.println(decodedMessage[0].toUpperCase() + " se ha desconectado.");
                } else {
                    System.out.println(decodedMessage[0].toUpperCase() + ": " + decodedMessage[1]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
