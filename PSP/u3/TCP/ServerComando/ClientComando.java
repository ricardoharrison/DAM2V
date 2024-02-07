//package ServerComando;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientComando {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String msg = "";

        try {
            Socket socket = new Socket("localhost", 1234);
            DataInputStream in = new DataInputStream(socket.getInputStream());

            System.out.println("Pulsa ENTER para solicitar el listado de ficheros:");
            sc.nextLine();

            msg = in.readUTF();

            System.out.println("Listado de ficheros recibido desde el servidor:");
            System.out.println("------------------------------------------------");
            System.out.println(msg);
            System.out.println("------------------------------------------------");

            in.close(); // Close the output stream
            socket.close(); // Close the socket
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}