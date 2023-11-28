public class SyncronizedWith5Threads {
    static int count = 0;
    final static int OPERAT_NUM = 3000;

    public static synchronized void increment() {
        count++;
    }

    public static synchronized void decrease() {
        count--;
    }

    public static void main(String[] args) {
        Runnable myAddingRunnable = () -> {
            for(int i = 0; i < OPERAT_NUM; i++){
                increment();
            }
        };

        Runnable mySubtractingRunnable = () -> {
            for(int i = 0; i < OPERAT_NUM; i++){
                decrease();
            }
        };

        Thread thread1 = new Thread(myAddingRunnable);
        Thread thread2 = new Thread(myAddingRunnable);
        Thread thread3 = new Thread(myAddingRunnable);
        Thread thread4 = new Thread(myAddingRunnable);
        Thread thread5 = new Thread(myAddingRunnable);

        Thread thread6 = new Thread(mySubtractingRunnable);
        Thread thread7 = new Thread(mySubtractingRunnable);
        Thread thread8 = new Thread(mySubtractingRunnable);
        Thread thread9 = new Thread(mySubtractingRunnable);
        Thread thread10 = new Thread(mySubtractingRunnable);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();
        thread7.start();
        thread8.start();
        thread9.start();
        thread10.start();
        
        try{
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
            thread5.join();
            thread6.join();
            thread7.join();
            thread8.join();
            thread9.join();
            thread10.join();
        } catch (InterruptedException e) {}

        System.out.println("Contador = " + count);
    }
}