//package HtmlServer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class HtmlServer {

    private static final int DEFAULT_PORT = 8765;
    private static final int RESOURCE_POSITION = 1;

    // peticion HTTP desde browser para hacer funcionar este server ->
    // http://localhost:8765/index.html
    // (es una ruta relativa, ejecutar este fichero java desde la misma carpeta que
    // aloja)

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(DEFAULT_PORT);

        while (true) {
            Socket clientConnexion = server.accept();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            clientConnexion.getInputStream()));
            String header = reader.readLine();
            System.out.println(header);
            // GET ________ HTTP/1.1
            String info = extractInfo(header);
            String html = generateWebPage(info);

            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(
                            clientConnexion.getOutputStream()));

            // Escribir cabecera
            writer.write(html);
            writer.flush();

            reader.close();
            writer.close();
            clientConnexion.close();
        }
    }

    private static String generateWebPage(String info) {
        String str = "HTTP/1.1 200 OK\n\n";
        String line = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(info))) {
            while ((line = reader.readLine()) != null) {
                str += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
            str = "HTTP/1.1 404 Not Found\n\n";
        }
        return str;
    }

    private static String extractInfo(String header) {
        return header.split(" ")[RESOURCE_POSITION];
    }
}