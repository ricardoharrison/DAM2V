package u3.MulticlientChat;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ChatClient {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String username;
        if (args.length != 1) {
            username = "GUEST";
        } else {
            username = args[0].replaceAll(";", ""); // solo para evitar que el split() del servidor funcione mal
        }

        String ip = "localhost";
        Integer port = 8000;
        String msg = "lorem ipsum";
        final int MAX_LENGTH = (int) (Math.pow(2, 14));

        while (!msg.trim().isEmpty()) {
            try {
                DatagramSocket socket = new DatagramSocket();
                InetAddress ipAddress = InetAddress.getByName(ip); // Dirección del servidor
                byte[] sentData = new byte[MAX_LENGTH];
                String sentence = sc.nextLine(); // Mensaje a enviar

                if (sentence.trim().isEmpty()) {
                    break;
                }

                String encodedMessage = username + ";" + sentence;
                sentData = encodedMessage.getBytes();

                DatagramPacket sendPacket = new DatagramPacket(sentData, sentData.length, ipAddress, port);
                socket.send(sendPacket); // Envía el paquete al servidor
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Desconexión
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress ipAddress = InetAddress.getByName(ip); // Dirección del servidor
            byte[] sentData = new byte[MAX_LENGTH];
            String sentence = ChatServer.DESC_MSG; // Mensaje a enviar ("DESC")

            String encodedSentence = username + ";" + sentence;

            sentData = encodedSentence.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(sentData, sentData.length, ipAddress, port);
            socket.send(sendPacket); // Envía el paquete al servidor
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
