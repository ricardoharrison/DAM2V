
/* package PracticaExamen.HidratacionHTTP;
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Hidraserver {
    private static final int DEFAULT_PORT = 8888;
    // primer split()
    private static final int RESOURCE_POSITION = 1;
    private static final int NUMBER_REQUEST_POSITION = 1;
    private static final int AMOUNT_REQUEST_POSITION = 2;

    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(DEFAULT_PORT);

            while (true) {
                Socket clientConnection = server.accept();
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(
                                clientConnection.getInputStream()));
                String requestLine = reader.readLine();

                // Comprobar si el requestLine es null o vacío
                if (requestLine == null || requestLine.isEmpty()) {
                    // Cerrar recursos y seguir a la siguiente iteración
                    reader.close();
                    clientConnection.close();
                    continue;
                }

                System.out.println("'RequestLine': " + requestLine);
                // ejemplo de requestLine -> GET /30/6 HTTP/1.1
                String[] info = extractInfo(requestLine);

                String html = generateWebPage(info);

                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(
                                clientConnection.getOutputStream()));

                writer.write(html);
                writer.flush();

                // Close resources and connection
                reader.close();
                writer.close();
                clientConnection.close();
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    private static String generateWebPage(String[] info) {
        String str = "HTTP/1.1 200 OK\n";
        // str += "Content-Type: application/json\n\n"; // para decirle al browser que
        // es un .json
        String body = "<p><ul>\n"; // <p> hace falta para que lo escriba como un html

        try {
            int number = Integer.parseInt(info[NUMBER_REQUEST_POSITION]);
            int amount = Integer.parseInt(info[AMOUNT_REQUEST_POSITION]);

            while (amount > 0) {
                if (isPrime(number + 1)) {
                    body += "<li>" + (number + 1) + "</li>\n";
                    amount--;
                }
                number++;
            }

            body += "</ul><p>";

        } catch (Exception e) {
            str = "HTTP/1.1 400 Bad Request\n\nFormat not valid.";
            return str;
        }

        return str + "\n" + body;

    }

    private static String[] extractInfo(String requestLine) {
        String[] result;
        result = requestLine.split(" ");
        result = result[RESOURCE_POSITION].trim().split("/"); // importante doble split
        return result;
    }

    public static boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
