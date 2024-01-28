package BroadcastNotepad;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class NotepadClient {
    private static final int MAX_LENGTH = (int) (Math.pow(2, 16) - 1);
    private static final int PORT = 8000;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress ipAddress = InetAddress.getByName("192.168.1.255"); // Dirección del servidor
            byte[] sendData = new byte[MAX_LENGTH];
            String sentence = ""; // Mensaje a enviar
            System.out.println("Escribe la instrucción a ejecutar: ");
            sentence = sc.nextLine();

            sendData = sentence.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, PORT);
            socket.send(sendPacket); // Envía el paquete al servidor
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
