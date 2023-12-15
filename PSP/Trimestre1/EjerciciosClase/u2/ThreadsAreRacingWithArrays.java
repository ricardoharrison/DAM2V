import java.util.ArrayList;

import javax.swing.JOptionPane;

public class ThreadsAreRacingWithArrays {
    public static void main(String[] args) {
        final int TOTAL_KMS = 100;
        final String ENDED_MSG = "THE RACE HAS FINISHED!\n";
        int number;

        number = Integer.parseInt(JOptionPane.showInputDialog(null, "Insert a number: "));

        ArrayList<Thread> listOfRunners = new ArrayList<>();

        for(int i = 0; i < number; i++){
            Runner runner = new Runner(TOTAL_KMS, i + 1);
            Thread thread = new Thread(runner);
            listOfRunners.add(thread);
        }

        for(Thread thread : listOfRunners){
            thread.start();
        }

        for(Thread thread : listOfRunners){
            try{
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(ENDED_MSG);
    }
}