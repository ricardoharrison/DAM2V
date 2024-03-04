import java.net.*;

public class UDPClient {
    public static void main(String[] args) {
        final String SERVER_IP = "127.0.0.1";
        final int SERVER_PORT = 9876;
        final int CLIENT_PORT = 1234; // Define the client port here

        try (DatagramSocket clientSocket = new DatagramSocket(CLIENT_PORT)) {
            InetAddress serverAddress = InetAddress.getByName(SERVER_IP);

            // Message to be sent to the server
            String message = "Hello, Server!";
            byte[] sendData = message.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, SERVER_PORT);
            clientSocket.send(sendPacket);

            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);

            String reversedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Received from server: " + reversedMessage);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
