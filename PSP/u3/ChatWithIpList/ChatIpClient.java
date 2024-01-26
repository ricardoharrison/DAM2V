import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ChatIpClient {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String ip = "localhost";
        Integer port = 9876;
        String msg = "lorem ipsum";
        final int MAX_LENGTH = (int) (Math.pow(2, 14));

        while (true) {
            try {
                DatagramSocket socket = new DatagramSocket();
                InetAddress ipAddress = InetAddress.getByName(ip); // Dirección del servidor
                byte[] sentData = new byte[MAX_LENGTH];
                String sentence = sc.nextLine(); // Mensaje a enviar

                System.out.println(sentence);

                if (sentence.trim().isEmpty()) {
                    sentence = "exit";
                    sentData = sentence.getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(sentData, sentData.length, ipAddress, port);
                    socket.send(sendPacket); // Envía el paquete al servidor
                    socket.close();
                    break;
                }

                sentData = sentence.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sentData, sentData.length, ipAddress, port);
                socket.send(sendPacket); // Envía el paquete al servidor
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("Exiting client...");
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
    }
}
