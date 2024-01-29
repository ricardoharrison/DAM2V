package ChatMulticast;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class MulticastChatClient {
    private static final int MAX_LENGTH = (int) (Math.pow(2, 16) - 1);
    private static final int PORT = 8000;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress ipAddress = InetAddress.getByName("localhost"); // Dirección del servidor
            byte[] sendData = new byte[MAX_LENGTH];
            String sentence = ""; // Mensaje a enviar
            new Thread(() -> {
                try {
                    DatagramSocket socket2 = new DatagramSocket(PORT); // Abre el socket en el puerto 8000
                    byte[] receivedData = new byte[MAX_LENGTH];

                    while (true) {
                        DatagramPacket receivedPacket = new DatagramPacket(receivedData, receivedData.length);
                        socket2.receive(receivedPacket); // Espera y recibe el paquete

                        // Extrae la información del paquete
                        String message = new String(receivedPacket.getData(), 0, receivedPacket.getLength());
                        System.out.println(receivedPacket.getAddress() + ": " + message);
                    }
                } catch (Exception e) {
                }
            }).start();

            while (true) {
                sentence = sc.nextLine();
                sendData = sentence.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, PORT);
                socket.send(sendPacket); // Envía el paquete al servidor
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}