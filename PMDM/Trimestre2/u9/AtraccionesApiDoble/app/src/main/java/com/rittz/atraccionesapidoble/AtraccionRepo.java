package com.rittz.atraccionesapidoble;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface AtraccionRepo {
    @GET("atracciones/")
    Call<List<Atraccion>> getAtracciones();

    @GET
    Call<AtraccionInstance> getAtraccionDetails(@Url String url);

}
