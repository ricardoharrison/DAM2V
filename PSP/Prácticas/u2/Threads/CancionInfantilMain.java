/* 03 Los elefantes...

Un elefante se balanceaba sobre la tela de una araña
Como veía que resistía, fue a llamar otro elefante
Dos elefantes se balanceaban sobre la tela de una araña
Como veían que resistía, fueron a llamar otro elefante

Basándote en esa canción, crea un Thread que reciba el tipo de animal, la acción y el número máximo. Cada vez que escriba una estrofa, el thread generará un número aleatorio entre 100000 y 300000 y verificará si es primo.

Crea un programa principal que gestion 3 canciones infantiles de forma concurrente con distintas prioridades (setPriority) */

/**
 * Elefantes
 */
public class CancionInfantilMain {

  public static void main(String[] args) {
    CancionInfantil cancion1 = new CancionInfantil(CancionInfantil.Animal.ELEFANTE, 10);
    CancionInfantil cancion2 = new CancionInfantil(CancionInfantil.Animal.OSO, 3);
    CancionInfantil cancion3 = new CancionInfantil(CancionInfantil.Animal.PATO, 15);

    Thread hilo1 = new Thread(cancion1);
    Thread hilo2 = new Thread(cancion2);
    Thread hilo3 = new Thread(cancion3);

    hilo1.setPriority(Thread.MAX_PRIORITY);   // == 10
    hilo2.setPriority(Thread.NORM_PRIORITY);  // == 5
    hilo3.setPriority(Thread.MIN_PRIORITY);   // == 1

    hilo1.start();
    hilo2.start();
    hilo3.start();    
  }   
}