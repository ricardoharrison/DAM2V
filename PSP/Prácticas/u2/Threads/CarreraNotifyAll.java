/**
 * CarreraNotifyAll
 */
public class CarreraNotifyAll {

    public static void main(String[] args) {
        Object lock = new Object();
        for (int i = 0; i < CarreraCorredor.NUM_CORREDORES; i++) {
            Thread thread = new Thread(new CarreraCorredor(i + 1, lock));
            thread.start();
        }
        try{
            Thread.sleep(2000);
        } catch (InterruptedException e) { }
        synchronized (lock) {
            lock.notifyAll();
        }
    }
}