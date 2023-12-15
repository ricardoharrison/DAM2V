/* 04 Contador

Crea una clase Counter con un método sincronizado increment que incremente una variable count. Crea dos hilos que incrementen el contador y observa el resultado. Modifica la clase Counter anterior para usar un bloque sincronizado en lugar de un método sincronizado.
Modificación 04a

Modifica el ejercicio para poder incrementar y decrementar, crea 5 hilos que incrementen 1000 veces y 5 que decrementen 1000 veces. Muestra el resultado de hacer esta operación con sincronización y sin sincornización.
 */

public class SynchronizedCounter {
    int count = 0;

    public synchronized void increment() { //el synchronized es necesario para obtener el resultado esperado (20_000)
        count++;
    }

    public static void main(String[] args) {
        SynchronizedCounter counter = new SynchronizedCounter();

        // Creación de hilos para incrementar el contador
        Runnable myRunnable = () -> {
            for (int i = 0; i < 10_000; i++) {
                counter.increment();
            }
        };

        Thread thread1 = new Thread(myRunnable);
        Thread thread2 = new Thread(myRunnable);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Valor final del contador: " + counter.count);
    }
}