import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class HtmlServerMain {

    private static final int DEFAULT_PORT = 8765;
    private static final int RESOURCE_POSITION = 1;

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(DEFAULT_PORT);

        while (true) {
            Socket clientConnexion = server.accept();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            clientConnexion.getInputStream()));
            String header = reader.readLine();

            // Comprobar si el header es null o vacío
            if (header == null || header.isEmpty()) {
                // Cerrar recursos y seguir a la siguiente iteración
                reader.close();
                clientConnexion.close();
                continue;
            }
            // GET /index.html HTTP/1.1 >>>> (petición tal y como le llega al server HTTP)
            System.out.println("Header: " + header);
            String info = extractInfo(header);
            System.out.println("'Info' antes de eliminar la barra: " + info);
            info = info.replaceFirst("/", ""); // Eliminar la barra de la petición HTTP
            System.out.println("'Info' después de eliminar la barra: " + info);

            String html = generateWebPage(info);

            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(
                            clientConnexion.getOutputStream()));

            writer.write(html);
            writer.flush();

            // Close resources and connection
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
