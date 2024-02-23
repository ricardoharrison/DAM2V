//package ut03comunicaciones.observer;

public class Main {
    public static void main(String[] args) {
        System.out.println("Enter Text : ");
        SujetoObservado descansillo = new DescansilloDeLaEscalera();

        Persona amparo = new Persona("Amparo");
        Persona joaquin = new Persona("Joaquin");
        Persona pedro = new Persona("Pedro");

        descansillo.addObserver(amparo);
        descansillo.addObserver(joaquin);
        descansillo.addObserver(pedro);
        descansillo.addObserver(new DescansilloDeLaEscalera.MarujaMarujo() {
            @Override
            public void update(String event) {
                if (event.contains("Jorge")) {
                    System.out.print("Eres un $#%$");
                }
            }
        });

        /*
         * eventSource.addObserver(event -> {
         * System.out.println("Received response: " + event);
         * });
         */

        // necesario para desencadenar la acción observable
        descansillo.seAbreElDescansillo();
    }
}
