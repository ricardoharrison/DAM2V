import java.util.ArrayList;

//event observer
public class Escalera {

    public interface ObservadorDeMirilla {
        void aviso(String info);
    }

    private ArrayList<ObservadorDeMirilla> misCotillas;

    public Escalera(){
        misCotillas = new ArrayList<ObservadorDeMirilla>();
    }

    public void addCotilla(ObservadorDeMirilla cotilla){
        misCotillas.add(cotilla);
    }

    public void dispararEvento(String info){
        for(ObservadorDeMirilla om : misCotillas){
            om.aviso(info);
        }
    }
}