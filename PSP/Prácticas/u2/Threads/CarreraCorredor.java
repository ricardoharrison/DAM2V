public class CarreraCorredor implements Runnable {
    int num;
    int distanciaRecorrida;
    Long tiempo = null;
    static final int DISTANCIA_TOTAL = 100, INTERVALO = 10, NUM_CORREDORES = 10;
    private static final long TIEMPO_ESPERA = 500;
    Object lock;
    Object lock2;

    public CarreraCorredor(int num, Object lock, Object lock2) {
        this.num = num;
        this.lock = lock;
        this.lock2 = lock2;
        this.distanciaRecorrida = 0;
    }

    public void run(){
        try{
            synchronized (lock) {
                lock.wait();
                Thread.sleep(TIEMPO_ESPERA);
            }
            long inicio = System.currentTimeMillis();
            while(distanciaRecorrida < DISTANCIA_TOTAL){
                distanciaRecorrida += (int)(Math.random() * INTERVALO);
                Thread.sleep(TIEMPO_ESPERA);
            }
            long llegada = System.currentTimeMillis();
            synchronized (lock2) {
                lock2.notify();
            }
            tiempo = llegada - inicio;
            System.out.printf("Soy el corredor nº %d y he llegado a la meta en %d milisegundos.\n", num, tiempo);
        } catch (InterruptedException e) {

        }
    }
}