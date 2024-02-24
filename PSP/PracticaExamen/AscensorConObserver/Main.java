//package PracticaExamen.AscensorConObserver;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Observador> listadoDeObservadores = new ArrayList<>();
        SalaDeControl sala = new SalaDeControl("SALA 1");
        listadoDeObservadores.add(sala);
        Ascensor asc1 = new Ascensor("ASCENSOR 1", listadoDeObservadores);
        Ascensor asc2 = new Ascensor("ASCENSOR 2", listadoDeObservadores);

        new Thread(() -> {
            sala.lanzarServidor();
        }).start();

        new Thread(() -> {
            asc1.lanzarCliente();
        }).start();

        new Thread(() -> {
            asc2.lanzarCliente();
        }).start();

    }
}
