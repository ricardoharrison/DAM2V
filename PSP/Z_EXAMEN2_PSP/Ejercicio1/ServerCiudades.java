/* package Z_EXAMEN2_PSP.Ejercicio1;
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ServerCiudades {
    // primer split()
    private static final int RESOURCE_POSITION = 1;
    // segundo split()
    // [0] -> campo vacío debido a que la cadena empieza por el
    // caracter con el que se realiza el split() (/)
    private static final int CITY_POSITION = 2;
    private static final String RESPONSE_PACIFIED = "HTTP/1.1 200 OK\n\nCiudad pacificada";
    private static final String RESPONSE_SAVAGE = "HTTP/1.1 200 OK\n\nCiudad salvaje";
    private static final String RESPONSE_NOT_FOUND = "HTTP/1.1 404 Not Found\n\nCiudad no encontrada";
    private static final String PACIFIED_MSG = "Ha sido consultada una capital pacificada: ";
    final static int EXPECTED_PARAMS = 1;

    public interface Observador {
        void update(String name);
    }

    Observador observador;

    void setObservador(Observador observador) {
        this.observador = observador;
    }

    public static void main(String[] args) throws IOException {

        if (args.length != EXPECTED_PARAMS) {
            System.out.println("Número inválido de parámetros");
            return;
        }
        int clientPort;
        try {
            clientPort = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("Los parámetros deben ser numéricos (puertos)");
            return;
        }
        ServerSocket server = new ServerSocket(clientPort);
        Map<String, Boolean> ciudades = loadCitiesMap();

        Observador observadorInstance = (name) -> {
            notifyPacifiedCapital(name);
        };
        ServerCiudades serverCiudades = new ServerCiudades();
        serverCiudades.setObservador(observadorInstance);

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
            // Ejemplo de requestLine -> "GET /suma/5/7 HTTP/1.1"
            String info = extractInfo(requestLine);

            System.out.println("Info: " + info);

            String html = generateWebPage(info, ciudades, observadorInstance);

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
    }

    private static void notifyPacifiedCapital(String name) {
        System.out.println(PACIFIED_MSG + name);
    }

    private static Map<String, Boolean> loadCitiesMap() {
        Map<String, Boolean> ciudades = new HashMap<>();
        ciudades.put("MADRID", true); // Capital de España
        ciudades.put("Barcelona", false); // No es capital
        ciudades.put("LISBOA", true); // Capital de Portugal
        ciudades.put("PARÍS", true); // Capital de Francia
        ciudades.put("LONDRES", true); // Capital del Reino Unido
        ciudades.put("Nueva York", false); // No es capital
        ciudades.put("BERLÍN", true); // Capital de Alemania
        ciudades.put("ROMA", true); // Capital de Italia
        ciudades.put("AMSTERDAM", true); // Capital de Países Bajos
        ciudades.put("DUBLÍN", true); // Capital de Irlanda
        ciudades.put("PRAGA", true); // Capital de República Checa
        ciudades.put("BRUSELAS", true); // Capital de Bélgica
        ciudades.put("VIENA", true); // Capital de Austria
        ciudades.put("BUDAPEST", true); // Capital de Hungría
        ciudades.put("ESTOCOLMO", true); // Capital de Suecia
        ciudades.put("TOKIO", true); // Capital de Japón
        ciudades.put("BEIJING", true); // Capital de China
        ciudades.put("Sídney", false); // No es capital
        ciudades.put("CIUDAD DE MÉXICO", true); // Capital de México
        ciudades.put("BUENOS AIRES", true); // Capital de Argentina
        ciudades.put("Valencia", false); // No es capital
        ciudades.put("Málaga", false); // No es capital
        ciudades.put("Osaka", false); // No es capital
        ciudades.put("Liverpool", false); // No es capital
        ciudades.put("Marsella", false); // No es capital
        ciudades.put("Florencia", false); // No es capital
        ciudades.put("San Francisco", false); // No es capital
        ciudades.put("Melbourne", false); // No es capital
        ciudades.put("Las Vegas", false); // No es capital
        ciudades.put("Boston", false); // No es capital

        return ciudades;
    }

    private static String generateWebPage(String info, Map<String, Boolean> ciudades, Observador obs) {
        String str;

        Boolean isCapital = null;

        System.out.println("info tratada :" + info);

        if (ciudades.containsKey(info)) {
            if (ciudades.get(info)) {
                if (info.equals(info.toUpperCase())) {
                    obs.update(info);
                }
                str = RESPONSE_PACIFIED;
                return str;
            } else {
                str = RESPONSE_SAVAGE;
                return str;
            }
        }
        str = RESPONSE_NOT_FOUND;
        return str;
    }

    private static String extractInfo(String requestLine) {
        String[] result;
        result = requestLine.split(" ");
        result = result[RESOURCE_POSITION].trim().split("/"); // importante doble split
        return result[CITY_POSITION];
    }
}
