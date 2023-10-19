import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

class actividad1_2 {
    public static void main(String[] args) {
        String archivo;
        if (args.length <= 0) {
            System.out.println("Inserta un nombre y extensión para el archivo a leer");
            Scanner sc = new Scanner(System.in);
            archivo = sc.nextLine();
            sc.close();
        } else {
            archivo = args[0];
        }

        File file = new File(archivo);
        if (file.exists()) {
            try {
            System.out.println("El contenido del archivo es:\n--------------");
            BufferedReader reader = new BufferedReader(new FileReader(archivo));
            String linea;
            while ((linea = reader.readLine()) != null) {
                System.out.println(linea);
            }
            reader.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        } else {
            System.out.println("El archivo no existe " + archivo);
        }
    }
}