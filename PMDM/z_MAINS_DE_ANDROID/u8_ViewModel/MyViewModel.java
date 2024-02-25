package com.rittz.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel{
    private static final double DELAY = 2000;
    private static final int MAX_NUM = 10000;
    public static final Integer FAIL = -1;
    private MutableLiveData<Integer> myData;

    public LiveData<Integer> getNumber(){
        if(myData == null){
            myData = new MutableLiveData<Integer>();
            loadNumber();
        }
        return myData;
    }

    public void loadNumber() {
        // Magia de threads!!! Ulver está feliz :)
        new Thread(
                ()->{
                    try {
                        Thread.sleep((long) ((Math.random() * DELAY) + DELAY));
                        // He recibido los datos (simulando petición remota)
                        int i = (int) (Math.random() * MAX_NUM);
                        // ¿?
                        myData.postValue(i);
                    } catch (InterruptedException e) {
                        myData.postValue(FAIL);
                        //throw new RuntimeException(e);
                    }
                }
        ).start();
    }
}
