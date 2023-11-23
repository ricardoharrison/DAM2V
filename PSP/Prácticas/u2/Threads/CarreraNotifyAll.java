import java.util.ArrayList;

/**
 * CarreraNotifyAll
 */
public class CarreraNotifyAll {

    public static void main(String[] args) {
        Object salida = new Object();
        Object llegada = new Object();
        ArrayList<Thread> lista = new ArrayList<>();
        for (int i = 0; i < CarreraCorredor.NUM_CORREDORES; i++) {
            Thread thread = new Thread(new CarreraCorredor(i + 1, salida, llegada));
            lista.add(thread);
            thread.start();
        }
        try {
            Thread.sleep(CarreraCorredor.TIEMPO_ESPERA);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (salida) {
            salida.notifyAll();
        }
        synchronized(llegada) {
            try{
                llegada.wait();
            } catch (InterruptedException e) {}            
        }
        for(Thread thread : lista){
            if(thread.isAlive()){
                thread.interrupt();
            }
        }
    }
}