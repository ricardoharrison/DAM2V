import java.net.*;

public class UDPServer {
    public static void main(String[] args) {
        final int SERVER_PORT = 9876;
        byte[] receiveData = new byte[1024];

        try (DatagramSocket serverSocket = new DatagramSocket(SERVER_PORT)) {
            System.out.println("Server started...");

            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);

                String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                System.out.println("Received from client: " + receivedMessage);

                // Reverse the received message
                String reversedMessage = new StringBuilder(receivedMessage).reverse().toString();
                byte[] sendData = reversedMessage.getBytes();

                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
                serverSocket.send(sendPacket);

                System.out.println("Sent to client: " + reversedMessage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
