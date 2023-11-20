public class CarreraCorredor implements Runnable {
    int num;
    int distanciaRecorrida;
    Long tiempo = null;
    static final int DISTANCIA_TOTAL = 100, INTERVALO = 10, NUM_CORREDORES = 10;
    private static final long TIEMPO_ESPERA = 500;
    Object lock;

    public CarreraCorredor(int num, Object lock) {
        this.num = num;
        this.lock = lock;
        this.distanciaRecorrida = 0;
    }

    public void run(){
        synchronized (lock) {
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long inicio = System.currentTimeMillis();
        while(distanciaRecorrida < DISTANCIA_TOTAL){
            distanciaRecorrida += (int)(Math.random() * INTERVALO);
            try {
                Thread.sleep(TIEMPO_ESPERA);
            } catch (InterruptedException e) {}
        }
        long llegada = System.currentTimeMillis();
        tiempo = llegada - inicio;
        System.out.printf("Soy el corredor nº %d y he llegado a la meta en %d milisegundos.\n", num, tiempo);
    }
}
