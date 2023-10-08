import java.time.LocalDate;
import java.util.Calendar;

public class test{
  public static void main(String[] args) {
    /* Calendar myDate = Calendar.getInstance();
    myDate.set(2024,1,20);

    System.out.println(myDate);
    try{
      Thread.sleep(1400);
    } catch (Exception e) {}
    System.out.println(myDate.get(Calendar.YEAR)); */

    LocalDate futureDate = LocalDate.of(2024, 1, 11);
    LocalDate pastDate = LocalDate.of(1969, 9, 9);
    LocalDate currentDate = LocalDate.now();

System.out.println(pastDate);
System.out.println(currentDate);
System.out.println(futureDate);
    System.out.println(futureDate.isBefore(currentDate));
    System.out.println(pastDate.isBefore(currentDate));


  }
}