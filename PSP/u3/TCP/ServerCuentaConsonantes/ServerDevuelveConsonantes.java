import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerDevuelveConsonantes {

    public static void main(String[] args) {
        ServerSocket server;
        try {
            server = new ServerSocket(1234);
            while (true) {
                // Espera cliente
                Socket socket = server.accept();

                DataInputStream in = new DataInputStream(socket.getInputStream());

                String msg = in.readLine();
                System.out.print("He recibido: '" + msg + "'");
                int vocales = 0, consonantes = 0;

                for (int i = 0; i < msg.length(); i++) {
                    if (Character.isLetter(msg.charAt(i))) {
                        if (Character.toLowerCase(msg.charAt(i)) == 'a' ||
                                Character.toLowerCase(msg.charAt(i)) == 'e' ||
                                Character.toLowerCase(msg.charAt(i)) == 'i' ||
                                Character.toLowerCase(msg.charAt(i)) == 'o' ||
                                Character.toLowerCase(msg.charAt(i)) == 'u') {
                            vocales += 1;
                        } else {
                            consonantes += 1;
                        }
                    }
                }

                System.out.println("; Vocales: " + vocales + "; Consonantes: " + consonantes);

                in.close();
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}