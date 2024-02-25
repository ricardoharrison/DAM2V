package com.rittz.japyapiconrecycler;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PeliculaService {

    private static PeliculaService instance;
    private static PeliculaRepo repo;

    private PeliculaService(){
        // Hago cosas
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.56.101:8000/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        repo = retrofit.create(PeliculaRepo.class);
    }

    public static PeliculaRepo getRepo(){
        return repo;
    }

    public static PeliculaService getInstance(){
        if(instance == null){
            instance =  new PeliculaService();
        }
        return instance;
    }
}