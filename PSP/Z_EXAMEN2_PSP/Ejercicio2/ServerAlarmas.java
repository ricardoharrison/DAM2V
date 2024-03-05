
/* package Z_EXAMEN2_PSP.Ejercicio2;
 */
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.time.LocalDateTime;

public class ServerAlarmas {
    private static final int MAX_LENGTH = (int) (Math.pow(2, 16) - 1);
    private static final String FILE = "bancos.txt";
    final static int EXPECTED_PARAMS = 2;
    final static int EXPECTED_MSG_SIZE = 5;
    final static char ERR_CHR_1 = 'B';
    final static char ERR_CHR_2 = 'O';
    final static String TXT_ALARM = "Alarma!\n";

    public interface Observador {
        void update(String msg);
    }

    Observador observador;

    void setObservador(Observador observador) {
        this.observador = observador;
    }

    public static void main(String[] args) {

        if (args.length != EXPECTED_PARAMS) {
            System.out.println("Número inválido de parámetros");
            return;
        }
        int clientPort;
        int serverPort;
        try {
            clientPort = Integer.parseInt(args[0]);
            serverPort = Integer.parseInt(args[1]);

        } catch (NumberFormatException e) {
            System.out.println("Los parámetros deben ser numéricos (puertos)");
            return;
        }

        Observador observadorInstance = (message) -> {
            sendAlarmBroadcast(TXT_ALARM, clientPort);
            if (message.contains("" + ERR_CHR_1)) {
                writeInLog();
            }
        };

        ServerAlarmas serverAlarmas = new ServerAlarmas();
        serverAlarmas.setObservador(observadorInstance);

        try {
            DatagramSocket socket = new DatagramSocket(serverPort); // Abre el socket en el puerto 8000
            byte[] receivedData = new byte[MAX_LENGTH];

            while (true) {
                DatagramPacket receivedPacket = new DatagramPacket(receivedData, receivedData.length);
                System.out.println("Esperando mensaje:");
                socket.receive(receivedPacket); // Espera y recibe el paquete

                // Extrae la información del paquete
                String message = new String(receivedPacket.getData(), 0, receivedPacket.getLength());
                if (message.length() == EXPECTED_MSG_SIZE) { // Comprobar que la trama tiene el tamaño esperado
                    if (message.contains("" + ERR_CHR_1) || message.contains("" + ERR_CHR_2)) {
                        observadorInstance.update(message);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void writeInLog() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE, true));
            writer.write(LocalDateTime.now().toString() + "\n");
            writer.flush();
            writer.close();

        } catch (Exception e) {
        }
    }

    private static void sendAlarmBroadcast(String msg, int port) {
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress ipAddress = InetAddress.getByName("192.168.56.255"); // Dirección del servidor
            // todos los que estén en la red 192.168.56.0 pueden recibir mi mensaje

            byte[] sendData = new byte[MAX_LENGTH];

            sendData = msg.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, port);
            socket.send(sendPacket); // Envía el paquete al servidor
            socket.close();
        } catch (Exception e) {
        }
    }
}
