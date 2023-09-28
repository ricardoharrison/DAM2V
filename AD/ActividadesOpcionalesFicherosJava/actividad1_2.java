import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class actividad1_2 {
    public static void main(String[] args) {
        // Especifica la ruta del archivo que deseas visualizar
        String nombreArchivo = "hola.txt";

        // Intenta abrir el archivo
        try {
            File archivo = new File(nombreArchivo);

            // Crea un objeto Scanner para leer el archivo
            Scanner scanner = new Scanner(archivo);

            // Itera sobre cada línea del archivo y muestra su contenido
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                System.out.println(linea);
            }

            // Cierra el scanner
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("El archivo no se pudo encontrar: " + nombreArchivo);
        }
    }
}
