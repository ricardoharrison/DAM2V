package com.rittz.atraccionesapidoble;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class CryptoService {
    private static CryptoService instance;
    private static CryptoRepo repo;

    private CryptoService(){
        // Hago cosas
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.56.101:8000/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        repo = retrofit.create(CryptoRepo.class);
    }

    public static CryptoRepo getRepo(){
        return repo;
    }

    public static CryptoService getInstance(){
        if(instance == null){
            instance =  new CryptoService();
        }
        return instance;
    }
}
