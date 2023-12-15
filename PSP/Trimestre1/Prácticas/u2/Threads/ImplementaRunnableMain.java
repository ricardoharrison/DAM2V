/* Crea una clase que implemente la interfaz Runnable y sobrescriba el método run para imprimir los números del 1 al 10. Luego, instancia y ejecuta el hilo en la clase principal.

Modifica el programa anterior para que cree un array de N Threads y los espere. A cada thread le dará un nombre (Método setName) y escribirá la tabla de un número.

    NOTA: La salida estará desordenada.

Ejecuta el comando en la terminal, y vuelca su salida a un fichero. Utiliza las redirecciones de linux y el comando sort para verificar que has escrito todas las tablas. */

import java.util.ArrayList;

public class ImplementaRunnableMain {

  public static void main(String[] args) {

    ArrayList<Thread> listaHilos = new ArrayList<>();

    for(int i = 1; i <= 10; i++) {
      ImplementaRunnableClase aux = new ImplementaRunnableClase(i);
      Thread hilo = new Thread(aux);
      hilo.setName("HILO" + i);
      listaHilos.add(hilo);
    }

    for(Thread hilo : listaHilos) {
      hilo.start();
    }

    for(Thread hilo : listaHilos) {
      try{
        hilo.join();
      } catch (InterruptedException e) {
        System.out.println("Error cerrando hilo con nombre " + hilo.getName());
      }
      
    }
  }
  
}