package UnicastPrime;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UnicastPrimeClient {
    private static final int MAX_LENGTH = (int) (Math.pow(2, 16) - 1);
    private static final int PORT = 9876;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress ipAddress = InetAddress.getByName("localhost"); // Dirección del servidor
            byte[] sendData = new byte[MAX_LENGTH];
            byte[] receivedData = new byte[MAX_LENGTH];
            String input = "";

            while (!input.equals("exit")) {
                System.out.println("Escribe un número para comprobar si es primo: ");
                input = sc.nextLine(); // Mensaje a enviar 
                if(!input.equals("exit")){
                    sendData = input.getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, PORT);
                    socket.send(sendPacket); // Envía el paquete al servidor
                    DatagramPacket receivedPacket = new DatagramPacket(receivedData, receivedData.length);
                    socket.receive(receivedPacket); // Espera y recibe el paquete

                    // Extrae la información del paquete
                    String message = new String(receivedPacket.getData(), 0, receivedPacket.getLength());
                    System.out.println("Respuesta del servidor:");
                    if(message.equals("YES")){
                        System.out.println("El número es primo");
                    } else if (message.equals("NO")) {
                        System.out.println("El número no es primo");
                    } else if (message.equals("NaN")) {
                        System.out.println("El mensaje enviado no se pudo procesar porque no es numérico");
                    } else {
                        System.out.println("Error en la comunicación");
                    }
                }
            }
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
