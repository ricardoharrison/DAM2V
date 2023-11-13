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
public class ElefantesSeBalanceaban {

  public static void main(String[] args) {
    int i = 1;

    while (true){
      Elefante elefante = new Elefante(i);
      Thread hilo = new Thread(elefante);
      hilo.start();
      i++;
      try{
        Thread.sleep(2000);
      } catch (InterruptedException e) {}
      
    }
  } 
  
}