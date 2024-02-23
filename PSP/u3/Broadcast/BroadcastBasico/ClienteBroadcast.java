package Broadcast.BroadcastBasico;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClienteBroadcast {
    private static final int MAX_LENGTH = (int) (Math.pow(2, 16) - 1);
    private static final int PORT = 8000;

    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress ipAddress = InetAddress.getByName("192.168.20.255"); // Dirección del servidor
            // todos los que estén en la red 192.168.20.0 pueden recibir mi mensaje

            byte[] sendData = new byte[MAX_LENGTH];
            String sentence = ""; // Mensaje a enviar
            sentence = "Envio de mensaje broadcast desde el ordenador de Ricardo";

            sendData = sentence.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, PORT);
            socket.send(sendPacket); // Envía el paquete al servidor
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
