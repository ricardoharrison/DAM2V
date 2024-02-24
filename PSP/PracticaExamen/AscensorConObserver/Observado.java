//package PracticaExamen.AscensorConObserver;

import java.util.ArrayList;

public interface Observado {
    void subscribe(Observador o);

    void notifySubs();

}
