package Broadcast.BroadcastBasico;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServerBroadcast {
    private static final int MAX_LENGTH = 65535;
    private static final int PORT = 8000;

    public static void main(String[] args) {
        try {
            // null == wildcard (cualquier red)
            DatagramSocket socket = new DatagramSocket(PORT, null); // Abre el socket en el
                                                                    // puerto 8000
            socket.setBroadcast(true); // imprescindible para que sea broadcast
            byte[] receivedData = new byte[MAX_LENGTH];

            while (true) {
                DatagramPacket receivedPacket = new DatagramPacket(receivedData, receivedData.length);
                socket.receive(receivedPacket); // Espera y recibe el paquete

                // Extrae la información del paquete
                String message = new String(receivedPacket.getData(), 0, receivedPacket.getLength());
                System.out.println("Mensaje recibido: " + message + "\nIP: " + receivedPacket.getAddress() +
                        "\tPORT: " + receivedPacket.getPort() + "\n--------------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
