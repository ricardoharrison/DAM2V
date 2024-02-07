//package ServerComando;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerComando {
    public static void main(String[] args) {
        ServerSocket server;
        try {
            server = new ServerSocket(1234);
            while (true) {
                // Espera cliente
                System.out.println("Servidor preparado.");
                Socket socket = server.accept();

                ProcessBuilder builder = new ProcessBuilder("dir", "C:\\");
                Process process = builder.start();

                InputStream stream = process.getInputStream();
                String msg = "";

                try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
                    String line = "";
                    while ((line = reader.readLine()) != null) {
                        msg += line + "\n"; // hace falta el salto de línea ya que el BufferedReader lo consume
                    }
                } catch (Exception e) {
                }

                DataOutputStream out = new DataOutputStream(socket.getOutputStream());

                out.writeUTF(msg);
                System.out.println("Mensaje enviado a cliente.");

                socket.close();
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
