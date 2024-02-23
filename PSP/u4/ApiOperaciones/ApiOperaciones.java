import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ApiOperaciones {

    private static final int DEFAULT_PORT = 8888;
    // primer split()
    private static final int RESOURCE_POSITION = 1;
    // segundo split()
    private static final int OPERATION_POSITION = 1;
    private static final int FIRST_OPERAND = 2;
    private static final int SECOND_OPERAND = 3;

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(DEFAULT_PORT);

        while (true) {
            Socket clientConnexion = server.accept();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            clientConnexion.getInputStream()));
            String requestLine = reader.readLine();

            // Comprobar si el requestLine es null o vacío
            if (requestLine == null || requestLine.isEmpty()) {
                // Cerrar recursos y seguir a la siguiente iteración
                reader.close();
                clientConnexion.close();
                continue;
            }

            System.out.println("'RequestLine': " + requestLine);
            String[] info = extractInfo(requestLine);

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

    private static String generateWebPage(String[] info) {
        String str = "HTTP/1.1 200 OK\n";
        str += "Content-Type: application/json\n\n"; // parece decirla al browser que es un .json
        int resultado;
        try {
            switch (info[OPERATION_POSITION]) {
                case "suma":
                    resultado = Integer.parseInt(info[FIRST_OPERAND]) + Integer.parseInt(info[SECOND_OPERAND]);
                    break;
                case "resta":
                    resultado = Integer.parseInt(info[FIRST_OPERAND]) + Integer.parseInt(info[SECOND_OPERAND]);
                    break;
                case "multiplicacion":
                    resultado = Integer.parseInt(info[FIRST_OPERAND]) * Integer.parseInt(info[SECOND_OPERAND]);
                    break;
                case "division":
                    if (Integer.parseInt(info[SECOND_OPERAND]) != 0) {
                        resultado = Integer.parseInt(info[FIRST_OPERAND]) / Integer.parseInt(info[SECOND_OPERAND]);
                    } else {
                        str = "HTTP/1.1 404 Not Found\n\n";
                        return str;
                    }
                    break;
                default:
                    str = "HTTP/1.1 400 Bad Request\n\n";
                    return str;
            }

            System.out.println("resultado: " + resultado);

            str += "{\n\t\"operando 1\": \"" + info[FIRST_OPERAND] + "\",\n\t\"operando 2\": \"" + info[SECOND_OPERAND]
                    + "\",\n\t\"operacion\": \"" + info[OPERATION_POSITION] + "\",\n\t\"resultado\": " + resultado
                    + "\n}";

            return str;

        } catch (NumberFormatException e) {
            str = "HTTP/1.1 403 Forbidden\n\n";
            return str;
        }
    }

    private static String[] extractInfo(String requestLine) {
        String[] result;
        result = requestLine.split(" ");
        result = result[RESOURCE_POSITION].trim().split("/"); // importante doble split
        return result;
    }
}