package Tarea1;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Server1 {

    private static final int MAX_LENGTH = (int)(Math.pow(2, 14));

    public static void main(String[] args) {
        if(args.length != 1){
            System.out.println("Se necesita un parámetro");
            return;
        }

        int port = 0;

        try{
            port = Integer.parseInt(args[0]); 
        } catch (NumberFormatException e){
            System.out.println("Error al convertir el parámetro a número");
        }

        try{
            DatagramSocket socket = new DatagramSocket(port);
            byte[] receivedData = new byte[MAX_LENGTH];

            while(true){
                DatagramPacket packet = new DatagramPacket(receivedData, receivedData.length);
                socket.receive(packet);

                String msg = new String(packet.getData(), 0, packet.getLength());
                System.out.println(msg);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }    
    }
}