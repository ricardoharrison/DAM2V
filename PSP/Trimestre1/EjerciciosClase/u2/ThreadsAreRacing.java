import java.util.ArrayList;

import javax.swing.JOptionPane;

public class ThreadsAreRacing {
    public static void main(String[] args) {
        final int TOTAL_KMS = 100;
        final String ENDED_MSG = "THE RACE HAS FINISHED!\n";
        
        Runner runner1 = new Runner(TOTAL_KMS, 1);
        Runner runner2 = new Runner(TOTAL_KMS, 2);

        //runner1.run(); //evitar utilizar, ya que no es concurrente

        Thread thread1 = new Thread(runner1);
        Thread thread2 = new Thread(runner2);

        thread1.start();  // este sí es concurrente
        thread2.start();

        try{
            thread1.join();  //el hilo principal (main) espera a que los dos hilos terminen
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } 

        System.out.println(ENDED_MSG);
    }
}