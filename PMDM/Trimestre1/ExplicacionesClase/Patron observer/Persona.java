public class Persona implements Escalera.ObservadorDeMirilla {

    private String nombre;

    public Persona(String name){
        nombre = name;
    }

    @Override
    public void aviso(String info){
        System.out.println(String.format("Soy %s y ha pasado %s", nombre, info));
    }
}