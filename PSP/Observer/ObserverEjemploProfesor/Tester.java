public class Tester {
    public static void main(String[] args) {
        Recibidor recibidorUDP = new Recibidor();
        recibidorUDP.setManejadorMensaje( // PIEZA 4 DEL OBSERVER
                new Recibidor.MensajeRecibido() {

                    @Override
                    public void haLlegadoMensaje(String s) {
                        // s la info de red
                        System.out.println("¡Primo!");
                    }
                });
        Thread t = new Thread(recibidorUDP);
        t.start();
    }
}