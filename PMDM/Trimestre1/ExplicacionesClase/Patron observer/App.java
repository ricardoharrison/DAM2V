public class App {

    public static void main(String[] args) {
        Escalera escalera = new Escalera(); //"gente" que se suscriba

        Persona ana = new Persona("Ana");
        Persona pepe = new Persona("Pepe");

        //lanzar eventos
        escalera.dispararEvento("Ha pasado Juan");
        escalera.dispararEvento("Ha pasado Juan");

        escalera.addCotilla(ana);
        escalera.addCotilla(pepe);
        escalera.addCotilla(       //bloque para crear un objeto "anónimo" al vuelo utilizando una interfaz (*)
            new Escalera.ObservadorDeMirilla() {
                 @Override
                 public void aviso(String info) {
                    System.out.println("argggghhh" + info);
                 }
            }
        );
        escalera.addCotilla((String i) -> {System.out.println("wtf!" +  i);}); // expresión lambda que hace lo mismo que el bloque *
        
    }
}