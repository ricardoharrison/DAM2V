//package PracticaExamen.AscensorConObserver;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Ascensor implements Observado {

    ArrayList<Observador> observadores;
    String nombre;
    int planta;

    public Ascensor(String nombre, ArrayList<Observador> observadores) {
        this.observadores = observadores;
        planta = 0;
        this.nombre = nombre;
    }

    @Override
    public void subscribe(Observador o) {
        observadores.add(o);
    }

    @Override
    public void notifySubs() {
        for (Observador o : observadores) {
            o.update();
        }
    }

    void lanzarCliente() {
        try {
            Socket socket = new Socket("127.0.0.1", 1234);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            while (true) {

                out.writeUTF(nombre + ";" + planta);
                if (planta % 3 == 0) {
                    notifySubs();
                }
                try {
                    Thread.sleep(1000);
                    planta++;
                } catch (Exception e) {
                }
            }
        } catch (Exception e) {
        }
    }

    void subirPlanta() {
        planta++;
    }

    void bajarPlanta() {
        planta--;
    }

}
