/**
 Imagina un sistema de banco en línea que maneja las cuentas de los usuarios. Dos usuarios, Alice y Bob, 
 intentan transferir dinero de sus cuentas a una tercera cuenta al mismo tiempo. Necesitas asegurarte de
  que las operaciones se realicen de manera segura y sin conflictos, utilizando sincronización.

Crea dos threads, uno para cada usuario. Cada thread intentará realizar 1000 transferencia de dinero de
 10 euros. Usa un método synchronized para asegurar que las operaciones en las cuentas no se realicen 
 simultáneamente, evitando así condiciones de carrera.
 */
public class Banco {
    static int balance = 0;

    public static synchronized void transferir(int cantidad){
        balance = balance + cantidad;
    }

    public static void main(String[] args) {
        final int CANTIDAD_PREDEFINIDA  = 10;
        final int NUM_TRANSFERENCIAS = 5000;

        Runnable runnable = () -> {
            for (int i = 0; i < NUM_TRANSFERENCIAS; i++) {
                transferir(CANTIDAD_PREDEFINIDA);
            }
        };

        Thread alice = new Thread(runnable);
        Thread bob = new Thread(runnable);

        alice.start();
        bob.start();

        try{
            alice.join();
            bob.join();
        } catch (InterruptedException e) {}

        System.out.println("El balance esperado es de " + NUM_TRANSFERENCIAS * CANTIDAD_PREDEFINIDA * 2 + " euros");
        System.out.println("El balance total es -> " + balance + " euros");
    }
}