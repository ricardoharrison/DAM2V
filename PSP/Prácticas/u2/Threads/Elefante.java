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
public class Elefante implements Runnable  {

  int numero;
  static final int MAX_ITERACIONES = 20;

  public Elefante(int numero) {
    this.numero = numero;
  }
  
  @Override
  public void run() {
    if(numero == 1){
      System.out.println(numero + " elefante se balanceaba sobre la tela de una araña");
      try{
        Thread.sleep(1000);
      } catch (Exception e) {}
      System.out.println("Como veía que no se caía, fue a llamar otro elefante");
      try{
        Thread.sleep(1000);
      } catch (Exception e) {}
    } else if (numero <= MAX_ITERACIONES) {
      System.out.println(numero + " elefantes se balanceaban sobre la tela de una araña");
      try{
        Thread.sleep(1000);
      } catch (Exception e) {}
      System.out.println("Como veían que no se caían, fueron a llamar otro elefante");
      try{
        Thread.sleep(1000);
      } catch (Exception e) {}
    } else {
      System.out.println("llamaron a otro elefante y...");
      System.out.println("...");
      //throw new Exception("¡SE CAYERON!");
    }
    
  }
}