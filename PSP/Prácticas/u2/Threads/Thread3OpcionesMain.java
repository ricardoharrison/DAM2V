/* Hilos:

Crea una clase que extienda de Thread y sobrescriba el método run para imprimir "Hola Mundo" en la consola. Luego, instancia y ejecuta el hilo en la clase principal.
Crea un versión de forma que implementes Runnable.
Crea una versión con un Lambda.
Haz un método princpial que arranque los 3 threads. */


public class Thread3OpcionesMain implements Runnable {

    String msg;

    public Thread3OpcionesMain(String msg) {
        this.msg = msg;
    }

    @Override
    public void run() {
            System.out.println(msg);
        }

    public static void main(String[] args) {

        //con "extends Thread"
        Thread3OpcionesAux saludarConExtends = new Thread3OpcionesAux("¡Hola mundo desde clase que extiende Threads!");
        
        //con Runnable
        Thread3OpcionesMain aux2 = new Thread3OpcionesMain("¡Hola mundo desde clase que implementa Runnable!");
        Thread saludarConRunnable = new Thread(aux2);
    

        //con Lambda
        Runnable aux3 = () -> {
            System.out.println("¡Hola mundo desde objeto Runnable creado con expresión Lambda!");
        };
        Thread saludarConLambda = new Thread(aux3);

        //lanzamos y esperamos a que terminen (start/join)
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