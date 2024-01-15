package MulticlientChat;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ChatClient {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String ip = "localhost";
        Integer port = 8000;
        String msg = "!";
        final int MAX_LENGTH = (int)(Math.pow(2, 14));

        while (!msg.trim().isEmpty()){
            try {
                DatagramSocket socket = new DatagramSocket();
                InetAddress ipAddress = InetAddress.getByName(ip); // Dirección del servidor
                byte[] sentData = new byte[MAX_LENGTH];
                String sentence = sc.nextLine(); // Mensaje a enviar
                
                sentData = sentence.getBytes();

                DatagramPacket sendPacket = new DatagramPacket(sentData, sentData.length, ipAddress, port);
                socket.send(sendPacket); // Envía el paquete al servidor
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //Desconexión
        try {
                DatagramSocket socket = new DatagramSocket();
                InetAddress ipAddress = InetAddress.getByName(ip); // Dirección del servidor
                byte[] sentData = new byte[MAX_LENGTH];
                String sentence = "DESC"; // Mensaje a enviar
                
                sentData = sentence.getBytes();

                DatagramPacket sendPacket = new DatagramPacket(sentData, sentData.length, ipAddress, port);
                socket.send(sendPacket); // Envía el paquete al servidor
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }


    }
    


}
