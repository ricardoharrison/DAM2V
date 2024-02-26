package com.rittz.atraccionesapidoble;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class AtraccionService {
    private static AtraccionService instance;
    private static AtraccionRepo repo;

    private AtraccionService(){
        // Hago cosas
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.56.101:8000/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        repo = retrofit.create(AtraccionRepo.class);
    }

    public static AtraccionRepo getRepo(){
        return repo;
    }

    public static AtraccionService getInstance(){
        if(instance == null){
            instance =  new AtraccionService();
        }
        return instance;
    }
}
