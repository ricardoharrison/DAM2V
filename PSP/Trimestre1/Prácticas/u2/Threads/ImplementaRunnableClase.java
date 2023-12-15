/* Crea una clase que implemente la interfaz Runnable y sobrescriba el método run para imprimir los números del 1 al 10. Luego, instancia y ejecuta el hilo en la clase principal.

Modifica el programa anterior para que cree un array de N Threads y los espere. A cada thread le dará un nombre (Método setName) y escribirá la tabla de un número.

    NOTA: La salida estará desordenada.

Ejecuta el comando en la terminal, y vuelca su salida a un fichero. Utiliza las redirecciones de linux y el comando sort para verificar que has escrito todas las tablas. */


public class ImplementaRunnableClase implements Runnable {

  int n;
  final static int TOTAL_NUM = 10; 

  public ImplementaRunnableClase(int n) {
    this.n = n;
  }

  @Override
  public void run() {
    System.out.print("Tabla del " + n + " -> ");
    for(int i = 0; i < TOTAL_NUM; i++){
      System.out.print(i * n + "\t");
    }    
    System.out.println("\n");
  }
  
}