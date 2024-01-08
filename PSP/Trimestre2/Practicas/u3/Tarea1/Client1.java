package Tarea1;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client1 {
    private static final int MAX_LENGTH = (int)(Math.pow(2, 14));

    public static void main(String[] args) {
        if(args.length != 3){
            System.out.println("Número erróneo de parámetros");
        }

        String ip;
        Integer port = null;
        String msg;

        try{
            port = Integer.parseInt(args[1]);
        } catch (NumberFormatException e){
            System.out.println("Introduce un valor numérico para el puerto");
        }

        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress ipAddress = InetAddress.getByName(args[0]); // Dirección del servidor
            byte[] sentData = new byte[MAX_LENGTH];
            String sentence = args[2]; // Mensaje a enviar
            
            sentData = sentence.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(sentData, sentData.length, ipAddress, port);
            socket.send(sendPacket); // Envía el paquete al servidor
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        
    }
}
