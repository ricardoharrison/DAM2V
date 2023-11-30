import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

class Ejercicio3 {
    public static void main(String[] args) {
        final int EXPECTED_PARAMS = 2;

        if (args.length != EXPECTED_PARAMS) {
            System.out.println("Número de parámetros incorrecto");
            return; // termina la ejecución
        }

        final int TEXT_POSITION = 0;
        final int HASH_POSITION = 1;
        final int MAX_HASH_LEN = 32;
        String text = args[TEXT_POSITION];
        String hash = args[HASH_POSITION];
        final String[] COMMAND = { "echo", "-ne", text, "|", "md5sum", "-" };

        ProcessBuilder builder = new ProcessBuilder(COMMAND);

        try {
            Process process = builder.start();
        } catch (IOException e) {
        }

        InputStream inputStream = process.getInputStream();

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("Línea leída: " + line);

            }
            System.out.printf("Cadena introducida: %s\tHash de la cadena: %s\tHash Introducido: %s\n", text, line,
                    hash);
            if (hash.substring(0, MAX_HASH_LEN).equals(line.trim())) {
                System.out.println("El hash introducido coincide con el que genera la cadena");
            } else {
                System.out.println("El hash introducido NO coincide con el de la cadena");
            }
        } catch (Exception e) {
        }

    }
}