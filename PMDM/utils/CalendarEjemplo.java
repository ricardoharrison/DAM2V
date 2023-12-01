import java.util.Calendar;

/**
 * Test
 */
public class CalendarEjemplo {

    public static void main(String[] args) {
        Calendar ahora = Calendar.getInstance();    //crea instancia con la fecha y hora actual

        Calendar luego = Calendar.getInstance();    //crea instancia de fecha y hora actual para después actualizarla con los datos de una fecha futura/pasada
        luego.set(Calendar.MONTH, Calendar.MARCH);    
        luego.set(Calendar.DAY_OF_MONTH, 16);
        luego.set(Calendar.YEAR, 2035);
        luego.set(Calendar.HOUR_OF_DAY, 12);    //importante usar esta ya que es basada en 24h
        luego.set(Calendar.MINUTE, 30);
        luego.set(Calendar.SECOND, 0);

        System.out.println(ahora.getTime());  //imprime el objeto Calendar en formato abreviado
        System.out.println(luego.getTime());

        //comparar fechas (usando getTimeInMillis)
        if(ahora.getTimeInMillis() < luego.getTimeInMillis()){
            System.out.println(ahora.getTime() + " es antes que " + luego.getTime());
        } else {
            System.out.println("...Oops");
        }

        //comprobar qué día de la semana es
        if(luego.get(Calendar.DAY_OF_WEEK) < Calendar.SATURDAY && luego.get(Calendar.DAY_OF_WEEK) > Calendar.SUNDAY){
            System.out.println("Es un día laborable");
        } else {
            System.out.println("ES finde");
        }

        //comprobar si el local está abierto (entre las 8AM y 8PM)
        if(ahora.get(Calendar.HOUR_OF_DAY) >= 20 || ahora.get(Calendar.HOUR_OF_DAY) <= 8){
            System.out.println("Está cerrado");
        } else {
            System.out.println("Está abierto");
        }
    }
}