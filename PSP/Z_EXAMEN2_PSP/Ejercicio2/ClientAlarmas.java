/* package Z_EXAMEN2_PSP.Ejercicio2;
 */

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ClientAlarmas {
    static final String SERVER_IP = "127.0.0.1";
    static final int SERVER_PORT = 4321;
    static final int CLIENT_PORT = 1234; // Define the client port here

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try (DatagramSocket clientSocket = new DatagramSocket(CLIENT_PORT)) {
            InetAddress serverAddress = InetAddress.getByName(SERVER_IP);

            // Message to be sent to the server
            System.out.println("Escribe una trama:");
            String message = sc.nextLine();
            byte[] sendData = message.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, SERVER_PORT);
            clientSocket.send(sendPacket);

            /*
             * byte[] receiveData = new byte[1024];
             * DatagramPacket receivePacket = new DatagramPacket(receiveData,
             * receiveData.length);
             * clientSocket.receive(receivePacket);
             * 
             * String reversedMessage = new String(receivePacket.getData(), 0,
             * receivePacket.getLength());
             * System.out.println("Received from server: " + reversedMessage);
             */

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
