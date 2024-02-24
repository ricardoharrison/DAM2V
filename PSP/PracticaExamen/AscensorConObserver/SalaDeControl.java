//package PracticaExamen.AscensorConObserver;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SalaDeControl implements Observador {

    String nombre;

    public SalaDeControl(String nombre) {
        this.nombre = nombre;
    }

    public void lanzarServidor() {
        ServerSocket server;

        try {
            server = new ServerSocket(1234);
            System.out.println("Sala de control activa");
            while (true) {
                Socket socket = server.accept(); // accept() dentro del while para liberar el socket

                new Thread(() -> {
                    System.out.println("Nueva conexión.");

                    while (true) {
                        try {
                            DataInputStream in = new DataInputStream(socket.getInputStream());

                            String msg = in.readUTF();

                            String nombreAscensor = msg.split(";")[0];
                            String planta = msg.split(";")[1];

                            System.out.println(nombreAscensor + " actualmente en planta " + planta);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
        System.out.println("La regla de TRES!");
    }

}
