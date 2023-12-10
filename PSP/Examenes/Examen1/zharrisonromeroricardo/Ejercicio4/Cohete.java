public class Cohete implements Runnable {
    static int cohetesLlegados = 0;
    final static double DISTANCIA_TOTAL = 408.00;
    final static double INTERVALO = 50.00;
    final double EXPLOSION = 0.1;
    final int MAX_DORMIR = 1000;
    final int MIN_DORMIR = 500;
    double distanciaRecorrida = 0.0;
    boolean exito = true; // explotar != exito

    public Cohete() {

    }

    public synchronized void informarResultado(boolean exito) {
        if (exito) {
            Cohete.cohetesLlegados += 1;
        }
    }

    @Override
    public void run() {
        while (distanciaRecorrida < DISTANCIA_TOTAL) {
            distanciaRecorrida += (Math.random() * INTERVALO);
            try {
                Thread.sleep((int) (Math.random() * (MAX_DORMIR - MIN_DORMIR) + MIN_DORMIR));
            } catch (InterruptedException e) {
            }
            if (Math.random() < EXPLOSION) {
                exito = false; // explota explota me expló
                break;
            }
        }
        informarResultado(exito);
    }
}