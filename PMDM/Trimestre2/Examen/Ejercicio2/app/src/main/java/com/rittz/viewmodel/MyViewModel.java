package com.rittz.viewmodel;

import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel{
    private static final double MIN_DELAY = 100;
    private static final double MAX_DELAY = 400;
    public static final String FAIL = MainActivity.ERROR_MSG;

    public static final String[] poema = {
        "Si", "hay", "hombres", "que", "contienen", "un", "alma", "sin", "fronteras,",
                "una", "esparcida", "frente", "de", "mundiales", "cabellos,",
                "cubierta", "de", "horizontes,", "barcos", "y", "cordilleras,",
                "con", "arena", "y", "con", "nieve,", "tú", "eres", "uno", "de", "aquellos.",
                "FIN"
    };
    private MutableLiveData<String> myData;



    public LiveData<String> getPoema(){
        if(myData == null){
            myData = new MutableLiveData<>();
            loadPoema();
        }
        return myData;
    }

    public void loadPoema() {
        // Magia de threads!!! Vitaliy está feliz :)
        new Thread(
                ()->{
                    String texto = "";
                    try {
                        for (int i = 0; i < poema.length; i++) {
                            Thread.sleep((long) ((Math.random() * (MAX_DELAY - MIN_DELAY) + MIN_DELAY)));
                            if(poema[i] != poema[poema.length - 1]){
                                texto += poema[i] + " ";
                                myData.postValue(texto);
                            }
                        }


                    } catch (InterruptedException e) {
                        myData.postValue(FAIL);
                        //throw new RuntimeException(e);
                    }
                }
        ).start();
    }
}
