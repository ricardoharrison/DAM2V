
/* package u3.ClienteServidorBasico;
 */
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UdpServer {

    private static final int MAX_LENGTH = (int) (Math.pow(2, 16) - 1); // = 65535
    private static final int EXPECTED_PARAMS = 2;

    public static void main(String[] args) {
        final int EXPECTED_PARAMS = 2;
        final int EXPECTED_MSG_SIZE = 5;
        final char ERR_CHR_1 = 'B';
        final char ERR_CHR_2 = 'O';

        if (args.length != EXPECTED_PARAMS) {
            System.out.println("Número inválido de parámetros");
            return;
        }
        int CLIENT_PORT;
        int SERVER_PORT;
        try {
            CLIENT_PORT = Integer.parseInt(args[0]);
            SERVER_PORT = Integer.parseInt(args[1]);

        } catch (NumberFormatException e) {
            System.out.println("Los parámetros deben ser numéricos (puertos)");
            return;
        }

        try {
            DatagramSocket socket = new DatagramSocket(CLIENT_PORT); // Abre el socket en el puerto 8000
            byte[] receivedData = new byte[MAX_LENGTH];

            while (true) {
                DatagramPacket receivedPacket = new DatagramPacket(receivedData, receivedData.length);
                socket.receive(receivedPacket); // Espera y recibe el paquete

                // Extrae la información del paquete
                String message = new String(receivedPacket.getData(), 0, receivedPacket.getLength());
                if (message.length() == EXPECTED_MSG_SIZE) { // Comprobar que la trama tiene el formato esperado
                    if (message.contains("" + ERR_CHR_1) || message.contains("" + ERR_CHR_2)) {

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}