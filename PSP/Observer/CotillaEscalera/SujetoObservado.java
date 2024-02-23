package Observer.CotillaEscalera;

import Observer.CotillaEscalera.DescansilloDeLaEscalera.MarujaMarujo;

interface SujetoObservado {

    void addObserver(MarujaMarujo observer);

    void seAbreElDescansillo();

}