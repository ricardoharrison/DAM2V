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
public class CancionInfantil implements Runnable  {

  public enum Animal{
    ELEFANTE, OSO, PATO;
  }

  Animal animal;
  int maxRepeticiones;
  
  public CancionInfantil(Animal animal, int maxRepeticiones) {
    this.animal = animal;
    this.maxRepeticiones = maxRepeticiones;
  }
  
  @Override
  public void run() {
    for(int i = 1; i <= maxRepeticiones; i++){
      if(i == 1){
        System.out.println(i + " " + animal.toString().toLowerCase() + " se balanceaba sobre la tela de una araña");
        try{
          Thread.sleep(1000);
        } catch (Exception e) {}
        System.out.println("Como veía que no se caía, fue a llamar otro " + animal.toString().toLowerCase());
        try{
          Thread.sleep(1000);
        } catch (Exception e) {}
      } else {
        System.out.println(i + " " + animal.toString().toLowerCase() + "s se balanceaban sobre la tela de una araña");
        try{
          Thread.sleep(1000);
        } catch (Exception e) {}
        System.out.println("Como veían que no se caían, fueron a llamar otro " + animal.toString().toLowerCase());
        try{
          Thread.sleep(1000);
        } catch (Exception e) {}
      }
    }        
  }
}