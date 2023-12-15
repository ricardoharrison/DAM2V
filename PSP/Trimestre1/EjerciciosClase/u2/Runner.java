public class Runner implements Runnable {
    int traveledKms;
    int totalKms;
    int number;
    static final long REST_TIME = 500;
    static final double MAX_KM_INTERVAL = 10;
    static final double STARTING_TRAVELED_KMS = 0;
    static final String REST_MSG = "I'm taking a break...\n";
    static final String TRAVELED_KM_MSG = "After last interval, I've made a total of %d kms.\n";
    static final String PERCENT_TRAVELED = "#%d has already traveled more than %.0f%% of the total distance (%d kms.).\n";
    static final double percent25 = 0.25, percent50 = 0.5, percent75 = 0.75;


    public Runner(int totalKms, int number) {
        this.totalKms = totalKms;
        this.number = number;
        this.traveledKms = 0;
    }

    @Override
    public void run() {
        boolean traveled25Percent = false, traveled50Percent = false, traveled75Percent = false;
        System.out.printf("I have number %d, I'm starting my race!\n", number);
        while(traveledKms < totalKms){
            try{
                Thread.sleep((long) ((Math.random() * REST_TIME) + REST_TIME));
                // System.out.println(REST_MSG);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            traveledKms += Math.random() * MAX_KM_INTERVAL;
            if(traveledKms > totalKms * percent25 && traveled25Percent == false){
                System.out.printf(PERCENT_TRAVELED, number, percent25 * 100, traveledKms);
                traveled25Percent = true;
            }
            if(traveledKms > totalKms * percent50 && traveled50Percent == false){
                System.out.printf(PERCENT_TRAVELED, number, percent50 * 100, traveledKms);
                traveled50Percent = true;
            }
            if(traveledKms > totalKms * percent75 && traveled75Percent == false){
                System.out.printf(PERCENT_TRAVELED, number, percent75 * 100, traveledKms);
                traveled75Percent = true;
            }
        }
        System.out.printf("I'm number %d , I already finished!\n", number);
    }    
}