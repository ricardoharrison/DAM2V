import java.util.ArrayList;

/**
 * CarreraNotifyAll
 */
public class CarreraNotifyAll {

    public static void main(String[] args) {
        Object lock = new Object();
        Object lock2 = new Object();
        ArrayList<Thread> lista = new ArrayList<>();
        for (int i = 0; i < CarreraCorredor.NUM_CORREDORES; i++) {
            Thread thread = new Thread(new CarreraCorredor(i + 1, lock, lock2));
            lista.add(thread);
            thread.start();
        }
        synchronized (lock) {
            lock.notifyAll();
        }
        synchronized(lock2) {
            try{
                lock2.wait();
            } catch (InterruptedException e) {}            
        }
        for(Thread thread : lista){
            if(thread.isAlive()){
                thread.interrupt();
            }
        }
    }
}