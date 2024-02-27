package com.rittz.atraccionesapidoble;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface CryptoRepo {
    @GET("cripto/")
    Call<List<Crypto>> getCrypto();

}
