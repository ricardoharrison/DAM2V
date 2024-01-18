import java.util.Scanner;

public class Leer {
    private static final Scanner scanner = new Scanner(System.in);

    public static int leerEntero(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextInt();
    }

    public static String leerCadena(String mensaje) {
        System.out.print(mensaje);
        return scanner.next();
    }
}
