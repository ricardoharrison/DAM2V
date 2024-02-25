package com.rittz.recyclerconclickyviewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyViewModel extends ViewModel{
    private static final double DELAY = 2000;
    private static final int MAX_NUM = 10000;
    public static final ArrayList<Pelicula> FAIL = null;
    private MutableLiveData<ArrayList<Pelicula>> myData;

    public LiveData<ArrayList<Pelicula>> getArrayListPeliculas(){
        if(myData == null){
            myData = new MutableLiveData<ArrayList<Pelicula>>();
            loadArrayListPeliculas();
        }
        return myData;
    }

    public void loadArrayListPeliculas() {
        // Magia de threads!!! Ulver está feliz :)
        new Thread(
                ()->{

                        //Thread.sleep((long) ((Math.random() * DELAY) + DELAY));
                        // He recibido los datos (simulando petición remota)
                        //PETICION API
                        //int i = (int) (Math.random() * MAX_NUM);
                        // ¿?
                        PeliculaService service = PeliculaService.getInstance();
                        Call<List<Pelicula>> llamada = service.getRepo().getPeliculas();

                        llamada.enqueue(new Callback<List<Pelicula>>() {
                            @Override
                            public void onResponse(Call<List<Pelicula>> call, Response<List<Pelicula>> response) {
                                if (response.isSuccessful()) {
                                    List<Pelicula> peliculas = response.body();
                                    if (peliculas != null) {
                                        // Create an ArrayList to store the movie data
                                        ArrayList<Pelicula> elements = new ArrayList<>(peliculas);
                                        myData.postValue(elements);
                                        // Initialize the adapter with the fetched data

                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<List<Pelicula>> call, Throwable t) {

                            }
                        });
                }
        ).start();
    }
}

