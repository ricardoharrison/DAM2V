import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UdpServer {
    private static final int MAX_LENGTH = 65535;
    private static final int PORT       = 9876;

    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(PORT); // Abre el socket en el puerto 9876
            byte[] receivedData = new byte[MAX_LENGTH];

            while(true) {
                DatagramPacket receivedPacket = new DatagramPacket(receivedData, receivedData.length);
                socket.receive(receivedPacket); // Espera y recibe el paquete

                // Extrae la información del paquete
                String message = new String(receivedPacket.getData(), 0, receivedPacket.getLength());
                System.out.println("Mensaje recibido: " + message);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}