/* Hilos:

Crea una clase que extienda de Thread y sobrescriba el método run para imprimir "Hola Mundo" en la consola. Luego, instancia y ejecuta el hilo en la clase principal.
Crea un versión de forma que implementes Runnable.
Crea una versión con un Lambda.
Haz un método princpial que arranque los 3 threads. */


public class Thread3Opciones implements Runnable {

    String msg;

    public Thread3Opciones(String msg) {
        this.msg = msg;
    }

    @Override
    public void run() {
            System.out.println(msg);
        }

    public static void main(String[] args) {

        //con "extends Thread"
        ClaseExtiendeThreads aux = new ClaseExtiendeThreads("¡Hola mundo desde clase que extiende Threads!");
        Thread saludarConExtends = new Thread(aux);
        
        //con Runnable
        Thread3Opciones aux2 = new Thread3Opciones("¡Hola mundo desde clase que eimplementa Runnable!");
        Thread saludarConRunnable = new Thread(aux2);
    

        //con Lambda
        Runnable aux3 = () -> {
            System.out.println("¡Hola mundo desde Runnable creado con expresión Lambda!");
        };
        Thread saludarConLambda = new Thread(aux3);

        saludarConExtends.start();
        saludarConRunnable.start();
        saludarConLambda.start();

        try{
            saludarConExtends.join();
            saludarConRunnable.join();
            saludarConLambda.join();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }        
    }
} 