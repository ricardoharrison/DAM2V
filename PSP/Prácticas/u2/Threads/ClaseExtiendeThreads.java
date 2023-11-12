public class ClaseExtiendeThreads extends Thread {

    String msg;

    public ClaseExtiendeThreads(String msg) {
        this.msg = msg;
    }
    
    @Override
    public void run() {
        System.out.println(msg);
    }
  
}