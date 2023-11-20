public class CarreraNotifyAll {
    /* 
     Práctica guiada: introducción a wait, notify y notifyAll.

    Crea una carrera de corredores. Los corredores esperan a la salida (notifyAll), cuando un corredor
    llega notifica al thread principal en la meta. El thread principal interrumpe a los corredores.
     */
    public static void main(String[] args) {
        class Corredor{
            int num;
            int distanciaRecorrida;
            final int distanciaTotal = 100; 
            Runnable vida = () -> {
                while(distanciaRecorrida < distanciaTotal){
                    distanciaRecorrida += (int)(Math.random() * 10);
                }
            };
            Thread carrera;

            public Thread getCarrera() {
                return carrera;
            }

            public Corredor(int num, Object avisoInicio, Object avisoFinal) {
                this.num = num;
                carrera = new Thread(vida);
            }           
        }

        //perparando los corredores
        //TO-DO

        System.out.print("La carrera comienza en... ");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) { }
        System.out.print("3 ");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) { }
        System.out.print("2 ");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) { }
        System.out.print("1 ");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) { }
        System.out.println("¡YA!");

    }
}
