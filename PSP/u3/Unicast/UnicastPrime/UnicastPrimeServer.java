package UnicastPrime;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UnicastPrimeServer {
    private static final int MAX_LENGTH = 65535;
    private static final int PORT = 9876;

    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(PORT); // Abre el socket en el puerto 9876
            InetAddress ipAddress = InetAddress.getByName("localhost"); // Dirección del servidor
            byte[] sendData = new byte[MAX_LENGTH];
            byte[] receivedData = new byte[MAX_LENGTH];
            int number;

            while (true) {
                DatagramPacket receivedPacket = new DatagramPacket(receivedData, receivedData.length);
                socket.receive(receivedPacket); // Espera y recibe el paquete
                int clientPort = receivedPacket.getPort(); // MUY IMPORTANTE - debe reenviarlo al puerto de origen del
                                                           // envío (no al de escucha)

                // Extrae la información del paquete
                String message = new String(receivedPacket.getData(), 0, receivedPacket.getLength());
                String response = "";
                try {
                    number = Integer.parseInt(message);
                    if (isPrime(number)) {
                        response = "YES";
                    } else {
                        response = "NO";
                    }

                } catch (Exception e) {
                    response = "NaN";

                } finally {
                    sendData = response.getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, clientPort);
                    socket.send(sendPacket);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean isPrime(int number) { // qué pasa prim!
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

}
