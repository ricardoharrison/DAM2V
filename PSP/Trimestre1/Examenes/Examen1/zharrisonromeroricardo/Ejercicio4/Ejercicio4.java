import java.util.ArrayList;

class Ejercicio4 {

    public static void main(String[] args) {

        final int POSICION_ARGS = 0;
        final int EXITO = 3;

        final int TOTAL_COHETES;

        try {
            TOTAL_COHETES = Integer.parseInt(args[POSICION_ARGS]);
        } catch (NumberFormatException e) {
            System.out.println("Parametro no válido");
            return; // salir de la ejecución
        }

        // inciar cohetes y lanzarlos
        ArrayList<Thread> listaCohetes = new ArrayList<>();
        for (int i = 0; i < TOTAL_COHETES; i++) {
            Cohete cohete = new Cohete();
            Thread thread = new Thread(cohete);
            listaCohetes.add(thread);
            thread.start();
        }

        // esperar llegada
        try {
            for (Thread thread : listaCohetes) {
                thread.join();
            }
        } catch (Exception e) {
        }

        System.out.println("Han llegado " + Cohete.cohetesLlegados + " cohetes");
        System.out.println("Han fracasado " + (TOTAL_COHETES - Cohete.cohetesLlegados) + " cohetes");

        if (Cohete.cohetesLlegados >= EXITO) {
            System.out.println("La misión a sido un ÉXITO");
        } else {
            System.out.println("La misión a sido un FRACASO");
        }

    }
}