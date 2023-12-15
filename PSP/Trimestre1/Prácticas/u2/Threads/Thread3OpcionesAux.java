public class Thread3OpcionesAux extends Thread {

    String msg;

    public Thread3OpcionesAux(String msg) {
        this.msg = msg;
    }
    
    @Override
    public void run() {
        System.out.println(msg);
    }  
}