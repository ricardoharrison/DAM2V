package Broadcast;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ServerBroadcast {
    private static final int MAX_LENGTH = 65535;
    private static final int PORT = 8000;

    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(PORT, null); // Abre el socket en el
                                                                    // puerto 8000
            socket.setBroadcast(true);
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
